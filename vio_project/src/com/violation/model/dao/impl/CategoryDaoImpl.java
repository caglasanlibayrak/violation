package com.violation.model.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.violation.model.dao.CategoryDao;
import com.violation.model.entity.Categories;

@Repository
public class CategoryDaoImpl extends BaseDao implements CategoryDao {

	@Override
	public Categories getCategoryById(int id) {
		String query = "from Categories where id=:id";
		Query createQuery = getSession().createQuery(query);
		createQuery.setInteger("id", id);
		Categories p = (Categories) createQuery.uniqueResult();
		return p;
	}

	/**
	 * Ana kategorileri getirmez. Sadece alt seviye kategorileri getirir. 
	 */
	@Override
	public List<Categories> getCategoriesList(int offset, int limit) {
		String queryString = "from Categories where parentId > 0 and status=:status";
		Query query = getSession().createQuery(queryString);
		query.setInteger("status", STATUS_ACTIVE);
		query.setFirstResult(offset);
		query.setMaxResults(limit);
		List<Categories> list = query.list();
		return list;
	}

	@Override
	public Categories saveOrUpdateCategory(Categories category) {
		getSession().saveOrUpdate(category);
		return category;
	}

	@Override
	public boolean deleteCategory(int id) {
		Categories category = getCategoryById(id);
		getSession().delete(category);
		return true;
	}

	@Override
	public List<Categories> getCategoriesListByParentId(int parentId, int offset, int limit) {
		String queryString = "from Categories where parentId=:parentId and status=:status";
		Query query = getSession().createQuery(queryString);
		query.setInteger("status", STATUS_ACTIVE);
		query.setInteger("parentId", parentId);
		query.setFirstResult(offset);
		query.setMaxResults(limit);
		List<Categories> list = query.list();
		return list;
	}

	@Override
	public List<Categories> getParentCategories(int offset, int limit) {
		String queryString = "from Categories where parentId =-1 and status=:status";
		Query query = getSession().createQuery(queryString);
		query.setInteger("status", STATUS_ACTIVE);
		query.setFirstResult(offset);
		query.setMaxResults(limit);
		List<Categories> list = query.list();
		return list;
	}
}
