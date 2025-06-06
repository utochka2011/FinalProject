package az.developia.FinalProject.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import az.developia.FinalProject.entity.BasketEntity;

public interface BasketRepository extends JpaRepository<BasketEntity, Long> {

    List<BasketEntity> findByUserId(Long userId);

    Optional<BasketEntity> findByUserIdAndProductId(Long userId, Long productId);
    void deleteByUserIdAndProductId(Long userId, Long productId);

}
