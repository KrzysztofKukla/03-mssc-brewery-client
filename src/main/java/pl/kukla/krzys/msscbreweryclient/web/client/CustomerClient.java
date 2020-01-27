package pl.kukla.krzys.msscbreweryclient.web.client;

import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pl.kukla.krzys.msscbreweryclient.web.model.CustomerDto;

import java.net.URI;
import java.util.UUID;

/**
 * @author Krzysztof Kukla
 */
//Brewery Client is automatically injected into RequestFactory
// and then requestFactory is injected into RestTemplate
@Component
@ConfigurationProperties("sfg.brewery")
public class CustomerClient {

    private static final String CUSTOMER_PATH_V1 = "/api/v1/customer";

    private final RestTemplate restTemplate;

    public CustomerClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Setter
    private String apiHost;

    public CustomerDto getCustomerById(UUID customerId) {
        return restTemplate.getForObject(apiHost + CUSTOMER_PATH_V1 + "/{customerId}", CustomerDto.class, customerId);
    }

    public URI saveNewCustomer(CustomerDto customerDto) {
        return restTemplate.postForLocation(apiHost + CUSTOMER_PATH_V1, customerDto);
    }

    public void updateCustomer(UUID customerId, CustomerDto customerDto) {
        restTemplate.put(apiHost + CUSTOMER_PATH_V1 + "/{customerId}", customerDto, customerId);
    }

    public void deleteCustomer(UUID customerId) {
        restTemplate.delete(apiHost + CUSTOMER_PATH_V1 + "/{customerId}", customerId);
    }

}
