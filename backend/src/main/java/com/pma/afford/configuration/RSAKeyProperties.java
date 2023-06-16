package com.pma.afford.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
/**

 Represents the RSA key properties that can be configured through the application configuration properties.
 This class is annotated with @ConfigurationProperties, which allows the properties to be bound to an instance of this class.
 The "prefix" parameter in the annotation specifies the prefix used in the configuration properties file to group the related properties.
 */
@ConfigurationProperties(prefix = "rsa")
public record RSAKeyProperties(RSAPublicKey publicKey, RSAPrivateKey privateKey) {
}
