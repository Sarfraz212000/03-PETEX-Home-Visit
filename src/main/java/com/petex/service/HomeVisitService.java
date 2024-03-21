package com.petex.service;

import java.io.IOException;
import java.util.List;

import com.lowagie.text.DocumentException;
import com.petex.entity.HomeVisitEntity;

public interface HomeVisitService {
	
	public Boolean save(HomeVisitEntity entity,Long userId) throws DocumentException, IOException;

	public List<HomeVisitEntity> getAllHomeVist();

	public String deleteHomeVistById(Integer customerId);

	public HomeVisitEntity getHomeVistById(Integer customerId);

	public Boolean updateHomeVistData(Integer customerId, HomeVisitEntity entity);


}
