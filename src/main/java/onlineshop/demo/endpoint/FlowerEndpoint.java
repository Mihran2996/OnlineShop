package onlineshop.demo.endpoint;

import onlineshop.demo.model.Category;
import onlineshop.demo.model.Flower;
import onlineshop.demo.service.FlowerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/rest/flower")
public class FlowerEndpoint {
    @Autowired
    private FlowerServiceImpl flowerService;

    @GetMapping("/recommendations/{id}")
    public List<Flower> getFlowers(@PathVariable List<Integer> id) {
        return flowerService.getById(id);
    }

    @GetMapping("/flowers")
    public List<Flower> getFlowers() {
        return flowerService.getFlowers();
    }

    @PostMapping("/create")
    public ResponseEntity create(@RequestBody Flower flower,MultipartFile file ) throws IOException {
        return ResponseEntity.ok(flowerService.addFlower(flower,file));
    }

    @PutMapping("/put")
    public ResponseEntity update(@RequestBody Flower flower) {
        return ResponseEntity.ok(flowerService.save(flower));
    }

    //jnjumeq caxik@ @st id-i
    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable int id) {
        flowerService.delete(id);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/filter/{category},{price}")
    public List<Flower> filterByCategoryAndPrice(@PathVariable(value = "category", required = false) Category category,
                                                 @PathVariable(value = "price", required = false) Double price) throws NullPointerException {
        if (category == null && price == null) {
            throw new NullPointerException();
        } else if (price == 50) {
            return flowerService.filterByCategoryAndPriceMax_50(category, price);
        } else if (price == 100) {
            return flowerService.filterByCategoryAndPriceMax_100(category, price);
        } else if (price == 199) {
            return flowerService.filterByCategoryAndPriceMax_200(category, price);
        } else {
            return flowerService.filterByCategoryAndPriceMin_200(category, price);
        }
    }

    @GetMapping("/filter/category/{category}")
    public List<Flower> filterByCategory(@PathVariable(value = "category", required = false) Category category) {
        return flowerService.getByCategory(category);
    }

    @GetMapping("/filter/price/{price}")
    public List<Flower> filterByPrice(@PathVariable(value = "price", required = false) double price) {
        if (price == 50) {
            return flowerService.filterByPriceMax_50(price);
        } else if (price == 100) {
            return flowerService.filterByPriceMax_100(price);
        } else if (price == 199) {
            return flowerService.filterByPriceMax_200(price);
        } else {
            return flowerService.filterByPriceMin_200(price);
        }
    }

}
