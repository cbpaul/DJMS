package com.paul.model;

import java.io.Serializable;

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
@Table(name="employee",catalog="appfuseStruts")
@Indexed
@XmlRootElement
public class Employee extends BaseObject implements Serializable {
	private static final long serialVersionUID = 7441188870490545958L;
	private Long id;
    private String trueName;
    private String nick;
    private String employeeNo;
    private String email;
    private String qq;
    private Long age;
    private Gender gender;

    @Id @GeneratedValue(strategy=GenerationType.AUTO) @DocumentId    
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    @Column(name="trueName")
    @Field
    public String getTrueName() {
        return this.trueName;
    }
    
    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }
    
    @Column(name="nick")
    @Field
    public String getNick() {
        return this.nick;
    }
    
    public void setNick(String nick) {
        this.nick = nick;
    }
    
    @Column(name="employeeNo")
    @Field
    public String getEmployeeNo() {
        return this.employeeNo;
    }
    
    public void setEmployeeNo(String employeeNo) {
        this.employeeNo = employeeNo;
    }
    
    @Column(name="email")
    @Field
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    @Column(name="qq")
    @Field
    public String getQq() {
        return this.qq;
    }
    
    public void setQq(String qq) {
        this.qq = qq;
    }
    
    @Column(name="age")
    @Field
    public Long getAge() {
        return this.age;
    }
    
    public void setAge(Long age) {
        this.age = age;
    }
   @Column(name="gender")
   @Enumerated(EnumType.ORDINAL)
    public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employee pojo = (Employee) o;

        if (trueName != null ? !trueName.equals(pojo.trueName) : pojo.trueName != null) return false;
        if (nick != null ? !nick.equals(pojo.nick) : pojo.nick != null) return false;
        if (employeeNo != null ? !employeeNo.equals(pojo.employeeNo) : pojo.employeeNo != null) return false;
        if (email != null ? !email.equals(pojo.email) : pojo.email != null) return false;
        if (qq != null ? !qq.equals(pojo.qq) : pojo.qq != null) return false;
        if (age != null ? !age.equals(pojo.age) : pojo.age != null) return false;
        if (gender != null ? !gender.equals(pojo.gender) : pojo.gender != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = (trueName != null ? trueName.hashCode() : 0);
        result = 31 * result + (nick != null ? nick.hashCode() : 0);
        result = 31 * result + (employeeNo != null ? employeeNo.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (qq != null ? qq.hashCode() : 0);
        result = 31 * result + (age != null ? age.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("id").append("='").append(getId()).append("', ");
        sb.append("trueName").append("='").append(getTrueName()).append("', ");
        sb.append("nick").append("='").append(getNick()).append("', ");
        sb.append("employeeNo").append("='").append(getEmployeeNo()).append("', ");
        sb.append("email").append("='").append(getEmail()).append("', ");
        sb.append("qq").append("='").append(getQq()).append("', ");
        sb.append("age").append("='").append(getAge()).append("', ");
        sb.append("gender").append("='").append(getGender()).append("'");
        sb.append("]");
      
        return sb.toString();
    }

}
