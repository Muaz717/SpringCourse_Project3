package ru.alishev.springcourse.SpringCourse_Project3.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.alishev.springcourse.SpringCourse_Project3.models.Sensor;
import ru.alishev.springcourse.SpringCourse_Project3.repositories.SensorRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SensorService {

    private final SensorRepository sensorRepository;

    @Autowired
    public SensorService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    @Transactional
    public List<Sensor> findAll() {
        return sensorRepository.findAll();
    }

    @Transactional
    public Optional<Sensor> findByName(String name) {
        Optional<Sensor> foundSensor = sensorRepository.findByName(name);

        return foundSensor;
    }

    @Transactional
    public void register(Sensor sensor) {

        sensorRepository.save(sensor);
    }

}









