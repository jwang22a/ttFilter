package dao;

import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import commonUtil.JDBC;
import entity.UserEntity;

public class PrepareedDao {
	public static void main(String[] args) {
		Connection conn=JDBC.getConn();
        PreparedStatement pstmt;
        try{
        	
        	pstmt = (PreparedStatement)conn.prepareStatement("delete from  ACTIVITYS_ZJJJ  where STATU=0"  );      
        	//pstmt.setString(1, id);
        	//pstmt.setInternal(1, id);
        	int indexs1=pstmt.executeUpdate();
        	//pstmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
	public static void updateZJJJ(){
		Connection conn=JDBC.getConn();
        PreparedStatement pstmt;
        try{
        	
        	pstmt = (PreparedStatement)conn.prepareStatement("delete from  ACTIVITYS_ZJJJ  where STATU=0"  );      
        	//pstmt.setString(1, id);
        	//pstmt.setInternal(1, id);
        	int indexs1=pstmt.executeUpdate();
        	//pstmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
	public static void prepareProcess(String processNo){
		Connection conn=JDBC.getConn();
        PreparedStatement pstmt;
        try{
        	pstmt = (PreparedStatement)conn.prepareStatement("update ACTIVITYS_CONFIG set statu=0");      
        	//pstmt.setString(1, id);
        	//pstmt.setInternal(1, id);
        	int indexs=pstmt.executeUpdate();
        	//pstmt.executeUpdate(sql);
        	pstmt = (PreparedStatement)conn.prepareStatement("update ACTIVITYS_CONFIG set statu=1 where model_id='"+processNo+"'"  );      
        	//pstmt.setString(1, id);
        	//pstmt.setInternal(1, id);
        	int indexs1=pstmt.executeUpdate();
        	//pstmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
	//
	public static void updateActivityApp(UserEntity user,String model_id){
		Connection conn=JDBC.getConn();
        PreparedStatement pstmt;
        try{
        	pstmt = (PreparedStatement)conn.prepareStatement("update ACTIVITYS_APPLICANT SET APPLICANT='"+user.getUserName()+"',applytelephone='"+user.getPhone()+"',parttimerole='"+user.getParttimeRole()+"',userId='"+user.getUserId()+"',username='"+user.getUserName()+"' where model_id='"+model_id+"' "  );      
        	int indexs1=pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
}
