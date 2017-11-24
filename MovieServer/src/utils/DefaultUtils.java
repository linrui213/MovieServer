package utils;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

public class DefaultUtils {

	public static String defalut(String page, String defaultVal) {
		if (null == page || page.equals(page)) {
			return defaultVal;
		}
		return page;
	}

	public static void defalutError(HttpServletResponse response, String message) {
		try {
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("response", "error");
			data.put("error", new ErrorMessage(message));
			CommonUtil.renderJson(response, data);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean checkNull(HttpServletResponse response, String needCheckStr, String errorMessage) {
		if (StringUtils.isEmpty(needCheckStr)) {
			defalutError(response, errorMessage);
			return true;
		}

		return false;
	}

	public static int checkNull(String str, int defalutValue) {
		if (StringUtils.isEmpty(str)) {
			return defalutValue;
		}
		return Integer.parseInt(str);
	}

	
	public static String checkNull(String contentStr, String defalutStr) {
		
		return StringUtils.isEmpty(contentStr)? defalutStr : contentStr;
	}
}
