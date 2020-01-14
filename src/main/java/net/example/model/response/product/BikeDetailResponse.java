package net.example.model.response.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.example.model.dto.BikeDetailDTO;
import net.example.model.dto.CarDetailDTO;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BikeDetailResponse {
    private String message;
    private HttpStatus status;
    private BikeDetailDTO body;
}
