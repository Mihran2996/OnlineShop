package onlineshop.demo.repository;


import onlineshop.demo.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Card, Integer> {
}
