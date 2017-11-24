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

import utils.CommonUtil;
import utils.DefaultUtils;
import bean.HotWord;
import bean.Movie;
import dao.ISearchDao;

@WebServlet("/search")
public class SearchServlet extends BaseServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String idstr = req.getParameter("id");
		if (StringUtils.isEmpty(idstr)) {
			DefaultUtils.defalutError(resp, "id不能为空");
			return;
		}
		String keyword = req.getParameter("keyword");
		if (StringUtils.isEmpty(keyword)) {
			DefaultUtils.defalutError(resp, "keyword不能为空");
			return;
		}
		String pageNumstr = req.getParameter("pageNum");
		int pageNum = DefaultUtils.checkNull(pageNumstr, 0) * 30;
		int id = Integer.parseInt(idstr);
		SqlSession session = sqlSessionFactory.openSession();
		ISearchDao searchDao = session.getMapper(ISearchDao.class);
		List<Movie> searchNovelList = searchDao.searchMovieListBykeyword(id,
				"%" + keyword + "%", pageNum);
		HotWord hotword = searchDao.queryIsExist(keyword);
		if (hotword == null) {
			searchDao.InsertHotWord(keyword);
		} else {
			searchDao.updateHotWord(keyword);

		}
		session.commit();
		session.close();
		Map<String, Object> data = new HashMap<>();
		data.put("response", "searchlist");
		data.put("searchlist", searchNovelList);
		CommonUtil.renderJson(resp, data);

	}

}
