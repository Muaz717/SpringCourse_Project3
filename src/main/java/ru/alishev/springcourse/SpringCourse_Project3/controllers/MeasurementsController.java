package ru.alishev.springcourse.SpringCourse_Project3.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.alishev.springcourse.SpringCourse_Project3.dto.MeasurementDTO;
import ru.alishev.springcourse.SpringCourse_Project3.models.Measurement;
import ru.alishev.springcourse.SpringCourse_Project3.services.MeasurementsService;

import java.util.List;
import java.util.stream.Collectors;

import static ru.alishev.springcourse.SpringCourse_Project3.util.ErrorsUtil.returnErrorsToClient;

@RestController
@RequestMapping("/measurements")
public class MeasurementsController {

    private final MeasurementsService measurementsService;
//    private final Measure
    private final ModelMapper modelMapper;

    @Autowired
    public MeasurementsController(MeasurementsService measurementsService, ModelMapper modelMapper) {
        this.measurementsService = measurementsService;
        this.modelMapper = modelMapper;
    }

    @GetMapping()
    public List<MeasurementDTO> getMeasurements() {
        return measurementsService.findAll().stream().map(this::convertToMeasurementDTO).
                collect(Collectors.toList());
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid MeasurementDTO measurementDTO,
                                             BindingResult bindingResult) {

        Measurement measurementToAdd = convertToMeasurement(measurementDTO);

        if (bindingResult.hasErrors()) {
            returnErrorsToClient(bindingResult);
        }

        measurementsService.add(measurementToAdd);

        return ResponseEntity.ok(HttpStatus.OK);
    }


    private MeasurementDTO convertToMeasurementDTO(Measurement measurement) {
        return modelMapper.map(measurement, MeasurementDTO.class);
    }

    private Measurement convertToMeasurement(MeasurementDTO measurementDTO) {
        return modelMapper.map(measurementDTO, Measurement.class);
    }
}
