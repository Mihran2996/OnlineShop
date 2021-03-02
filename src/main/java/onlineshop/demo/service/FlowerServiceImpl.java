package onlineshop.demo.service;

import onlineshop.demo.exception.ResourceNotFoundException;
import onlineshop.demo.model.Category;
import onlineshop.demo.model.Flower;
import onlineshop.demo.model.Image;
import onlineshop.demo.repository.FlowerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FlowerServiceImpl implements FlowerService {

    @Autowired
    private FlowerRepository flowerRepository;

    @Value("${file.upload.dir}")
    private String uploadDir;

    @Override
    public Flower addFlower(Flower flower,MultipartFile file) throws IOException {
        String name = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        File image = new File(uploadDir, name);
        file.transferTo(image);
        final List<Image> images = flower.getImages();
        for (Image image1 : images) {
            image1.setName(name);
        }
        flower.setImages(images);
        return flowerRepository.save(flower);
    }

    @Override
    public List<Flower> getById(List<Integer> list) {
        List<Flower> flowers = new ArrayList<>();
        for (Integer id : list) {
            flowers.add(flowerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException()));
        }
        return flowers;
    }

    @Override
    public Optional<Flower> findById(int id) {
        return flowerRepository.findById(id);
    }


    @Override
    public Flower save(Flower flower) {
        return flowerRepository.save(flower);
    }

    @Override
    public void delete(int id) {
        flowerRepository.deleteById(id);
    }

    @Override
    public List<Flower> getByCategory(Category category) {
        return flowerRepository.findByCategory(category);
    }

    @Override
    public List<Flower> getFlowers() {
        return flowerRepository.findAll();
    }

    @Override
    public List<Flower> filterByCategoryAndPriceMax_50(Category category, Double price) {
        return flowerRepository.filterByCategoryAndPriceMax_50(category.name(), price);
    }

    @Override
    public List<Flower> filterByCategoryAndPriceMax_100(Category category, Double price) {
        return flowerRepository.filterByCategoryAndPriceDiapason_50_From_100(category.name(), price);
    }

    @Override
    public List<Flower> filterByCategoryAndPriceMax_200(Category category, Double price) {
        return flowerRepository.filterByCategoryAndPriceDiapason_100_From_200(category.name(), price);
    }

    @Override
    public List<Flower> filterByCategoryAndPriceMin_200(Category category, Double price) {
        return flowerRepository.filterByCategoryAndPriceMin_200(category.name(), price);
    }

    @Override
    public List<Flower> filterByPriceMax_50(double price) {
        return flowerRepository.filterByPriceMax_50(price);
    }

    @Override
    public List<Flower> filterByPriceMax_100(double price) {
        return flowerRepository.filterByPriceDiapason_50_From_100(price);
    }

    @Override
    public List<Flower> filterByPriceMax_200(double price) {
        return flowerRepository.filterByPriceDiapason_100_From_200(price);
    }

    @Override
    public List<Flower> filterByPriceMin_200(double price) {
        return flowerRepository.filterByPriceMin_200(price);
    }

    @Override
    public List<Flower> getFlowersByCart(int carId) {
        return null;
    }


    public Flower getOne(int flowerId) {
        return flowerRepository.getOne(flowerId);
    }
}
