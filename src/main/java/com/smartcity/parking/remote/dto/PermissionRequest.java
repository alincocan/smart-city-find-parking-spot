package com.smartcity.parking.remote.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PermissionRequest {
    private String externalId;
    private String type;
}
