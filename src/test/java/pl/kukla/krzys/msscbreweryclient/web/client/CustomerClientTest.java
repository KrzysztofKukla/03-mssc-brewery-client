package pl.kukla.krzys.msscbreweryclient.web.client;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.kukla.krzys.msscbreweryclient.web.model.CustomerDto;

import java.net.URI;
import java.util.UUID;

/**
 * @author Krzysztof Kukla
 */
@SpringBootTest
class CustomerClientTest {

    @Autowired
    private CustomerClient customerClient;

    @Test
    void getCustomerById() {
        CustomerDto customer = customerClient.getCustomerById(UUID.randomUUID());
        Assertions.assertNotNull(customer);
    }

    @Test
    void saveCustomer() throws Exception {
        CustomerDto newCustomer = CustomerDto.builder().name("new Customer").build();

        URI uri = customerClient.saveNewCustomer(newCustomer);
        Assertions.assertNotNull(uri);
    }

    @Test
    void updateCustomer() throws Exception {
        CustomerDto newCustomer = CustomerDto.builder().uuid(UUID.randomUUID()).name("new Customer").build();
        customerClient.updateCustomer(UUID.randomUUID(), newCustomer);
    }

    @Test
    void deleteCustomer() throws Exception {
        customerClient.deleteCustomer(UUID.randomUUID());
    }

}