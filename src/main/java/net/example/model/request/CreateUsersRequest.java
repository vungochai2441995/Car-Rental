package net.example.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateUsersRequest {

    @Email(message = "Please provide a valid email")
    private String email;

    @NotNull(message = "Username is required")
    @NotEmpty(message = "Username is required")

    private String username;
    private String address;

    @NotNull(message = "Password is required")
    @NotEmpty(message = "Password is required")
    @Size(min = 4, max = 20, message = "Pasword must be between 4 and 20 characters")
    @ApiModelProperty(
            example="verysecretpassword",
            notes="Password can't be empty",
            required=true
    )
    private String password;

    @Pattern(regexp="(09|01[2|6|8|9])+([0-9]{8})\\b", message = "Please provide a valid phone number")
    @ApiModelProperty(
            example="0916016972",
            notes="Phone cannot be empty",
            required=true
    )
    private String phone;
}
