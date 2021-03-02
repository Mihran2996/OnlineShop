package onlineshop.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "user")
@JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String surname;
    private int age;
    private String profilePic;
    private String username;
    private String password;
    @Enumerated(value = EnumType.STRING)
    private Gender gender;
    @Enumerated(value = EnumType.STRING)
    private Role role;
    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.LAZY)
    private List<Address> addresses = new ArrayList<>();
    private boolean active;
    private String token;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.LAZY)
    @JsonIgnore
    private Card card;

    public void setCard(Card card) {
        card.setUser(this);
        this.card = card;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses.clear();
        for (Address address : addresses) {
            address.setUser(this);
            this.addresses.add(address);
        }
    }

    public void setAddresses2(List<Address> addresses) {
        this.addresses = addresses;
    }

}
