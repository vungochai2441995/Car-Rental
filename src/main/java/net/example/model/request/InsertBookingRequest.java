package net.example.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class InsertBookingRequest {
    private int type;
    private Long user_id;
    private Long vehicle_id;
    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd HH-mm-ss" )
    private Date endDate;
    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd HH-mm-ss" )
    private Date startDate;
}
