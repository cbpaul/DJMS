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
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

@Entity
@Table(name="customer",catalog="appfuseStruts")
@Indexed
@XmlRootElement
public class Customer extends BaseObject implements Serializable {
	private static final long serialVersionUID = 3998072804740298409L;
	private Long id;
    private String name;
    private Gender gender;
    private String phoneNum;
    private CustomerType type;
    private Date createTime;

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
    
    
    @Column(name="phoneNum")
    @Field
    public String getPhoneNum() {
        return this.phoneNum;
    }
    
    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }
    
  
    @Column(name="gender")
    @Field
    @Enumerated(EnumType.ORDINAL)
    public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}
	@Column(name="type")
	@Field
	@Enumerated(EnumType.ORDINAL)
	public CustomerType getType() {
		return type;
	}

	public void setType(CustomerType type) {
		this.type = type;
	}
	@Column(name="createTime")
	@Field
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer pojo = (Customer) o;

        if (name != null ? !name.equals(pojo.name) : pojo.name != null) return false;
        if (gender != null ? !gender.equals(pojo.gender) : pojo.gender != null) return false;
        if (phoneNum != null ? !phoneNum.equals(pojo.phoneNum) : pojo.phoneNum != null) return false;
        if (type != null ? !type.equals(pojo.type) : pojo.type != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = (name != null ? name.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (phoneNum != null ? phoneNum.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("id").append("='").append(getId()).append("', ");
        sb.append("name").append("='").append(getName()).append("', ");
        sb.append("gender").append("='").append(getGender()).append("', ");
        sb.append("phoneNum").append("='").append(getPhoneNum()).append("', ");
        sb.append("type").append("='").append(getType()).append("'");
        sb.append("]");
      
        return sb.toString();
    }

}
