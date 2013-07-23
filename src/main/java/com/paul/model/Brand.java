package com.paul.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

@Entity
@Table(name="brand",catalog="appfuseStruts")
@Indexed
@XmlRootElement
public class Brand extends BaseObject implements Serializable {
	private static final long serialVersionUID = -1385668999882306173L;
	private Long id;
    private String name;
    private String description;
    private String officialUrl;

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
    
    @Column(name="description")
    @Field
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    @Column(name="officialUrl")
    @Field
    public String getOfficialUrl() {
        return this.officialUrl;
    }
    
    public void setOfficialUrl(String officialUrl) {
        this.officialUrl = officialUrl;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Brand pojo = (Brand) o;

        if (name != null ? !name.equals(pojo.name) : pojo.name != null) return false;
        if (description != null ? !description.equals(pojo.description) : pojo.description != null) return false;
        if (officialUrl != null ? !officialUrl.equals(pojo.officialUrl) : pojo.officialUrl != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (officialUrl != null ? officialUrl.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("id").append("='").append(getId()).append("', ");
        sb.append("name").append("='").append(getName()).append("', ");
        sb.append("description").append("='").append(getDescription()).append("', ");
        sb.append("officialUrl").append("='").append(getOfficialUrl()).append("'");
        sb.append("]");
      
        return sb.toString();
    }

}
