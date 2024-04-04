package com.sayu.cem_app_service.controller;

import com.sayu.cem_app_service.dto.AppResponseDto;
import com.sayu.cem_app_service.entity.AppServiceEntity;
import com.sayu.cem_app_service.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/services")
public class AppController {
    private final AppService appService;

    @Autowired
    AppController(AppService appService){
        this.appService = appService;
    }
    @GetMapping("/ping")
    @ResponseBody AppResponseDto getServicesPing(){
        return appService.ping();
    }
    @PostMapping("/")
    @ResponseBody AppResponseDto createService(@RequestBody AppServiceEntity appServiceEntity){
        return appService.createService(appServiceEntity);
    }
    @GetMapping("/")
    List<AppResponseDto> getServices(){
        return appService.getServices();
    }
    @GetMapping("/search")
    @ResponseBody AppResponseDto getService(@RequestBody Map<String,String> serviceDataMap){
        return appService.getService(serviceDataMap);
    }
    @PutMapping("/{serviceId}")
    @ResponseBody AppResponseDto updateService(@PathVariable(value = "serviceId") Long serviceId,
                                            @RequestBody AppServiceEntity appServiceEntity){
        return appService.updateService(serviceId,appServiceEntity);
    }
    @DeleteMapping("/{serviceId}")
    void deleteService(@PathVariable("serviceId") Long altKey){
        appService.deleteService(altKey);
    }
}
