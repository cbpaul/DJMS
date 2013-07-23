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
import com.paul.model.Brand;
import com.paul.service.BrandManager;

public class BrandActionTest extends BaseActionTestCase {
    private BrandAction action;

    @Before
    public void onSetUp() {
        super.onSetUp();

        action = new BrandAction();
        BrandManager brandManager = (BrandManager) applicationContext.getBean("brandManager");
        action.setBrandManager(brandManager);

        // add a test brand to the database
        Brand brand = new Brand();

        // enter all required fields

        brandManager.save(brand);
    }

    @Test
    public void testGetAllBrands() throws Exception {
        assertEquals(action.list(), ActionSupport.SUCCESS);
        assertTrue(action.getBrands().size() >= 1);
    }

    @Test
    public void testSearch() throws Exception {
        // regenerate indexes
        BrandManager brandManager = (BrandManager) applicationContext.getBean("brandManager");
        brandManager.reindex();

        action.setQ("*");
        assertEquals(action.list(), ActionSupport.SUCCESS);
        assertEquals(4, action.getBrands().size());
    }

    @Test
    public void testEdit() throws Exception {
        log.debug("testing edit...");
        action.setId(-1L);
        assertNull(action.getBrand());
        assertEquals("success", action.edit());
        assertNotNull(action.getBrand());
        assertFalse(action.hasActionErrors());
    }

    @Test
    public void testSave() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        ServletActionContext.setRequest(request);
        action.setId(-1L);
        assertEquals("success", action.edit());
        assertNotNull(action.getBrand());

        Brand brand = action.getBrand();
        // update required fields

        action.setBrand(brand);

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
        Brand brand = new Brand();
        brand.setId(-2L);
        action.setBrand(brand);
        assertEquals("success", action.delete());
        assertNotNull(request.getSession().getAttribute("messages"));
    }
}