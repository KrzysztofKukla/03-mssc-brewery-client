package pl.kukla.krzys.msscbreweryclient.web.client;

import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pl.kukla.krzys.msscbreweryclient.web.model.BeerDto;

import java.net.URI;
import java.util.UUID;

/**
 * @author Krzysztof Kukla
 */
//microservice can have many, many client connections
//Brewery Client is automatically injected into RequestFactory
// and then requestFactory is injected into RestTemplate
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

    public BeerDto getBeerById(UUID beerId) {
        return restTemplate.getForObject(apiHost + BEER_PATH_VI + "{beerId}", BeerDto.class, beerId);
    }

    public URI saveNewBeer(BeerDto beerDto) {
        //postForLocation returns back a location header
        // and this method is going to be looking at response header for the value
        return restTemplate.postForLocation(apiHost + BEER_PATH_VI, beerDto);
//        return restTemplate.postForObject(apiHost + BEER_PATH_VI, beerDto, BeerDto.class);
    }

    public void updateBeer(UUID beerId, BeerDto beerDto) {
        restTemplate.put(apiHost + BEER_PATH_VI + "{beerId}", beerDto, beerId);
    }

    public void deleteBeer(UUID beerId) {
        restTemplate.delete(apiHost + BEER_PATH_VI + "{beerId}", beerId);
    }

}
