package net.example.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class ChangePasswordUserRequest {
    @NotNull(message = "Old password is required")
    @NotEmpty(message = "Old password is required")
    @NotBlank
    @ApiModelProperty(
            example="oldPassword",
            notes="old password can't be empty",
            required=true
    )
    private String oldPassword;

    @NotNull(message = "New password is required")
    @NotEmpty(message = "New password is required")
    @NotBlank
    @ApiModelProperty(
            example="newPassword",
            notes="new password can't be empty",
            required=true
    )
    private String newPassword;
}
