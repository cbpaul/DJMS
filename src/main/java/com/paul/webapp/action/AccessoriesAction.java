package com.paul.webapp.action;

import java.util.List;

import com.opensymphony.xwork2.Preparable;
import com.paul.dao.SearchException;
import com.paul.model.Accessories;
import com.paul.model.Brand;
import com.paul.service.AccessoriesManager;
import com.paul.service.BrandManager;

public class AccessoriesAction extends BaseAction implements Preparable {
	private static final long serialVersionUID = -5689014158834503774L;
	private AccessoriesManager accessoriesManager;
	 private BrandManager brandManager;
    private List accessoriess;
    private Accessories accessories;
    private Long id;
    private String query;
    private List<Brand> brandList;

    public void setAccessoriesManager(AccessoriesManager accessoriesManager) {
        this.accessoriesManager = accessoriesManager;
    }

    public List getAccessoriess() {
        return accessoriess;
    }
    
    public List<Brand> getBrandList() {
		return brandList;
	}

	public void setBrandManager(BrandManager brandManager) {
		this.brandManager = brandManager;
	}

	/**
     * Grab the entity from the database before populating with request parameters
     */
    public void prepare() {
        if (getRequest().getMethod().equalsIgnoreCase("post")) {
            // prevent failures on new
            String accessoriesId = getRequest().getParameter("accessories.id");
            if (accessoriesId != null && !accessoriesId.equals("")) {
                accessories = accessoriesManager.get(new Long(accessoriesId));
            }
        }
    }

    public void setQ(String q) {
        this.query = q;
    }

    public String list() {
        try {
            accessoriess = accessoriesManager.search(query, Accessories.class);
        } catch (SearchException se) {
            addActionError(se.getMessage());
            accessoriess = accessoriesManager.getAll();
        }
        return SUCCESS;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Accessories getAccessories() {
        return accessories;
    }

    public void setAccessories(Accessories accessories) {
        this.accessories = accessories;
    }

    public String delete() {
        accessoriesManager.remove(accessories.getId());
        saveMessage(getText("accessories.deleted"));

        return SUCCESS;
    }

    public String edit() {
    	brandList = brandManager.getAll();
        if (id != null) {
            accessories = accessoriesManager.get(id);
        } else {
            accessories = new Accessories();
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

        boolean isNew = (accessories.getId() == null);
        accessoriesManager.save(accessories);

        String key = (isNew) ? "accessories.added" : "accessories.updated";
        saveMessage(getText(key));

        if (!isNew) {
            return INPUT;
        } else {
            return SUCCESS;
        }
    }
}