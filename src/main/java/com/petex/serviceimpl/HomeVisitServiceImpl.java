package com.petex.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petex.entity.HomeVisitEntity;
import com.petex.repo.HomeVisitRepo;
import com.petex.service.HomeVisitService;
@Service
public class HomeVisitServiceImpl implements HomeVisitService {
	
	@Autowired
	private HomeVisitRepo repo;

	@Override
	public Boolean save(HomeVisitEntity entity) {
		repo.save(entity);
		return true;
	}

	@Override
	public List<HomeVisitEntity> getAllHomeVist() {
	
		return repo.findAll();
	}

	@Override
	public String deleteHomeVistById(Integer customerId) {
		repo.deleteById(customerId);
		return "deleted";
	}

	@Override
	public HomeVisitEntity getHomeVistById(Integer customerId) {
		Optional<HomeVisitEntity> findById = repo.findById(customerId);
		if (findById.isPresent()) {
			return findById.get();
		}
		return null;
	}

	@Override
	public Boolean updateHomeVistData(Integer customerId, HomeVisitEntity entity) {
		Optional<HomeVisitEntity> optinalId = repo.findById(customerId);
		if (optinalId.isPresent()) {
			HomeVisitEntity entites = optinalId.get();
			BeanUtils.copyProperties(entity, entites);
			entites.setCustomerId(customerId);
			repo.save(entites);
			return true;
		}
		return false;
	}

	
}
