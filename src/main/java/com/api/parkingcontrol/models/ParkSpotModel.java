package com.api.parkingcontrol.models;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TB_PARKING_SPORT")
public class ParkSpotModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false, unique = true, length = 10)
	private String parkingStopNumber;
	@Column(nullable = false, unique = true, length = 7)
	private String licensePlateCar;// placa
	@Column(nullable = false, length = 70)
	private String brandCar;
	@Column(nullable = false, length = 70)
	private String modelCar;// modelo do veículo
	@Column(nullable = false, length = 70)
	private String colorCar;// cor do veículo
	@Column(nullable = false)
	private LocalDateTime registrationDate;// data do registro
	@Column(nullable = false, length = 130)
	private String responsibleName;// nome do responsável
	@Column(nullable = false, length = 30)
	private String apartment;// apartamento
	@Column(nullable = false, length = 30)
	private String block;// bloco do prédio

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getParkingStopNumber() {
		return parkingStopNumber;
	}

	public void setParkingStopNumber(String parkingStopNumber) {
		this.parkingStopNumber = parkingStopNumber;
	}

	public String getLicensePlateCar() {
		return licensePlateCar;
	}

	public void setLicensePlateCar(String licensePlateCar) {
		this.licensePlateCar = licensePlateCar;
	}

	public String getBrandCar() {
		return brandCar;
	}

	public void setBrandCar(String brandCar) {
		this.brandCar = brandCar;
	}

	public String getModelCar() {
		return modelCar;
	}

	public void setModelCar(String modelCar) {
		this.modelCar = modelCar;
	}

	public String getColorCar() {
		return colorCar;
	}

	public void setColorCar(String colorCar) {
		this.colorCar = colorCar;
	}

	public LocalDateTime getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(LocalDateTime registrationDate) {
		this.registrationDate = registrationDate;
	}

	public String getResponsibleName() {
		return responsibleName;
	}

	public void setResponsibleName(String responsibleName) {
		this.responsibleName = responsibleName;
	}

	public String getApartment() {
		return apartment;
	}

	public void setApartment(String apartment) {
		this.apartment = apartment;
	}

	public String getBlock() {
		return block;
	}

	public void setBlock(String block) {
		this.block = block;
	}

}
