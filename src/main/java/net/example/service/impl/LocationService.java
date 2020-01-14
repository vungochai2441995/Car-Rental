package net.example.service.impl;

import net.example.dao.LocationDAO;
import net.example.entity.Location;
import net.example.model.dto.LocationDTO;
import net.example.service.ILocationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class LocationService implements ILocationService {
    @Autowired
    LocationDAO locationDAO;

    @Override
    public List<LocationDTO> searchLocation() {

        List<Location> locations = locationDAO.findAll();
        List<LocationDTO> locationDTOS = new CopyOnWriteArrayList<>();
        for (Location location : locations) {
            LocationDTO locationDTO = new LocationDTO();
            BeanUtils.copyProperties(location, locationDTO);
            locationDTOS.add(locationDTO);
        }
        return locationDTOS;
    }

    @Override
    public List<LocationDTO> searchLocationProduct() {
        List<Location> locations = locationDAO.findAllLocationInProduction();
        List<LocationDTO> locationDTOS = new CopyOnWriteArrayList<>();
        for (Location location : locations) {
            LocationDTO locationDTO = new LocationDTO();
            BeanUtils.copyProperties(location, locationDTO);
            locationDTOS.add(locationDTO);
        }
        return locationDTOS;
    }
}
