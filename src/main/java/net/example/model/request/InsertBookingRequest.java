package net.example.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class InsertBookingRequest {
    @NotNull(message = "type is required")
    @ApiModelProperty(
            example = "1",
            notes = "type can't be empty",
            required = true
    )
    private int type;

    @NotNull(message = "vehicle_id is required")
    @ApiModelProperty(
            example = "1",
            notes = "vehicle_id can't be empty",
            required = true
    )
    private Long vehicle_id;

    @NotNull(message = "endDate is required")
    @ApiModelProperty(
            example = "2021-12-12 23-59-59",
            notes = "endDate can't be empty",
            required = true
    )
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH-mm-ss")
    private Date endDate;

    @NotNull(message = "startDate is required")
    @ApiModelProperty(
            example = "2020-12-12 23-59-59",
            notes = "startDate can't be empty",
            required = true
    )
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH-mm-ss")
    private Date startDate;
}
