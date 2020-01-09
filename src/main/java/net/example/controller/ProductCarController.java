package net.example.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import net.example.model.dto.BikeSearchDTO;
import net.example.model.dto.CarSearchDTO;
import net.example.model.dto.LocationDTO;
import net.example.model.request.SearchInfRequest;
import net.example.service.ICarProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@Api(value = "Car APIs")
public class ProductCarController {
    @Autowired
    private ICarProductService carProductService;

    @ApiOperation(value="Get list locations", response = LocationDTO.class)
    @ApiResponses({
            @ApiResponse(code = 400, message="Bad request"),
            @ApiResponse(code = 500, message="Internal Server Error"),
    })

    @GetMapping(name = "")
    public ResponseEntity<?> findAllLocation(){
        List<LocationDTO> locationDTOS = carProductService.searchLocation();
        return ResponseEntity.ok(locationDTOS);
    }


    @ApiOperation(value="Get list car", response = CarSearchDTO.class)
    @ApiResponses({
            @ApiResponse(code = 400, message="Bad request"),
            @ApiResponse(code = 500, message="Internal Server Error"),
    })
    @PostMapping("/car")
    public ResponseEntity<?> searchCarInf(@RequestBody SearchInfRequest searchInfRequest){
        List<CarSearchDTO> carSearchDTOS = carProductService.searchCarInf(searchInfRequest);
        return new ResponseEntity<List<CarSearchDTO>>(carSearchDTOS, HttpStatus.OK);
    }

    @ApiOperation(value="Get list bike", response = BikeSearchDTO.class)
    @ApiResponses({
            @ApiResponse(code = 400, message="Bad request"),
            @ApiResponse(code = 500, message="Internal Server Error"),
    })
    @PostMapping("/bike")
    public ResponseEntity<?> searchBikeInf(@RequestBody SearchInfRequest searchInfRequest){
        List<BikeSearchDTO> bikeSearchDTOS = carProductService.searchBikeInf(searchInfRequest);
        return new ResponseEntity<List<BikeSearchDTO>>(bikeSearchDTOS, HttpStatus.OK);
    }

//    @ApiOperation(value="Get detail information bike", response = BikeSearchDTO.class)
//    @ApiResponses({
//            @ApiResponse(code = 400, message="Bad request"),
//            @ApiResponse(code = 500, message="Internal Server Error"),
//    })
//    @PostMapping("/bikeInf")
//    public ResponseEntity<?> searchInfBike(@RequestParam SearchInfRequest searchInfRequest){
//        List<BikeSearchDTO> bikeSearchDTOS = carProductService.searchBikeInf(searchInfRequest);
//        return ResponseEntity.ok(bikeSearchDTOS);
//    }

}
