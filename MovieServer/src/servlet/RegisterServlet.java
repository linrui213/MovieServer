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

import bean.User;
import dao.IRegisterDao;
import utils.CommonUtil;
import utils.DefaultUtils;

@WebServlet("/register")
public class RegisterServlet extends BaseServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String name = req.getParameter("name");
		String pwd = req.getParameter("pwd");
		String gender = req.getParameter("gender");
		String strAge = req.getParameter("age");
		if (StringUtils.isEmpty(name) || StringUtils.isEmpty(pwd)
				|| StringUtils.isEmpty(gender) || StringUtils.isEmpty(strAge)) {
			DefaultUtils.defalutError(resp, "用户名,密码,性别,年龄不能为空");
			return;
		}
		int age = Integer.parseInt(strAge);
		SqlSession session = sqlSessionFactory.openSession();
		IRegisterDao registerDao = session.getMapper(IRegisterDao.class);
		List<User> userListByUsername = registerDao.getUserListByUsername(name);
		if (userListByUsername.size()>0) {
			DefaultUtils.defalutError(resp, "用户名已经存在,注册失败");
			return;
		}
		User user = new User();
		user.setName(name);
		user.setPwd(pwd);
		user.setGender(gender);
		user.setAge(age);
		int addNewUser = registerDao.addNewUser(user);
		session.commit();
		if (addNewUser < 1) {
			DefaultUtils.defalutError(resp, "注册新用户失败,请稍候再试");
			return;
		}
		int userId = registerDao.getUserIdByUserName(name);
		session.close();
		Map<String, Object> data = new HashMap<>();
		Map<String, Integer> userIdMap = new HashMap<>();
		userIdMap.put("userId", userId);
		data.put("response", "register");
		data.put("user", userIdMap);
		
		CommonUtil.renderJson(resp, data);
		

	}

}
