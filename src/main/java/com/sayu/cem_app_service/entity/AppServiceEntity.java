package com.sayu.cem_app_service.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.stereotype.Component;
import java.util.Date;

@Getter
@Component
@Entity
@Builder
@Table(name="cem_app_service")
public class AppServiceEntity {
    @Id
    @Column(name = "alt_key")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long altKey;
    @Setter
    @Column(name = "service_name")
    private String serviceName;
    @Setter
    @Column(name = "module_name")
    private String moduleName;
    @Setter
    @Column(name="auth_token")
    private String authToken;
    @Setter
    @Column(name="service_url")
    private String serviceUrl;
    @Setter
    @Column(name="status")
    private String status;
    @Setter
    @Column(name = "created_date", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdDate;
    @Setter
    @Column(name = "modified_date")
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date modifiedDate;

    @Override
    public String toString() {
        return "AppServiceEntity{" +
                "altKey=" + altKey +
                ", serviceName='" + serviceName + '\'' +
                ", moduleName='" + moduleName + '\'' +
                ", authToken='" + authToken + '\'' +
                ", serviceUrl='" + serviceUrl + '\'' +
                ", status='" + status + '\'' +
                ", createdDate=" + createdDate +
                ", modifiedDate=" + modifiedDate +
                '}';
    }

    public AppServiceEntity() {
    }

    public AppServiceEntity(Long altKey, String serviceName,
                            String moduleName, String authToken, String serviceUrl,
                            String status, Date createdDate, Date modifiedDate) {
        this.altKey = altKey;
        this.serviceName = serviceName;
        this.moduleName = moduleName;
        this.authToken = authToken;
        this.serviceUrl = serviceUrl;
        this.status = status;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }
}
