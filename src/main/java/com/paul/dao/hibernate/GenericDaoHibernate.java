package com.paul.dao.hibernate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.util.Version;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.IdentifierLoadAccess;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.sql.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.orm.ObjectRetrievalFailureException;

import com.paul.dao.GenericDao;
import com.paul.dao.SearchException;

/**
 * This class serves as the Base class for all other DAOs - namely to hold
 * common CRUD methods that they might all use. You should only need to extend
 * this class when your require custom CRUD logic.
 * <p/>
 * <p>
 * To register this class in your Spring context file, use the following XML.
 * 
 * <pre>
 *      &lt;bean id="fooDao" class="com.paul.dao.hibernate.GenericDaoHibernate"&gt;
 *          &lt;constructor-arg value="com.paul.model.Foo"/&gt;
 *      &lt;/bean&gt;
 * </pre>
 * 
 * @author <a href="mailto:bwnoll@gmail.com">Bryan Noll</a> Updated by jgarcia:
 *         update hibernate3 to hibernate4
 * @author jgarcia (update: added full text search + reindexing)
 * @param <T>
 *            a type variable
 * @param <PK>
 *            the primary key for that type
 */
public class GenericDaoHibernate<T, PK extends Serializable> implements
		GenericDao<T, PK> {
	/**
	 * Log variable for all child classes. Uses LogFactory.getLog(getClass())
	 * from Commons Logging
	 */
	protected final Log log = LogFactory.getLog(getClass());
	private Class<T> persistentClass;
	@Resource
	private SessionFactory sessionFactory;
	private Analyzer defaultAnalyzer;

	/**
	 * Constructor that takes in a class to see which type of entity to persist.
	 * Use this constructor when subclassing.
	 * 
	 * @param persistentClass
	 *            the class type you'd like to persist
	 */
	public GenericDaoHibernate(final Class<T> persistentClass) {
		this.persistentClass = persistentClass;
		defaultAnalyzer = new StandardAnalyzer(Version.LUCENE_35);
	}

	/**
	 * Constructor that takes in a class and sessionFactory for easy creation of
	 * DAO.
	 * 
	 * @param persistentClass
	 *            the class type you'd like to persist
	 * @param sessionFactory
	 *            the pre-configured Hibernate SessionFactory
	 */
	public GenericDaoHibernate(final Class<T> persistentClass,
			SessionFactory sessionFactory) {
		this.persistentClass = persistentClass;
		this.sessionFactory = sessionFactory;
		defaultAnalyzer = new StandardAnalyzer(Version.LUCENE_35);
	}

	public SessionFactory getSessionFactory() {
		return this.sessionFactory;
	}

	public Session getSession() throws HibernateException {
		Session sess = getSessionFactory().getCurrentSession();
		if (sess == null) {
			sess = getSessionFactory().openSession();
		}
		return sess;
	}

	@Autowired
	@Required
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<T> getAll() {
		Session sess = getSession();
		return sess.createCriteria(persistentClass).list();
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<T> getAllDistinct() {
		Collection<T> result = new LinkedHashSet<T>(getAll());
		return new ArrayList<T>(result);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<T> search(String searchTerm) throws SearchException {
		Session sess = getSession();
		FullTextSession txtSession = Search.getFullTextSession(sess);

		org.apache.lucene.search.Query qry;
		try {
			qry = HibernateSearchTools.generateQuery(searchTerm,
					this.persistentClass, sess, defaultAnalyzer);
		} catch (ParseException ex) {
			throw new SearchException(ex);
		}
		org.hibernate.search.FullTextQuery hibQuery = txtSession
				.createFullTextQuery(qry, this.persistentClass);
		return hibQuery.list();
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public T get(PK id) {
		Session sess = getSession();
		IdentifierLoadAccess byId = sess.byId(persistentClass);
		T entity = (T) byId.load(id);

		if (entity == null) {
			log.warn("Uh oh, '" + this.persistentClass + "' object with id '"
					+ id + "' not found...");
			throw new ObjectRetrievalFailureException(this.persistentClass, id);
		}

		return entity;
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public boolean exists(PK id) {
		Session sess = getSession();
		IdentifierLoadAccess byId = sess.byId(persistentClass);
		T entity = (T) byId.load(id);
		return entity != null;
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public T save(T object) {
		Session sess = getSession();
		return (T) sess.merge(object);
	}

	/**
	 * {@inheritDoc}
	 */
	public void remove(T object) {
		Session sess = getSession();
		sess.delete(object);
	}

	/**
	 * {@inheritDoc}
	 */
	public void remove(PK id) {
		Session sess = getSession();
		IdentifierLoadAccess byId = sess.byId(persistentClass);
		T entity = (T) byId.load(id);
		sess.delete(entity);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<T> findByNamedQuery(String queryName,
			Map<String, Object> queryParams) {
		Session sess = getSession();
		Query namedQuery = sess.getNamedQuery(queryName);

		for (String s : queryParams.keySet()) {
			namedQuery.setParameter(s, queryParams.get(s));
		}
		return namedQuery.list();
	}

	/**
	 * 条件查询
	 * 
	 */
	@SuppressWarnings("unchecked")
	public List<T> criteriaQuery(String[] entryFildNames, List<Criterion> cris,
			Integer firstIndex, Integer count, Map<String, String> sortMap) {
		List<T> list = null;
		Criteria criteria = getSession().createCriteria(persistentClass);
		if (entryFildNames != null) {
			for (String fildName : entryFildNames) {
				criteria.createAlias(fildName, fildName,
						JoinType.LEFT_OUTER_JOIN);
			}
		}
		for (Criterion expression : cris) {
			if (expression != null) {
				criteria.add(expression);
			}
		}
		if (sortMap != null) {
			if (!sortMap.isEmpty()) {
				for (Object o : sortMap.keySet()) {
					String fieldName = o.toString();
					String orderType = sortMap.get(fieldName).toString();
					if ("asc".equalsIgnoreCase(orderType)) {
						criteria.addOrder(Order.asc(fieldName));
					} else if ("desc".equals(orderType)) {
						criteria.addOrder(Order.desc(fieldName));
					}
				}
			}
		}
		criteria.setProjection(null);
		criteria.setResultTransformer(Criteria.ROOT_ENTITY);
		if (count != null) {
			criteria.setFirstResult(firstIndex);
			criteria.setMaxResults(count);
		}
		list = criteria.list();
		return list;
	}

	/**
	 * {@inheritDoc}
	 */
	public void reindex() {
		HibernateSearchTools.reindex(persistentClass, getSessionFactory()
				.getCurrentSession());
	}

	/**
	 * {@inheritDoc}
	 */
	public void reindexAll(boolean async) {
		HibernateSearchTools.reindexAll(async, getSessionFactory()
				.getCurrentSession());
	}

	@SuppressWarnings("unchecked")
	public List<T> query(final String hql, final Object... params) {
		Session sess = getSession();
		Query query = sess.createQuery(hql);
		for (int i = 0; i < params.length; i++) {
			query.setParameter(i, params[i]);
		}
		return (List<T>) query.list();
	}

	@SuppressWarnings("unchecked")
	public List<T> query(QueryCriteria queryCriteria) {
		Session sess = getSession();
		Criteria criteria = sess.createCriteria(persistentClass);
		for (String fildName : queryCriteria.getEntryFildNames()) {
			criteria.createAlias(fildName, fildName, JoinType.LEFT_OUTER_JOIN);
		}
		for (Criterion expression : queryCriteria.getCris()) {//条件
			if (expression != null) {
				criteria.add(expression);
			}
		}
		/**
		 * 排序
		 */
		for (Map<String,String> map : queryCriteria.getSortMapList()) {
			String orderType = (String)map.entrySet().iterator().next().getKey();
			String fieldName = (String)map.entrySet().iterator().next().getValue();
			if ("asc".equalsIgnoreCase(orderType)) {
				criteria.addOrder(Order.asc(fieldName));
			} else if ("desc".equals(orderType)) {
				criteria.addOrder(Order.desc(fieldName));
			}
		}
		 criteria.setProjection(null);
		 criteria.setResultTransformer(Criteria.ROOT_ENTITY);
		 if (queryCriteria.getCount() != null) {
		 criteria.setFirstResult(queryCriteria.getFirstIndex());
		 criteria.setMaxResults(queryCriteria.getCount());
		 }
		 return criteria.list();
	}
}
