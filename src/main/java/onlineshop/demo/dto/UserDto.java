package onlineshop.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import onlineshop.demo.model.Address;
import onlineshop.demo.model.Card;
import onlineshop.demo.model.Gender;
import onlineshop.demo.model.Role;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private int id;
    private String name;
    private String surname;
    private int age;
    private String profilePic;
    private String username;
    private String password;
    private Gender gender;
    private Role role;
    private List<Address> addresses = new ArrayList<>();
    private Card card;

}