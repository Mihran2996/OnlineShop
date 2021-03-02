package onlineshop.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double sum;
    @OneToOne(fetch = FetchType.LAZY)
    private Flower flower;
    @OneToOne(fetch = FetchType.LAZY)
    private User user;
}
