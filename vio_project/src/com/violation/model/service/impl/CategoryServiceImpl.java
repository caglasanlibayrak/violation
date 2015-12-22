package com.violation.model.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.violation.model.dao.CategoryDao;
import com.violation.model.entity.Categories;
import com.violation.model.service.CategoryService;

@Repository
@Transactional
public class CategoryServiceImpl extends BaseService implements CategoryService {

	@Autowired
	private CategoryDao categoryDao;

	@Override
	public Categories getCategoryById(int id) {
		return categoryDao.getCategoryById(id);
	}

	@Override
	public List<Categories> getCategoriesList(int offset, int limit) {
		List<Categories> list = categoryDao.getCategoriesList(offset, limit);
		return fillParentCatName(list);
	}

	@Override
	public Categories saveOrUpdateCategory(Categories category) {
		return categoryDao.saveOrUpdateCategory(category);
	}

	@Override
	public boolean deleteCategory(int id) {
		return categoryDao.deleteCategory(id);
	}

	@Override
	public List<Categories> getCategoriesListByParentId(int parentId, int offset, int limit) {
		List<Categories> list = categoryDao.getCategoriesListByParentId(parentId, offset, limit);
		return fillParentCatName(list);

	}

	@Override
	public List<Categories> getParentCategories(int offset, int limit) {
		List<Categories> list = categoryDao.getParentCategories(offset, limit);
		return fillParentCatName(list);
	}

	private List<Categories> fillParentCatName(List<Categories> categories) {
		for (Categories cat : categories) {
			if (cat.getParentId() > 0) {
				Categories c = categoryDao.getCategoryById(cat.getParentId());
				cat.setParentCatName(c.getName());
			}
		}
		return categories;
	}
}
