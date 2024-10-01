package net.breezeware.dynamo.forms.webbff.keycloak.service;

import java.util.ArrayList;
import java.util.List;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import net.breezeware.dynamo.forms.webbff.keycloak.dto.UserDto;
import net.breezeware.dynamo.utils.exception.DynamoException;

import lombok.extern.slf4j.Slf4j;

import jakarta.ws.rs.NotFoundException;

@Service
@Slf4j
public class KeycloakService {

    private final Keycloak keycloak;

    @Value("${keycloak.realm}")
    private String realm;

    @Autowired
    public KeycloakService(Keycloak keycloak) {
        this.keycloak = keycloak;
    }

    public UserDto getUserById(String userId) {
        log.info("Entering getUserById()");
        try {
            UserRepresentation userRepresentation = keycloak.realm(realm).users().get(userId).toRepresentation();
            UserDto userDto = new UserDto();
            userDto.setUserId(userRepresentation.getId());
            userDto.setFirstName(userRepresentation.getFirstName());
            userDto.setLastName(userRepresentation.getLastName());
            userDto.setEmail(userRepresentation.getEmail());
            log.info("Leaving getUserById()");
            return userDto;
        } catch (NotFoundException e) {
            throw new DynamoException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    public List<UserDto> getAllUsers() {
        List<UserRepresentation> list = keycloak.realm(realm).users().list();
        List<UserDto> userDtos = new ArrayList<>();
        for (UserRepresentation userRepresentation : list) {
            UserDto userDto = new UserDto();
            userDto.setUserId(userRepresentation.getId());
            userDto.setFirstName(userRepresentation.getFirstName());
            userDto.setLastName(userRepresentation.getLastName());
            userDto.setEmail(userRepresentation.getEmail());
            userDtos.add(userDto);
        }
        return userDtos;
    }
}
