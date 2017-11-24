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

import bean.Movie;
import dao.IHomeListDao;
import utils.CommonUtil;
import utils.DefaultUtils;

@WebServlet("/home")
public class HomeListServlet extends BaseServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String idstr = req.getParameter("id");
		String categorystr = req.getParameter("category");
		String pageNumstr = req.getParameter("pageNum");
		if (StringUtils.isEmpty(idstr)) {
			DefaultUtils.defalutError(resp, "id不能为空");
			return;
		}
		int category=DefaultUtils.checkNull(categorystr, 0);
		int pageNum = DefaultUtils.checkNull(pageNumstr, 0) * 30;
		int id = Integer.parseInt(idstr);
		SqlSession session = sqlSessionFactory.openSession();
		IHomeListDao homeListDao = session.getMapper(IHomeListDao.class);
		List<Movie> homeList = homeListDao.homeList(id,category, pageNum);
		session.close();

		Map<String, Object> data = new HashMap<>();
		data.put("response", "homelist");
		data.put("homelist", homeList);
		CommonUtil.renderJson(resp, data);

	}
}
