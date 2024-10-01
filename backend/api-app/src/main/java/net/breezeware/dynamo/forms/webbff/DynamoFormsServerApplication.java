package net.breezeware.dynamo.forms.webbff;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableAutoConfiguration
@PropertySources({ @PropertySource(value = { "classpath:dynamo-auth.properties" }) })
@ComponentScan(
        basePackages = { "net.breezeware.dynamo.auth.config", "net.breezeware.dynamo" })
@EnableJpaRepositories(basePackages = { "net.breezeware.dynamo" })
@EntityScan(basePackages = { "net.breezeware.dynamo.forms" })
public class DynamoFormsServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(DynamoFormsServerApplication.class, args);
    }

}
