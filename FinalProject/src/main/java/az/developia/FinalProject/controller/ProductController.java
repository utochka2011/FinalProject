package az.developia.FinalProject.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import az.developia.FinalProject.entity.ProductEntity;
import az.developia.FinalProject.request.ProductRequest;
import az.developia.FinalProject.service.ProductService;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = {"http://127.0.0.1:5500", "http://localhost:5500"}, allowCredentials = "true")
@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<ProductEntity> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/my-products")
    public List<ProductEntity> getMyProducts() {
        return productService.getMyProducts();
    }

    @GetMapping("/{id}")
    public Optional<ProductEntity> getProduct(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @GetMapping("/search")
    public List<ProductEntity> searchProducts(@RequestParam String keyword) {
        return productService.searchProducts(keyword);
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_USER')")
    public void addProduct(@RequestBody @Valid ProductRequest productRequest) {
        productService.addProductForCurrentUser(productRequest);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

    @GetMapping("/filter-by-rating")
    public List<ProductEntity> filterByMinRating(@RequestParam Integer minRating) {
        return productService.filterByMinRating(minRating);
    }

    @GetMapping("/sort-by-price-asc")
    public List<ProductEntity> sortByPriceAsc() {
        return productService.sortByPriceAsc();
    }

    @GetMapping("/sort-by-price-desc")
    public List<ProductEntity> sortByPriceDesc() {
        return productService.sortByPriceDesc();
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public void updateProduct(@PathVariable Long id, @RequestBody @Valid ProductRequest productRequest) {
        productService.updateProduct(id, productRequest);
    }
}
