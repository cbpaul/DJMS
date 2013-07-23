package com.paul.dao.hibernate;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.paul.webapp.util.ObjectUtil;

public class QueryCriteria {
	private List<String> entryFildNames = new ArrayList<String>();
	private List<Criterion> cris = new ArrayList<Criterion>();
	private List<Map<String,String>> sortMapList = new ArrayList<Map<String, String>>();
	private Integer firstIndex = 0;
	private Integer count;
	/**
	 * 添加查询的集合类
	 * @param entryNames
	 * @return
	 */
	public QueryCriteria addEntryFildName(String...entryNames){
		if(entryNames != null){
			Collections.addAll(entryFildNames, entryNames) ;
		}
		return this;
	}
	/**
	 * 添加查询条件
	 * @param criterion
	 * @return
	 */
	public QueryCriteria addCriterion(Criterion criterion){
		cris.add(criterion);
		return this;
	}
	/**
	 * 二维数组{{"asc","name"},{"desc","id"}}
	 * @param sort
	 * @return
	 */
	public QueryCriteria addSort(String[]...sort){
		for (int i = 0;sort !=null && i < sort.length; i++) {
			Map<String,String> sortMap = new HashMap<String,String>();
			sortMap.put(sort[i][0], sort[i][1]);
			sortMapList.add(sortMap);
		}
		return this;
	}
	/**
	 * 设置查询记录index
	 * @param firstIndex
	 * @return
	 */
	public QueryCriteria setFirstIndex(Integer firstIndex){
		this.firstIndex = firstIndex;
		return this;
	}
	/**
	 * 设置查询记录数
	 * @param count
	 * @return
	 */
	public QueryCriteria setCount(Integer count){
		this.count = count;
		return this;
	}
	/**
	 * bean查询条件生成 eq
	 * @param object
	 */
	public void generateConditionByBean(Object object){
		@SuppressWarnings("rawtypes")
		Class clazz = object.getClass();
		BeanInfo beanInfo;
		try {
			beanInfo = Introspector.getBeanInfo(clazz);
			PropertyDescriptor[] propertyDescriptors = beanInfo
					.getPropertyDescriptors();
			for (int i = 0; i < propertyDescriptors.length; i++) {
				PropertyDescriptor descriptor = propertyDescriptors[i];
				String propertyName = descriptor.getName();
				if(!propertyName.equals("class")){
					Method readMethod = descriptor.getReadMethod();
					Object result = readMethod.invoke(object, new Object[0]);
					if (result != null) {
						if(ObjectUtil.isPrimitive(result)){
							this.addEntryFildName(propertyName);
							generateConditionByBean(result);
						}else{
							this.addCriterion(Restrictions.eq(propertyName, result));
						}
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<String> getEntryFildNames() {
		return entryFildNames;
	}
	public List<Criterion> getCris() {
		return cris;
	}
	public List<Map<String, String>> getSortMapList() {
		return sortMapList;
	}
	public Integer getFirstIndex() {
		return firstIndex;
	}
	public Integer getCount() {
		return count;
	}
	
	
}
