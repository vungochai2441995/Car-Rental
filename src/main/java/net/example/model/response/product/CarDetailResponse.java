package net.example.model.response.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.example.model.dto.CarDetailDTO;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarDetailResponse {
    private String message;
    private HttpStatus status;
    private CarDetailDTO carDetailDTO;
}
