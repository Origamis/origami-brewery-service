package origamis.springframework.beerservice.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class WebConfigs {
    
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
