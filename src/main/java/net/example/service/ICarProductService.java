package net.example.service;

import net.example.dao.CarDao;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ICarProductService {
    public List<String> searchLocation();
}
