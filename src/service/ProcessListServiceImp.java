package service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.ProcessDao;
import entity.ProcessEntity;
@Service("eventsService")
public class ProcessListServiceImp implements ProcessListService{
	@Autowired
	ProcessDao processDao;
	@Override
	public List<ProcessEntity> getProcessList(String userId) {
		// TODO Auto-generated method stub
		List list = processDao.getProcessList(userId);
		return list;
	}
	
}
