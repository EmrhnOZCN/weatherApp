package com.weather.weatherApp.mappers;

import org.modelmapper.ModelMapper;

public interface IModelMappers {
	
	ModelMapper forResponse();
	
	ModelMapper forRequest();
	

}
