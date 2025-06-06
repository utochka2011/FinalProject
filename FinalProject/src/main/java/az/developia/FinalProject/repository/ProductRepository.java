package az.developia.FinalProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import az.developia.FinalProject.entity.ProductEntity;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    List<ProductEntity> findByOwnerId(Long ownerId);
    List<ProductEntity> findAllByOrderByPriceAsc();
    List<ProductEntity> findAllByOrderByPriceDesc();
    List<ProductEntity> findByBrandContainingIgnoreCase(String brand);
    List<ProductEntity> findAllByRatingGreaterThanEqual(Integer minRating);
}
