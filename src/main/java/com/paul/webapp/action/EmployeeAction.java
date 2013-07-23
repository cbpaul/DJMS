package com.paul.webapp.action;

import com.opensymphony.xwork2.Preparable;
import com.paul.service.EmployeeManager;
import com.paul.dao.SearchException;
import com.paul.model.Employee;
import com.paul.webapp.action.BaseAction;

import java.util.List;

public class EmployeeAction extends BaseAction implements Preparable {
    private EmployeeManager employeeManager;
    private List employees;
    private Employee employee;
    private Long id;
    private String query;

    public void setEmployeeManager(EmployeeManager employeeManager) {
        this.employeeManager = employeeManager;
    }

    public List getEmployees() {
        return employees;
    }

    /**
     * Grab the entity from the database before populating with request parameters
     */
    public void prepare() {
        if (getRequest().getMethod().equalsIgnoreCase("post")) {
            // prevent failures on new
            String employeeId = getRequest().getParameter("employee.id");
            if (employeeId != null && !employeeId.equals("")) {
                employee = employeeManager.get(new Long(employeeId));
            }
        }
    }

    public void setQ(String q) {
        this.query = q;
    }

    public String list() {
        try {
            employees = employeeManager.search(query, Employee.class);
        } catch (SearchException se) {
            addActionError(se.getMessage());
            employees = employeeManager.getAll();
        }
        return SUCCESS;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String delete() {
        employeeManager.remove(employee.getId());
        saveMessage(getText("employee.deleted"));

        return SUCCESS;
    }

    public String edit() {
        if (id != null) {
            employee = employeeManager.get(id);
        } else {
            employee = new Employee();
        }

        return SUCCESS;
    }

    public String save() throws Exception {
        if (cancel != null) {
            return "cancel";
        }

        if (delete != null) {
            return delete();
        }

        boolean isNew = (employee.getId() == null);

        employeeManager.save(employee);

        String key = (isNew) ? "employee.added" : "employee.updated";
        saveMessage(getText(key));

        if (!isNew) {
            return INPUT;
        } else {
            return SUCCESS;
        }
    }
}