package dao;

import java.util.List;

import bean.History;

public interface IHistoryDao {
	/**
	 * 获取收藏列表,这个必须排序
	 * 
	 * @param userid
	 * @return
	 */
	List<History> historyList(int userid, int pageNum);

	/**
	 * 将某个电影加入到浏览历史中
	 * 
	 * @param userid
	 * @param movieid
	 */
	void addNewHistory(int userid, int movieid);

	/**
	 * 清空某个人所有历史记录
	 * 
	 * @param userid
	 */
	void removeAllHistory(int userid);

}
