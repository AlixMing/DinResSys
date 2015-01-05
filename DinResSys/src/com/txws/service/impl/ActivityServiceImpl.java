package com.txws.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.txws.dao.interfaces.ICommonDAO;
import com.txws.model.ActivityTable;
import com.txws.service.interfaces.IActivityService;

@Service("activityService")
public class ActivityServiceImpl implements IActivityService {

	@Resource(name = "commonDAO")
	private ICommonDAO commonDAO;
	
	@Override
	public void addActivity(ActivityTable activityTable) {
		commonDAO.save(activityTable);
	}

	@Override
	public void delActivity(int activityId) {
		ActivityTable activityTable = commonDAO.getObject(ActivityTable.class, activityId);
		commonDAO.delete(activityTable);
	}

	@Override
	public ActivityTable getActivity(int activityId) {
		return commonDAO.getObject(ActivityTable.class, activityId);
	}

	@Override
	public void updateActivity(ActivityTable activityTable) {
		commonDAO.update(activityTable);
	}

	@Override
	public List<ActivityTable> loadAllActivity() {
		List<ActivityTable> activityList = commonDAO.getAllObjects(ActivityTable.class);
		return activityList;
	}

}
