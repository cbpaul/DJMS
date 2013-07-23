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
import com.paul.model.ServiceOrder;
import com.paul.service.ServiceOrderManager;

public class ServiceOrderActionTest extends BaseActionTestCase {
    private ServiceOrderAction action;

    @Before
    public void onSetUp() {
        super.onSetUp();

        action = new ServiceOrderAction();
        ServiceOrderManager serviceOrderManager = (ServiceOrderManager) applicationContext.getBean("serviceOrderManager");
        action.setServiceOrderManager(serviceOrderManager);

        // add a test serviceOrder to the database
        ServiceOrder serviceOrder = new ServiceOrder();

        // enter all required fields

        serviceOrderManager.save(serviceOrder);
    }

    @Test
    public void testGetAllServiceOrders() throws Exception {
        assertEquals(action.list(), ActionSupport.SUCCESS);
        assertTrue(action.getServiceOrders().size() >= 1);
    }

    @Test
    public void testSearch() throws Exception {
        // regenerate indexes
        ServiceOrderManager serviceOrderManager = (ServiceOrderManager) applicationContext.getBean("serviceOrderManager");
        serviceOrderManager.reindex();

        action.setQ("*");
        assertEquals(action.list(), ActionSupport.SUCCESS);
        assertEquals(4, action.getServiceOrders().size());
    }

    @Test
    public void testEdit() throws Exception {
        log.debug("testing edit...");
        action.setId(-1L);
        assertNull(action.getServiceOrder());
        assertEquals("success", action.edit());
        assertNotNull(action.getServiceOrder());
        assertFalse(action.hasActionErrors());
    }

    @Test
    public void testSave() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        ServletActionContext.setRequest(request);
        action.setId(-1L);
        assertEquals("success", action.edit());
        assertNotNull(action.getServiceOrder());

        ServiceOrder serviceOrder = action.getServiceOrder();
        // update required fields

        action.setServiceOrder(serviceOrder);

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
        ServiceOrder serviceOrder = new ServiceOrder();
        serviceOrder.setId(-2L);
        action.setServiceOrder(serviceOrder);
        assertEquals("success", action.delete());
        assertNotNull(request.getSession().getAttribute("messages"));
    }
}