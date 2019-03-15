package service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import dao.LockDao;
import entity.LockEntity;
import entity.ZJXTEntity;
@Service()
public class LockProcessServiceImp implements LockProcessService {
	@Autowired 
	LockDao lockDao;
	@Override
	public List<LockEntity> getLockList(String sData) {
		// TODO Auto-generated method stub
		   List dataList=new ArrayList();
		   JSONArray jsonArr = JSONArray.parseArray(sData);
           for (int i = 0; i < jsonArr.size(); i++) {  
	            JSONObject jo = jsonArr.getJSONObject(i);
	            LockEntity lockEntity = new LockEntity();
	            lockEntity.setApplyTitle(jo.getString("applyTitle"));
	            lockEntity.setAccountGroup(jo.getString("accountGroup"));
	            lockEntity.setDealingPeople(jo.getString("dealingPeople"));
	            lockEntity.setLockReason(jo.getString("lockReason"));
	            lockEntity.setOperatoring(jo.getString("operatoring"));
	            lockEntity.setSystemAccount(jo.getString("systemAccount"));
	            lockEntity.setSystemName(jo.getString("systemName"));
	            lockEntity.setUserId(jo.getString("userId"));
	            dataList.add(lockEntity); 
           }
           return dataList;
	}

	@Override
	public void insertsData(List list)  {
		// TODO Auto-generated method stub
		try {
			lockDao.insertData(list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	//将数据插入自建系统数据表
	@Override
	public void insertZJXTData(List list) {
		// TODO Auto-generated method stub
		try {
			lockDao.insertZJXTData(list);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	//将数据转换为自建系统list
	@Override
	public List<ZJXTEntity> getZJXTList(String sData) {
		// TODO Auto-generated method stub
		 List dataList=new ArrayList();
		   JSONArray jsonArr = JSONArray.parseArray(sData);
         for (int i = 0; i < jsonArr.size(); i++) {  
	            JSONObject jo = jsonArr.getJSONObject(i);
	            //LockEntity lockEntity = new LockEntity();
	            ZJXTEntity entity = new ZJXTEntity();
	            entity.setAPPLYTITLE(jo.getString("APPLYTITLE"));
	            entity.setAPPLICATIONDEPARTMENT(jo.getString("APPLICATIONDEPARTMENT"));
	            entity.setAPPLICATIONINSTRUCTION(jo.getString("APPLICATIONINSTRUCTION"));
	            entity.setAPPLICATIONNAME(jo.getString("APPLICATIONNAME"));
	            entity.setAPPLICATIONOA(jo.getString("APPLICATIONOA"));
	            entity.setCRMACCOUNT(jo.getString("CRMACCOUNT"));
	            entity.setDESCRIPTION(jo.getString("DESCRIPTION"));
	            entity.setPHONE(jo.getString("PHONE"));
	            entity.setSYSTEMNAME(jo.getString("SYSTEMNAME"));
	            entity.setUSERID(jo.getString("USERID"));
	            entity.setUSERNAME(jo.getString("USERNAME"));
	            dataList.add(entity); 
         }
         return dataList;
		
	}

}
