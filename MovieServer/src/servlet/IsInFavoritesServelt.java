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

import bean.Favorites;
import utils.CommonUtil;
import utils.DefaultUtils;
import dao.IFavoritesDao;

@WebServlet("/isinfavorites")
public class IsInFavoritesServelt extends BaseServlet {
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
		Favorites favorites = favoritesDao.isInFavorites(id, movieid);
		session.close();
		int exists=0;
		//已经存在了返回1,不存在返回0
		if (favorites!=null) {
			exists=1;
		}
		Map<String,Integer> data=new HashMap<>();
		data.put("response", exists);
		CommonUtil.renderJson(resp, data);
	}
}
