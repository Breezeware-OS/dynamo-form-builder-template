package net.breezeware.dynamo.forms.webbff.keycloak.config;

import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class KeycloakConfig {

    @Value("${keycloak.auth-server-url}")
    private String authServerUrl;

    @Value("${keycloak.realm}")
    private String realm;

    @Value("${keycloak.service.account.client-id}")
    private String clientId;

    @Value("${keycloak.client-secret}")
    private String clientSecret;

    @Bean("keycloak")
    public Keycloak buildKeycloakAdminClient() {
        log.info("Entering buildKeycloakAdminClient()");
        Keycloak keycloak = KeycloakBuilder.builder().serverUrl(authServerUrl).realm(realm)
                .grantType(OAuth2Constants.CLIENT_CREDENTIALS).clientId(clientId).clientSecret(clientSecret).build();
        log.info("Leaving buildKeycloakAdminClient()");
        return keycloak;
    }
}
