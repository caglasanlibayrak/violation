package com.violation.model.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Table(name = "violation_categories")
@NamedQueries({ @NamedQuery(name = "Categories.findAll", query = "SELECT p FROM Categories p"),
		@NamedQuery(name = "Categories.findById", query = "SELECT p FROM Categories p WHERE p.id = :id"),
		@NamedQuery(name = "Categories.findByName", query = "SELECT p FROM Categories p WHERE p.name = :name") })
@Entity
public class Categories implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id", nullable = false)
	private Integer id;

	@Column(name = "parent_id", nullable = false)
	private Integer parentId;

	@Column(name = "name")
	private String name;

	@Column(name = "status")
	private Integer status;

	@Transient
	private String parentCatName;
	
	@Column(name = "type")
	private Integer type;
	
	@Column(name = "min")
	private Double min;
	
	@Column(name = "max")
	private Double max;

	
	public Categories() {
	}

	public Categories(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getParentCatName() {
		return parentCatName;
	}

	public void setParentCatName(String parentCatName) {
		this.parentCatName = parentCatName;
	}
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Double getMin() {
		return min;
	}

	public void setMin(Double min) {
		this.min = min;
	}

	public Double getMax() {
		return max;
	}

	public void setMax(Double max) {
		this.max = max;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {

		if (!(object instanceof Categories)) {
			return false;
		}
		Categories other = (Categories) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.violation.entity.Categories[ id=" + id + " ]";
	}

}
