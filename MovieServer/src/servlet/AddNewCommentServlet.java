package servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.SqlSession;

import dao.ICommentDao;
import utils.CommonUtil;
import utils.DefaultUtils;

@WebServlet("/addcomment")
public class AddNewCommentServlet extends BaseServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String useridstr = req.getParameter("userid");
		// userid,username,novelid,score,content
		String name = req.getParameter("name");
		String movieidstr = req.getParameter("movieid");
		String scorestr = req.getParameter("score");
		String content = req.getParameter("content");
		if (StringUtils.isEmpty(useridstr) || StringUtils.isEmpty(name)
				|| StringUtils.isEmpty(movieidstr)
				|| StringUtils.isEmpty(scorestr)
				|| StringUtils.isEmpty(content)) {
			DefaultUtils.defalutError(resp, "参数不全");
			return;

		}
		int userid = Integer.parseInt(useridstr);
		int movieid = Integer.parseInt(movieidstr);
		float score = Float.parseFloat(scorestr);
		SqlSession session = sqlSessionFactory.openSession();
		ICommentDao commentDao = session.getMapper(ICommentDao.class);
		commentDao.addNewComment(userid, name, movieid, score, content);
		session.commit();
		session.close();
		Map<String, Object> data = new HashMap<>();
		data.put("response", "addcomment");
		CommonUtil.renderJson(resp, data);

	}
}
