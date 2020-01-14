package net.example.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import net.example.model.dto.LocationDTO;
import net.example.service.ICarProductService;
import net.example.service.ILocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 2592000)
@RequestMapping("")
@Api(value = "Location APIs")
public class LocationController {
    @Autowired
    private ILocationService locationService;

    @ApiOperation(value = "Tìm tất cả các tỉnh", response = LocationDTO.class)
    @ApiResponses({
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Internal Server Error"),
    })

    @GetMapping("/locations")
    public ResponseEntity<?> findAllLocation() {
        List<LocationDTO> locationDTOS = locationService.searchLocation();
        return ResponseEntity.ok(locationDTOS);
    }

    @ApiOperation(value = "Tìm tất cả các tỉnh có trong product", response = LocationDTO.class)
    @ApiResponses({
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Internal Server Error"),
    })

    @GetMapping("/product-location")
    public ResponseEntity<?> findAllLocationProduct() {
        List<LocationDTO> locations = locationService.searchLocationProduct();
        return ResponseEntity.ok(locations);
    }
}
