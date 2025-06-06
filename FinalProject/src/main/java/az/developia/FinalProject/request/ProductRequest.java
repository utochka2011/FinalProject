package az.developia.FinalProject.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {
    @NotBlank(message = "Название бренда не должно быть пустым")
    @Size(min = 3, max = 50)
    private String brand;

    @NotBlank(message = "Название модели не должно быть пустым")
    @Size(max = 50)
    private String model;

    @NotBlank(message = "Описание не должно быть пустым")
    @Size(max = 1000)
    private String description;

    @NotNull(message = "Цена товара не должна быть пустой")
    private Double price;

    @NotNull(message = "Размер не должен быть пустым")
    @Size(max = 10)
    private String size;

    @JsonProperty("imageUrl")
    private String imgUrl;

    @NotNull(message = "Рейтинг не должен быть пустым")
    @Min(1)
    @Max(5)
    private Integer rating;
}
