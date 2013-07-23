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
import com.paul.model.Employee;
import com.paul.service.EmployeeManager;

public class EmployeeActionTest extends BaseActionTestCase {
    private EmployeeAction action;

    @Before
    public void onSetUp() {
        super.onSetUp();

        action = new EmployeeAction();
        EmployeeManager employeeManager = (EmployeeManager) applicationContext.getBean("employeeManager");
        action.setEmployeeManager(employeeManager);

        // add a test employee to the database
        Employee employee = new Employee();

        // enter all required fields

        employeeManager.save(employee);
    }

    @Test
    public void testGetAllEmployees() throws Exception {
        assertEquals(action.list(), ActionSupport.SUCCESS);
        assertTrue(action.getEmployees().size() >= 1);
    }

    @Test
    public void testSearch() throws Exception {
        // regenerate indexes
        EmployeeManager employeeManager = (EmployeeManager) applicationContext.getBean("employeeManager");
        employeeManager.reindex();

        action.setQ("*");
        assertEquals(action.list(), ActionSupport.SUCCESS);
        assertEquals(4, action.getEmployees().size());
    }

    @Test
    public void testEdit() throws Exception {
        log.debug("testing edit...");
        action.setId(-1L);
        assertNull(action.getEmployee());
        assertEquals("success", action.edit());
        assertNotNull(action.getEmployee());
        assertFalse(action.hasActionErrors());
    }

    @Test
    public void testSave() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        ServletActionContext.setRequest(request);
        action.setId(-1L);
        assertEquals("success", action.edit());
        assertNotNull(action.getEmployee());

        Employee employee = action.getEmployee();
        // update required fields

        action.setEmployee(employee);

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
        Employee employee = new Employee();
        employee.setId(-2L);
        action.setEmployee(employee);
        assertEquals("success", action.delete());
        assertNotNull(request.getSession().getAttribute("messages"));
    }
}