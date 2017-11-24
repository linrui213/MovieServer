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

import utils.CommonUtil;
import utils.DefaultUtils;
import dao.IFavoritesDao;

@WebServlet("/removefavorite")
public class RemoveFavoriteServlet extends BaseServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String idstr = req.getParameter("id");
		if (StringUtils.isEmpty(idstr)) {
			DefaultUtils.defalutError(resp, "id不能为空");
			return;
		}
		int id = Integer.parseInt(idstr);
		String movieidstr = req.getParameter("movieid");
		if (StringUtils.isEmpty(movieidstr)) {
			DefaultUtils.defalutError(resp, "movieid不能为空");
			return;
		}
		int movieid = Integer.parseInt(movieidstr);
		SqlSession session = sqlSessionFactory.openSession();
		IFavoritesDao favoritesDao = session.getMapper(IFavoritesDao.class);
		favoritesDao.removeFavorite(id, movieid);
		session.commit();
		session.close();
		Map<String, String> data = new HashMap<>();
		data.put("response", "removefavorite");
		CommonUtil.renderJson(resp, data);
	}

}
