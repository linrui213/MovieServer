package dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import bean.HotWord;
import bean.Movie;

public interface ISearchDao {
	/**
	 * 根据关键字搜索
	 * 
	 * @param userid
	 * @param keyword
	 * @param pageNum
	 * @return
	 */
	List<Movie> searchMovieListBykeyword(@Param("id") int userid,
			@Param("keyword") String keyword, @Param("pageNum") int pageNum);
	/**
	 * 查询keyword是否在hotwords表中
	 * @param keyword
	 * @return
	 */
	HotWord queryIsExist(String keyword);
	/**
	 * 如果在表中,更新hot度
	 * @param keyword
	 */
	void updateHotWord(String keyword);
	/**
	 * 如果不在,则插入到表中
	 * @param keyword
	 */
	void InsertHotWord(String keyword);

}
