package dao;

import java.util.List;

import bean.Movie;

public interface IHomeListDao {
	/**
	 * 获取首页内容
	 * 
	 * @param userid
	 * @param pageNum
	 * @return
	 */
	List<Movie> homeList(int userid, int category, int pageNum);

}
