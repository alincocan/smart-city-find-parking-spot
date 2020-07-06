package com.smartcity.parking.remote.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smartcity.parking.domain.EntityType;
import com.smartcity.parking.remote.dto.PermissionResponse;
import com.smartcity.parking.remote.service.PermissionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.ClientResponse;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.UUID;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PermissionServiceTest {

    @Mock
    private PermissionClient permissionClient;

    private ObjectMapper objectMapper = new ObjectMapper();

    @InjectMocks
    private PermissionService permissionService;
    private final String permissionId = UUID.randomUUID().toString();

    private final PermissionResponse permissionResponse =
        PermissionResponse
            .builder()
            .id(UUID.randomUUID().toString())
            .externalId(permissionId)
            .type(EntityType.CAR.toString())
            .build();


    @Test
    public void when_okReceived_expectMonoOK() throws JsonProcessingException {
        final ClientResponse clientResponse =
            ClientResponse.create(HttpStatus.OK)
                .body(objectMapper.writeValueAsString(permissionResponse))
                .build();

        when(permissionClient.create(any())).thenReturn(Mono.just(clientResponse));

        StepVerifier
            .create(permissionService.create(permissionId, EntityType.CAR))
            .expectNext(permissionResponse)
            .expectComplete()
            .verify();
    }

}
