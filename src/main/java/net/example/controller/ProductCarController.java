package net.example.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import net.example.entity.Ticket;
import net.example.model.dto.*;
import net.example.model.request.InsertBookingRequest;
import net.example.model.request.SearchInfRequest;
import net.example.model.response.BookTicketResponse;
import net.example.service.ICarProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@CrossOrigin(origins = "*", maxAge = 2592000)
@RequestMapping("")
@Api(value = "Car APIs")
public class ProductCarController {
    @Autowired
    private ICarProductService carProductService;

    @ApiOperation(value="Tìm tất cả các tỉnh", response = LocationDTO.class)
    @ApiResponses({
            @ApiResponse(code = 400, message="Bad request"),
            @ApiResponse(code = 500, message="Internal Server Error"),
    })

    @GetMapping("/locations")
    public ResponseEntity<?> findAllLocation(){
        List<LocationDTO> locationDTOS = carProductService.searchLocation();
        return ResponseEntity.ok(locationDTOS);
    }

    @ApiOperation(value="Tìm nâng cao xe ô tô", response = CarSearchDTO.class)
    @ApiResponses({
            @ApiResponse(code = 400, message="Bad request"),
            @ApiResponse(code = 500, message="Internal Server Error"),
    })
    @PostMapping("/car")
    public ResponseEntity<?> searchCarInf(@RequestBody @Valid SearchInfRequest searchInfRequest){
        List<CarSearchDTO> carSearchDTOS = carProductService.searchCarInf(searchInfRequest);
        return new ResponseEntity<List<CarSearchDTO>>(carSearchDTOS, HttpStatus.OK);
    }

    @ApiOperation(value="Tìm nâng cao xe máy", response = BikeSearchDTO.class)
    @ApiResponses({
            @ApiResponse(code = 400, message="Bad request"),
            @ApiResponse(code = 500, message="Internal Server Error"),
    })
    @PostMapping("/bike")
    public ResponseEntity<?> searchBikeInf(@RequestBody @Valid SearchInfRequest searchInfRequest){
        List<BikeSearchDTO> bikeSearchDTOS = carProductService.searchBikeInf(searchInfRequest);
        return new ResponseEntity<List<BikeSearchDTO>>(bikeSearchDTOS, HttpStatus.OK);
    }

    @ApiOperation(value="Lấy thông tin chi tiết của xe ô tô thông qua ID", response = CarDetailDTO.class)
    @ApiResponses({
            @ApiResponse(code = 400, message="Bad request"),
            @ApiResponse(code = 500, message="Internal Server Error"),
    })
    @GetMapping("/car")
    public ResponseEntity<?> searchDetailCar(@RequestParam Long id){
        CarDetailDTO carDetailDTO = carProductService.searchCarDetail(id);
        return ResponseEntity.ok(carDetailDTO);
    }

    @ApiOperation(value="Lấy thông tin chi tiết của xe xe máy thông qua ID", response = BikeDetailDTO.class)
    @ApiResponses({
            @ApiResponse(code = 400, message="Bad request"),
            @ApiResponse(code = 500, message="Internal Server Error"),
    })
    @GetMapping("/bike")
    public ResponseEntity<?> searchDetailBike(@RequestParam Long id){
        BikeDetailDTO bikeDetailDTO = carProductService.searchBikeDetail(id);
        return ResponseEntity.ok(bikeDetailDTO);
    }


    @ApiOperation(value="Đặt xe", response = Long.class)
    @ApiResponses({
            @ApiResponse(code = 400, message="Bad request"),
            @ApiResponse(code = 500, message="Internal Server Error"),
    })
    @PutMapping("/book")
    public ResponseEntity<?> bookTicket(@RequestBody @Valid InsertBookingRequest insertBookingRequest){
        BookTicketResponse result = carProductService.insertTicket(insertBookingRequest);
        return ResponseEntity.ok(result);
    }

    @ApiOperation(value="Tìm tất cả các xe ô tô", response = Long.class)
    @ApiResponses({
            @ApiResponse(code = 400, message="Bad request"),
            @ApiResponse(code = 500, message="Internal Server Error"),
    })
    @GetMapping("/all-car")
    public ResponseEntity<?> findAllCar(){
        List<CarSearchDTO> carSearchDTOS = carProductService.findAllCar();
        return ResponseEntity.ok(carSearchDTOS);
    }

    @ApiOperation(value="Tìm tất cả xe máy", response = Long.class)
    @ApiResponses({
            @ApiResponse(code = 400, message="Bad request"),
            @ApiResponse(code = 500, message="Internal Server Error"),
    })
    @GetMapping("/all-bike")
    public ResponseEntity<?> findAllBike(){
        List<BikeSearchDTO> bikeSearchDTOS = carProductService.findAllBike();
        return ResponseEntity.ok(bikeSearchDTOS);
    }

    @ApiOperation(value="Tìm tất cả hãng xe máy", response = Long.class)
    @ApiResponses({
            @ApiResponse(code = 400, message="Bad request"),
            @ApiResponse(code = 500, message="Internal Server Error"),
    })
    @GetMapping("/catalog-bike")
    public ResponseEntity<?> findAllBikeCatalog(){
        List<BikeCatalogDTO>  bikeCatalogDTO = carProductService.findAllBikeCatalog();
        return ResponseEntity.ok(bikeCatalogDTO);
    }

    @ApiOperation(value="Tìm tất cả hãng xe ô tô", response = Long.class)
    @ApiResponses({
            @ApiResponse(code = 400, message="Bad request"),
            @ApiResponse(code = 500, message="Internal Server Error"),
    })
    @GetMapping("/catalog-car")
    public ResponseEntity<?> findAllCarCatalog(){
        List<CarCatalogDTO> carCatalogDTOS = carProductService.findAllCarCatalog();
        return ResponseEntity.ok(carCatalogDTOS);
    }

    @ApiOperation(value="Tìm tất cả thông tin đặt xe", response = TicketDTO .class)
    @ApiResponses({
            @ApiResponse(code = 400, message="Bad request"),
            @ApiResponse(code = 500, message="Internal Server Error"),
    })
    @GetMapping("/all-ticket")
    public ResponseEntity<?> findAllTicket(){
        List<TicketDTO> tickets = carProductService.findAllTicket();
        return ResponseEntity.ok(tickets);
    }
}
