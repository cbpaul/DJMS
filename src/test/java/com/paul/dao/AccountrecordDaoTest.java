package com.paul.dao;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.annotation.ExpectedException;

import com.paul.model.Accountrecord;

public class AccountrecordDaoTest extends BaseDaoTestCase {
    @Autowired
    private AccountrecordDao accountrecordDao;

    @Test
    @ExpectedException(DataAccessException.class)
    public void testAddAndRemoveAccountrecord() {
        Accountrecord accountrecord = new Accountrecord();

        // enter all required fields

        log.debug("adding accountrecord...");
        accountrecord = accountrecordDao.save(accountrecord);

        accountrecord = accountrecordDao.get(accountrecord.getId());

        assertNotNull(accountrecord.getId());

        log.debug("removing accountrecord...");

        accountrecordDao.remove(accountrecord.getId());

        // should throw DataAccessException 
        accountrecordDao.get(accountrecord.getId());
    }
}