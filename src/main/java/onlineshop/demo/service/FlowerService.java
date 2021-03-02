package onlineshop.demo.service;

import onlineshop.demo.model.Category;
import onlineshop.demo.model.Flower;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface FlowerService {
    Flower addFlower(Flower flower,MultipartFile file ) throws IOException;

    Flower getOne(int id);

    List<Flower> getById(List<Integer> list);

    Optional<Flower> findById(int id);

    Flower save(Flower flower);

    void delete(int id);

    List<Flower> getByCategory(Category category);

    List<Flower> getFlowers();

    List<Flower> filterByCategoryAndPriceMax_50(Category category, Double price);

    List<Flower> filterByCategoryAndPriceMax_100(Category category, Double price);

    List<Flower> filterByCategoryAndPriceMax_200(Category category, Double price);

    List<Flower> filterByCategoryAndPriceMin_200(Category category, Double price);

    List<Flower> filterByPriceMax_50(double price);

    List<Flower> filterByPriceMax_100(double price);

    List<Flower> filterByPriceMax_200(double price);

    List<Flower> filterByPriceMin_200(double price);
    List<Flower> getFlowersByCart(int carId);

}
