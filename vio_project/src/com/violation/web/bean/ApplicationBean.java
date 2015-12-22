package com.violation.web.bean;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import com.violation.model.entity.Categories;
import com.violation.model.entity.Users;
import com.violation.model.entity.Violations;

@Component
public class ApplicationBean {

	public ApplicationBean() {

	}

	public String getAddViolationPage() {
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		String url = request.getContextPath() + "/admin/addviolation";
		return url;
	}

	public String getDomainUrl() {
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		String url = request.getContextPath();
		return url;
	}

	public String getAddViolationPageUrl() {
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		String url = request.getContextPath() + "/admin/violation/add-violation";
		return url;
	}

	public String getViolationEditDetailPageUrl(Violations violation) {
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		String url = request.getContextPath() + "/violation/edit/" + violation.getId();
		return url;
	}
	
	public String getViolationDetailPageUrl(Violations violation) {
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		String url = request.getContextPath() + "/violation/detail/" + violation.getId();
		return url;
	}

	public String getViolationListUrl() {
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		String url = request.getContextPath() + "/admin/violation/list/all";
		return url;
	}
	
	public String getUserOpenViolationListUrl() {
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		String url = request.getContextPath() + "/admin/violation/list/all/open";
		return url;
	}
	
	public String getUserClosedViolationListUrl() {
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		String url = request.getContextPath() + "/admin/violation/list/all/closed";
		return url;
	}

	public String getAddCategoryPageUrl() {
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		String url = request.getContextPath() + "/admin/category/add-category";
		return url;
	}

	public String getCategoryDetailPageUrl(Categories category) {
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		String url = request.getContextPath() + "/admin/category/" + category.getId();
		return url;
	}

	public String getCategoryListUrl() {
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		String url = request.getContextPath() + "/admin/category/list/all";
		return url;
	}

	public String getServerAddress() {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
		return "http://" + (request.getServerName().contains("www") ? "" : "www.") + request.getServerName()
				+ (request.getServerPort() == 80 ? "" : (":" + request.getServerPort()));
	}

	public String getLoginPageUrl() {
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		String url = request.getContextPath();
		return url;
	}

	public String getSignUpPageUrl() {
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		String url = request.getContextPath() + "/user/sign-up";
		return url;
	}

	public String getUserProfilePageUrl(Users user) {
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		String url;
		if (user != null && user.getId() != null) {
			url = request.getContextPath() + "/user/profile/" + user.getId();
		} else {
			url = request.getContextPath() + "/user/profile/" + 1;
		}
		return url;
	}
	
	public String getUserProfileEditPageUrl(Users user) {
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		String url;
		if (user != null && user.getId() != null) {
			url = request.getContextPath() + "/user/profile/edit/" + user.getId();
		} else {
			url = request.getContextPath() + "/user/profile/edit/" + 1;
		}
		return url;
	}

}
