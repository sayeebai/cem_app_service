package com.sayu.cem_app_service.repository;

import com.sayu.cem_app_service.entity.AppServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRepository extends JpaRepository<AppServiceEntity,Long> {

    AppServiceEntity findByServiceNameAndModuleName(String serviceName, String moduleName);
}
