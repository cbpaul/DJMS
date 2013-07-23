package com.paul.dao.hibernate;

import com.paul.model.Brand;
import com.paul.dao.BrandDao;
import com.paul.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

@Repository("brandDao")
public class BrandDaoHibernate extends GenericDaoHibernate<Brand, Long> implements BrandDao {

    public BrandDaoHibernate() {
        super(Brand.class);
    }
}
