package com.paul.webapp.action;

import java.util.Date;
import java.util.List;

import com.opensymphony.xwork2.Preparable;
import com.paul.dao.SearchException;
import com.paul.dao.hibernate.QueryCriteria;
import com.paul.model.Brand;
import com.paul.model.Customer;
import com.paul.model.CustomerType;
import com.paul.model.Gender;
import com.paul.model.ServiceOrder;
import com.paul.model.ServiceStatus;
import com.paul.service.BrandManager;
import com.paul.service.CustomerManager;
import com.paul.service.ServiceOrderManager;

public class ServiceOrderAction extends BaseAction implements Preparable {
    private ServiceOrderManager serviceOrderManager;
    private BrandManager brandManager;
    private CustomerManager customerManager;
    private List serviceOrders;
    private ServiceOrder serviceOrder;
    private Long id;
    private String query;
    private List<Brand> brandList;
    private ServiceStatus[] statusList;
    private CustomerType[] customerTypeList;
    private List<Customer> businesses;
    private List<Customer> commonCustomers;
    private Gender[] genderList;
    public Gender[] getGenderList(){
    	return Gender.values();
    }
    public void setServiceOrderManager(ServiceOrderManager serviceOrderManager) {
        this.serviceOrderManager = serviceOrderManager;
    }
    public void setBrandManager(BrandManager brandManager) {
		this.brandManager = brandManager;
	}
	public void setCustomerManager(CustomerManager customerManager) {
		this.customerManager = customerManager;
	}

	public List<Customer> getCommonCustomers() {
		return commonCustomers;
	}
	public CustomerType[] getCustomerTypeList() {
		return CustomerType.values();
	}
	public ServiceStatus[] getStatusList() {
		return ServiceStatus.values();
	}
	public List getServiceOrders() {
        return serviceOrders;
    }
	

    public List<Customer> getBusinesses() {
		return businesses;
	}
	/**
     * Grab the entity from the database before populating with request parameters
     */
    public void prepare() {
        if (getRequest().getMethod().equalsIgnoreCase("post")) {
            // prevent failures on new
            String serviceOrderId = getRequest().getParameter("serviceOrder.id");
            if (serviceOrderId != null && !serviceOrderId.equals("")) {
                serviceOrder = serviceOrderManager.get(new Long(serviceOrderId));
            }
        }
    }

    public void setQ(String q) {
        this.query = q;
    }

    public String list() {
        try {
            serviceOrders = serviceOrderManager.search(query, ServiceOrder.class);
        } catch (SearchException se) {
            addActionError(se.getMessage());
            serviceOrders = serviceOrderManager.query(new QueryCriteria().addSort(new String[]{"desc","createTime"}));
        }
        return SUCCESS;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ServiceOrder getServiceOrder() {
        return serviceOrder;
    }

    public void setServiceOrder(ServiceOrder serviceOrder) {
        this.serviceOrder = serviceOrder;
    }

    public String delete() {
        serviceOrderManager.remove(serviceOrder.getId());
        saveMessage(getText("serviceOrder.deleted"));

        return SUCCESS;
    }

    public String edit() {
    	brandList = brandManager.getAll();
    	businesses = customerManager.query(new Object[]{"type",CustomerType.MERCHANT});
    	commonCustomers = customerManager.query(new Object[]{"type",CustomerType.COMMON});
        if (id != null) {
            serviceOrder = serviceOrderManager.get(id);
        } else {
            serviceOrder = new ServiceOrder();
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

        boolean isNew = (serviceOrder.getId() == null);
        if(isNew){
        	serviceOrder.setCreateTime(new Date());
        }
        serviceOrderManager.save(serviceOrder);
        String key = (isNew) ? "serviceOrder.added" : "serviceOrder.updated";
        saveMessage(getText(key));
        return SUCCESS;
    }

	public List<Brand> getBrandList() {
		return brandList;
	}
    
}