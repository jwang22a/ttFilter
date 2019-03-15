package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.support.json.JSONUtils;

import commonUtil.LogManager;
import entity.SysUser;
/*
 * 用来测试springMVC的Controller的各种参数接收方式，以及返回
 * 默认返回页面路径，加了@ResponseBody返回的就是纯数据
 */
@Controller
@RequestMapping("/testParam")
public class testParamController {
	//最原始的参数接收方式，最原始的返回json串的方式
	@RequestMapping("/Login.do")
	public void login(HttpServletRequest request,HttpServletResponse response,HttpSession session) throws IOException{
		request.setCharacterEncoding("utf-8");
	 	Map<String,Object> jsonMap = new HashMap<String,Object>();
	 	String name = request.getParameter("name");
	 	String passWord= request.getParameter("passWord");
	 	session.setAttribute("username", name);
	 	int res=0;
	 	LogManager.infoMessage("用户+++"+name+"+++登录成功！");
	 	jsonMap.put("code","200");
		jsonMap.put("message","登陆成功");
        Cookie cookie=new Cookie("userId",name);
        response.addCookie(cookie);
	 	//将数据放入model中可以返回给页面
	 	//指定页面的位置
	 	response.setContentType("application/json");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out= null;
        out = response.getWriter();
        JSONUtils.toJSONString(jsonMap);
        System.out.println("输出的结果是：" + JSONUtils.toJSONString(jsonMap));
        //3、将json对象转化为json字符串
        String result = JSONUtils.toJSONString(jsonMap);
        out.print(result);
        out.flush();
        out.close();
	}
	
	
	//1，普通方式，请求参数名跟Controller的方法参数一致（由于方法没有限制请求方式，所以get和post均可）
	@RequestMapping("/test.do")
	@ResponseBody
	public String testParams1(String userName,String passWord){
		System.out.println("参数测试>>>>>>>>>>>>>>>>");
		System.out.println("userName++++++++++"+userName);
		System.out.println("passWord++++++++++"+passWord);
		//return "/WEB-INF/jsp/logon";
		return "11111111111";
	}
	//2，这个时候，会出现，后台可以接收到前台传递的参数，但是无法将对象转换为json返回，
	//报500错误原因很简单，是因为SpringMVC默认是没有对象转换成json的转换器（@ResponseBody），所以需要手动添加jackson依赖
	//Post，get都可以，两种传参数的方式都行
		@RequestMapping("/test2.do")
		@ResponseBody
		/*public Map testParams2(SysUser sysUser){
			System.out.println("参数测试>>>>>>>>>>>>>>>>");
			System.out.println("userName++++++++++"+sysUser.getPassWord());
			System.out.println("passWord++++++++++"+sysUser.getUserName());
			Map<String,Object> jsonMap = new HashMap<String,Object>();
			jsonMap.put("code","200");
			jsonMap.put("data",sysUser);
			return jsonMap;
		}*/
		public SysUser testParams2(SysUser sysUser){
			System.out.println("参数测试>>>>>>>>>>>>>>>>");
			System.out.println("passWord++++++++++"+sysUser.getPassWord());
			System.out.println("userName++++++++++"+sysUser.getUserName());
			//通常情况下返回map
			//Map<String,Object> jsonMap = new HashMap<String,Object>();
			//jsonMap.put("code","200");
			//jsonMap.put("data",sysUser);
			return sysUser;
		}
		//3，当请求参数名跟方法参数名不一致时，@RequestParam
				@RequestMapping("/test3.do")
				@ResponseBody
				public String testParams3(@RequestParam("user_name") String userName,String passWord){
					System.out.println("参数测试>>>>>>>>>>>>>>>>");
					System.out.println("userName++++++++++"+userName);
					System.out.println("passWord++++++++++"+passWord);
					
					return "33333333333333";
				}
		//4.当需要传递Json格式的数据时(contentType:"application/json;charset=utf-8"),后台用@RequestBody接收，否则接收不到
		@RequestMapping("/test4.do")
		@ResponseBody
		public String testParams4(@RequestBody SysUser sysUser){
			System.out.println("参数测试>>>>>>>>>>>>>>>>");
			System.out.println("passWord++++++++++"+sysUser.getPassWord());
			System.out.println("userName++++++++++"+sysUser.getUserName());
			
			return "444444444444";
		}
		
		//5.url参数传递
				@RequestMapping("/test1.do")
				@ResponseBody
				public String testParams5(@RequestParam(value = "sessionId") String sessionId,@RequestParam(value = "user_name") String userName,String passWord){
					System.out.println("参数测试>>>>>>>>>>>>>>>>");
					System.out.println("sessionId++++++++++"+sessionId);
					System.out.println("userName++++++++++"+userName);
					System.out.println("passWord++++++++++"+sessionId);
					return "5555555555555";
				}
}
