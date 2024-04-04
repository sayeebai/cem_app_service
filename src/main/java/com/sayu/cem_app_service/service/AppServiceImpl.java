package com.sayu.cem_app_service.service;

import com.sayu.cem_app_service.dto.AppResponseDto;
import com.sayu.cem_app_service.entity.AppServiceEntity;
import com.sayu.cem_app_service.repository.AppRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.slf4j.Logger;

@Service
public class AppServiceImpl implements AppService{
    private static final Logger logger = LoggerFactory.getLogger(AppServiceImpl.class);
    private final AppRepository appRepository;
    @Autowired
    public AppServiceImpl(AppRepository appRepository){
        this.appRepository = appRepository;
    }
    @Override
    public AppResponseDto createService(AppServiceEntity appServiceEntity) {
        AppResponseDto appResponseDto = new AppResponseDto();
        try{
            AppServiceEntity createdAppServiceEntity= appRepository.save(appServiceEntity);
            appResponseDto.setStatus("Susses");
            appResponseDto.setStatusCode("200");
            appResponseDto.setError(null);
            appResponseDto.setDto(createdAppServiceEntity);
        }catch(Exception e){
            logger.error("Unexpected error occurred: {}", e.getMessage());
            appResponseDto.setStatus("Failure");
            appResponseDto.setStatusCode("400");
            appResponseDto.setError(e.toString());
            appResponseDto.setDto(null);
        }
        return appResponseDto;
    }
    @Override
    public List<AppResponseDto> getServices() {
        //AppResponseDto appResponseDto = new AppResponseDto();
        List<AppResponseDto> responseDtoList = new ArrayList<>();
        try{
            List<AppServiceEntity> createdAppServiceEntity= appRepository.findAll();
            //mapping AppServiceEntity to Dto
            List<AppResponseDto> dtoList =
                    createdAppServiceEntity.stream().map(entity -> {
                        AppResponseDto appResponseDto = new AppResponseDto();
                        appResponseDto.setStatus("Susses");
                        appResponseDto.setStatusCode("200");
                        appResponseDto.setError(null);
                        appResponseDto.setDto(entity);
                        return appResponseDto;
                    }).toList();
            responseDtoList.addAll(dtoList);
        }catch(Exception e){
            AppResponseDto appResponseDto = new AppResponseDto();
            appResponseDto.setStatus("Failure");
            appResponseDto.setStatusCode("400");
            appResponseDto.setError(e.toString());
            appResponseDto.setDto(null);
            responseDtoList.add(appResponseDto);
        }
        return responseDtoList;
    }

    @Override
    public AppResponseDto ping() {
        AppResponseDto appResponseDto = new AppResponseDto();
        appResponseDto.setStatus("Success");
        appResponseDto.setStatusCode("200");
        appResponseDto.setError(null);
        appResponseDto.setDto("ok");
        return appResponseDto;
    }

    @Override
    public AppResponseDto updateService(Long altKey, AppServiceEntity appServiceEntity) {
        AppResponseDto appResponseDto = new AppResponseDto();
        Optional<AppServiceEntity> fetchedServiceEntity = appRepository.findById(altKey);
        if ( fetchedServiceEntity.isPresent()) {
            try {
                AppServiceEntity fetchedAppServiceEntity = fetchedServiceEntity.get();
                if(appServiceEntity.getServiceName()!=null && appServiceEntity.getServiceName().isEmpty())
                    fetchedAppServiceEntity.setServiceName(appServiceEntity.getServiceName());
                if(appServiceEntity.getServiceUrl()!=null && appServiceEntity.getServiceUrl().isEmpty())
                    fetchedAppServiceEntity.setServiceUrl(appServiceEntity.getServiceUrl());
                if(appServiceEntity.getStatus()!=null && !appServiceEntity.getStatus().isEmpty())
                    fetchedAppServiceEntity.setStatus(appServiceEntity.getStatus());
                if(appServiceEntity.getAuthToken()!=null && !appServiceEntity.getAuthToken().isEmpty())
                    fetchedAppServiceEntity.setAuthToken(fetchedAppServiceEntity.getAuthToken());
                if(appServiceEntity.getModuleName()!=null && !appServiceEntity.getModuleName().isEmpty())
                    fetchedAppServiceEntity.setModuleName(appServiceEntity.getModuleName());
                if(appServiceEntity.getCreatedDate()!=null )
                    fetchedAppServiceEntity.setCreatedDate(fetchedServiceEntity.orElseThrow().getCreatedDate());
                if(appServiceEntity.getModifiedDate()!=null)
                    fetchedAppServiceEntity.setCreatedDate(fetchedServiceEntity.orElseThrow().getModifiedDate());
                AppServiceEntity updatededAppServiceEntity = appRepository.save(fetchedAppServiceEntity);
                appResponseDto.setStatus("Susses");
                appResponseDto.setStatusCode("200");
                appResponseDto.setError(null);
                appResponseDto.setDto(updatededAppServiceEntity);
            } catch (Exception e) {
                logger.error("Unexpected error occurred: {}", e.getMessage());
                appResponseDto.setStatus("Failure");
                appResponseDto.setStatusCode("400");
                appResponseDto.setError(e.toString());
                appResponseDto.setDto(null);
            }
        } else {
            // Handle case where altKey doesn't exist
            appResponseDto.setStatus("Failure");
            appResponseDto.setStatusCode("404");
            appResponseDto.setError("Entity with altKey " + altKey + " not found");
            appResponseDto.setDto(null);
        }
        return appResponseDto;
    }

    @Override
    public void deleteService(Long altKey) {
        if(appRepository.existsById(altKey))
        { appRepository.deleteById(altKey);
            System.out.println("deleted service with id:" + altKey);
            return;
        }
        System.out.println("there is no service with service id:" + altKey);
    }

    @Override
    public AppResponseDto getService(Map<String, String> serviceDataMap) {
        AppResponseDto appResponseDto = new AppResponseDto();
        //servicename, modelName
        String serviceName = serviceDataMap.get("service_name");
        String moduleName = serviceDataMap.get("module_name");
        try{
            AppServiceEntity createdAppServiceEntity= appRepository.
                    findByServiceNameAndModuleName(serviceName, moduleName);
            appResponseDto.setStatus("Susses");
            appResponseDto.setStatusCode("200");
            appResponseDto.setError(null);
            appResponseDto.setDto(createdAppServiceEntity);
            return appResponseDto;
        }catch(Exception e){
            appResponseDto.setStatus("Failure");
            appResponseDto.setStatusCode("400");
            appResponseDto.setError(e.toString());
            appResponseDto.setDto(null);
            return appResponseDto;
        }
    }
}
