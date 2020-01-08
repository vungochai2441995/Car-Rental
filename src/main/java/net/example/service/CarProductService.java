package net.example.service;

import net.example.dao.CarDao;
import net.example.dao.ShowroomDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
public class CarProductService implements ICarProductService {
    @Autowired
    ShowroomDAO showroomDAO;

    @Override
    public List<String> searchLocation() {
        return showroomDAO.findDistinctLocation();
    }
}
