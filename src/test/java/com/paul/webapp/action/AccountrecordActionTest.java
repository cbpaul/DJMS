package com.paul.webapp.action;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.apache.struts2.ServletActionContext;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.paul.model.Accountrecord;
import com.paul.service.AccountrecordManager;

public class AccountrecordActionTest extends BaseActionTestCase {
    private AccountrecordAction action;

    @Before
    public void onSetUp() {
        super.onSetUp();

        action = new AccountrecordAction();
        AccountrecordManager accountrecordManager = (AccountrecordManager) applicationContext.getBean("accountrecordManager");
        action.setAccountrecordManager(accountrecordManager);

        // add a test accountrecord to the database
        Accountrecord accountrecord = new Accountrecord();

        // enter all required fields

        accountrecordManager.save(accountrecord);
    }

    @Test
    public void testGetAllAccountrecords() throws Exception {
        assertEquals(action.list(), ActionSupport.SUCCESS);
        assertTrue(action.getAccountrecords().size() >= 1);
    }

    @Test
    public void testSearch() throws Exception {
        // regenerate indexes
        AccountrecordManager accountrecordManager = (AccountrecordManager) applicationContext.getBean("accountrecordManager");
        accountrecordManager.reindex();

        action.setQ("*");
        assertEquals(action.list(), ActionSupport.SUCCESS);
        assertEquals(4, action.getAccountrecords().size());
    }

    @Test
    public void testEdit() throws Exception {
        log.debug("testing edit...");
        action.setId(-1L);
        assertNull(action.getAccountrecord());
        assertEquals("success", action.edit());
        assertNotNull(action.getAccountrecord());
        assertFalse(action.hasActionErrors());
    }

    @Test
    public void testSave() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        ServletActionContext.setRequest(request);
        action.setId(-1L);
        assertEquals("success", action.edit());
        assertNotNull(action.getAccountrecord());

        Accountrecord accountrecord = action.getAccountrecord();
        // update required fields

        action.setAccountrecord(accountrecord);

        assertEquals("input", action.save());
        assertFalse(action.hasActionErrors());
        assertFalse(action.hasFieldErrors());
        assertNotNull(request.getSession().getAttribute("messages"));
    }

    @Test
    public void testRemove() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        ServletActionContext.setRequest(request);
        action.setDelete("");
        Accountrecord accountrecord = new Accountrecord();
        accountrecord.setId(-2L);
        action.setAccountrecord(accountrecord);
        assertEquals("success", action.delete());
        assertNotNull(request.getSession().getAttribute("messages"));
    }
}