package net.example.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUsersRequest {
    @NotNull(message = "Username is required")
    @NotEmpty(message = "Username is required")
    private String username;

    @NotNull(message = "address is required")
    private String address;

    @NotNull(message = "email is required")
    @Email(message = "Please provide a valid email")
    private String email;

    @NotNull(message = "Password is required")
    @NotEmpty(message = "Password is required")
    @Size(min = 4, max = 20, message = "Pasword must be between 4 and 20 characters")
    @ApiModelProperty(
            example = "verysecretpassword",
            notes = "Password can't be empty",
            required = true
    )
    private String password;

    @Size(min = 9, max = 15, message = "Please provide a valid phone number")
    @ApiModelProperty(
            example = "0916016972",
            notes = "Phone cannot be empty",
            required = true
    )
    private String phone;
}
