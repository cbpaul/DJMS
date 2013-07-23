package com.paul.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

@Entity
@Table(name="accessories",catalog="appfuseStruts")
@Indexed
@XmlRootElement
public class Accessories extends BaseObject implements Serializable {

	private static final long serialVersionUID = 5514601548630701905L;
	private Long id;
    private String name;
    private Brand brand;
    private Integer stock=0;
    private Integer remainderAmount=0;
    private Integer usedAmount=0;
    
    @Id @GeneratedValue(strategy=GenerationType.AUTO) @DocumentId    
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    @Column(name="name")
    @Field
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    @ManyToOne
    @JoinColumn(name="brandId")
    public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	
	@Column(name="stock")
	@Field
    public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}
	@Field
	@Column(name="remainderAmount")
	public Integer getRemainderAmount() {
		return remainderAmount;
	}

	public void setRemainderAmount(Integer remainderAmount) {
		this.remainderAmount = remainderAmount;
	}
	@Field
	@Column(name="usedAmount")
	public Integer getUsedAmount() {
		return usedAmount;
	}

	public void setUsedAmount(Integer usedAmount) {
		this.usedAmount = usedAmount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((brand == null) ? 0 : brand.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Accessories other = (Accessories) obj;
		if (brand == null) {
			if (other.brand != null)
				return false;
		} else if (!brand.equals(other.brand))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Accessories [id=" + id + ", name=" + name + ", brand=" + brand
				+ "]";
	}


}
