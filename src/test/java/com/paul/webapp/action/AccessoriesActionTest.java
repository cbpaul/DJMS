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
import com.paul.model.Accessories;
import com.paul.service.AccessoriesManager;

public class AccessoriesActionTest extends BaseActionTestCase {
    private AccessoriesAction action;

    @Before
    public void onSetUp() {
        super.onSetUp();

        action = new AccessoriesAction();
        AccessoriesManager accessoriesManager = (AccessoriesManager) applicationContext.getBean("accessoriesManager");
        action.setAccessoriesManager(accessoriesManager);

        // add a test accessories to the database
        Accessories accessories = new Accessories();

        // enter all required fields

        accessoriesManager.save(accessories);
    }

    @Test
    public void testGetAllAccessoriess() throws Exception {
        assertEquals(action.list(), ActionSupport.SUCCESS);
        assertTrue(action.getAccessoriess().size() >= 1);
    }

    @Test
    public void testSearch() throws Exception {
        // regenerate indexes
        AccessoriesManager accessoriesManager = (AccessoriesManager) applicationContext.getBean("accessoriesManager");
        accessoriesManager.reindex();

        action.setQ("*");
        assertEquals(action.list(), ActionSupport.SUCCESS);
        assertEquals(4, action.getAccessoriess().size());
    }

    @Test
    public void testEdit() throws Exception {
        log.debug("testing edit...");
        action.setId(-1L);
        assertNull(action.getAccessories());
        assertEquals("success", action.edit());
        assertNotNull(action.getAccessories());
        assertFalse(action.hasActionErrors());
    }

    @Test
    public void testSave() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        ServletActionContext.setRequest(request);
        action.setId(-1L);
        assertEquals("success", action.edit());
        assertNotNull(action.getAccessories());

        Accessories accessories = action.getAccessories();
        // update required fields

        action.setAccessories(accessories);

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
        Accessories accessories = new Accessories();
        accessories.setId(-2L);
        action.setAccessories(accessories);
        assertEquals("success", action.delete());
        assertNotNull(request.getSession().getAttribute("messages"));
    }
}