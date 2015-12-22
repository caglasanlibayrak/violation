package com.violation.web.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.violation.model.entity.Categories;
import com.violation.model.service.CategoryService;
import com.violation.web.utils.EndirimUtils;
import com.violation.web.utils.MessagesUtils;

@Scope("session")
@Component
public class CategoryBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private ApplicationBean applicationBean;

	@Autowired
	private CategoryService categoryService;
	
	List<SelectItem> categoriesAsSelectItem = new ArrayList<>();

	@Autowired
	private UserBean userBean;

	private int offset;
	private int limit;
	private Categories selectedCategory = new Categories();
	private List<Categories> selectedList = new ArrayList<>();

	public String gotoCategoryListPage() {
		userBean.checkForLogin();
		selectedList = categoryService.getCategoriesList(0, 10000);
		return "";
	}

	public String gotoCategoryDetailPage() {
		userBean.checkForLogin();
		categoriesAsSelectItem = EndirimUtils.convertCategoriesToSelectItemList(categoryService.getParentCategories(0, 1000));
		selectedCategory = categoryService.getCategoryById(selectedCategory.getId());
		return "";
	}

	public String gotoAddCategoryPage() {
		userBean.checkForLogin();
		categoriesAsSelectItem = EndirimUtils.convertCategoriesToSelectItemList(categoryService.getParentCategories(0, 1000));
		selectedCategory = new Categories();
		return "";
	}

	public String saveOrUpdateCategory() throws IOException {
		userBean.checkForLogin();
		selectedCategory = categoryService.saveOrUpdateCategory(selectedCategory);
		MessagesUtils.addMessage("Adding/Updating Operations Completed Successfully!", FacesMessage.SEVERITY_INFO);
		FacesContext.getCurrentInstance().getExternalContext()
				.redirect(applicationBean.getCategoryDetailPageUrl(selectedCategory));
		return null;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public Categories getSelectedCategory() {
		return selectedCategory;
	}

	public void setSelectedCategory(Categories selectedCategory) {
		this.selectedCategory = selectedCategory;
	}

	public List<Categories> getSelectedList() {
		return selectedList;
	}

	public void setSelectedList(List<Categories> selectedList) {
		this.selectedList = selectedList;
	}

	public List<SelectItem> getCategoriesAsSelectItem() {
		return categoriesAsSelectItem;
	}

	public void setCategoriesAsSelectItem(List<SelectItem> categoriesAsSelectItem) {
		this.categoriesAsSelectItem = categoriesAsSelectItem;
	}
	
}
