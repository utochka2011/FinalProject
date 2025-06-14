package az.developia.FinalProject.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BasketResponse {

    private Long id;
    private String brand;
    private String model;
    private String description;
    private Double price;
    private String size;
    private String imgUrl;
    private Integer rating;
    private Long ownerId;
    private Integer quantity;
}
