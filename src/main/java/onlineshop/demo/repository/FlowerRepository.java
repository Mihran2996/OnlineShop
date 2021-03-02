package onlineshop.demo.repository;

import onlineshop.demo.model.Category;
import onlineshop.demo.model.Flower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FlowerRepository extends JpaRepository<Flower, Integer> {
    List<Flower> findByCategory(Category category);

    @Query(value = "select * from flower where category=:category and price<=:price", nativeQuery = true)
    List<Flower> filterByCategoryAndPriceMax_50(@Param("category") String category, @Param("price") double price);

    @Query(value = "select * from flower where category=:category and price>=50 and price<=:price", nativeQuery = true)
    List<Flower> filterByCategoryAndPriceDiapason_50_From_100(@Param("category") String category, @Param("price") double price);

    @Query(value = "select * from flower where category=:category and price>=100 and price<=:price", nativeQuery = true)
    List<Flower> filterByCategoryAndPriceDiapason_100_From_200(@Param("category") String category, @Param("price") double price);

    @Query(value = "select * from flower where category=:category and price>=:price", nativeQuery = true)
    List<Flower> filterByCategoryAndPriceMin_200(@Param("category") String category, @Param("price") double price);

    @Query(value = "select * from flower where  price<=:price", nativeQuery = true)
    List<Flower> filterByPriceMax_50(@Param("price") double price);

    @Query(value = "select * from flower where  price>=50 and price<=:price", nativeQuery = true)
    List<Flower> filterByPriceDiapason_50_From_100(@Param("price") double price);

    @Query(value = "select * from flower where price>=100 and price<=:price", nativeQuery = true)
    List<Flower> filterByPriceDiapason_100_From_200(@Param("price") double price);

    @Query(value = "select * from flower where  price>=:price", nativeQuery = true)
    List<Flower> filterByPriceMin_200(@Param("price") double price);


}
