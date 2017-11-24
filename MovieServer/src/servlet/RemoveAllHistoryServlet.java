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

import dao.IHistoryDao;
import utils.CommonUtil;
import utils.DefaultUtils;

@WebServlet("/removeallhistory")
public class RemoveAllHistoryServlet extends BaseServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String idstr = req.getParameter("id");
		if (StringUtils.isEmpty(idstr)) {
			DefaultUtils.defalutError(resp, "id不能为空");
			return;
		}
		int id = Integer.parseInt(idstr);
		SqlSession session = sqlSessionFactory.openSession();
		IHistoryDao historyDao = session.getMapper(IHistoryDao.class);
		historyDao.removeAllHistory(id);
		session.commit();
		session.close();
		Map<String, String> data = new HashMap<>();
		data.put("response", "removeallhistory");
		CommonUtil.renderJson(resp, data);

	}

}
