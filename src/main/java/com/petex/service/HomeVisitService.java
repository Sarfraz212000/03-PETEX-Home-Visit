package com.petex.service;

import java.util.List;

import com.petex.entity.HomeVisitEntity;

public interface HomeVisitService {
	
	public Boolean save(HomeVisitEntity entity);

	public List<HomeVisitEntity> getAllHomeVist();

	public String deleteHomeVistById(Integer customerId);

	public HomeVisitEntity getHomeVistById(Integer customerId);

	public Boolean updateHomeVistData(Integer customerId, HomeVisitEntity entity);

}
