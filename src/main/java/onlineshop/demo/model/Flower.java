package onlineshop.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "flower")
@JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
public class Flower {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int size;
    private double price;
    private int count;
    @Enumerated(value = EnumType.STRING)
    private Category category;
    private String color;
    @Enumerated(value = EnumType.STRING)
    private Status status;
    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "flower", fetch = FetchType.LAZY)
    private List<Image> images = new ArrayList<>();
    @ManyToMany(mappedBy = "flowers", fetch = FetchType.LAZY)
    private List<Card> card;

    public void setImages(List<Image> images) {
        this.images.clear();
        for (Image image : images) {
            image.setFlower(this);
            this.images.add(image);
        }
    }
}
