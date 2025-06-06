package az.developia.FinalProject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import az.developia.FinalProject.request.BasketResponse;
import az.developia.FinalProject.service.BasketService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/basket")
public class BasketController {

    @Autowired
    private BasketService basketService;

    @PostMapping("/add/{productId}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public void addToBasket(@PathVariable Long productId) {
        basketService.addToBasket(productId);
    }

    @DeleteMapping("/remove/{productId}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public void removeFromBasket(@PathVariable Long productId) {
        basketService.removeFromBasket(productId);
    }

    @GetMapping("/my-basket")
    @PreAuthorize("hasRole('ROLE_USER')")
    public List<BasketResponse> getMyBasket() {
        return basketService.getMyBasket();
    }
}
