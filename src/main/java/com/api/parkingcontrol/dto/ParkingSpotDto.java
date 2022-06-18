package com.api.parkingcontrol.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


public class ParkingSpotDto {

	@NotBlank
	private String parkingStopNumber;
	@NotBlank
	@Size(max = 7)
	private String licensePlateCar;// placa
	@NotBlank
	private String brandCar;
	@NotBlank
	private String modelCar;// modelo do veículo
	@NotBlank
	private String colorCar;// cor do veículo
	@NotBlank
	private String responsibleName;// nome do responsável
	@NotBlank
	private String apartment;// apartamento
	@NotBlank
	private String block;// bloco do prédio

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
