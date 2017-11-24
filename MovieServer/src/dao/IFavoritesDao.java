package dao;

import java.util.List;

import bean.Favorites;

public interface IFavoritesDao {
	/**
	 * 获取收藏列表
	 * 
	 * @param userid
	 * @return
	 */
	List<Favorites> favoritesList(int userid, int pageNum);

	/**
	 * 判断某一个是否收藏了某个电影
	 * 
	 * @param userid
	 * @param movieid
	 * @return
	 */
	Favorites getFavoriteById(int userid, int movieid);

	

	/**
	 * 收藏某个电影
	 * 
	 * @param userid
	 * @param movieid
	 */
	void addNewFavorite(int userid, int movieid);

	/**
	 * 移除某一部电影
	 * 
	 * @param userid
	 * @param movieid
	 */
	void removeFavorite(int userid, int movieid);
	/**
	 * 判断一部电影是否在某个人的收藏中
	 * @param userid
	 * @param movieid
	 * @return
	 */
	Favorites isInFavorites(int userid,int movieid);

}
