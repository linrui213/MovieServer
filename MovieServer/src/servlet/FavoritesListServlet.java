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

import bean.Favorites;
import dao.IFavoritesDao;
import utils.CommonUtil;
import utils.DefaultUtils;

@WebServlet("/favoriteslist")
public class FavoritesListServlet extends BaseServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String idstr = req.getParameter("id");
		if (StringUtils.isEmpty(idstr)) {
			DefaultUtils.defalutError(resp, "id不能为空");
			return;
		}
		int id = Integer.parseInt(idstr);
		String pageNumstr = req.getParameter("pageNum");
		int pageNum = DefaultUtils.checkNull(pageNumstr, 0) * 100;
		SqlSession session = sqlSessionFactory.openSession();
		IFavoritesDao favoritesDao = session.getMapper(IFavoritesDao.class);
		List<Favorites> favoritesList = favoritesDao.favoritesList(id, pageNum);
		session.close();
		Map<String, Object> data = new HashMap<>();
		data.put("response", "favoriteslist");
		data.put("favoriteslist", favoritesList);
		CommonUtil.renderJson(resp, data);

	}

}
