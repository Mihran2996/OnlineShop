package onlineshop.demo.service;

import onlineshop.demo.exception.ResourceNotFoundException;
import onlineshop.demo.model.Card;
import onlineshop.demo.model.Flower;
import onlineshop.demo.model.User;
import onlineshop.demo.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CardServiceImpl implements CardService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private FlowerServiceImpl flowerService;

    public Card updateCart(int flowerId, int count, User user) {
        Card cardFromDb =user.getCard();
        Optional<Flower> flowerFromDb = flowerService.findById(flowerId);
         List<Flower> flowers = cardFromDb.getFlowers();
        if (flowerFromDb.isPresent()) {
            for (int i = 0; i < count; i++) {
                flowers.add(flowerService.getOne(flowerId));
            }
            cardFromDb.setFlowers(flowers);
            cardFromDb.setUser(user);
            cardFromDb.setSum(getCartSum(cardFromDb));
            cartRepository.save(cardFromDb);

        } else {
            throw new ResourceNotFoundException();
        }
        return null;
    }


    private double getCartSum(Card card) {
        final List<Flower> flowers = card.getFlowers();
        double sum = 0.0;
        for (Flower flower : flowers) {
            sum += Double.sum(flower.getPrice(), 0.0);
        }
        return sum;
    }


}
