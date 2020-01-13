package net.example.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UpdateUserRequest {
    private String address;

    @Email(message = "Please provide a valid email")
    private String email;

    @NotNull(message = "Password is required")
    @NotEmpty(message = "Password is required")
    @Size(min = 4, max = 20, message = "Pasword must be between 4 and 20 characters")
    @ApiModelProperty(
            example="verysecretpassword",
            notes="Password can't be empty",
            required=true
    )
    private String password;

    @Size(min = 9, max = 15, message = "Please provide a valid phone number")
    @ApiModelProperty(
            example="0916016972"
    )
    private String phone;

    @ApiModelProperty(
            example="https://i.imgur.com/kjrD6Gb.png"
    )
    private String url;
}
