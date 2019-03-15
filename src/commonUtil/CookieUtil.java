package commonUtil;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class CookieUtil {
	public static String getCookieMessage(HttpServletRequest request)throws Exception {
		for (Cookie c:request.getCookies()){
			if(c.getName().equals("userId")){
				String userId=c.getValue();
				return userId;
			}
		}
		return "";
	}
}
