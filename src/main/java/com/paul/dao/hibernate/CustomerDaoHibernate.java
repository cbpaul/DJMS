package com.paul.dao.hibernate;

import com.paul.model.Customer;
import com.paul.dao.CustomerDao;
import com.paul.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

@Repository("customerDao")
public class CustomerDaoHibernate extends GenericDaoHibernate<Customer, Long> implements CustomerDao {

    public CustomerDaoHibernate() {
        super(Customer.class);
    }
}
