package com.txws.service.interfaces;

import java.util.Map;

import com.txws.model.AppraiseTable;

public interface IAppraiseService {
	Map<String, Object> getAppraiseByMenuID(int menuId);
	void addAppraise(AppraiseTable appraiseTable);
	void deleteAppraiseByMenu(int menuId);
}
