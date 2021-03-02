package onlineshop.demo.endpoint;

import onlineshop.demo.service.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderEndpoint {
    @Autowired
    private OrderServiceImpl orderService;

}
