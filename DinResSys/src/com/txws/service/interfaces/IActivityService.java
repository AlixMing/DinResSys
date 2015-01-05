package com.txws.service.interfaces;

import java.util.List;

import com.txws.model.ActivityTable;

public interface IActivityService {
	void addActivity(ActivityTable activityTable);
	void delActivity(int activityId);
	void updateActivity(ActivityTable activityTable);
	ActivityTable getActivity(int activityId);
	List<ActivityTable> loadAllActivity();
}
