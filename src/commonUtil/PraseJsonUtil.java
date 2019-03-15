package commonUtil;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import entity.ZjjjEntity;

public class PraseJsonUtil {
	public static List getListBysDate(String sdate){
		List dataList=new ArrayList();
		JSONArray jsonArr = JSONArray.parseArray(sdate);
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
		return dataList;
	}
}
