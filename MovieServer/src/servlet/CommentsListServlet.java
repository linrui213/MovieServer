package servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.SqlSession;

import bean.Comment;
import dao.ICommentDao;
import utils.CommonUtil;
import utils.DefaultUtils;

@WebServlet("/commentslist")
public class CommentsListServlet extends BaseServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String movieidstr = req.getParameter("movieid");
		if (StringUtils.isEmpty(movieidstr)) {
			DefaultUtils.defalutError(resp, "movieid不能为空");
			return;
		}
		int movieid = Integer.parseInt(movieidstr);
		SqlSession session = sqlSessionFactory.openSession();
		ICommentDao commentDao = session.getMapper(ICommentDao.class);
		List<Comment> commentList = commentDao.commentList(movieid);
		Map<String, Object> data = new HashMap<>();
		data.put("response", "commentslist");
		data.put("commentslist", commentList);
		CommonUtil.renderJson(resp, data);

	}

}
