package com.sayu.cem_app_service.service;

import com.sayu.cem_app_service.dto.AppResponseDto;
import com.sayu.cem_app_service.entity.AppServiceEntity;

import java.util.List;
import java.util.Map;

public interface AppService {
    AppResponseDto createService(AppServiceEntity appServiceEntity);

    AppResponseDto getService(Map<String, String> serviceDataMap);

    List<AppResponseDto> getServices();

    AppResponseDto ping();

    AppResponseDto updateService(Long serviceId, AppServiceEntity appServiceEntity);

    void deleteService(Long altKey);
}
