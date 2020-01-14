package net.example.service.impl;

import net.example.dao.BikeDAO;
import net.example.dao.BikeDetailDAO;
import net.example.dao.BikeSearchDAOCustom;
import net.example.entity.Bike;
import net.example.model.dto.BikeDetailDTO;
import net.example.model.dto.BikeSearchDTO;
import net.example.model.request.SearchInfRequest;
import net.example.service.IBikeProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

@Component
public class BikeProductService implements IBikeProductService {
    @Autowired
    BikeDAO bikeDAO;

    @Autowired
    BikeSearchDAOCustom bikeSearchDAOCustom;

    @Autowired
    BikeDetailDAO bikeDetailDAO;

    @Override
    public List<BikeSearchDTO> findAllBike() {
        List<Bike> bikes = bikeDAO.findAll();
        List<BikeSearchDTO> bikeSearchDTOS = new CopyOnWriteArrayList<>();
        for (Bike bike : bikes) {
            BikeSearchDTO bikeSearchDTO = new BikeSearchDTO();
            bikeSearchDTO = mapBikeEntityToModel(bike);
            bikeSearchDTOS.add(bikeSearchDTO);
        }
        return bikeSearchDTOS;
    }

    @Override
    public List<BikeSearchDTO> searchBikeInf(SearchInfRequest searchInfRequest) {
        List<BikeSearchDTO> bikeSearchDTOS = new CopyOnWriteArrayList<>();
        List<Bike> bikes = bikeSearchDAOCustom.searchInfBike(searchInfRequest);
        for (Bike bike : bikes) {
            BikeSearchDTO bikeSearchDTO = new BikeSearchDTO();
            bikeSearchDTO = mapBikeEntityToModel(bike);
            bikeSearchDTOS.add(bikeSearchDTO);
        }
        return bikeSearchDTOS;
    }

    @Override
    public BikeDetailDTO searchBikeDetail(Long id) {
        Bike bike = bikeDetailDAO.findOneById(id);
        BikeDetailDTO bikeDetailDTO = new BikeDetailDTO();
        BeanUtils.copyProperties(bike, bikeDetailDTO);
        return bikeDetailDTO;
    }

    @Override
    public Set<String> findAllBikeCatalog() {
        List<Bike> bikes = bikeDAO.findAll();
        Set<String> catalog = new CopyOnWriteArraySet<>();
        for (Bike bike : bikes) {
            catalog.add(bike.getCata());
        }
        return catalog;
    }

    private BikeSearchDTO mapBikeEntityToModel(Bike bike) {
        BikeSearchDTO bikeSearchDTO = new BikeSearchDTO();
        bikeSearchDTO.setGear(bike.getGear());
        bikeSearchDTO.setImage(bike.getUrl());
        bikeSearchDTO.setName(bike.getName());
        bikeSearchDTO.setPrice(bike.getPrice());
        bikeSearchDTO.setLocation(bike.getLocation().getId());
        bikeSearchDTO.setId(bike.getId());
        bikeSearchDTO.setShowroomName(bike.getShowroom());
        return bikeSearchDTO;
    }
}
