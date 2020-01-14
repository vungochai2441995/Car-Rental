package net.example.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    @NotNull(message = "Username is required")
    @NotEmpty(message = "Username is required")
    @Size(min = 4, max = 20, message = "Pasword must be between 4 and 20 characters")
    @ApiModelProperty(
            example = "user",
            notes = "username cannot be empty",
            required = true
    )
    private String username;

    @NotNull(message = "Password is required")
    @NotEmpty(message = "Password is required")
    @ApiModelProperty(
            example = "1234",
            notes = "Password can't be empty",
            required = true
    )
    private String password;
}
