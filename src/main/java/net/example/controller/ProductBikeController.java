package net.example.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import net.example.model.dto.BikeDetailDTO;
import net.example.model.dto.BikeSearchDTO;
import net.example.model.request.SearchInfRequest;
import net.example.service.IBikeProductService;
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
@Api(value = "Car APIs")
public class ProductBikeController {
    @Autowired
    private ICarProductService carProductService;

    @Autowired
    private IBikeProductService bikeProductService;

    @ApiOperation(value = "Tìm nâng cao xe máy", response = BikeSearchDTO.class)
    @ApiResponses({
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Internal Server Error"),
    })
    @PostMapping("/bike")
    public ResponseEntity<?> searchBikeInf(@RequestBody @Valid SearchInfRequest searchInfRequest) {
        List<BikeSearchDTO> bikeSearchDTOS = bikeProductService.searchBikeInf(searchInfRequest);
        return new ResponseEntity<List<BikeSearchDTO>>(bikeSearchDTOS, HttpStatus.OK);
    }

    @ApiOperation(value = "Lấy thông tin chi tiết của xe xe máy thông qua ID", response = BikeDetailDTO.class)
    @ApiResponses({
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Internal Server Error"),
    })
    @GetMapping("/bike")
    public ResponseEntity<?> searchDetailBike(@RequestParam Long id) {
        BikeDetailDTO bikeDetailDTO = bikeProductService.searchBikeDetail(id);
        return ResponseEntity.ok(bikeDetailDTO);
    }

    @ApiOperation(value = "Lấy tất cả thông tin xe máy", response = BikeDetailDTO.class)
    @ApiResponses({
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Internal Server Error"),
    })
    @GetMapping("/all-bike")
    public ResponseEntity<?> findAllBike() {
        List<BikeSearchDTO> bikeSearchDTOS = bikeProductService.findAllBike();
        return ResponseEntity.ok(bikeSearchDTOS);
    }

    @ApiOperation(value = "Lấy tất cả thông tin hãng xe máy", response = BikeDetailDTO.class)
    @ApiResponses({
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Internal Server Error"),
    })
    @GetMapping("/catalog-bike")
    public ResponseEntity<?> findAllBikeCatalog() {
        Set<String> catalog = bikeProductService.findAllBikeCatalog();
        return ResponseEntity.ok(catalog);
    }

}
