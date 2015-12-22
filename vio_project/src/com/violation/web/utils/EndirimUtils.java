package com.violation.web.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.faces.model.SelectItem;
import com.violation.model.entity.Categories;

public class EndirimUtils {

	public static List<SelectItem> convertCategoriesToSelectItemList(List<Categories> list) {

		List<SelectItem> brandsAsSelectItem = new ArrayList<SelectItem>();
		for (Categories category : list) {
			brandsAsSelectItem.add(new SelectItem(category.getId(), category.getName()));
		}
		return brandsAsSelectItem;
	}

	public static String generateRandomString(int lenght) {
		String randomString = UUID.randomUUID().toString().substring(0, lenght);
		return randomString;
	}

}
