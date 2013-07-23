package com.paul.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

@Entity
@Table(name="purchase",catalog="appfuseStruts")
@Indexed
@XmlRootElement
public class Purchase extends BaseObject implements Serializable {
	private static final long serialVersionUID = 6764712279775736934L;
	private Long id;
    private Accessories accessories;
    private Float price;
    private Integer count;
    private Float totalPrice;
    private Employee employee;
    private Date craeteTime;

    @Id @GeneratedValue(strategy=GenerationType.AUTO) @DocumentId    
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    @Column(name="price", precision=8)
    @Field
    public Float getPrice() {
        return this.price;
    }
    
    public void setPrice(Float price) {
        this.price = price;
    }
    
    @Column(name="count")
    @Field
    public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
    
    @Column(name="totalPrice", precision=8)
    @Field
    public Float getTotalPrice() {
        return this.totalPrice;
    }
    
   

	public void setTotalPrice(Float totalPrice) {
        this.totalPrice = totalPrice;
    }
    @ManyToOne
    @JoinColumn(name="accessoriesId")
    public Accessories getAccessories() {
		return accessories;
	}

	public void setAccessories(Accessories accessories) {
		this.accessories = accessories;
	}
	@ManyToOne
	@JoinColumn(name="employeeId")
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@Temporal(TemporalType.TIMESTAMP)
    @Column(name="craeteTime", length=19)
    @Field
    public Date getCraeteTime() {
        return this.craeteTime;
    }
    
	public void setCraeteTime(Date craeteTime) {
        this.craeteTime = craeteTime;
    }

  

    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((accessories == null) ? 0 : accessories.hashCode());
		result = prime * result + ((count == null) ? 0 : count.hashCode());
		result = prime * result
				+ ((craeteTime == null) ? 0 : craeteTime.hashCode());
		result = prime * result
				+ ((employee == null) ? 0 : employee.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result
				+ ((totalPrice == null) ? 0 : totalPrice.hashCode());
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
		Purchase other = (Purchase) obj;
		if (accessories == null) {
			if (other.accessories != null)
				return false;
		} else if (!accessories.equals(other.accessories))
			return false;
		if (count == null) {
			if (other.count != null)
				return false;
		} else if (!count.equals(other.count))
			return false;
		if (craeteTime == null) {
			if (other.craeteTime != null)
				return false;
		} else if (!craeteTime.equals(other.craeteTime))
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
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (totalPrice == null) {
			if (other.totalPrice != null)
				return false;
		} else if (!totalPrice.equals(other.totalPrice))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Purchase [id=" + id + ", accessories=" + accessories
				+ ", price=" + price + ", count=" + count + ", totalPrice="
				+ totalPrice + ", employee=" + employee + ", craeteTime="
				+ craeteTime + "]";
	}

	

}
