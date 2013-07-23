package com.paul.dao.hibernate;

import com.paul.model.ServiceOrder;
import com.paul.dao.ServiceOrderDao;
import com.paul.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

@Repository("serviceOrderDao")
public class ServiceOrderDaoHibernate extends GenericDaoHibernate<ServiceOrder, Long> implements ServiceOrderDao {

    public ServiceOrderDaoHibernate() {
        super(ServiceOrder.class);
    }
}
