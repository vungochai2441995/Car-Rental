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
    @NotNull
    @NumberFormat
    @ApiModelProperty(
            example="1"
    )
    private int location;
    private int moneyLow;

    @ApiModelProperty(
            example="500000",
            required=true
    )
    private int moneyHigh;

    @ApiModelProperty(
            example="2020-12-12 12-00-00",
            notes="Email cannot be empty",
            required=true
    )
    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd HH-mm-ss")
    private Date startDate;

    private int seat;
    private int gear;

    private String cata;

}
