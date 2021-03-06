package net.example.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UpdateUserRequest {
    @NotNull(message = "address is required")
    private String address;

    @Email(message = "Please provide a valid email")
    private String email;

    @NotNull(message = "phone is required")
    @Size(min = 9, max = 15, message = "Please provide a valid phone number")
    @ApiModelProperty(
            example = "0916016972"
    )
    private String phone;

    @NotNull(message = "Url is required")
    @ApiModelProperty(
            example = "https://i.imgur.com/kjrD6Gb.png"
    )
    private String url;
}
