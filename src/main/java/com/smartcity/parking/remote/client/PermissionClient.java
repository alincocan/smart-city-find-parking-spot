package com.smartcity.parking.remote.client;

import com.smartcity.parking.remote.dto.PermissionRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class PermissionClient {

    private final WebClient webClient;

    public PermissionClient() {
        this.webClient = WebClient.create("http://localhost:8082");
    }

    public Mono<ClientResponse> create(PermissionRequest permissionRequest) {
        return webClient.post()
            .uri("/permission")
            .body(permissionRequest, PermissionRequest.class)
            .exchange();
    }

    public Mono<ClientResponse> getByExternalId(String externalId) {
        return webClient.get()
            .uri("/permission?externalId=" + externalId)
            .exchange();
    }

    public Mono<ClientResponse> getByUserId(String userId) {
        return webClient.get()
            .uri("/permission?userId=" + userId)
            .exchange();
    }
}
