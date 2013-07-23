package com.paul.dao.hibernate;

import com.paul.model.Purchase;
import com.paul.dao.PurchaseDao;
import com.paul.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

@Repository("purchaseDao")
public class PurchaseDaoHibernate extends GenericDaoHibernate<Purchase, Long> implements PurchaseDao {

    public PurchaseDaoHibernate() {
        super(Purchase.class);
    }
}
