package onlineshop.demo.endpoint;

import onlineshop.demo.model.Card;
import onlineshop.demo.security.CurrentUser;
import onlineshop.demo.service.CardServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/card")
public class CardEndpoint {
    @Autowired
    private CardServiceImpl cardService;

    @PutMapping("/update/{flowerId},{count}")
    public Card create(@PathVariable(value = "flowerId", required = false) int flowerId, @PathVariable(value = "count", required = false) int count,
                       @AuthenticationPrincipal CurrentUser user) {
        return cardService.updateCart(flowerId, count, user.getUser());
    }

}
