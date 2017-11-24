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

import dao.IHotKeyword;
import utils.CommonUtil;
import utils.DefaultUtils;

@WebServlet("/hotkeywords")
public class HotKeywordServlet extends BaseServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String idstr = req.getParameter("id");
		if (StringUtils.isEmpty(idstr)) {
			DefaultUtils.defalutError(resp, "id不能为空");
			return;
		}
		SqlSession session = sqlSessionFactory.openSession();
		IHotKeyword hotKeyword = session.getMapper(IHotKeyword.class);
		List<String> hotKeywordList = hotKeyword.getHotKeywordList();
		session.close();
		Map<String, Object> data = new HashMap<>();
		data.put("response", "hotkeywords");
		data.put("hotkeywords", hotKeywordList);
		CommonUtil.renderJson(resp, data);
	}
}
