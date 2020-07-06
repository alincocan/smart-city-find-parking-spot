package com.smartcity.parking.remote.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PermissionResponse {
    private String id;
    private String externalId;
    private String type;
}
