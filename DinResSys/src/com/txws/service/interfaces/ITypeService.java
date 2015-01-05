package com.txws.service.interfaces;

import java.util.List;

import com.txws.model.TypeTable;

public interface ITypeService {
	void addType(TypeTable type);
	List<TypeTable> getAllType();
}
