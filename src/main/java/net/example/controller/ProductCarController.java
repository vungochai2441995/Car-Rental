package net.example.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import net.example.model.dto.*;
import net.example.model.request.SearchInfRequest;
import net.example.service.ICarProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;


@RestController
@CrossOrigin(origins = "*", maxAge = 2592000)
@RequestMapping("")
@Api(value = "Bike APIs")
public class ProductCarController {
    @Autowired
    private ICarProductService carProductService;

    @ApiOperation(value = "Tìm nâng cao xe ô tô", response = CarSearchDTO.class)
    @ApiResponses({
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Internal Server Error"),
    })
    @PostMapping("/car")
    public ResponseEntity<?> searchCarInf(@RequestBody @Valid SearchInfRequest searchInfRequest) {
        List<CarSearchDTO> carSearchDTOS = carProductService.searchCarInf(searchInfRequest);
        return new ResponseEntity<List<CarSearchDTO>>(carSearchDTOS, HttpStatus.OK);
    }

    @ApiOperation(value = "Lấy thông tin chi tiết của xe ô tô thông qua ID", response = CarDetailDTO.class)
    @ApiResponses({
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Internal Server Error"),
    })
    @GetMapping("/car")
    public ResponseEntity<?> searchDetailCar(@RequestParam Long id) {
        CarDetailDTO carDetailDTO = carProductService.searchCarDetail(id);
        return ResponseEntity.ok(carDetailDTO);
    }

    @ApiOperation(value = "Tìm tất cả các xe ô tô", response = Long.class)
    @ApiResponses({
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Internal Server Error"),
    })
    @GetMapping("/all-car")
    public ResponseEntity<?> findAllCar() {
        List<CarSearchDTO> carSearchDTOS = carProductService.findAllCar();
        return ResponseEntity.ok(carSearchDTOS);
    }


    @ApiOperation(value = "Tìm tất cả hãng xe ô tô", response = Set.class)
    @ApiResponses({
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Internal Server Error"),
    })
    @GetMapping("/catalog-car")
    public ResponseEntity<?> findAllCarCatalog() {
        Set<String> catalog = carProductService.findAllCarCatalog();
        return ResponseEntity.ok(catalog);
    }


}
