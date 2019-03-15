package dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import commonUtil.JDBC;
import commonUtil.UserOAMessage;
import entity.LockEntity;
import entity.UserEntity;
import entity.ZJXTEntity;
@Repository
public class LockDao {
	//将数据插入activitys_lock
	public void insertData(List list) throws Exception{
		Connection conn=JDBC.getConn();
		String insertSql="insert into activitys_lock(USERID,USERNAME,APPLYTITLE,SYSTEMNAME,SYSTEMACCOUNT,ACCOUNTGROUP,DEALINGPEOPLE,OPERATORING,LOCKREASON,STATU,UPTIME,PARTTIMEROLE) values(?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement pstmt=(PreparedStatement) conn.prepareStatement(insertSql);   ;
        for(int j=0;j<list.size();j++){
        	//ZjjjEntity jjEntity=(ZjjjEntity) list.get(j);
        	LockEntity lockEntity=(LockEntity) list.get(j);
        	pstmt.setString(1,lockEntity.getUserId());
        	pstmt.setString(2,lockEntity.getDealingPeople());
        	pstmt.setString(3,lockEntity.getApplyTitle() );
        	pstmt.setString(4,lockEntity.getSystemName() );
        	pstmt.setString(5,lockEntity.getSystemAccount() );
        	pstmt.setString(6,lockEntity.getAccountGroup() );
        	pstmt.setString(7,lockEntity.getDealingPeople() );
        	pstmt.setString(8,lockEntity.getOperatoring() );
        	pstmt.setString(9,lockEntity.getLockReason() );
        	pstmt.setString(10,"0");
        	pstmt.setString(11,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        	UserDao uDao=new UserDao();
        	pstmt.setString(12,uDao.getUserRoleId(lockEntity.getUserId()));
        	System.out.println("PARTTIMEROLE+++"+uDao.getUserRoleId(lockEntity.getUserId()));
        	//System.out.println("jjEntity.getHANDOVERPERSON()++++"+UserOAMessage.getUserId(jjEntity.getHANDOVERPERSON()));
        	//pstmt.setString(8, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        	pstmt.addBatch();  
        }
        pstmt.executeBatch(); 
        
		//return 0;
		
	}
	//将数据插入activitys_zjxt
	public void insertZJXTData(List list) throws Exception{
		Connection conn=JDBC.getConn();
		String insertSql="insert into activitys_zjxt(USERID,USERNAME,APPLYTITLE,"+
                "APPLICATIONNAME,APPLICATIONOA,SYSTEMNAME,"+
                "APPLICATIONDEPARTMENT,PHONE,CRMACCOUNT,"+
                "DESCRIPTION,APPLICATIONINSTRUCTION,STATU,UPTIME,PARTTIMEROLE,COUNTRY) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement pstmt=(PreparedStatement) conn.prepareStatement(insertSql);   ;
        for(int j=0;j<list.size();j++){
        	//ZjjjEntity jjEntity=(ZjjjEntity) list.get(j);
        	//LockEntity lockEntity=(LockEntity) list.get(j);
        	ZJXTEntity entuty=(ZJXTEntity) list.get(j);
        	pstmt.setString(1,entuty.getUSERID());
        	pstmt.setString(2,entuty.getUSERNAME());
        	pstmt.setString(3,entuty.getAPPLYTITLE() );
        	pstmt.setString(4,entuty.getAPPLICATIONNAME() );
        	pstmt.setString(5,entuty.getAPPLICATIONOA() );
        	pstmt.setString(6,entuty.getSYSTEMNAME() );
        	pstmt.setString(7,entuty.getAPPLICATIONDEPARTMENT() );
        	pstmt.setString(8,entuty.getPHONE() );
        	pstmt.setString(9,entuty.getCRMACCOUNT() );
        	pstmt.setString(10,entuty.getDESCRIPTION());
        	pstmt.setString(11,entuty.getAPPLICATIONINSTRUCTION());
        	pstmt.setString(12,"0");
        	pstmt.setString(13,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        	UserDao uDao=new UserDao();
        	pstmt.setString(14,uDao.getUserRoleId(entuty.getUSERID()));
        	pstmt.setString(15,"市场经营部");
        	//System.out.println("jjEntity.getHANDOVERPERSON()++++"+UserOAMessage.getUserId(jjEntity.getHANDOVERPERSON()));
        	//pstmt.setString(8, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        	pstmt.addBatch();  
        }
        pstmt.executeBatch(); 
        
		//return 0;
		
	}
	
}
