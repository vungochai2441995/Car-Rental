package net.example.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.example.model.dto.UserDTO;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonUserResponse {
    private String message;
    private HttpStatus status;
    private UserDTO userDTO;

}
