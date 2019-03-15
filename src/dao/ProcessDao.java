package dao;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import commonUtil.JDBC;
import entity.ProcessEntity;
/**
 * 持久层，数据操作
 * @author Administrator
 *
 */
@Repository
public class ProcessDao {
	/*
	 * 获取系统中所有流程
	 */
	public List<ProcessEntity> getProcessList(String userId){
		//String sql = "select * from ACTIVITYS_CONFIG";
		String sql = "select * from ACTIVITYS_CONFIG a,user_info b where a.MODEL_ID=b.processNo and flag='1' and b.USERID='"+userId+"'";
		if("lixuem".equals(userId)){
			 sql = "select * from ACTIVITYS_CONFIG where flag='1'";
		}
        Connection conn=JDBC.getConn();
        PreparedStatement pstmt;
        ResultSet rs;
        ArrayList<ProcessEntity> config_list=new ArrayList();
        
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
    			ProcessEntity proccess=new ProcessEntity();
    			proccess.setProcessNo(String.valueOf(rowData.get("MODEL_ID")));
    			proccess.setProcessName(String.valueOf(rowData.get("MODEL_NAME")));
    			config_list.add(proccess);
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
}
