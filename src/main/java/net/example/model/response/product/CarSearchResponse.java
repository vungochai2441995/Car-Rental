package net.example.model.response.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.example.model.dto.CarSearchDTO;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarSearchResponse {
    private String message;
    private HttpStatus status;
    private List<CarSearchDTO> locationDTOS;
}
