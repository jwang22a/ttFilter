package service;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.springframework.util.StringUtils;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import commonUtil.HttpUtil;
import commonUtil.JDBC;
import entity.ProcessEntity;

public class testService {
	public static void main(String[] args) {
		testService.createProcess("ZJJJ");
		
	}
	public static String createProcess(String modelid){
		
		//String modelid="LocalAcctAbnormalOpeAudit";
		String sql = "select * from ACTIVITYS_CONFIG  where STATU=1 limit 1";
        Connection conn=JDBC.getConn();
        PreparedStatement pstmt;
        ResultSet rs;
        ArrayList<Map> config_list=new ArrayList();
        try {
            pstmt = (PreparedStatement)conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            
            int col = rs.getMetaData().getColumnCount();
            System.out.println("============================");
            ResultSetMetaData md = rs.getMetaData();
            int columnCount = md.getColumnCount(); 
            
            while (rs.next()) {
               /* for (int i = 1; i <= col; i++) {
                    System.out.print(rs.getString(i) + "\t");
                    if ((i == 2) && (rs.getString(i).length() < 8)) {
                        System.out.print("\t");
                    }
                 }
                System.out.println("");*/
            	Map<String,Object> rowData = new HashMap<String,Object>();
            	
            	
    			for (int i = 1; i <= columnCount; i++) {
    				rowData.put(md.getColumnName(i), rs.getObject(i));
    			}
    			config_list.add(rowData);
            }
                System.out.println("============================"+config_list.toString());
               // System.out.println("============================"+config_list.get(0).get("MODEL_TYPE"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String model_type = String.valueOf(config_list.get(0).get("MODEL_TYPE"));
        String model_id = String.valueOf(config_list.get(0).get("MODEL_ID"));
        //sql = "select t.*,TO_CHAR(sysdate,'yyyy-MM-dd') t_date from (select * from ACTIVITYS_APPLICANT where STATU=1) t where rownum=1   ";
        sql="select t.*,current_date  AS  t_date from ACTIVITYS_APPLICANT t where STATU=1 limit 1";
        if("1".equals(model_type)){
        	//sql = "select t.*,TO_CHAR(sysdate,'yyyy-MM-dd') t_date from (select * from ACTIVITYS_APPLICANT where STATU=1  and model_id=?) t where rownum=1  ";
        	sql = "select t.*,current_date  AS  t_date from ACTIVITYS_APPLICANT t where STATU=1  and model_id=? limit 1";
        }
        List<Map> appliant_list=new ArrayList<>();
        try {
            pstmt = (PreparedStatement)conn.prepareStatement(sql);
            if("1".equals(model_type)){
            	pstmt.setString(1, modelid);
            }
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
				appliant_list.add(rowData);
				System.out.println("appliant_list+++++++++"+appliant_list.toString());
				
            }
            if(appliant_list.size()<=0) {
                Map<String, String> reMsg = new HashMap<String, String>();
                reMsg.put("msg", "鎵句笉鍒拌璺戠殑娴佺▼锛�");
                reMsg.put("code", "-1");
                String message= JSON.toJSONString(reMsg);
                //return "jiekou";
                //return message;

            }
            else{
            	System.out.println("============================"+appliant_list.toString());
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String target_table = String.valueOf(config_list.get(0).get("TARGET_TABLE"));
        System.out.println("target_table============================"+target_table);
        String model_url = String.valueOf(config_list.get(0).get("MODEL_URL"));
        System.out.println("model_url===="+model_url);
        if(StringUtils.isEmpty(target_table)||"null".equals(target_table)){
            Map<String, String> reMsg = new HashMap<String, String>();
            reMsg.put("msg", "鎵句笉鍒板搴旂殑琛紒");
            reMsg.put("code", "-1");
            String message= JSON.toJSONString(reMsg);
            //return "jiekou";
        }
        //paras.clear();
       //sql = "select * from(select * from "+target_table+" where STATU=0  order by dbms_random.value) where rownum<630000  ";
        sql = "select * from "+target_table+" where STATU=0  limit 63000";
        //System.out.println(sql);
        //List<Map> data_list = baseService.queryBaseTOAllSQLMap(sql, paras);
        List<Map> data_list = new ArrayList();
        try {
            pstmt = (PreparedStatement)conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            
            int col = rs.getMetaData().getColumnCount();
            System.out.println("target_table============================"+target_table);
            ResultSetMetaData md = rs.getMetaData();
            int columnCount = md.getColumnCount(); 
            
            while (rs.next()) {
               /* for (int i = 1; i <= col; i++) {
                    System.out.print(rs.getString(i) + "\t");
                    if ((i == 2) && (rs.getString(i).length() < 8)) {
                        System.out.print("\t");
                    }
                 }
                System.out.println("");*/
            	Map<String,Object> rowData = new HashMap<String,Object>();
            	
            	
    			for (int i = 1; i <= columnCount; i++) {
    				rowData.put(md.getColumnName(i), rs.getObject(i));
    			}
    			data_list.add(rowData);
            }
                System.out.println("============================3");
                System.out.println("============================3");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(data_list.size()<=0) {
            //鏇存柊鏁版嵁琛�
            //int ii =  baseService.updateSQL("update ACTIVITYS_CONFIG set STATU=0 where target_table='"+target_table+"'"   );
            try {
                pstmt = (PreparedStatement)conn.prepareStatement("update ACTIVITYS_CONFIG set STATU=0 where target_table='"+target_table+"'");;
                int indexs=pstmt.executeUpdate();
                System.out.println("indexs============================"+indexs);
                System.out.println("============================4");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        	
        	
            Map<String, String> reMsg = new HashMap<String, String>();
            reMsg.put("msg", "鎵句笉鍒版暟鎹紒");
            reMsg.put("code", "-1");
            String message= JSON.toJSONString(reMsg);
            //return "jiekou";


        }
        JSONObject data_map = new JSONObject();
        data_map.put("templateId",String.valueOf(config_list.get(0).get("MODEL_ID")));//娴佺▼缂栧彿
        data_map.put("additionFlag",String.valueOf(config_list.get(0).get("ADDITIONFLAG")));

        //鎸囧畾鑺傜偣璧疯崏宸ュ崟
        //Map startNode_map = new HashMap();
        JSONObject startNode_map = new JSONObject();
        startNode_map.put("notion",String.valueOf(config_list.get(0).get("NOTION")));//
        startNode_map.put("option",String.valueOf(config_list.get(0).get("OPTIOM")));//
        startNode_map.put("nodeId",String.valueOf(config_list.get(0).get("NODEID")));//


        //鎻愯捣浜�
        //Map Handlers_map = new HashMap();
        JSONObject Handlers_map = new JSONObject();
        Handlers_map.put("parttimeRole",String.valueOf(appliant_list.get(0).get("PARTTIMEROLE")));//
        Handlers_map.put("userId",String.valueOf(appliant_list.get(0).get("USERID")));//
        Handlers_map.put("userName",String.valueOf(appliant_list.get(0).get("USERNAME")));//
        startNode_map.put("handler",Handlers_map);//
        data_map.put("startNode",startNode_map);
        
        int k1=data_list.size();
        System.out.println("k1++++++++++++"+k1);
        int k2=0;

        String add_cloum = String.valueOf(config_list.get(0).get("ADD_CLOUM"));
        String table_cloum = String.valueOf(config_list.get(0).get("TABLE_CLOUM"));
        String[] add_cloums = add_cloum.split(",");
        String[] table_cloums = table_cloum.split(",");
        if(table_cloums.length<=0||add_cloums.length!=table_cloums.length){
            Map<String, String> reMsg = new HashMap<String, String>();
            reMsg.put("msg", "璁剧疆鐨勫垪涓嶅锛�");
            reMsg.put("code", "-1");
            String message= JSON.toJSONString(reMsg);
            //return "jiekou";

        }

        Random rand = new Random();

        //閬嶅巻鏁版嵁缁勮
        for (int j=0;j<data_list.size();j++){


            try {
                Thread.sleep(rand.nextInt(500)); //1000 姣锛屼篃灏辨槸1绉�. 闅忔満鐢熸垚
            } catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }

            Map data_s = data_list.get(j);

            //涓嬩竴涓鐞嗕汉
            ///Map nextHandlers_map = new HashMap();
            JSONObject nextHandlers_map = new JSONObject();
            nextHandlers_map.put("parttimeRole",String.valueOf(data_s.get("PARTTIMEROLE")));//
            nextHandlers_map.put("userId",String.valueOf(data_s.get("USERID")));
            nextHandlers_map.put("userName",String.valueOf(data_s.get("USERNAME")));
            JSONArray handlerss = new JSONArray();
            handlerss.add(0,nextHandlers_map);
            data_map.put("nextHandlers",handlerss);



            data_map.put("applyTitle", String.valueOf(data_list.get(j).get("APPLYTITLE")));
            JSONObject formData = new JSONObject();
            formData.put("applicateDepartment",String.valueOf(appliant_list.get(0).get("APPLICATEDEPARTMENT")));//
            formData.put("applyTelephone",String.valueOf(appliant_list.get(0).get("APPLYTELEPHONE")));//
            formData.put("applyCompany",String.valueOf(appliant_list.get(0).get("APPLYCOMPANY")));//
            formData.put("applicant",String.valueOf(appliant_list.get(0).get("APPLICANT")));//
            formData.put("applicateDate",String.valueOf(appliant_list.get(0).get("t_date")));//

            for (int s=1;s<table_cloums.length;s++){
                //浣跨敤鍔ㄦ�侀厤缃�
                formData.put(add_cloums[s],String.valueOf(data_s.get(table_cloums[s])));//
             }

            data_map.put("formData",formData);
            String data_json = data_map.toJSONString();
            System.out.println("paramjson====="+data_json);
            List<NameValuePair> params= new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("paramjson",data_map.toJSONString()));
            
            String app_return_str = HttpUtil.postUrl(model_url, HTTP.UTF_8,params,"");
            System.out.println("app_return_str+++++++++"+app_return_str);
            //String app_return_str="";
            String message = app_return_str;
            System.out.println(message);
            if((!"瓒呮椂".equals(app_return_str))&&!StringUtils.isEmpty(app_return_str)&&!StringUtils.isEmpty(app_return_str.trim())){
                JSONObject  obj = JSONObject.parseObject(app_return_str);
                String rest = String.valueOf(obj.get("result"));
                if("true".equals(rest)){
                    k2++;
                    //鏇存柊鐘舵��
                    String id  = String.valueOf(data_list.get(j).get("ID"));
                    //int ii =  baseService.updateSQL("update "+ target_table+" set uptime=sysdate,STATU=1 where id='"+id+"'"  );
                    try{
                    	pstmt = (PreparedStatement)conn.prepareStatement("update "+ target_table+" set uptime=current_date,STATU=1 where id='"+id+"'"  );      
                    	//pstmt.setString(1, id);
                    	//pstmt.setInternal(1, id);
                    	int indexs=pstmt.executeUpdate();
                    	//pstmt.executeUpdate(sql);
                    	System.out.println("indexs====================6");
                    	System.out.println("indexs===================="+indexs);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    
                    //System.out.println("杩斿洖鎴愬姛锛�");

                }
            }else{
                Map<String, String> reMsg = new HashMap<String, String>();
                reMsg.put("msg", "鏈嶅姟鍣ㄦ病杩斿洖鏁版嵁");
                reMsg.put("code", "-1");
                message= JSON.toJSONString(reMsg);
                //return "jiekou";
            }


        }


        Map<String, String> reMsg = new HashMap<String, String>();
        reMsg.put("msg", "鏇存柊鎬绘暟 "+k1+" ===== "+"鏇存柊瀹屾垚鎬绘暟 "+k2);
        reMsg.put("code", "1");
        String message= JSON.toJSONString(reMsg);
        System.out.println("message+++++++++++++++"+message);
        //return "jiekou";


        return "";
	}
}
