package cn.itsource.service.system.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itsource.dao.system.IAddressDao;
import cn.itsource.domain.Address;
import cn.itsource.service.sysytem.IAddressService;

@Service
public class AddressServiceImpl implements IAddressService {
	@Autowired
	private IAddressDao dao;
	@Override
	public List<Address> list() {
		return dao.list();
	}

}
