package ru.alishev.springcourse.SpringCourse_Project3.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.alishev.springcourse.SpringCourse_Project3.models.Measurement;
import ru.alishev.springcourse.SpringCourse_Project3.repositories.MeasurementsRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MeasurementsService {

    private final MeasurementsRepository measurementsRepository;

    @Autowired
    public MeasurementsService(MeasurementsRepository measurementsRepository) {
        this.measurementsRepository = measurementsRepository;
    }

    @Transactional
    public List<Measurement> findAll() {
        return measurementsRepository.findAll();
    }

    @Transactional
    public void add(Measurement measurement) {


        measurementsRepository.save(measurement);
    }

    private void enrichMeasurement(Measurement measurement) {
//        measurement.setSensor();

        measurement.setMeasurementDateTime(LocalDateTime.now());

    }
}
