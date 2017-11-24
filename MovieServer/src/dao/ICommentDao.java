package dao;

import java.util.List;

import bean.Comment;

public interface ICommentDao {
	/**
	 * 根据movieid查询这部电影的所有评论
	 * @param movieid
	 * @return
	 */
	List<Comment> commentList(int movieid);
	
	/**
	 * 添加一条评论
	 * @param userid
	 * @param username
	 * @param movieid
	 * @param score
	 * @param content
	 */
	void addNewComment(int userid,String username,int movieid,float score,String content);

}
