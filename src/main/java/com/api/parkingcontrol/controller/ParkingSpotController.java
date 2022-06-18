package com.api.parkingcontrol.controller;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
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

import com.api.parkingcontrol.dto.ParkingSpotDto;
import com.api.parkingcontrol.models.ParkSpotModel;
import com.api.parkingcontrol.services.ParkingSpotService;

@RestController
@CrossOrigin(originPatterns = "*", maxAge = 3600)
@RequestMapping("/parking-spot")
public class ParkingSpotController {

	private ParkingSpotService parkingSpotService;
	
	/**
	 * ponto de injeção via construtor
	 * @param parkingSpotService
	 */
	public ParkingSpotController(ParkingSpotService parkingSpotService) {
		this.parkingSpotService = parkingSpotService;
	}
	
	@PostMapping
	public ResponseEntity<Object> save(
			@RequestBody 
			@Valid
			ParkingSpotDto parkingSpotDto){
		
		if (parkingSpotService.existsByLicensePlateCar(parkingSpotDto.getLicensePlateCar())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflito: Carro de matrícula " + parkingSpotDto.getLicensePlateCar() + " já está em uso!");
		}
		
		if (parkingSpotService.existsByParkingStopNumber(parkingSpotDto.getParkingStopNumber())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflito: Estacionamento " + parkingSpotDto.getParkingStopNumber() + " já está em uso!");
		}
		
		if (parkingSpotService.existsByApartmentAndBlock(parkingSpotDto.getApartment(), parkingSpotDto.getBlock())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflito: Vaga de estacionamento já estar registrado para o apartamento: " +parkingSpotDto.getApartment()+ " bloco: " +parkingSpotDto.getBlock()+ "!");
		}
		
		ParkSpotModel parkingSpotModel = new ParkSpotModel();
		BeanUtils.copyProperties(parkingSpotDto, parkingSpotModel);
		parkingSpotModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
		return ResponseEntity.status(HttpStatus.CREATED).body(parkingSpotService.save(parkingSpotModel));
	}
	
	@GetMapping
	public ResponseEntity<Page<ParkSpotModel>> getAllParkingSpots(
			@PageableDefault(page = 0, size = 10, sort = "id", direction = Direction.ASC) Pageable pageable){
		
		return ResponseEntity.status(HttpStatus.OK).body(parkingSpotService.findAll(pageable));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getOneParkingSpot(@PathVariable(value = "id") Long id){
		Optional<ParkSpotModel> optional = parkingSpotService.findBayId(id);
		
		if (!optional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Informe: Vaga de estacionamento não existe!");
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(optional.get());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteParkingSpot(@PathVariable(value = "id") Long id){
		Optional<ParkSpotModel> optional = parkingSpotService.findBayId(id);
		
		if (!optional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Informe: Vaga de estacionamento não existe!");
		}
		parkingSpotService.delete(optional.get());
		return ResponseEntity.status(HttpStatus.OK).body("Vaga de estacionamento removido com sucesso!");
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> updateParkingSpot(
			@PathVariable(value = "id") Long id, 
			@RequestBody 
			@Valid
			ParkingSpotDto parkingSpotDto){
		
		Optional<ParkSpotModel> optional = parkingSpotService.findBayId(id);
		if (!optional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Informe: Vaga de estacionamento não existe!");
		}
		
		var parkSpotModel = new ParkSpotModel();//realiza das duas maneiras
//		var parkSpotModel = optional.get();
//		parkSpotModel.setParkingStopNumber(parkingSpotDto.getParkingStopNumber());
//		parkSpotModel.setLicensePlateCar(parkingSpotDto.getLicensePlateCar());
//		parkSpotModel.setBrandCar(parkingSpotDto.getBrandCar());
//		parkSpotModel.setModelCar(parkingSpotDto.getModelCar());
//		parkSpotModel.setColorCar(parkingSpotDto.getColorCar());
//		parkSpotModel.setResponsibleName(parkingSpotDto.getResponsibleName());
//		parkSpotModel.setApartment(parkingSpotDto.getApartment());
//		parkSpotModel.setBlock(parkingSpotDto.getBlock());
		
		//segunda maneira de faer a atualização
		BeanUtils.copyProperties(parkingSpotDto, parkSpotModel);
		parkSpotModel.setId(optional.get().getId());
		parkSpotModel.setRegistrationDate(optional.get().getRegistrationDate());
		return ResponseEntity.status(HttpStatus.OK).body(parkingSpotService.save(parkSpotModel));
	}
}
