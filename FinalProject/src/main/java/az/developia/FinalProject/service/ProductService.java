package az.developia.FinalProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import az.developia.FinalProject.entity.ProductEntity;
import az.developia.FinalProject.entity.UserEntity;
import az.developia.FinalProject.jwt.SecurityUtil;
import az.developia.FinalProject.repository.ProductRepository;
import az.developia.FinalProject.repository.UserRepository;
import az.developia.FinalProject.request.ProductRequest;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    public void addProductForCurrentUser(ProductRequest request) {
        String username = SecurityUtil.getCurrentUsername();
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        ProductEntity product = new ProductEntity();
        product.setBrand(request.getBrand());
        product.setModel(request.getModel());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setSize(request.getSize());
        product.setImgUrl(request.getImgUrl());
        product.setRating(request.getRating());
        product.setOwnerId(user.getId());

        productRepository.save(product);
    }

    public List<ProductEntity> getMyProducts() {
        String username = SecurityUtil.getCurrentUsername();
        Long userId = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found")).getId();
        return productRepository.findByOwnerId(userId);
    }

    public ProductEntity getMyProductById(Long id) {
        String username = SecurityUtil.getCurrentUsername();
        Long currentUserId = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found")).getId();

        ProductEntity product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        if (!product.getOwnerId().equals(currentUserId)) {
            throw new RuntimeException("Access denied");
        }
        return product;
    }

    public void deleteProduct(Long id) {
        ProductEntity product = getMyProductById(id);
        productRepository.delete(product);
    }

    public List<ProductEntity> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<ProductEntity> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public List<ProductEntity> searchProducts(String keyword) {
        return productRepository.findByBrandContainingIgnoreCase(keyword);
    }

    public List<ProductEntity> filterByMinRating(Integer minRating) {
        return productRepository.findAllByRatingGreaterThanEqual(minRating);
    }

    public List<ProductEntity> sortByPriceAsc() {
        return productRepository.findAllByOrderByPriceAsc();
    }

    public List<ProductEntity> sortByPriceDesc() {
        return productRepository.findAllByOrderByPriceDesc();
    }

    public void updateProduct(Long id, ProductRequest request) {
        ProductEntity existing = getMyProductById(id);
        existing.setBrand(request.getBrand());
        existing.setModel(request.getModel());
        existing.setDescription(request.getDescription());
        existing.setPrice(request.getPrice());
        existing.setSize(request.getSize());
        existing.setImgUrl(request.getImgUrl());
        existing.setRating(request.getRating());
        productRepository.save(existing);
    }
}
