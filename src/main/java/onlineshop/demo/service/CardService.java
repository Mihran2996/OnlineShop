package onlineshop.demo.service;


import onlineshop.demo.model.Card;
import onlineshop.demo.model.User;

public interface CardService {
    Card updateCart(int flowerId, int count, User user);

}
