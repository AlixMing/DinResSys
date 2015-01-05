package com.txws.service.interfaces;

import java.util.List;

import com.txws.model.AddressTable;

public interface IAddressService {
	void addAddress(AddressTable addressTable);
	void setDefaultAddress(AddressTable addressTable, int userId);
	List<AddressTable> getAddressTablesByUser(int userId);
	AddressTable loadAddress(int id);
	AddressTable loadAddressByAddressName(String name);
}
