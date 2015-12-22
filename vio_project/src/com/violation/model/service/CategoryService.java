package com.violation.model.service;

import java.util.List;

import com.violation.model.entity.Categories;

public interface CategoryService {

	public Categories getCategoryById(int id);

	public List<Categories> getCategoriesList(int offset, int limit);

	public Categories saveOrUpdateCategory(Categories category);

	public boolean deleteCategory(int id);

	public List<Categories> getCategoriesListByParentId(int parentId, int offset, int limit);
	
	public List<Categories> getParentCategories(int offset, int limit);
}
