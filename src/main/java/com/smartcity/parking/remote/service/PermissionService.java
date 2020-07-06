package com.smartcity.parking.remote.service;

import com.smartcity.parking.domain.EntityType;
import com.smartcity.parking.remote.client.PermissionClient;
import com.smartcity.parking.remote.dto.PermissionRequest;
import com.smartcity.parking.remote.dto.PermissionResponse;
import com.smartcity.parking.service.exception.ForbiddenException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PermissionService {

    private final PermissionClient permissionClient;

    public PermissionService(PermissionClient permissionClient) {
        this.permissionClient = permissionClient;
    }

    public Mono<PermissionResponse> create(String id, EntityType type) {
        final PermissionRequest permissionRequest =
            PermissionRequest
                .builder()
                .externalId(id)
                .type(type.toString())
                .build();
        return permissionClient.create(permissionRequest)
            .flatMap(this::getResponse)
            .flatMap(response -> response.bodyToMono(PermissionResponse.class));
    }

    public Mono<String> getByExternalId(String externalId) {
        return permissionClient.getByExternalId(externalId)
            .flatMap(this::getResponse)
            .flatMap(response -> response.bodyToMono(PermissionResponse.class))
            .map(PermissionResponse::getExternalId);
    }

    public Mono<List<String>> getByUserId(String userId) {
        return permissionClient.getByUserId(userId)
            .flatMap(this::getResponse)
            .flatMap(response -> response.bodyToMono(new ParameterizedTypeReference<List<PermissionResponse>>() {}))
            .map(list -> list.stream().map(PermissionResponse::getExternalId).collect(Collectors.toList()));
    }

    private Mono<ClientResponse> getResponse (ClientResponse clientResponse) {
        switch (clientResponse.statusCode()) {
            case OK: return Mono.just(clientResponse);
            case NOT_FOUND: return Mono.error(new ForbiddenException());
            default: return Mono.error(new RuntimeException());
        }
    }
}
