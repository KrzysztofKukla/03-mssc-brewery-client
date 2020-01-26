package pl.kukla.krzys.msscbreweryclient.web.client;

import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pl.kukla.krzys.msscbreweryclient.web.model.BeerDto;

import java.util.UUID;

/**
 * @author Krzysztof Kukla
 */
@Component
@ConfigurationProperties(value = "sfg.brewery", ignoreUnknownFields = false)
public class BreweryClient {

    private static final String BEER_PATH_VI = "/api/v1/beer/";

    @Setter
    private String apiHost;

    private final RestTemplate restTemplate;

    public BreweryClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public BeerDto getBeerById(UUID uuid) {
        return restTemplate.getForObject(apiHost + BEER_PATH_VI + uuid.toString(), BeerDto.class);
    }

}
