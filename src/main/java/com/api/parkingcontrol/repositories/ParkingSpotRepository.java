package com.api.parkingcontrol.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.parkingcontrol.models.ParkSpotModel;

@Repository//isso é opcional
public interface ParkingSpotRepository extends JpaRepository<ParkSpotModel, Long> {
	
	boolean existsByLicensePlateCar(String licensePlateCar);
	
	boolean existsByParkingStopNumber(String parkingStopNumber);
	
	boolean existsByApartmentAndBlock(String apartment, String block);

}
