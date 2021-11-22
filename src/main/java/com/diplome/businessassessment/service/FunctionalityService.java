package com.diplome.businessassessment.service;

import com.diplome.businessassessment.model.FunctionalityModel;
import com.diplome.businessassessment.model.Metric;
import com.diplome.businessassessment.model.System;
import com.diplome.businessassessment.repository.FunctionalityRepository;
import com.diplome.businessassessment.repository.SystemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class FunctionalityService {

    @Autowired
    private FunctionalityRepository functionalityRepository;

    @Autowired
    private SystemRepository systemRepository;

    public void createOrUpdateFunctionality(FunctionalityModel functionality) {
        Optional<FunctionalityModel> functionalityForSaveOrUpdate = functionalityRepository.findById(functionality.getId());
        functionalityRepository.save(functionality);

        if (functionalityForSaveOrUpdate.isEmpty()) {
            List<System> systems = systemRepository.findAll();
            systems.forEach(system -> system.getFunctionality().put(functionality.getId(), false));
            systemRepository.saveAll(systems);
        }
    }

    public List<FunctionalityModel> findAll() {
        return functionalityRepository.findAll();
    }

    public Optional<FunctionalityModel> findById(String metricId) {
        return functionalityRepository.findById(metricId);
    }

    public Map<String, FunctionalityModel> findAllFunctionalitiesMapWithIdAsKey() {
        return functionalityRepository.findAllFunctionalitiesMapWithIdAsKey();
    }

    public void deleteById(String functionalityId) {
        Optional<FunctionalityModel> functionalityForDelete = functionalityRepository.findById(functionalityId);

        if (functionalityForDelete.isPresent()) {
            List<System> systems = systemRepository.findAll();
            systems.forEach(system -> system.getFunctionality().remove(functionalityId));

            functionalityRepository.deleteById(functionalityId);
            systemRepository.saveAll(systems);
        }

    }
}
