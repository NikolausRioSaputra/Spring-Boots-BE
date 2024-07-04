package com.project.usersProject.Controller;

import com.project.usersProject.Entity.Product;
import com.project.usersProject.Repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;
import java.util.UUID;

@Controller
@RequestMapping(path = "/product")
public class ProductController {
    @Autowired
    private ProductRepository productRepo;

    @GetMapping(path = "/findByPrice")
    public @ResponseBody List<Product> findProduct(@RequestParam Double price, @RequestParam Integer page, @RequestParam Integer size){
        Pageable pageable = PageRequest.of(page, size);
        return productRepo.findAllByPrice(price, pageable);
    }

    @GetMapping(path = "/findByPriceSort")
    public @ResponseBody List<Product> findProductSort(@RequestParam Double price, @RequestParam Integer page, @RequestParam Integer size, @RequestParam String sortColumn){
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortColumn).descending());
        return productRepo.findAllByPrice(price, pageable);
    }

    @GetMapping(path = "/generate")
    public @ResponseBody String generateProduct(){
        Random rand = new Random();
        for (int i = 0; i < 10000; i++) {
            Product product = new Product();
            product.setPrice(rand.nextDouble(1000));
            product.setName(UUID.randomUUID().toString());
            productRepo.save(product);
        }
        return "generate success";
    }

}
