package ru.alishev.springcourse.SpringCourse_Project3.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.alishev.springcourse.SpringCourse_Project3.models.Measurement;
import ru.alishev.springcourse.SpringCourse_Project3.repositories.MeasurementRepository;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class MeasurementService {

    private final MeasurementRepository measurementsRepository;
    private final SensorService sensorService;

    @Autowired
    public MeasurementService(MeasurementRepository measurementsRepository, SensorService sensorService) {
        this.measurementsRepository = measurementsRepository;
        this.sensorService = sensorService;
    }

    @Transactional
    public List<Measurement> findAll() {
        return measurementsRepository.findAll();
    }

    @Transactional
    public void add(Measurement measurement) {
        enrichMeasurement(measurement);

        measurementsRepository.save(measurement);
    }

    private void enrichMeasurement(Measurement measurement) {
        measurement.setSensor(sensorService.findByName(measurement.getSensor().getName()).get());

        measurement.setMeasurementDateTime(LocalDateTime.now());
    }
}
