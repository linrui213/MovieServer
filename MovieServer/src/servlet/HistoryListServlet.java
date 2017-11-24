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

import bean.History;
import dao.IHistoryDao;
import utils.CommonUtil;
import utils.DefaultUtils;

@WebServlet("/historylist")
public class HistoryListServlet extends BaseServlet {
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
		IHistoryDao historyDao = session.getMapper(IHistoryDao.class);
		List<History> historyList = historyDao.historyList(id, pageNum);
		session.close();
		Map<String, Object> data = new HashMap<>();
		data.put("response", "favoriteslist");
		data.put("favoriteslist", historyList);
		//这里我偷了点小懒,History和Favorites的字段一模一样,我客户端为了少些一个bean,这里改成了favoriteslist
//		data.put("response", "historylist");
//		data.put("historylist", historyList);
		CommonUtil.renderJson(resp, data);

	}
}
