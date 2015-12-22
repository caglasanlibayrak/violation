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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.persistence.Entity;

@Table(name = "violation_violation")
@NamedQueries({ @NamedQuery(name = "Violations.findAll", query = "SELECT p FROM Violations p"),
		@NamedQuery(name = "Violations.findById", query = "SELECT p FROM Violations p WHERE p.id = :id"),
		@NamedQuery(name = "Violations.findByName", query = "SELECT p FROM Violations p WHERE p.name = :name") })
@Entity
public class Violations implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id", nullable = false)
	private Integer id;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 255)
	@Column(name = "name", nullable = false, length = 255)
	private String name;

	@Column(name = "cat_parent_id")
	private Integer catParentId;

	@Column(name = "cat_child_id")
	private Integer catChildId;

	@Column(name = "description")
	private String description;

	@Column(name = "image_url")
	private String imageUrl;

	@Column(name = "sub_category")
	private String subCategory;

	@Column(name = "latitude")
	private Double latitude;

	@Column(name = "longitude")
	private Double longitude;

	@Column(name = "status")
	private Integer status;

	@Column(name = "severity")
	private Integer severity;

	@Column(name = "user_id")
	private Integer userId;

	@Column(name = "value")
	private Double value;

	public Violations() {
	}

	public Violations(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Integer getCatParentId() {
		return catParentId;
	}

	public void setCatParentId(Integer catParentId) {
		this.catParentId = catParentId;
	}

	public Integer getCatChildId() {
		return catChildId;
	}

	public void setCatChildId(Integer catChildId) {
		this.catChildId = catChildId;
	}

	public String getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Integer getSeverity() {
		return severity;
	}

	public void setSeverity(Integer severity) {
		this.severity = severity;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {

		if (!(object instanceof Violations)) {
			return false;
		}
		Violations other = (Violations) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.violation.entity.Violation[ id=" + id + " ]";
	}

}
