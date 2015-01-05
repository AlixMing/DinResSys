package com.txws.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.txws.dao.interfaces.ICommonDAO;
import com.txws.model.AddressTable;
import com.txws.service.interfaces.IAddressService;
@Service("addressService")
public class AddressServiceImpl implements IAddressService {

	@Resource(name = "commonDAO")
	private ICommonDAO commonDAO;
	
	@Override
	public void addAddress(AddressTable addressTable){
		List<AddressTable> addressTables = commonDAO.getObjectsByKey(AddressTable.class, "userId", String.valueOf(addressTable.getUserTable().getId()));
		for (AddressTable addressTable2 : addressTables) {
			if(addressTable.getAd().equals(addressTable2.getAd())){
				return ;
			}
		}
		commonDAO.save(addressTable);
	}

	@Override
	public void setDefaultAddress(AddressTable addressTable, int userId) {
		
		commonDAO.update(addressTable);
		List<AddressTable> addressList = commonDAO.getObjectsByKey(AddressTable.class, "userTable", String.valueOf(userId));
		for (AddressTable addressTable2 : addressList) {
			if(addressTable2.getIsDefault() == 1) {
				if(addressTable2.getAd().equals(addressTable.getAd()))
					continue;
				else {
					addressTable2.setIsDefault(0);
					commonDAO.update(addressTable2);
				}
			}
		}
	}

	@Override
	public List<AddressTable> getAddressTablesByUser(int userId) {
		List<AddressTable> list = new ArrayList<>();
		list = commonDAO.getObjectsByKey(AddressTable.class, "userId", String.valueOf(userId));
		return list;
	}

	@Override
	public AddressTable loadAddress(int id) {
		AddressTable addressTable = commonDAO.getObject(AddressTable.class, id);
		return addressTable;
	}
	
	@Override
	public AddressTable loadAddressByAddressName(String name) {
		List<AddressTable> addressTables = commonDAO.getObjectsByKey(AddressTable.class, "ad", name);
		return addressTables.get(0);
	}
	
}
