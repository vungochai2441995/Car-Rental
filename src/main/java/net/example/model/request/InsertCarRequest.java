package net.example.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.example.entity.Location;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class InsertCarRequest {
    @NotNull(message = "License is required")
    @NotEmpty(message = "License is required")
    @ApiModelProperty(
            example="29X1-07104",
            notes="License can't be empty",
            required=true
    )
    private String license;

    @NotNull(message = "Deposit is required")
    @NotEmpty(message = "Deposit is required")
    @ApiModelProperty(
            example="10000000",
            notes="Deposit can't be empty",
            required=true
    )
    @Column(name = "deposit",nullable = false)
    private int deposit;

    @NotNull(message = "Name is required")
    @NotEmpty(message = "Name is required")
    @ApiModelProperty(
            example="Lexus 570",
            notes="Name can't be empty",
            required=true
    )
    @Column(name = "name",nullable = false)
    private String name;

    @NotNull(message = "Seat is required")
    @NotEmpty(message = "Seat is required")
    @ApiModelProperty(
            example="4",
            notes="Seat can't be empty",
            required=true
    )
    @Column(name = "seat",nullable = false)
    private int seat;

    @NotNull(message = "Catalog is required")
    @NotEmpty(message = "Catalog is required")
    @ApiModelProperty(
            example="Lexus",
            notes="Catalog can't be empty",
            required=true
    )
    @Column(name="catalog",nullable = false)
    private String cata;

    @NotNull(message = "Gear is required")
    @NotEmpty(message = "Gear is required")
    @ApiModelProperty(
            example="1",
            notes="Gear can't be empty",
            required=true
    )
    @Column(name="gear",nullable = false)
    private int gear;

    @NotNull(message = "Fuel is required")
    @NotEmpty(message = "Fuel is required")
    @ApiModelProperty(
            example="1",
            notes="Fuel can't be empty",
            required=true
    )
    @Column(name="fuel",nullable = false)
    private int fuel;

    @NotNull(message = "Consume is required")
    @NotEmpty(message = "Consume is required")
    @ApiModelProperty(
            example="7",
            notes="Consume can't be empty",
            required=true
    )
    @Column(name="consume",nullable = false)
    private int consume;

    @NotNull(message = "Price is required")
    @NotEmpty(message = "Price is required")
    @ApiModelProperty(
            example="70000",
            notes="Price can't be empty",
            required=true
    )
    @Column(name="price",nullable = false)
    private int price;

    @NotNull(message = "Showroom is required")
    @NotEmpty(message = "Showroom is required")
    @ApiModelProperty(
            example="Royal City",
            notes="Price can't be empty",
            required=true
    )
    @Column(name="showroom",nullable = false)
    private String showroomName;

    @NotNull(message = "Image is required")
    @NotEmpty(message = "Image is required")
    @ApiModelProperty(
            example="http://url",
            notes="Price can't be empty",
            required=true
    )
    @Column(columnDefinition = "TEXT",name = "image",nullable = false)
    private String url;

    @NotNull(message = "Location is required")
    @NotEmpty(message = "Location is required")
    @ApiModelProperty(
            example="1",
            notes="Location can't be empty",
            required=true
    )
    private Location location;
}
