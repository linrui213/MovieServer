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
import bean.Favorites;
import dao.IFavoritesDao;

@WebServlet("/addfavorite")
public class AddFavoriteServlet extends BaseServlet {
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
		Favorites favoriteById = favoritesDao.getFavoriteById(id, movieid);
		if (favoriteById != null) {
			DefaultUtils.defalutError(resp, "已经在收藏里面了");
			return;
		}
		favoritesDao.addNewFavorite(id, movieid);
		session.commit();
		session.close();
		Map<String, Object> data = new HashMap<>();
		data.put("response", "addnewfavorite");
		CommonUtil.renderJson(resp, data);

	}

}
