package com.txws.service.impl;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.txws.dao.interfaces.ICommonDAO;
import com.txws.model.AppraiseTable;
import com.txws.model.AppraiseVO;
import com.txws.service.interfaces.IAppraiseService;


@Service("appraiseService")
public class AppraiseServiceImpl implements IAppraiseService {
	
	@Resource(name = "commonDAO")
	private ICommonDAO commonDAO;
	
	@Override
	public Map<String, Object> getAppraiseByMenuID(int menuId) {
		List<AppraiseTable> list = commonDAO.getObjectsByKey(AppraiseTable.class, "menuId", String.valueOf(menuId));
		Map<String, Object> map = new HashMap<String, Object>();
		int num = list.size();
		double[] percents = new double[]{0, 0, 0, 0, 0};
		for (AppraiseTable item : list) {
			switch (item.getPraiseLevel()) {
			case 5:
				percents[0]++;
				break;
			case 4:
				percents[1]++;
				break;
			case 3:
				percents[2]++;
				break;
			case 2:
				percents[3]++;
				break;
			case 1:
				percents[4]++;
				break;
			default:
				break;
			}
		}
		
		for (int i = 0; i < percents.length; i++) {
			percents[i] /= num;
		}
		
		double scope = 5 * percents[0] + 4 * percents[1] + 3 * percents[2] + 2 * percents[3] + 1 * percents[4];
		
		List<String> praiseLevels = new ArrayList<>(5);
		for (double d : percents) {
			praiseLevels.add(new DecimalFormat("0%").format(d));
		}
		
		List<AppraiseVO> resultList = new ArrayList<>();
		SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd");
		for (Iterator<AppraiseTable> it = list.iterator(); it.hasNext(); ) {
			AppraiseTable appraise = it.next();
			if (null != appraise.getDetail()) {
				AppraiseVO vo = new AppraiseVO();
				vo.setName(appraise.getUserTable().getName());
				vo.setMsg(appraise.getDetail());
				vo.setDate(df.format(appraise.getPraiseTime()));
				resultList.add(vo);
			}
		}
		
		map.put("num", num);
		map.put("scope", new DecimalFormat("0.0").format(scope));
		map.put("praiseLevels", praiseLevels);
		map.put("appraises", resultList);
		return map;
	}

	@Override
	public void addAppraise(AppraiseTable appraiseTable) {
		commonDAO.save(appraiseTable);
	}

	@Override
	public void deleteAppraiseByMenu(int menuId) {
		List<AppraiseTable> appraiseTables = commonDAO.getObjectsByKey(AppraiseTable.class, "menuId", String.valueOf(menuId));
		commonDAO.deleteAll(appraiseTables);
	}
}
