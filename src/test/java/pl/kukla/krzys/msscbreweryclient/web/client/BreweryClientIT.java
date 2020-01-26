package pl.kukla.krzys.msscbreweryclient.web.client;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.kukla.krzys.msscbreweryclient.web.model.BeerDto;

import java.net.URI;
import java.util.UUID;

/**
 * @author Krzysztof Kukla
 */
@SpringBootTest
class BreweryClientIT {

    @Autowired
    private BreweryClient breweryClient;

    @Test
    void getBeerById() {
        BeerDto beerDto = breweryClient.getBeerById(UUID.randomUUID());

        Assertions.assertNotNull(beerDto);
    }

    @Test
    void saveNewBeer() throws Exception {
        //given
        BeerDto beerDto = BeerDto.builder()
            .beerName("new first Beer")
            .beerStyle("new beer style")
            .upc(22222L)
            .build();

        //when
        URI uri = breweryClient.saveNewBeer(beerDto);

        //then
        Assertions.assertNotNull(uri);
        System.out.println(uri.toString());
    }

    @Test
    void updateBeer() throws Exception {
        //given
        BeerDto beerDto = BeerDto.builder()
            .beerName("new first Beer")
            .beerStyle("new beer style")
            .upc(22222L)
            .build();

        breweryClient.updateBeer(UUID.randomUUID(), beerDto);
    }

    @Test
    void deleteBeer() throws Exception {
        breweryClient.deleteBeer(UUID.randomUUID());
    }

}