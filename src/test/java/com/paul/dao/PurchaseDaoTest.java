package com.paul.dao;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.annotation.ExpectedException;

import com.paul.model.Purchase;

public class PurchaseDaoTest extends BaseDaoTestCase {
     @Autowired
    private PurchaseDao purchaseDao;

    @Test
    @ExpectedException(DataAccessException.class)
    public void testAddAndRemovePurchase() {
        Purchase purchase = new Purchase();

        // enter all required fields

        log.debug("adding purchase...");
        purchase = purchaseDao.save(purchase);

        purchase = purchaseDao.get(purchase.getId());

        assertNotNull(purchase.getId());

        log.debug("removing purchase...");

        purchaseDao.remove(purchase.getId());

        // should throw DataAccessException 
        purchaseDao.get(purchase.getId());
    }
}