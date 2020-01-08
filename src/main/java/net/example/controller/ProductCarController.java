package net.example.controller;

import io.swagger.annotations.Api;
import net.example.service.ICarProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("")
@Api(value = "Car APIs")
public class ProductCarController {
    @Autowired
    private ICarProductService carProductService;
//
    @GetMapping(name = "")
    public List<String> findAllLocation(){
        return carProductService.searchLocation();
    }
}
