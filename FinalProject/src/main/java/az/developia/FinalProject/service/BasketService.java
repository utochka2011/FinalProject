package az.developia.FinalProject.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import az.developia.FinalProject.entity.BasketEntity;
import az.developia.FinalProject.entity.ProductEntity;
import az.developia.FinalProject.entity.UserEntity;
import az.developia.FinalProject.jwt.SecurityUtil;
import az.developia.FinalProject.repository.BasketRepository;
import az.developia.FinalProject.repository.ProductRepository;
import az.developia.FinalProject.repository.UserRepository;
import az.developia.FinalProject.request.BasketResponse;
import jakarta.transaction.Transactional;

@Service
public class BasketService {

    @Autowired
    private BasketRepository basketRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    public void addToBasket(Long productId) {
        String username = SecurityUtil.getCurrentUsername();
        UserEntity user = userRepository.findByUsername(username).orElseThrow();

        BasketEntity basket = basketRepository.findByUserIdAndProductId(user.getId(), productId)
                .orElse(new BasketEntity());

        basket.setUserId(user.getId());
        basket.setProductId(productId);

        if (basket.getQuantity() == null) {
            basket.setQuantity(1);
        } else {
            basket.setQuantity(basket.getQuantity() + 1);
        }

        basketRepository.save(basket);
    }

    @Transactional
    public void removeFromBasket(long productId) {
        String username = SecurityUtil.getCurrentUsername();
        UserEntity user = userRepository.findByUsername(username).orElseThrow();
        basketRepository.deleteByUserIdAndProductId(user.getId(), productId);
    }

    public List<BasketResponse> getMyBasket() {
        String username = SecurityUtil.getCurrentUsername();
        UserEntity user = userRepository.findByUsername(username).orElseThrow();
        List<BasketEntity> baskets = basketRepository.findByUserId(user.getId());

        return baskets.stream()
                .map(basket -> {
                    ProductEntity product = productRepository.findById(basket.getProductId()).orElse(null);
                    if (product == null) {
                        return null; 
                    }

                    BasketResponse response = new BasketResponse();
                    response.setId(product.getId());
                    response.setBrand(product.getBrand());
                    response.setModel(product.getModel());
                    response.setDescription(product.getDescription());
                    response.setSize(product.getSize());
                    response.setPrice(product.getPrice());
                    response.setImgUrl(product.getImgUrl());
                    response.setRating(product.getRating());
                    response.setOwnerId(product.getOwnerId());
                    response.setQuantity(basket.getQuantity());

                    return response;
                })
                .collect(Collectors.toList());
    }
}
