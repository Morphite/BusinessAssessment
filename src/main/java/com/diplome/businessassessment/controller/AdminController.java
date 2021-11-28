package com.diplome.businessassessment.controller;

import com.diplome.businessassessment.model.Answer;
import com.diplome.businessassessment.model.FunctionalityModel;
import com.diplome.businessassessment.model.Metric;
import com.diplome.businessassessment.model.System;
import com.diplome.businessassessment.repository.*;
import com.diplome.businessassessment.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class AdminController {

    @Autowired
    private MetricService metricService;

    @Autowired
    private MetricRepository metricRepository;

    @Autowired
    private FunctionalityService functionalityService;

    @Autowired
    private SystemService systemService;

    @Autowired
    private AssessmentService assessmentService;

    @Autowired
    private AnswerService answerService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/admin")
    public String getAdminPage() {
        return "admin-panel";
    }

    @GetMapping("/admin/users")
    public String getAdminUsersPage(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "admin-users";
    }

    @GetMapping("/admin/assessments")
    public String getAdminAssessmentsPage(Model model) {
        model.addAttribute("assessments", assessmentService.findAllAssessmentsWithSystemNameAsKey());
        return "admin-assessments";
    }

    // ======================== SYSTEMS ===============================================================================

    @GetMapping("/admin/systems")
    public String getAdminSystemPage(Model model) {
        model.addAttribute("systems", systemService.getSystemWithNamedMetricsAndFunctionality());
        return "admin-systems";
    }

    @GetMapping({"/admin/systems/create-or-update-form/{systemId}", "/admin/systems/create-or-update-form"})
    public String getAdminCreateUpdateSystemPage(@PathVariable(required = false) Optional<String> systemId, Model model) {

        if (systemId.isPresent()) {
            System system = systemService.findById(systemId.get()).orElse(null);
            model.addAttribute("system", system);
        } else {
            model.addAttribute("systemMetrics", metricService.findAllMetricsMapWithIdAsKey());
            model.addAttribute("functionality", functionalityService.findAllFunctionalitiesMapWithIdAsKey());
        }


        model.addAttribute("metricMap", metricService.findAllMetricsMapWithIdAsKey());
        model.addAttribute("functionalityMap", functionalityService.findAllFunctionalitiesMapWithIdAsKey());

        return "createUpdateSystemForm";
    }

    @PostMapping("/admin/systems/create-or-update")
    public String createUpdateSystem(System system) {
        java.lang.System.out.println(system);
        systemService.createOrUpdateSystem(system);
        return "redirect:/admin/systems";
    }

    // ======================== FUNCTIONALITIES =======================================================================

    @GetMapping("/admin/functionalities")
    public String getAdminFunctionalityPage(Model model) {
        model.addAttribute("functionalities", functionalityService.findAll());
        return "admin-functionalities";
    }

    @GetMapping({"/admin/functionalities/create-or-update-form/{functionalityId}", "/admin/functionalities/create-or-update-form"})
    public String getAdminCreateUpdateFunctionalityPage(@PathVariable(required = false) Optional<String> functionalityId, Model model) {
        functionalityId.ifPresent(s -> model.addAttribute("functionality",
                functionalityService.findById(s).orElse(null)));
        return "createUpdateFunctionalityForm";
    }

    @PostMapping("/admin/functionalities/create-or-update")
    public String createUpdateFunctionality(FunctionalityModel functionality) {
        functionalityService.createOrUpdateFunctionality(functionality);
        return "redirect:/admin/functionalities";
    }

    @PostMapping("/admin/functionalities/delete")
    public String deleteFunctionality(String functionalityId) {
        functionalityService.deleteById(functionalityId);
        return "redirect:/admin/functionalities";
    }

    // ======================== METRICS ===============================================================================

    @GetMapping("/admin/metrics")
    public String getAdminMetricPage(Model model) {
        model.addAttribute("metrics", metricService.getAllMetrics());
        return "admin-metrics";
    }

    @GetMapping({"/admin/metrics/create-or-update-form/{metricId}", "/admin/metrics/create-or-update-form"})
    public String getAdminCreateUpdateMetricPage(@PathVariable(required = false) Optional<String> metricId, Model model) {
        metricId.ifPresent(s -> model.addAttribute("metric",
                metricService.findById(s).orElse(null)));
        return "createUpdateMetricForm";
    }

    @PostMapping("/admin/metrics/create-or-update")
    public String createUpdateMetric(Metric metric) {
        metricService.createOrUpdateMetric(metric);
        return "redirect:/admin/metrics";
    }

    @PostMapping("/admin/metrics/delete")
    public String deleteMetric(String metricId) {
        metricService.deleteById(metricId);
        return "redirect:/admin/metrics";
    }

    // ======================== ANSWERS ===============================================================================

    @GetMapping("/admin/answers")
    public String getAdminAnswersPage(Model model) {
        model.addAttribute("answers", answerService.findAll());
        return "admin-answers";
    }

    @GetMapping({"/admin/answers/create-or-update-form/{answerId}", "/admin/answers/create-or-update-form"})
    public String createUpdateAnswer(@PathVariable(required = false) Optional<String> answerId, Model model) {
        answerId.ifPresent(s -> model.addAttribute("answer",
                answerService.findById(s).orElse(null)));
        return "createUpdateAnswerForm";
    }

    @PostMapping("/admin/answers/create-or-update")
    public String postCreateUpdateAnswer(Answer answer) {
        answerService.createOrUpdateAnswer(answer);
        return "redirect:/admin/answers";
    }
}
