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

import bean.Category;
import dao.ICategoryDao;
import dao.IHotKeyword;
import utils.CommonUtil;
import utils.DefaultUtils;

@WebServlet("/categorylist")
public class CategoryServlet extends BaseServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String idstr = req.getParameter("id");
		if (StringUtils.isEmpty(idstr)) {
			DefaultUtils.defalutError(resp, "id不能为空");
			return;
		}
		SqlSession session = sqlSessionFactory.openSession();
		ICategoryDao categoryDao = session.getMapper(ICategoryDao.class);
		List<Category> categoryList = categoryDao.getCategoryList();
		session.close();
		Map<String, Object> data = new HashMap<>();
		data.put("response", "categorylist");
		data.put("categorylist", categoryList);
		CommonUtil.renderJson(resp, data);
	}
}
