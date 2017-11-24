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

import bean.User;
import dao.ILoginDao;
import utils.CommonUtil;
import utils.DefaultUtils;

@WebServlet("/login")
public class LoginServlet extends BaseServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String name = req.getParameter("name");
		String pwd = req.getParameter("pwd");
		if (StringUtils.isEmpty(name) || StringUtils.isEmpty(pwd)) {
			DefaultUtils.defalutError(resp, "用户名或密码不能为空");
			return;
		}
		SqlSession session = sqlSessionFactory.openSession();
		ILoginDao loginDao = session.getMapper(ILoginDao.class);
		User user = loginDao.login(name, pwd);
		session.close();
		if (user == null) {
			DefaultUtils.defalutError(resp, "用户不存在,或密码错误");
			return;
		}
		Map<String, Object> data = new HashMap<>();
		Map<String, Object> mapUser = new HashMap<>();
		mapUser.put("id", user.getId());
		mapUser.put("name", user.getName());
		mapUser.put("gender", user.getGender());
		mapUser.put("age", user.getAge());
		mapUser.put("money", user.getMoney());
		data.put("response", "login");
		data.put("user", mapUser);
		CommonUtil.renderJson(resp, data);

	}
}
