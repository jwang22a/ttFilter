package dao;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import commonUtil.JDBC;
import entity.ProcessEntity;
import entity.UserEntity;

public class UserDao {
	public UserEntity getUser(String userId){
		UserEntity user=new UserEntity();
		String sql = "select * from user_info where userId='"+userId+"'";
        Connection conn=JDBC.getConn();
        PreparedStatement pstmt;
        ResultSet rs;
        
        try {
            pstmt = (PreparedStatement)conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            
            int col = rs.getMetaData().getColumnCount();
            System.out.println("============================");
            ResultSetMetaData md = rs.getMetaData();
            int columnCount = md.getColumnCount(); 
            while (rs.next()) {
            	Map<String,Object> rowData = new HashMap<String,Object>();
    			for (int i = 1; i <= columnCount; i++) {
    				rowData.put(md.getColumnName(i), rs.getObject(i));
    			}
    			user.setUserId(String.valueOf(rowData.get("USERID")));
    			user.setPassword(String.valueOf(rowData.get("passWd")));
    			user.setPhone(String.valueOf(rowData.get("PHONE")));
    			user.setParttimeRole(String.valueOf(rowData.get("PARTTIMEROLE")));
    			user.setUserName(String.valueOf(rowData.get("USERNAME")));
    			
    			//ProcessEntity proccess=new ProcessEntity();
    			//proccess.setProcessNo(String.valueOf(rowData.get("MODEL_ID")));
    			//proccess.setProcessName(String.valueOf(rowData.get("MODEL_NAME")));
            }
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return user;
	}
	//根据用户姓名获取用户信息，可能会出现重名的情况（只是暂时的）
	public UserEntity getUserByName(String userName){
		UserEntity user=new UserEntity();
		String sql = "select * from user_info where userName='"+userName+"'";
        Connection conn=JDBC.getConn();
        PreparedStatement pstmt;
        ResultSet rs;
        
        try {
            pstmt = (PreparedStatement)conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            
            int col = rs.getMetaData().getColumnCount();
            System.out.println("============================");
            ResultSetMetaData md = rs.getMetaData();
            int columnCount = md.getColumnCount(); 
            while (rs.next()) {
            	Map<String,Object> rowData = new HashMap<String,Object>();
    			for (int i = 1; i <= columnCount; i++) {
    				rowData.put(md.getColumnName(i), rs.getObject(i));
    			}
    			user.setUserId(String.valueOf(rowData.get("USERID")));
    			user.setPassword(String.valueOf(rowData.get("passWd")));
    			user.setPhone(String.valueOf(rowData.get("PHONE")));
    			user.setParttimeRole(String.valueOf(rowData.get("PARTTIMEROLE")));
    			user.setUserName(String.valueOf(rowData.get("USERNAME")));
    			
    			//ProcessEntity proccess=new ProcessEntity();
    			//proccess.setProcessNo(String.valueOf(rowData.get("MODEL_ID")));
    			//proccess.setProcessName(String.valueOf(rowData.get("MODEL_NAME")));
            }
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return user;
	}
	
	public String getUserRoleId(String userId){
		UserEntity user=new UserEntity();
		String sql = "select * from user_info where userId='"+userId+"'";
        Connection conn=JDBC.getConn();
        PreparedStatement pstmt;
        ResultSet rs;
        String roleId="";
        try {
            pstmt = (PreparedStatement)conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            
            int col = rs.getMetaData().getColumnCount();
            System.out.println("============================");
            ResultSetMetaData md = rs.getMetaData();
            int columnCount = md.getColumnCount(); 
            while (rs.next()) {
            	Map<String,Object> rowData = new HashMap<String,Object>();
    			for (int i = 1; i <= columnCount; i++) {
    				rowData.put(md.getColumnName(i), rs.getObject(i));
    			}
    			
    			roleId=String.valueOf(rowData.get("PARTTIMEROLE"));
    			
    			
    			//ProcessEntity proccess=new ProcessEntity();
    			//proccess.setProcessNo(String.valueOf(rowData.get("MODEL_ID")));
    			//proccess.setProcessName(String.valueOf(rowData.get("MODEL_NAME")));
            }
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return roleId;
	}
	
	/*public static void main(String[] args) {
		UserDao dao=new UserDao();
		String ss = dao.getUserRoleId("wangyao5");
		System.out.println("ss++++++++++++"+ss);
	}*/
	public int addUser(UserEntity user){
		
		String insertSql="insert into user_info(USERID,USERNAME,PHONE,PARTTIMEROLE,processNo,processName,passWd) values(?,?,?,?,?,?,?)";
        Connection conn=JDBC.getConn();
        PreparedStatement pstmt;
        ResultSet rs;
        int index=0;
        try {
            pstmt = (PreparedStatement)conn.prepareStatement(insertSql);
            pstmt.setString(1, user.getUserId());
            pstmt.setString(2, user.getUserName());
            pstmt.setString(3, user.getPhone());
            pstmt.setString(4, user.getParttimeRole());
            pstmt.setString(5, user.getProcessNo());
            pstmt.setString(6, user.getProcessName());
            pstmt.setString(7, user.getPassword());
            index = pstmt.executeUpdate();
            System.out.println("index+++++++++++"+index);

        } catch (SQLException e) {
            e.printStackTrace();
        }
		return index;
	}
	
//删除用户
public int deleteUser(String processNo,String userId){
		
		String insertSql="delete from  user_info where USERID='"+userId+"' and processNo='"+processNo+"'";
        Connection conn=JDBC.getConn();
        PreparedStatement pstmt;
        ResultSet rs;
        int index=0;
        try {
            pstmt = (PreparedStatement)conn.prepareStatement(insertSql);
            
            index = pstmt.executeUpdate();
            System.out.println("index+++++++++++"+index);

        } catch (SQLException e) {
            e.printStackTrace();
        }
		return index;
	}
	
	public List<UserEntity> getUserList(){
		String sql = "select * from user_info";
        Connection conn=JDBC.getConn();
        PreparedStatement pstmt;
        ResultSet rs;
        ArrayList<UserEntity> config_list=new ArrayList();
        
        try {
            pstmt = (PreparedStatement)conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            int col = rs.getMetaData().getColumnCount();
            System.out.println("============================");
            ResultSetMetaData md = rs.getMetaData();
            int columnCount = md.getColumnCount(); 
            while (rs.next()) {
            	Map<String,Object> rowData = new HashMap<String,Object>();
    			for (int i = 1; i <= columnCount; i++) {
    				rowData.put(md.getColumnName(i), rs.getObject(i));
    			}
    			UserEntity user=new UserEntity();
    			user.setParttimeRole(String.valueOf(rowData.get("PARTTIMEROLE")));
    			user.setPassword(String.valueOf(rowData.get("passWd")));
    			user.setPhone(String.valueOf(rowData.get("PHONE")));
    			user.setUserId(String.valueOf(rowData.get("USERID")));
    			user.setUserName(String.valueOf(rowData.get("USERNAME")));
    			user.setProcessNo(String.valueOf(rowData.get("processNo")));
    			user.setProcessName(String.valueOf(rowData.get("processName")));
    			//proccess.setProcessNo(String.valueOf(rowData.get("MODEL_ID")));
    			config_list.add(user);
            }
          /*  for(int i=0;i<config_list.size();i++){
            	ProcessEntity p=new ProcessEntity();
            	p=config_list.get(i);
            	System.out.println(p.getProcessName());
            	System.out.println(p.getProcessNo());
            }*/
               // System.out.println("============================"+config_list.get(0).get("MODEL_TYPE"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return config_list;
	}
	public int loginCheck(String userId,String passWD){
		int result=0;
		String passWord="";
		UserEntity user=new UserEntity();
		String sql = "select * from user_info where userId='"+userId+"'";
        Connection conn=JDBC.getConn();
        PreparedStatement pstmt;
        ResultSet rs;
        
        try {
            pstmt = (PreparedStatement)conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            
            int col = rs.getMetaData().getColumnCount();
            System.out.println("============================");
            ResultSetMetaData md = rs.getMetaData();
            int columnCount = md.getColumnCount(); 
            while (rs.next()) {
            	Map<String,Object> rowData = new HashMap<String,Object>();
    			for (int i = 1; i <= columnCount; i++) {
    				rowData.put(md.getColumnName(i), rs.getObject(i));
    			}
    			passWord=String.valueOf(rowData.get("passWd"));
    			if(passWord==null) passWord="";
    			if(passWord.equals(passWD)){
    				result=1;
    			}
    			
    			//ProcessEntity proccess=new ProcessEntity();
    			//proccess.setProcessNo(String.valueOf(rowData.get("MODEL_ID")));
    			//proccess.setProcessName(String.valueOf(rowData.get("MODEL_NAME")));
            }
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return result;
	}
}
