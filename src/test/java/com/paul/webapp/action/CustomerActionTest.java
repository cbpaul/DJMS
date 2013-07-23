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
import com.paul.model.Customer;
import com.paul.service.CustomerManager;

public class CustomerActionTest extends BaseActionTestCase {
    private CustomerAction action;

    @Before
    public void onSetUp() {
        super.onSetUp();

        action = new CustomerAction();
        CustomerManager customerManager = (CustomerManager) applicationContext.getBean("customerManager");
        action.setCustomerManager(customerManager);

        // add a test customer to the database
        Customer customer = new Customer();

        // enter all required fields

        customerManager.save(customer);
    }

    @Test
    public void testGetAllCustomers() throws Exception {
        assertEquals(action.list(), ActionSupport.SUCCESS);
        assertTrue(action.getCustomers().size() >= 1);
    }

    @Test
    public void testSearch() throws Exception {
        // regenerate indexes
        CustomerManager customerManager = (CustomerManager) applicationContext.getBean("customerManager");
        customerManager.reindex();

        action.setQ("*");
        assertEquals(action.list(), ActionSupport.SUCCESS);
        assertEquals(4, action.getCustomers().size());
    }

    @Test
    public void testEdit() throws Exception {
        log.debug("testing edit...");
        action.setId(-1L);
        assertNull(action.getCustomer());
        assertEquals("success", action.edit());
        assertNotNull(action.getCustomer());
        assertFalse(action.hasActionErrors());
    }

    @Test
    public void testSave() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        ServletActionContext.setRequest(request);
        action.setId(-1L);
        assertEquals("success", action.edit());
        assertNotNull(action.getCustomer());

        Customer customer = action.getCustomer();
        // update required fields

        action.setCustomer(customer);

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
        Customer customer = new Customer();
        customer.setId(-2L);
        action.setCustomer(customer);
        assertEquals("success", action.delete());
        assertNotNull(request.getSession().getAttribute("messages"));
    }
}