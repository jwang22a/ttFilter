package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.support.json.JSONUtils;

import commonUtil.LogManager;
import dao.UserDao;
@Controller
@RequestMapping("/test")
public class testController {
	@RequestMapping("/toLogin.do")
	public String toLogin(){
		System.out.println("11111111111111111111111111111122");
		return "/WEB-INF/jsp/logon";
	}
	@RequestMapping("/Login.do")
	public void login(HttpServletRequest request,HttpServletResponse response,HttpSession session) throws IOException{
		request.setCharacterEncoding("utf-8");
	 	Map<String,Object> jsonMap = new HashMap<String,Object>();
	 	String name = request.getParameter("name");
	 	String passWord= request.getParameter("passWord");
	 	session.setAttribute("username", name);
	 	//jsonMap.put("total",name);
	 	//jsonMap.put("aa",passWord);
	 	//UserDao userDao=new UserDao();
	 	//int res=userDao.loginCheck(name, passWord);
	 	int res=0;
	 	if("lixuem".equals(name) && "111111".equals(passWord)){
	 		res=1;
	 	}
	 	if(res==1){
	 		LogManager.infoMessage("�û�+++"+name+"+++��¼�ɹ���");
	 		jsonMap.put("code","200");
		 	jsonMap.put("message","��½�ɹ�");
		 	
		 	//HttpSession session = request.getSession();
            //���û����ݱ�����session�������
            //session.setAttribute("loginName", testName);
            //String sessionId=session.getId();
            Cookie cookie=new Cookie("userId",name);
            response.addCookie(cookie);
	 	}else{
	 		jsonMap.put("code","-1");
		 	jsonMap.put("message","�û������������");
	 	}
	 	
	 	
	 	//�����ݷ���model�п��Է��ظ�ҳ��
	 	//ָ��ҳ���λ��
	 	response.setContentType("application/json");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out= null;
        out = response.getWriter();
        //out.print(JSONUtil.toJSONString(jsonMap));
       // JSONObject itemJSONObj= JSONObject.parseObject(JSON.toJSONString(itemMap));
        JSONUtils.toJSONString(jsonMap);
        System.out.println("����Ľ���ǣ�" + JSONUtils.toJSONString(jsonMap));
        //3����json����ת��Ϊjson�ַ���
        String result = JSONUtils.toJSONString(jsonMap);
        out.print(result);
        out.flush();
        out.close();
	}
	
	
	 @RequestMapping(value="/Login1.do",method=RequestMethod.POST)  
     @ResponseBody
        public  Map<String,Object> login(HttpServletRequest request, HttpServletResponse response){  
            Map<String,Object> map = new HashMap<String,Object>();  
              //map.put("a", "1");
              map.put("code","200");
              map.put("message","��½�ɹ�");
              request.getSession().setAttribute("username", "lixuem");
              for (String s : map.keySet()) {
                  System.out.println("key:" + s);
                  System.out.println("values:" + map.get(s));
                  }
            return map;  
        }  


	
	
	@RequestMapping("/index.do")
	public String toindex(){
		return "/WEB-INF/jsp/index";
	}

}
