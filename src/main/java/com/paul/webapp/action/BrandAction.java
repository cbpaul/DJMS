package com.paul.webapp.action;

import com.opensymphony.xwork2.Preparable;
import com.paul.service.BrandManager;
import com.paul.dao.SearchException;
import com.paul.model.Brand;
import com.paul.webapp.action.BaseAction;

import java.util.List;

public class BrandAction extends BaseAction implements Preparable {
    private BrandManager brandManager;
    private List brands;
    private Brand brand;
    private Long id;
    private String query;

    public void setBrandManager(BrandManager brandManager) {
        this.brandManager = brandManager;
    }

    public List getBrands() {
        return brands;
    }

    /**
     * Grab the entity from the database before populating with request parameters
     */
    public void prepare() {
        if (getRequest().getMethod().equalsIgnoreCase("post")) {
            // prevent failures on new
            String brandId = getRequest().getParameter("brand.id");
            if (brandId != null && !brandId.equals("")) {
                brand = brandManager.get(new Long(brandId));
            }
        }
    }

    public void setQ(String q) {
        this.query = q;
    }

    public String list() {
        try {
            brands = brandManager.search(query, Brand.class);
        } catch (SearchException se) {
            addActionError(se.getMessage());
            brands = brandManager.getAll();
        }
        return SUCCESS;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public String delete() {
        brandManager.remove(brand.getId());
        saveMessage(getText("brand.deleted"));

        return SUCCESS;
    }

    public String edit() {
        if (id != null) {
            brand = brandManager.get(id);
        } else {
            brand = new Brand();
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

        boolean isNew = (brand.getId() == null);

        brandManager.save(brand);

        String key = (isNew) ? "brand.added" : "brand.updated";
        saveMessage(getText(key));

        if (!isNew) {
            return INPUT;
        } else {
            return SUCCESS;
        }
    }
}