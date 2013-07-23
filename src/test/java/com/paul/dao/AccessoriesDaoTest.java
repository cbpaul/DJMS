package com.paul.dao;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.annotation.ExpectedException;

import com.paul.model.Accessories;

public class AccessoriesDaoTest extends BaseDaoTestCase {
	 @Autowired
    private AccessoriesDao accessoriesDao;

    @Test
    @ExpectedException(DataAccessException.class)
    public void testAddAndRemoveAccessories() {
        Accessories accessories = new Accessories();

        // enter all required fields

        log.debug("adding accessories...");
        accessories = accessoriesDao.save(accessories);

        accessories = accessoriesDao.get(accessories.getId());

        assertNotNull(accessories.getId());

        log.debug("removing accessories...");

        accessoriesDao.remove(accessories.getId());

        // should throw DataAccessException 
        accessoriesDao.get(accessories.getId());
    }
}