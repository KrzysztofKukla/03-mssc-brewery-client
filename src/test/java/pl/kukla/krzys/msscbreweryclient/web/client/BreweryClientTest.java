package pl.kukla.krzys.msscbreweryclient.web.client;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.kukla.krzys.msscbreweryclient.web.model.BeerDto;

import java.util.UUID;

/**
 * @author Krzysztof Kukla
 */
@SpringBootTest
class BreweryClientTest {

    @Autowired
    private BreweryClient breweryClient;

    @Test
    void getBeerById() {
        BeerDto beerDto = breweryClient.getBeerById(UUID.randomUUID());

        Assertions.assertNotNull(beerDto);
    }

}