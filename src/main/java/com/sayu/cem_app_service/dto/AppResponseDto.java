package com.sayu.cem_app_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppResponseDto {
    private String status;
    private String statusCode;
    private Object dto;
    private  String error;
}
