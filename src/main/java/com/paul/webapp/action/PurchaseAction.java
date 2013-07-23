package com.paul.webapp.action;

import java.util.Date;
import java.util.List;

import com.opensymphony.xwork2.Preparable;
import com.paul.dao.SearchException;
import com.paul.model.Accessories;
import com.paul.model.Purchase;
import com.paul.service.AccessoriesManager;
import com.paul.service.PurchaseManager;

public class PurchaseAction extends BaseAction implements Preparable {
    private PurchaseManager purchaseManager;
    private AccessoriesManager accessoriesManager;
    private List purchases;
    private Purchase purchase;
    private Long id;
    private String query;
    private List<Accessories> accessorieList;
    public void setPurchaseManager(PurchaseManager purchaseManager) {
        this.purchaseManager = purchaseManager;
    }

    public List<Accessories> getAccessorieList() {
		return accessorieList;
	}

	public List getPurchases() {
        return purchases;
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
            String purchaseId = getRequest().getParameter("purchase.id");
            if (purchaseId != null && !purchaseId.equals("")) {
                purchase = purchaseManager.get(new Long(purchaseId));
            }
        }
    }

    public void setQ(String q) {
        this.query = q;
    }

    public String list() {
        try {
            purchases = purchaseManager.search(query, Purchase.class);
        } catch (SearchException se) {
            addActionError(se.getMessage());
            purchases = purchaseManager.getAll();
        }
        return SUCCESS;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Purchase getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }

    public String delete() {
        purchaseManager.remove(purchase.getId());
        saveMessage(getText("purchase.deleted"));

        return SUCCESS;
    }

    public String edit() {
    	accessorieList = accessoriesManager.getAll();
        if (id != null) {
            purchase = purchaseManager.get(id);
        } else {
            purchase = new Purchase();
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
        boolean isNew = (purchase.getId() == null);
        if(isNew){
        	purchase.setCraeteTime(new Date());
        	if(purchase.getAccessories() != null){
        		Accessories accessories = accessoriesManager.get(purchase.getAccessories().getId());
        		accessories.setStock(accessories.getStock()+purchase.getCount());
        		accessories.setRemainderAmount(accessories.getStock()-accessories.getUsedAmount());
        		accessoriesManager.save(accessories);
        	}
        }
        purchaseManager.save(purchase);
        String key = (isNew) ? "purchase.added" : "purchase.updated";
        saveMessage(getText(key));
        if (!isNew) {
            return INPUT;
        } else {
            return SUCCESS;
        }
    }
}