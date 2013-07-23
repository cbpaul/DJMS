package com.paul.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.search.annotations.DateBridge;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Resolution;

/**
 * @author user
 *
 */
@Entity
@Table(name="accountrecord",catalog="appfuseStruts")
@Indexed
@XmlRootElement
public class Accountrecord extends BaseObject implements Serializable {
   
	private static final long serialVersionUID = 4323923805989896479L;
	private Long id;
    private Brand brand;
    private Accessories accessories;
    private Float charge;
    private Date createTime;
    private AccountrecordType type;
    private Employee employee;

    @Id @GeneratedValue(strategy=GenerationType.AUTO) @DocumentId    
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    
    @Column(name="charge", precision=8)
    @Field
    public Float getCharge() {
        return this.charge;
    }
    
    public void setCharge(Float charge) {
        this.charge = charge;
    }
    @Column(name="createTime")
    @Field
    @DateBridge(resolution = Resolution.MINUTE)
    public Date getCreateTime() {
        return this.createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
   
    @ManyToOne
    @JoinColumn(name="brandId")
	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}
	@ManyToOne
	@JoinColumn(name="accessoriesId")
	public Accessories getAccessories() {
		return accessories;
	}

	public void setAccessories(Accessories accessories) {
		this.accessories = accessories;
	}
	@Column(name="type")
	@Field
	@Enumerated(EnumType.ORDINAL)
	public AccountrecordType getType() {
		return type;
	}

	public void setType(AccountrecordType type) {
		this.type = type;
	}
	@ManyToOne
	@JoinColumn(name="employeeId")
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
 
    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((accessories == null) ? 0 : accessories.hashCode());
		result = prime * result + ((brand == null) ? 0 : brand.hashCode());
		result = prime * result + ((charge == null) ? 0 : charge.hashCode());
		result = prime * result
				+ ((createTime == null) ? 0 : createTime.hashCode());
		result = prime * result
				+ ((employee == null) ? 0 : employee.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Accountrecord other = (Accountrecord) obj;
		if (accessories == null) {
			if (other.accessories != null)
				return false;
		} else if (!accessories.equals(other.accessories))
			return false;
		if (brand == null) {
			if (other.brand != null)
				return false;
		} else if (!brand.equals(other.brand))
			return false;
		if (charge == null) {
			if (other.charge != null)
				return false;
		} else if (!charge.equals(other.charge))
			return false;
		if (createTime == null) {
			if (other.createTime != null)
				return false;
		} else if (!createTime.equals(other.createTime))
			return false;
		if (employee == null) {
			if (other.employee != null)
				return false;
		} else if (!employee.equals(other.employee))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (type != other.type)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Accountrecord [id=" + id + ", brand=" + brand
				+ ", accessories=" + accessories + ", charge=" + charge
				+ ", createTime=" + createTime + ", type=" + type
				+ ", employee=" + employee + "]";
	}

}
