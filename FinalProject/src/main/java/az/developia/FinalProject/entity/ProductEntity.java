package az.developia.FinalProject.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String brand;
    private String model;
    private String description;
    private Double price;
    private String size;

    @JsonProperty("imageUrl")
    private String imgUrl;

    private Integer rating;

    private Long ownerId;
}
