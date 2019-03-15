package service;

import java.util.List;

import entity.ProcessEntity;

public interface ProcessListService {
	public List<ProcessEntity> getProcessList(String userId);
}
