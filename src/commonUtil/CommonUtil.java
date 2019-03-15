package commonUtil;

public class CommonUtil {
	/**
	 * 妫�娴嬫槸鍚︿负绌�
	 * 
	 * @param in
	 * @return
	 */
	public static String checkNull(String in) {
		if (in == null)
			in = "";
		in = in.trim();
		return in;
	}
}
