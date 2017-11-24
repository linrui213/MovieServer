package dao;

import java.util.List;

import bean.Category;

public interface ICategoryDao {
	/**
	 * 获取所有的分类信息
	 * @param userid
	 * @return
	 */
	List<Category> getCategoryList();
	
	
	

}
