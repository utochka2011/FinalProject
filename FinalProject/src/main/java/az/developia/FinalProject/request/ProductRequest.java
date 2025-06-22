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
    @Size(min = 3, max = 50, message = "Название бренда должно быть от 3 до 50 символов")
    private String brand;

    @NotBlank(message = "Название модели не должно быть пустым")
    @Size(max = 50, message = "Название модели должно быть не более 50 символов")
    private String model;

    @NotBlank(message = "Описание не должно быть пустым")
    @Size(max = 5000, message = "Описание должно быть не более 1000 символов")
    private String description;

    @NotNull(message = "Цена товара не должна быть пустой")
    @DecimalMin(value = "0.0", inclusive = false, message = "Цена должна быть больше 0")
    private Double price;

    @NotNull(message = "Память устройства не должна быть пустой")
    @Min(value = 1, message = "Память должна быть не меньше 1 ГБ")
    @Max(value = 1024, message = "Память должна быть не больше 1024 ГБ")
    private Integer memory;
    @JsonProperty("category")
    private String category;
    @JsonProperty("imageUrl")
    private String imgUrl;

    @NotNull(message = "Рейтинг не должен быть пустым")
    @Min(value = 1, message = "Рейтинг должен быть не меньше 1")
    @Max(value = 5, message = "Рейтинг должен быть не больше 5")
    private Integer rating;
}
