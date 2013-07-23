package com.paul.webapp.action;

import java.util.Date;
import java.util.List;

import org.apache.struts2.json.JSONUtil;

import com.opensymphony.xwork2.Preparable;
import com.paul.dao.SearchException;
import com.paul.model.Customer;
import com.paul.model.CustomerType;
import com.paul.model.Gender;
import com.paul.service.CustomerManager;

public class CustomerAction extends BaseAction implements Preparable {
    private CustomerManager customerManager;
    private List customers;
    private Customer customer;
    private Long id;
    private String query;
    private CustomerType[] customerTypeList;
    private Gender[] genderList;
    public void setCustomerManager(CustomerManager customerManager) {
        this.customerManager = customerManager;
    }

    public List getCustomers() {
        return customers;
    }

    public CustomerType[] getCustomerTypeList() {
		return CustomerType.values();
	}
    public Gender[] getGenderList(){
    	return Gender.values();
    }
	/**
     * Grab the entity from the database before populating with request parameters
     */
    public void prepare() {
        if (getRequest().getMethod().equalsIgnoreCase("post")) {
            // prevent failures on new
            String customerId = getRequest().getParameter("customer.id");
            if (customerId != null && !customerId.equals("")) {
                customer = customerManager.get(new Long(customerId));
            }
        }
    }

    public void setQ(String q) {
        this.query = q;
    }

    public String list() {
        try {
            customers = customerManager.search(query, Customer.class);
        } catch (SearchException se) {
            addActionError(se.getMessage());
            customers = customerManager.getAll();
        }
        return SUCCESS;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String delete() {
        customerManager.remove(customer.getId());
        saveMessage(getText("customer.deleted"));

        return SUCCESS;
    }

    public String edit() {
        if (id != null) {
            customer = customerManager.get(id);
        } else {
            customer = new Customer();
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
        customer = customerManager.getOne(customer);
    	boolean isNew = (customer.getId() == null);
        if(isNew){
        	customer.setCreateTime(new Date());
        }
        customerManager.save(customer);

        String key = (isNew) ? "customer.added" : "customer.updated";
        saveMessage(getText(key));

        if (!isNew) {
            return INPUT;
        } else {
            return SUCCESS;
        }
    }
    public void saveAjax() throws Exception{
    	customer = customerManager.getOne(customer);
    	boolean isNew = (customer.getId() == null);
        if(isNew){
        	customer.setCreateTime(new Date());
        }
    	customerManager.save(customer);
    	System.out.println(JSONUtil.serialize(customer));
    	getResponse().getWriter().write(JSONUtil.serialize(customer));
    }
}