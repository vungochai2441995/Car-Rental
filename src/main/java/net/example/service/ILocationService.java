package net.example.service;

import net.example.model.dto.LocationDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ILocationService {
    public List<LocationDTO> searchLocation();

    public List<LocationDTO> searchLocationProduct();
}
