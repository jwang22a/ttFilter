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

// ������д��ע��Ҫ���springmvc.xml��������ú�index.jsp���������һ�𿴲��������
@Controller
@RequestMapping("/items")
public class ProcessController {
	@Autowired
	ProcessListService processListService;
	@Autowired
	LockProcessService lockProcessService;
	@Autowired ProcessService processService;
    // RequestMapping�е�hello��Ҫ�ͻ�������ʱ��ӳ����ַ��������˵���⣬��ʱ�Ҳ������õ�˵��������helloWorld�������Ǵ����ӳ��
	//�û��б�
	@RequestMapping("/list.do")
    public ModelAndView customerList(HttpServletRequest request,HttpServletResponse response) {  // ͨ��Model������Խ����ݷ��ظ���̬ҳ��/ǰ��ҳ�棨�ɷ���������
    	//List<Items> itemList = itemsService.findItemsAll();
		//String aa=request.getParameter("txtName");
		ModelAndView modelAndView = new ModelAndView();
		//�����ݷ���model�п��Է��ظ�ҳ��
		//ָ��ҳ���λ��
		UserDao user = new UserDao();
		List list = user.getUserList();
		modelAndView.addObject("list", list);
		modelAndView.setViewName("/WEB-INF/jsp/list");
		return modelAndView;
    }
	//ɾ���û�
	@RequestMapping("/deleteUser.do")
    public ModelAndView deleteUser(HttpServletRequest request,HttpServletResponse response) {  // ͨ��Model������Խ����ݷ��ظ���̬ҳ��/ǰ��ҳ�棨�ɷ���������
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
		//�����ݷ���model�п��Է��ظ�ҳ��
		//ָ��ҳ���λ��
		UserDao user = new UserDao();
		List list = user.getUserList();
		modelAndView.addObject("list", list);
		modelAndView.setViewName("list");
		return modelAndView;
    }
	//�����û�ҳ��
	@RequestMapping("/add.do")
    public ModelAndView addCustomer(HttpServletRequest request,HttpServletResponse response) {  // ͨ��Model������Խ����ݷ��ظ���̬ҳ��/ǰ��ҳ�棨�ɷ���������
    	//List<Items> itemList = itemsService.findItemsAll();
		ModelAndView modelAndView = new ModelAndView();
		//�����ݷ���model�п��Է��ظ�ҳ��
		//ָ��ҳ���λ��
		modelAndView.setViewName("add");
		//modelAndView.setViewName("logon");
		return modelAndView;
    }
	/*//�����û�ҳ��
		@RequestMapping("/add.do")
	    public String addCustomer(HttpServletRequest request,HttpServletResponse response) {  // ͨ��Model������Խ����ݷ��ظ���̬ҳ��/ǰ��ҳ�棨�ɷ���������
	    	//List<Items> itemList = itemsService.findItemsAll();
			ModelAndView modelAndView = new ModelAndView();
			//�����ݷ���model�п��Է��ظ�ҳ��
			//ָ��ҳ���λ��
			//modelAndView.setViewName("add");
			//modelAndView.setViewName("logon");
			return "redirect:/logon";
	    }*/
	//�����û�
	@RequestMapping("/addUser.do")
    public void addUser(HttpServletRequest request,HttpServletResponse response) throws IOException {  // ͨ��Model������Խ����ݷ��ظ���̬ҳ��/ǰ��ҳ�棨�ɷ���������
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
				jsonMap.put("message","�û���ӳɹ���");
		 	}else{
		 		jsonMap.put("code","-1");
				jsonMap.put("message","�û����ʧ�ܣ�");
		 	}
		}else{
			jsonMap.put("code","-1");
			jsonMap.put("message","���ʧ�ܣ���Ȩ�ޣ�");
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
	@RequestMapping("/addProcess.do")
    public ModelAndView addProcess(HttpServletRequest request,HttpServletResponse response) {  // ͨ��Model������Խ����ݷ��ظ���̬ҳ��/ǰ��ҳ�棨�ɷ���������
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
		
		//�����ݷ���model�п��Է��ظ�ҳ��
		modelAndView.addObject("list", list);
		//ָ��ҳ���λ��
		modelAndView.setViewName("addProcess");
		
		return modelAndView;
    }
	//���ɹ������Խ�ϵͳ�˺�Ȩ�����룩
		@RequestMapping("/ZJXTCreateProcess.do")
		public void ZJXTCreateProcess(HttpServletRequest request,HttpServletResponse response) throws IOException, SQLException {  // ͨ��Model������Խ����ݷ��ظ���̬ҳ��/ǰ��ҳ�棨�ɷ���������
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
			 	//��������
			 	List dataList=lockProcessService.getZJXTList(aData);
			 	System.out.println("!!!!!!!!!!!!!"+dataList.size());
			 	//�����ݲ���activity_lock���ݱ�
			 	lockProcessService.insertZJXTData(dataList);
			    //��ȡcookie�еĵ�¼�û���
			    
				UserDao uDao=new UserDao();
				UserEntity user = uDao.getUser(userId);
				//���ݲ�ͬ�ĵ�¼�û�����activity_applicant�еķ�������Ϣ
				PrepareedDao.updateActivityApp(user, processName);
				System.out.println("processName+++++++++"+processName);
				result=processService.createProcess(processName,userId);
			 	
			 	jsonMap.put("code","200");
				jsonMap.put("message",result);
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
		        String resultMap = JSONUtils.toJSONString(jsonMap);
		        out.print(resultMap);
		        out.flush();
		        out.close();
	    }
	//���ɹ������쳣�˺żӽ�����
	@RequestMapping("/lockCreateProcess.do")
	public void lockCreateProcess(HttpServletRequest request,HttpServletResponse response) throws IOException, SQLException {  // ͨ��Model������Խ����ݷ��ظ���̬ҳ��/ǰ��ҳ�棨�ɷ���������
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
		 	//��������
		 	List dataList=lockProcessService.getLockList(aData);
		 	System.out.println("!!!!!!!!!!!!!"+dataList.size());
		 	//�����ݲ���activity_lock���ݱ�
		 	lockProcessService.insertsData(dataList);   
		    //��ȡcookie�еĵ�¼�û���
		    
			UserDao uDao=new UserDao();
			UserEntity user = uDao.getUser(userId);
			//���ݲ�ͬ�ĵ�¼�û�����activity_applicant�еķ�������Ϣ
			PrepareedDao.updateActivityApp(user, processName);
			System.out.println("processName+++++++++"+processName);
			result=processService.createProcess(processName,userId);
		 	
		 	jsonMap.put("code","200");
			jsonMap.put("message",result);
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
	        String resultMap = JSONUtils.toJSONString(jsonMap);
	        out.print(resultMap);
	        out.flush();
	        out.close();
    }
	@RequestMapping("/createProcess.do")
	public void createProcess(HttpServletRequest request,HttpServletResponse response) throws IOException, SQLException {  // ͨ��Model������Խ����ݷ��ظ���̬ҳ��/ǰ��ҳ�棨�ɷ���������
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
		        System.out.println("����ɹ���");
		        //��ȡcookie�еĵ�¼�û���
		        String userId="";
				try {
					userId = CookieUtil.getCookieMessage(request);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				UserDao uDao=new UserDao();
				UserEntity user = uDao.getUser(userId);
				//���ݲ�ͬ�ĵ�¼�û�����activity_applicant�еķ�������Ϣ
				PrepareedDao.updateActivityApp(user, processName);
				result=processService.createProcess(processName,userId);
		 	}
		 	jsonMap.put("code","200");
			jsonMap.put("message",result);
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
	        
	        String resultMap = JSONUtils.toJSONString(jsonMap);
	        out.print(resultMap);
	        out.flush();
	        out.close();
    }
	//��������ҵ��
	@RequestMapping("/exprotProcess.do")
    public ModelAndView exprotProcess(HttpServletRequest request,HttpServletResponse response) {  // ͨ��Model������Խ����ݷ��ظ���̬ҳ��/ǰ��ҳ�棨�ɷ���������
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
		String processNo=request.getParameter("processNo");//���̱��
		String pageName="OtherImportProcess";//ҳ������
		ModelAndView modelAndView = new ModelAndView();
		//�����ݷ���model�п��Է��ظ�ҳ��
		//modelAndView.addObject("name", "222222222222222");
		//ָ��ҳ���λ��
		//�������̽��벻ͬ��ҳ��
		//ֵ�����Ӱ�ҳ��
		if("ZJJJ".equals(processNo)){
			pageName="ZJJJImportProcess";
		}
		//�쳣�˺żӽ���ҳ��
		if("accountLocking".equals(processNo)){
			pageName="LockImportProcess";
		}
		//�Խ�ϵͳ�˺�Ȩ������ҳ��
		if("accountAuthority".equals(processNo)){
			pageName="ZJXTImportProcess";
		}
		modelAndView.setViewName(pageName);
		
		return modelAndView;
    }
	@RequestMapping(value="/toLogon.do", method = RequestMethod.POST )
    public void toLogon(HttpServletRequest request,HttpServletResponse response) throws IOException {  // ͨ��Model������Խ����ݷ��ظ���̬ҳ��/ǰ��ҳ�棨�ɷ���������
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
		 		LogManager.infoMessage("�û�+++"+name+"+++��¼�ɹ���");
		 		jsonMap.put("code","200");
			 	jsonMap.put("message","��½�ɹ�");
			 	
			 	HttpSession session = request.getSession();
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
}
