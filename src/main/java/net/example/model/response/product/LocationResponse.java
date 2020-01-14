package net.example.model.response.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.example.model.dto.LocationDTO;
import net.example.model.dto.UserDTO;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocationResponse {
    private String message;
    private HttpStatus status;
    private List<LocationDTO> locationDTOS;

}
