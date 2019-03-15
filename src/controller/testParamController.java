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
 * ��������springMVC��Controller�ĸ��ֲ������շ�ʽ���Լ�����
 * Ĭ�Ϸ���ҳ��·��������@ResponseBody���صľ��Ǵ�����
 */
@Controller
@RequestMapping("/testParam")
public class testParamController {
	//��ԭʼ�Ĳ������շ�ʽ����ԭʼ�ķ���json���ķ�ʽ
	@RequestMapping("/Login.do")
	public void login(HttpServletRequest request,HttpServletResponse response,HttpSession session) throws IOException{
		request.setCharacterEncoding("utf-8");
	 	Map<String,Object> jsonMap = new HashMap<String,Object>();
	 	String name = request.getParameter("name");
	 	String passWord= request.getParameter("passWord");
	 	session.setAttribute("username", name);
	 	int res=0;
	 	LogManager.infoMessage("�û�+++"+name+"+++��¼�ɹ���");
	 	jsonMap.put("code","200");
		jsonMap.put("message","��½�ɹ�");
        Cookie cookie=new Cookie("userId",name);
        response.addCookie(cookie);
	 	//�����ݷ���model�п��Է��ظ�ҳ��
	 	//ָ��ҳ���λ��
	 	response.setContentType("application/json");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out= null;
        out = response.getWriter();
        JSONUtils.toJSONString(jsonMap);
        System.out.println("����Ľ���ǣ�" + JSONUtils.toJSONString(jsonMap));
        //3����json����ת��Ϊjson�ַ���
        String result = JSONUtils.toJSONString(jsonMap);
        out.print(result);
        out.flush();
        out.close();
	}
	
	
	//1����ͨ��ʽ�������������Controller�ķ�������һ�£����ڷ���û����������ʽ������get��post���ɣ�
	@RequestMapping("/test.do")
	@ResponseBody
	public String testParams1(String userName,String passWord){
		System.out.println("��������>>>>>>>>>>>>>>>>");
		System.out.println("userName++++++++++"+userName);
		System.out.println("passWord++++++++++"+passWord);
		//return "/WEB-INF/jsp/logon";
		return "11111111111";
	}
	//2�����ʱ�򣬻���֣���̨���Խ��յ�ǰ̨���ݵĲ����������޷�������ת��Ϊjson���أ�
	//��500����ԭ��ܼ򵥣�����ΪSpringMVCĬ����û�ж���ת����json��ת������@ResponseBody����������Ҫ�ֶ����jackson����
	//Post��get�����ԣ����ִ������ķ�ʽ����
		@RequestMapping("/test2.do")
		@ResponseBody
		/*public Map testParams2(SysUser sysUser){
			System.out.println("��������>>>>>>>>>>>>>>>>");
			System.out.println("userName++++++++++"+sysUser.getPassWord());
			System.out.println("passWord++++++++++"+sysUser.getUserName());
			Map<String,Object> jsonMap = new HashMap<String,Object>();
			jsonMap.put("code","200");
			jsonMap.put("data",sysUser);
			return jsonMap;
		}*/
		public SysUser testParams2(SysUser sysUser){
			System.out.println("��������>>>>>>>>>>>>>>>>");
			System.out.println("passWord++++++++++"+sysUser.getPassWord());
			System.out.println("userName++++++++++"+sysUser.getUserName());
			//ͨ������·���map
			//Map<String,Object> jsonMap = new HashMap<String,Object>();
			//jsonMap.put("code","200");
			//jsonMap.put("data",sysUser);
			return sysUser;
		}
		//3���������������������������һ��ʱ��@RequestParam
				@RequestMapping("/test3.do")
				@ResponseBody
				public String testParams3(@RequestParam("user_name") String userName,String passWord){
					System.out.println("��������>>>>>>>>>>>>>>>>");
					System.out.println("userName++++++++++"+userName);
					System.out.println("passWord++++++++++"+passWord);
					
					return "33333333333333";
				}
		//4.����Ҫ����Json��ʽ������ʱ(contentType:"application/json;charset=utf-8"),��̨��@RequestBody���գ�������ղ���
		@RequestMapping("/test4.do")
		@ResponseBody
		public String testParams4(@RequestBody SysUser sysUser){
			System.out.println("��������>>>>>>>>>>>>>>>>");
			System.out.println("passWord++++++++++"+sysUser.getPassWord());
			System.out.println("userName++++++++++"+sysUser.getUserName());
			
			return "444444444444";
		}
		
		//5.url��������
				@RequestMapping("/test1.do")
				@ResponseBody
				public String testParams5(@RequestParam(value = "sessionId") String sessionId,@RequestParam(value = "user_name") String userName,String passWord){
					System.out.println("��������>>>>>>>>>>>>>>>>");
					System.out.println("sessionId++++++++++"+sessionId);
					System.out.println("userName++++++++++"+userName);
					System.out.println("passWord++++++++++"+sessionId);
					return "5555555555555";
				}
}
