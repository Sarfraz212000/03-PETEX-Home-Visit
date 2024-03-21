package com.petex.serviceimpl;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lowagie.text.DocumentException;
import com.petex.entity.HomeVisitEntity;
import com.petex.entity.UserEntity;
import com.petex.repo.HomeVisitRepo;
import com.petex.repo.UserRepo;
import com.petex.service.HomeVisitService;
import com.petex.utils.EmailUtils;
import com.petex.utils.PdfGenerator;
@Service
public class HomeVisitServiceImpl implements HomeVisitService {
	
	@Autowired
	private HomeVisitRepo repo;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private EmailUtils emailUtils;
	
	@Autowired
	private PdfGenerator pdfGenerator;
	
	

	@Override
	public Boolean save(HomeVisitEntity entity,Long userId) throws DocumentException, IOException {
		
		Optional<UserEntity> optinalId = userRepo.findById(userId);
		if (optinalId.isPresent()) {
			UserEntity user = optinalId.get();
			
			entity.setUser(user);
			HomeVisitEntity save = repo.save(entity);
			
			File f= new File("HomeVisitReport.pdf");
			
			
			pdfGenerator.generate(save, f);
			
			String subject = "HomeVist Booking Report";
			String body = "HomeVist";
			String to = user.getEmail();

			emailUtils.sendEmail(subject, body, to, f);
			f.delete();
			
			return true;
		}
		
		return false;
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
