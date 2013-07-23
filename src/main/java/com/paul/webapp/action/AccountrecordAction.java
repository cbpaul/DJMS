package com.paul.webapp.action;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.struts2.json.JSONException;
import org.apache.struts2.json.JSONUtil;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.Preparable;
import com.paul.dao.SearchException;
import com.paul.dao.hibernate.QueryCriteria;
import com.paul.model.Accessories;
import com.paul.model.Accountrecord;
import com.paul.model.AccountrecordType;
import com.paul.model.Brand;
import com.paul.service.AccessoriesManager;
import com.paul.service.AccountrecordManager;
import com.paul.service.BrandManager;
import com.paul.service.PurchaseManager;

public class AccountrecordAction extends BaseAction implements Preparable {
    private AccountrecordManager accountrecordManager;
    private AccessoriesManager accessoriesManager;
    private PurchaseManager purchaseManager;
    private BrandManager brandManager;
    private List accountrecords;
    private Accountrecord accountrecord;
    private Long id;
    private String query;
    private List<Brand> brandList;
    private List<Accessories> accessorieList = new ArrayList<Accessories>();
    private AccountrecordType[] accountrecordTypes;
    private Long brandId;
    public void setBrandManager(BrandManager brandManager) {
		this.brandManager = brandManager;
	}

	public void setAccountrecordManager(AccountrecordManager accountrecordManager) {
        this.accountrecordManager = accountrecordManager;
    }
	
    public Long getBrandId() {
		return brandId;
	}

	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}

	public List getAccountrecords() {
        return accountrecords;
    }
    
    public AccountrecordType[] getAccountrecordTypes() {
		return AccountrecordType.values();
	}

	public List<Brand> getBrandList() {
		return brandList;
	}

	public void setPurchaseManager(PurchaseManager purchaseManager) {
		this.purchaseManager = purchaseManager;
	}

	public List<Accessories> getAccessorieList() {
		return accessorieList;
	}

	public void setAccessoriesManager(AccessoriesManager accessoriesManager) {
		this.accessoriesManager = accessoriesManager;
	}

	/**
     * Grab the entity from the database before populating with request parameters
     */
    public void prepare() {
        if (getRequest().getMethod().equalsIgnoreCase("post")) {
            // prevent failures on new
            String accountrecordId = getRequest().getParameter("accountrecord.id");
            if (accountrecordId != null && !accountrecordId.equals("")) {
                accountrecord = accountrecordManager.get(new Long(accountrecordId));
            }
        }
    }

    public void setQ(String q) {
        this.query = q;
    }

    public String list() {
        try {
            accountrecords = accountrecordManager.search(query, Accountrecord.class);
        } catch (SearchException se) {
            addActionError(se.getMessage());
            accountrecords = accountrecordManager.getAll();
        }
        return SUCCESS;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Accountrecord getAccountrecord() {
        return accountrecord;
    }

    public void setAccountrecord(Accountrecord accountrecord) {
        this.accountrecord = accountrecord;
    }

    public String delete() {
        accountrecordManager.remove(accountrecord.getId());
        saveMessage(getText("accountrecord.deleted"));

        return SUCCESS;
    }

    public String edit() {
    	brandList = brandManager.getAll();
        if (id != null) {
            accountrecord = accountrecordManager.get(id);
            QueryCriteria queryCriteria = new QueryCriteria();
        	queryCriteria.addEntryFildName("brand").addCriterion(Restrictions.eq("brand.id",  accountrecord.getBrand().getId()));
        	accessorieList = accessoriesManager.query(queryCriteria);
        } else {
            accountrecord = new Accountrecord();
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
        boolean isNew = (accountrecord.getId() == null);
        if(isNew){
        	accountrecord.setCreateTime(new Date());
        	if(accountrecord.getType().getIndex()==0){
        		Accessories acc = accessoriesManager.get(accountrecord.getAccessories().getId());
        		acc.setStock(acc.getStock()-1);
        	}
        }
        accountrecordManager.save(accountrecord);
        String key = (isNew) ? "accountrecord.added" : "accountrecord.updated";
        saveMessage(getText(key));
        if (!isNew) {
            return INPUT;
        } else {
            return SUCCESS;
        }
    }
    
    public void accessoriesListByBrand(){
    	QueryCriteria queryCriteria = new QueryCriteria();
    	queryCriteria.addEntryFildName("brand").addCriterion(Restrictions.eq("brand.id", brandId));
    	accessorieList = accessoriesManager.query(queryCriteria);
    	try {
			JSONUtil.serialize(getResponse().getWriter(), accessorieList);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}