package cn.itsource.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itsource.dao.ICityDao;
import cn.itsource.domain.City;
import cn.itsource.service.ICityService;

@Service
public class CityServiceImpl implements ICityService {
	
	@Autowired
	private ICityDao dao;
	@Override
	public List<City> list() {

		return dao.list();
	}

}
