package com.petex.rest;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lowagie.text.DocumentException;
import com.petex.entity.HomeVisitEntity;
import com.petex.service.HomeVisitService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/petex")
public class HomeVisitRestController {

	@Autowired
	private HomeVisitService service;

	@PostMapping("/save/{userId}")
	public ResponseEntity<String> saveHome(@RequestBody HomeVisitEntity entity,@PathVariable Long userId) throws DocumentException, IOException {
		Boolean status = service.save(entity, userId);
		if (status) {
			return new ResponseEntity<String>("Home data save successfully", HttpStatus.CREATED);
		}
		return new ResponseEntity<String>("Home data not save successfully", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@GetMapping("/get/{customerId}")
	public ResponseEntity<HomeVisitEntity> getHomeDataById(@PathVariable Integer customerId) {
		HomeVisitEntity homeVistById = service.getHomeVistById(customerId);
		return new ResponseEntity<HomeVisitEntity>(homeVistById, HttpStatus.OK);
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<HomeVisitEntity>> getAllHomeData(){
		List<HomeVisitEntity> allHomeVist = service.getAllHomeVist();
		return new ResponseEntity<List<HomeVisitEntity>>(allHomeVist,HttpStatus.OK);
	}
	
	@PutMapping("/update/{customerId}")
	public ResponseEntity<String> upadteHomeVisit(@PathVariable Integer customerId, @RequestBody HomeVisitEntity entity){
		Boolean status = service.updateHomeVistData(customerId, entity);
		if (status) {
			return new ResponseEntity<String>("Home data Edit successfully",HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<String>("Home data Edit not successfully",HttpStatus.ACCEPTED);
		
	}
	
	@DeleteMapping("/delete/{customerId}")
	public ResponseEntity<String> deleteById(@PathVariable Integer customerId ){
		String deleteStatus = service.deleteHomeVistById(customerId);
		
		if (deleteStatus.equals("deleted")) {
			 return new ResponseEntity<>("Home Visit deleted successfully", HttpStatus.OK);
		}
		return new ResponseEntity<>("Home Visit not deleted  successfully", HttpStatus.NO_CONTENT);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
