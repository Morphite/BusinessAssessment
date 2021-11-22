package com.diplome.businessassessment.service;

import com.diplome.businessassessment.model.FunctionalityModel;
import com.diplome.businessassessment.model.Metric;
import com.diplome.businessassessment.model.System;
import com.diplome.businessassessment.repository.FunctionalityRepository;
import com.diplome.businessassessment.repository.MetricRepository;
import com.diplome.businessassessment.repository.SystemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SystemService {

    @Autowired
    private SystemRepository systemRepository;

    @Autowired
    private MetricRepository metricRepository;

    @Autowired
    private FunctionalityRepository functionalityRepository;



    public List<System> getSystemWithNamedMetricsAndFunctionality() {
        Map<String, Metric> metrics = metricRepository.findAllMetricsMapWithIdAsKey();
        Map<String, FunctionalityModel> functionalities = functionalityRepository.findAllFunctionalitiesMapWithIdAsKey();

        List<System> systems = systemRepository.findAll();

        systems.forEach(system -> {
            system.setSystemMetrics(system.getSystemMetrics().entrySet().stream()
                    .collect(Collectors.toMap(metricIdValue -> metrics.get(metricIdValue.getKey()).getName(),
                            Map.Entry::getValue)));
        });

        systems.forEach(system -> {
            system.setFunctionality(system.getFunctionality().entrySet().stream()
                    .collect(Collectors.toMap(functionalityIdValue -> functionalities.get(functionalityIdValue.getKey()).getQuestion(),
                            Map.Entry::getValue)));
        });

        return systems;
    }

    public Optional<System> findById(String systemId){
        return systemRepository.findById(systemId);
    }

    public void createOrUpdateSystem(System system) {
        systemRepository.save(system);
    }
}
