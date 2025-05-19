
package com.example.jpa.common.properties;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("naver-app")
public class NaverAppProperties {

    private String clientId;
    private String clientSecret;

}
