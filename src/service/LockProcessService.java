package service;

import java.util.List;

import entity.LockEntity;
import entity.ZJXTEntity;

/**
 * 异常账号加解锁服务层接口
 * @author Administrator
 *
 */
public interface LockProcessService {
	//异常账号加解锁
	public List<LockEntity> getLockList(String sData);
	public List<ZJXTEntity> getZJXTList(String sData);
	public void insertsData(List list);
	public void insertZJXTData(List list);
}
