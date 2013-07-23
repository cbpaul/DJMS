package com.paul.dao;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.annotation.ExpectedException;

import com.paul.model.Brand;

public class BrandDaoTest extends BaseDaoTestCase {
     @Autowired
    private BrandDao brandDao;

    @Test
    @ExpectedException(DataAccessException.class)
    public void testAddAndRemoveBrand() {
        Brand brand = new Brand();

        // enter all required fields

        log.debug("adding brand...");
        brand = brandDao.save(brand);

        brand = brandDao.get(brand.getId());

        assertNotNull(brand.getId());

        log.debug("removing brand...");

        brandDao.remove(brand.getId());

        // should throw DataAccessException 
        brandDao.get(brand.getId());
    }
}