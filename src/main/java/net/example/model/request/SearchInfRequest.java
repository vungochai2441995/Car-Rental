package net.example.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SearchInfRequest {
    @NotNull(message = "location is required")
    @NumberFormat
    @ApiModelProperty(
            example="1"
    )
    private int location;

    @NotNull(message = "moneyLow is required")
    @NumberFormat
    @ApiModelProperty(
            example="0",
            notes="Email cannot be empty",
            required=true
    )
    private int moneyLow;

    @NotNull(message = "moneyHigh is required")
    @NumberFormat
    @ApiModelProperty(
            example="500000",
            notes="moneyHigh cannot be empty",
            required=true
    )
    private int moneyHigh;

    @NotNull(message = "startDate is required")
    @ApiModelProperty(
            example="2020-12-12 12-00-00",
            notes="Email cannot be empty",
            required=true
    )
    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd HH-mm-ss")
    private Date startDate;

    @NotNull(message = "seat is required")
    private int seat;

    @NotNull(message = "gear is required")
    private int gear;

    @NotNull(message = "Catalog is required")
    private String cata;

}
