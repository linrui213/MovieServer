package servlet;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class BaseServlet extends HttpServlet {
	public SqlSessionFactory sqlSessionFactory;

	public BaseServlet() {
		super();
		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
		try {
			InputStream inputStream = Resources
					.getResourceAsStream("SqlMapConfig.xml");
			sqlSessionFactory = builder.build(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}

}
