package com.paul.dao.hibernate;

import com.paul.model.Accessories;
import com.paul.dao.AccessoriesDao;
import com.paul.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

@Repository("accessoriesDao")
public class AccessoriesDaoHibernate extends GenericDaoHibernate<Accessories, Long> implements AccessoriesDao {

    public AccessoriesDaoHibernate() {
        super(Accessories.class);
    }
}
