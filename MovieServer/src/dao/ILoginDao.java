package dao;

import bean.User;

public interface ILoginDao {
	/**
	 * 登录
	 * @param name
	 * @param pwd
	 * @return
	 */
	User login(String name, String pwd);

}
