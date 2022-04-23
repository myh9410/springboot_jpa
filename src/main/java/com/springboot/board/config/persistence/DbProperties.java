package com.springboot.board.config.persistence;

import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.util.Strings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.InvalidPropertiesFormatException;

@Configuration
@Getter
@Setter
@ConfigurationProperties(prefix = "spring.datasource.board")
public class DbProperties {
    private String hostname;
    private String username;
    private String password;

    private final String dbname = "board";
    private final int port = 3306;
    private final String zoneId = "Asia/Seoul";
    private final String characterEncoding= "UTF-8";
    private JpaProperties jpaProperties;

    public String getConnectionString() throws InvalidPropertiesFormatException {
        if (!isValid()) {
            throw new InvalidPropertiesFormatException(
                    this.toString()
            );
        }

        StringBuilder connectionStringBuilder = new StringBuilder();

        connectionStringBuilder
                .append("jdbc:mysql://")
                .append(hostname)
                .append(":").append(port)
                .append("/").append(dbname);
//                .append("?")
//                .append("serverTimezone=").append(zoneId)
//                .append("&characterEncoding=").append(characterEncoding);

        return connectionStringBuilder.toString();
    }

    public boolean isValidHostName() {
        if (Strings.isBlank(hostname)) return false;

        return true;
    }

    public boolean isValidPassword() {
        if (Strings.isBlank(password)) return false;

        return true;
    }

    public boolean isValidUserName() {
        if (Strings.isBlank(password)) return false;

        return true;
    }

    public boolean isValid() {
        if (!isValidHostName()) return false;

        if (!isValidUserName()) return false;

        if (!isValidPassword()) return false;

        return true;
    }

}
