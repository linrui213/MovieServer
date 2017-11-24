package dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import bean.User;

public interface IRegisterDao {
	/**
	 * 先查询该用户名是否存在
	 * @param username
	 * @return 如果返回的list为空表示不存在该用户,可以注册,否则用户已经存在
	 */
	List<User> getUserListByUsername(String userName);
	/**
	 * 向数据库里插入新用户
	 * @param user
	 * @return
	 */
	
	
	int addNewUser(@Param("user")User user);
	/**
	 * 注册成功后根据username返回userid
	 * @param userName
	 * @return
	 */
	int getUserIdByUserName(String userName);

}
