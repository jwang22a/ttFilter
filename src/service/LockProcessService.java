package service;

import java.util.List;

import entity.LockEntity;
import entity.ZJXTEntity;

/**
 * �쳣�˺żӽ��������ӿ�
 * @author Administrator
 *
 */
public interface LockProcessService {
	//�쳣�˺żӽ���
	public List<LockEntity> getLockList(String sData);
	public List<ZJXTEntity> getZJXTList(String sData);
	public void insertsData(List list);
	public void insertZJXTData(List list);
}
