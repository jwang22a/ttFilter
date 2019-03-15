package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import commonUtil.CookieUtil;
import commonUtil.JDBC;
import commonUtil.LogManager;
import commonUtil.UserOAMessage;
import dao.PrepareedDao;
import dao.UserDao;
import entity.UserEntity;
import entity.ZjjjEntity;
import service.LockProcessService;
import service.ProcessListService;
import service.ProcessService;

// 这里我写的注释要结合springmvc.xml里面的配置和index.jsp里面的内容一起看才容易理解
@Controller
@RequestMapping("/items")
public class ProcessController {
	@Autowired
	ProcessListService processListService;
	@Autowired
	LockProcessService lockProcessService;
	@Autowired ProcessService processService;
    // RequestMapping中的hello是要客户端请求时所映射的字符串（个人的理解，暂时找不到更好的说法），而helloWorld函数则是处理该映射
	//用户列表
	@RequestMapping("/list.do")
    public ModelAndView customerList(HttpServletRequest request,HttpServletResponse response) {  // 通过Model对象可以将数据返回给动态页面/前端页面（由服务器处理）
    	//List<Items> itemList = itemsService.findItemsAll();
		//String aa=request.getParameter("txtName");
		ModelAndView modelAndView = new ModelAndView();
		//将数据放入model中可以返回给页面
		//指定页面的位置
		UserDao user = new UserDao();
		List list = user.getUserList();
		modelAndView.addObject("list", list);
		modelAndView.setViewName("/WEB-INF/jsp/list");
		return modelAndView;
    }
	//删除用户
	@RequestMapping("/deleteUser.do")
    public ModelAndView deleteUser(HttpServletRequest request,HttpServletResponse response) {  // 通过Model对象可以将数据返回给动态页面/前端页面（由服务器处理）
    	String processNo=request.getParameter("processNo");
    	String userId=request.getParameter("userId");
    	String loginUserId="";
		try {
			loginUserId = CookieUtil.getCookieMessage(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if("lixuem".equals(loginUserId)){
			UserDao userDao = new UserDao();
			userDao.deleteUser(processNo, userId);
		}
		ModelAndView modelAndView = new ModelAndView();
		//将数据放入model中可以返回给页面
		//指定页面的位置
		UserDao user = new UserDao();
		List list = user.getUserList();
		modelAndView.addObject("list", list);
		modelAndView.setViewName("list");
		return modelAndView;
    }
	//新增用户页面
	@RequestMapping("/add.do")
    public ModelAndView addCustomer(HttpServletRequest request,HttpServletResponse response) {  // 通过Model对象可以将数据返回给动态页面/前端页面（由服务器处理）
    	//List<Items> itemList = itemsService.findItemsAll();
		ModelAndView modelAndView = new ModelAndView();
		//将数据放入model中可以返回给页面
		//指定页面的位置
		modelAndView.setViewName("add");
		//modelAndView.setViewName("logon");
		return modelAndView;
    }
	/*//新增用户页面
		@RequestMapping("/add.do")
	    public String addCustomer(HttpServletRequest request,HttpServletResponse response) {  // 通过Model对象可以将数据返回给动态页面/前端页面（由服务器处理）
	    	//List<Items> itemList = itemsService.findItemsAll();
			ModelAndView modelAndView = new ModelAndView();
			//将数据放入model中可以返回给页面
			//指定页面的位置
			//modelAndView.setViewName("add");
			//modelAndView.setViewName("logon");
			return "redirect:/logon";
	    }*/
	//新增用户
	@RequestMapping("/addUser.do")
    public void addUser(HttpServletRequest request,HttpServletResponse response) throws IOException {  // 通过Model对象可以将数据返回给动态页面/前端页面（由服务器处理）
    	//List<Items> itemList = itemsService.findItemsAll();
		String loginUserId="";
		try {
			loginUserId = CookieUtil.getCookieMessage(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		request.setCharacterEncoding("utf-8");
		if("lixuem".equals(loginUserId)){
		 	
		 	String userName = request.getParameter("userName");
		 	String userId= request.getParameter("userId");
		 	String phone = request.getParameter("phone");
		 	String processNo= request.getParameter("processNo");
		 	String processName = request.getParameter("processName");
		 	String partTimeRole= request.getParameter("partTimeRole");
		 	String passWord = request.getParameter("passWord");
		 	UserEntity user =  new UserEntity();
		 	user.setParttimeRole(partTimeRole);
		 	user.setPassword(passWord);
		 	user.setUserId(userId);
		 	user.setUserName(userName);
		 	user.setProcessNo(processNo);
		 	user.setProcessName(processName);
		 	user.setPhone(phone);
		 	//jsonMap.put("total",name);
		 	//jsonMap.put("aa",passWord);
		 	UserDao userDao=new UserDao();
		 	int index=userDao.addUser(user);
		 	if(index==1){
		 		jsonMap.put("code","200");
				jsonMap.put("message","用户添加成功！");
		 	}else{
		 		jsonMap.put("code","-1");
				jsonMap.put("message","用户添加失败！");
		 	}
		}else{
			jsonMap.put("code","-1");
			jsonMap.put("message","添加失败！无权限！");
		} 
	 	//将数据放入model中可以返回给页面
	 	//指定页面的位置
	 	response.setContentType("application/json");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out= null;
        out = response.getWriter();
        //out.print(JSONUtil.toJSONString(jsonMap));
       // JSONObject itemJSONObj= JSONObject.parseObject(JSON.toJSONString(itemMap));
        JSONUtils.toJSONString(jsonMap);
        System.out.println("输出的结果是：" + JSONUtils.toJSONString(jsonMap));
        //3、将json对象转化为json字符串
        String result = JSONUtils.toJSONString(jsonMap);
        out.print(result);
        out.flush();
        out.close();
    }
	@RequestMapping("/addProcess.do")
    public ModelAndView addProcess(HttpServletRequest request,HttpServletResponse response) {  // 通过Model对象可以将数据返回给动态页面/前端页面（由服务器处理）
    	//List<Items> itemList = itemsService.findItemsAll();
		ModelAndView modelAndView = new ModelAndView();
		List list=new ArrayList();
		String userId="";
		try {
			userId = CookieUtil.getCookieMessage(request);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("userId+++++++"+userId);
		list=processListService.getProcessList(userId);
		
		//将数据放入model中可以返回给页面
		modelAndView.addObject("list", list);
		//指定页面的位置
		modelAndView.setViewName("addProcess");
		
		return modelAndView;
    }
	//生成工单（自建系统账号权限申请）
		@RequestMapping("/ZJXTCreateProcess.do")
		public void ZJXTCreateProcess(HttpServletRequest request,HttpServletResponse response) throws IOException, SQLException {  // 通过Model对象可以将数据返回给动态页面/前端页面（由服务器处理）
				request.setCharacterEncoding("utf-8");
			 	String result="";
			 	String userId="";
				try {
					userId = CookieUtil.getCookieMessage(request);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Map<String,Object> jsonMap = new HashMap<String,Object>();
			 	String aData = request.getParameter("data");
			 	System.out.println("data++++++"+aData);
			 	String processName= request.getParameter("processName");
			 	//解析数据
			 	List dataList=lockProcessService.getZJXTList(aData);
			 	System.out.println("!!!!!!!!!!!!!"+dataList.size());
			 	//将数据插入activity_lock数据表
			 	lockProcessService.insertZJXTData(dataList);
			    //获取cookie中的登录用户名
			    
				UserDao uDao=new UserDao();
				UserEntity user = uDao.getUser(userId);
				//根据不同的登录用户更新activity_applicant中的发起人信息
				PrepareedDao.updateActivityApp(user, processName);
				System.out.println("processName+++++++++"+processName);
				result=processService.createProcess(processName,userId);
			 	
			 	jsonMap.put("code","200");
				jsonMap.put("message",result);
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
		        String resultMap = JSONUtils.toJSONString(jsonMap);
		        out.print(resultMap);
		        out.flush();
		        out.close();
	    }
	//生成工单（异常账号加解锁）
	@RequestMapping("/lockCreateProcess.do")
	public void lockCreateProcess(HttpServletRequest request,HttpServletResponse response) throws IOException, SQLException {  // 通过Model对象可以将数据返回给动态页面/前端页面（由服务器处理）
			request.setCharacterEncoding("utf-8");
		 	String result="";
		 	String userId="";
			try {
				userId = CookieUtil.getCookieMessage(request);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Map<String,Object> jsonMap = new HashMap<String,Object>();
		 	String aData = request.getParameter("data");
		 	System.out.println("data++++++"+aData);
		 	String processName= request.getParameter("processName");
		 	//解析数据
		 	List dataList=lockProcessService.getLockList(aData);
		 	System.out.println("!!!!!!!!!!!!!"+dataList.size());
		 	//将数据插入activity_lock数据表
		 	lockProcessService.insertsData(dataList);   
		    //获取cookie中的登录用户名
		    
			UserDao uDao=new UserDao();
			UserEntity user = uDao.getUser(userId);
			//根据不同的登录用户更新activity_applicant中的发起人信息
			PrepareedDao.updateActivityApp(user, processName);
			System.out.println("processName+++++++++"+processName);
			result=processService.createProcess(processName,userId);
		 	
		 	jsonMap.put("code","200");
			jsonMap.put("message",result);
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
	        String resultMap = JSONUtils.toJSONString(jsonMap);
	        out.print(resultMap);
	        out.flush();
	        out.close();
    }
	@RequestMapping("/createProcess.do")
	public void createProcess(HttpServletRequest request,HttpServletResponse response) throws IOException, SQLException {  // 通过Model对象可以将数据返回给动态页面/前端页面（由服务器处理）
			request.setCharacterEncoding("utf-8");
		 	String result="";
			Map<String,Object> jsonMap = new HashMap<String,Object>();
		 	String aData = request.getParameter("data");
		 	String processName= request.getParameter("processName");
		 	List dataList=new ArrayList();
		 	if("ZJJJ".equals(processName)){
		 		//dataList=(List) JSONUtils.parse(aData);
		 		JSONArray jsonArr = JSONArray.parseArray(aData);
		 		System.out.println(jsonArr.size());
		 		System.out.println(jsonArr.get(0));
		 		//JSONObject zjEntity=(JSONObject) JSONUtils.parse((String) jsonArr.get(0));
		 		
		        for (int i = 0; i < jsonArr.size(); i++) {  
		            JSONObject jo = jsonArr.getJSONObject(i);
		            ZjjjEntity jjEntity=new ZjjjEntity();
		            jjEntity.setAPPLYTITLE(jo.getString("APPLYTITLE"));
		            jjEntity.setCOMPLAINANT(jo.getString("COMPLAINANT"));
		            jjEntity.setHANDOVERCONTENT(jo.getString("HANDOVERCONTENT"));
		            jjEntity.setHANDOVERPERSON(jo.getString("HANDOVERPERSON"));
		            jjEntity.setPROBLEMSOURCE(jo.getString("PROBLEMSOURCE"));
		            jjEntity.setSLEVEL(jo.getString("SLEVEL"));
		            dataList.add(jjEntity);
		        }
		        Connection conn=JDBC.getConn();
		        String insertSql="insert into activitys_zjjj(APPLYTITLE,COMPLAINANT,HANDOVERCONTENT,HANDOVERPERSON,PROBLEMSOURCE,SLEVEL,STATU,UPTIME,USERID,USERNAME,PARTTIMEROLE) values(?,?,?,?,?,?,?,?,?,?,?)";
	            PreparedStatement pstmt=(PreparedStatement) conn.prepareStatement(insertSql);   ;
		        for(int j=0;j<dataList.size();j++){
		        	ZjjjEntity jjEntity=(ZjjjEntity) dataList.get(j);
		        	//System.out.println(j+"++++++++"+jjEntity.APPLYTITLE);
		        	pstmt.setString(1, jjEntity.getAPPLYTITLE());
		        	pstmt.setString(2, jjEntity.getCOMPLAINANT());
		        	pstmt.setString(3, jjEntity.getHANDOVERCONTENT());
		        	pstmt.setString(4, jjEntity.getHANDOVERPERSON());
		        	pstmt.setString(5, jjEntity.getPROBLEMSOURCE());
		        	pstmt.setString(6, jjEntity.getSLEVEL());
		        	pstmt.setString(7, "0");
		        	System.out.println("jjEntity.getHANDOVERPERSON()++++"+UserOAMessage.getUserId(jjEntity.getHANDOVERPERSON()));
		        	pstmt.setString(8, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		        	
		        	UserDao uDao=new UserDao();
		        	UserEntity sUser=uDao.getUserByName(jjEntity.getHANDOVERPERSON());
		        	pstmt.setString(9, sUser.getUserId());
		        	pstmt.setString(10, sUser.getUserName());
		        	pstmt.setString(11, sUser.getParttimeRole());
		        	/*pstmt.setString(9, UserOAMessage.getUserId(jjEntity.getHANDOVERPERSON()));
		        	pstmt.setString(10, UserOAMessage.getUserName(jjEntity.getHANDOVERPERSON()));
		        	pstmt.setString(11, UserOAMessage.getParttimerole(jjEntity.getHANDOVERPERSON()));*/
		        	pstmt.addBatch();  
		        }
		        pstmt.executeBatch(); 
		        System.out.println("插入成功！");
		        //获取cookie中的登录用户名
		        String userId="";
				try {
					userId = CookieUtil.getCookieMessage(request);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				UserDao uDao=new UserDao();
				UserEntity user = uDao.getUser(userId);
				//根据不同的登录用户更新activity_applicant中的发起人信息
				PrepareedDao.updateActivityApp(user, processName);
				result=processService.createProcess(processName,userId);
		 	}
		 	jsonMap.put("code","200");
			jsonMap.put("message",result);
		 	//将数据放入model中可以返回给页面
		 	//指定页面的位置
		 	response.setContentType("application/json");
	        response.setHeader("Pragma", "No-cache");
	        response.setHeader("Cache-Control", "no-cache");
	        response.setCharacterEncoding("UTF-8");
	        PrintWriter out= null;
	        out = response.getWriter();
	        //out.print(JSONUtil.toJSONString(jsonMap));
	       // JSONObject itemJSONObj= JSONObject.parseObject(JSON.toJSONString(itemMap));
	        JSONUtils.toJSONString(jsonMap);
	        System.out.println("输出的结果是：" + JSONUtils.toJSONString(jsonMap));
	        //3、将json对象转化为json字符串
	        
	        String resultMap = JSONUtils.toJSONString(jsonMap);
	        out.print(resultMap);
	        out.flush();
	        out.close();
    }
	//导入流程业务
	@RequestMapping("/exprotProcess.do")
    public ModelAndView exprotProcess(HttpServletRequest request,HttpServletResponse response) {  // 通过Model对象可以将数据返回给动态页面/前端页面（由服务器处理）
    	//List<Items> itemList = itemsService.findItemsAll();
		HttpSession session = request.getSession(false);
		//String loginName = (String)session.getAttribute("loginName");
		//System.out.println("loginName+++++++++"+loginName);
		/*for (Cookie c:request.getCookies()){
			if(c.getName().equals("userId")){
				String userId=c.getValue();
				System.out.println("userId++++++++++++++"+userId);
			}
		}*/
		String processNo=request.getParameter("processNo");//流程编号
		String pageName="OtherImportProcess";//页面名称
		ModelAndView modelAndView = new ModelAndView();
		//将数据放入model中可以返回给页面
		//modelAndView.addObject("name", "222222222222222");
		//指定页面的位置
		//根据流程进入不同的页面
		//值机交接班页面
		if("ZJJJ".equals(processNo)){
			pageName="ZJJJImportProcess";
		}
		//异常账号加解锁页面
		if("accountLocking".equals(processNo)){
			pageName="LockImportProcess";
		}
		//自建系统账号权限申请页面
		if("accountAuthority".equals(processNo)){
			pageName="ZJXTImportProcess";
		}
		modelAndView.setViewName(pageName);
		
		return modelAndView;
    }
	@RequestMapping(value="/toLogon.do", method = RequestMethod.POST )
    public void toLogon(HttpServletRequest request,HttpServletResponse response) throws IOException {  // 通过Model对象可以将数据返回给动态页面/前端页面（由服务器处理）
    	//List<Items> itemList = itemsService.findItemsAll();
			//String testName="lixuem";
			//String testPassW="111111";
			request.setCharacterEncoding("utf-8");
		 	Map<String,Object> jsonMap = new HashMap<String,Object>();
		 	String name = request.getParameter("name");
		 	String passWord= request.getParameter("passWord");
		 	//jsonMap.put("total",name);
		 	//jsonMap.put("aa",passWord);
		 	UserDao userDao=new UserDao();
		 	int res=userDao.loginCheck(name, passWord);
		 	if(res==1){
		 		LogManager.infoMessage("用户+++"+name+"+++登录成功！");
		 		jsonMap.put("code","200");
			 	jsonMap.put("message","登陆成功");
			 	
			 	HttpSession session = request.getSession();
	            //把用户数据保存在session域对象中
	            //session.setAttribute("loginName", testName);
	            //String sessionId=session.getId();
	            Cookie cookie=new Cookie("userId",name);
	            
	            response.addCookie(cookie);
		 	}else{
		 		jsonMap.put("code","-1");
			 	jsonMap.put("message","用户名或密码错误！");
		 	}
		 	
		 	
		 	//将数据放入model中可以返回给页面
		 	//指定页面的位置
		 	response.setContentType("application/json");
	        response.setHeader("Pragma", "No-cache");
	        response.setHeader("Cache-Control", "no-cache");
	        response.setCharacterEncoding("UTF-8");
	        PrintWriter out= null;
	        out = response.getWriter();
	        //out.print(JSONUtil.toJSONString(jsonMap));
	       // JSONObject itemJSONObj= JSONObject.parseObject(JSON.toJSONString(itemMap));
	        JSONUtils.toJSONString(jsonMap);
	        System.out.println("输出的结果是：" + JSONUtils.toJSONString(jsonMap));
	        //3、将json对象转化为json字符串
	        String result = JSONUtils.toJSONString(jsonMap);
	        out.print(result);
	        out.flush();
	        out.close();
    }
}
