package com.txws.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.txws.dao.interfaces.ICommonDAO;
import com.txws.model.TypeTable;
import com.txws.service.interfaces.ITypeService;
@Service("typeService")

public class TypeServiceImpl implements ITypeService {

	@Resource(name = "commonDAO")
	private ICommonDAO commonDAO;
	
	@Override
	public void addType(TypeTable type) {
		commonDAO.save(type);
	}

	@Override
	public List<TypeTable> getAllType() {
		return commonDAO.getAllObjects(TypeTable.class);
	}
}
