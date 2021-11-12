package com.diplome.businessassessment.controller;

import com.diplome.businessassessment.model.Metric;
import com.diplome.businessassessment.repository.*;
import com.diplome.businessassessment.service.AssessmentService;
import com.diplome.businessassessment.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;
import java.util.Scanner;

@Controller
public class AdminController {

    @Autowired
    private MetricRepository metricRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private SystemRepository systemRepository;

    @Autowired
    private SystemService systemService;

    @Autowired
    private AssessmentService assessmentService;

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

    @GetMapping("/admin/systems")
    public String getAdminSystemPage(Model model) {
        model.addAttribute("systems", systemService.getSystemWithNamedMetricsAndFunctionality());
        return "admin-systems";
    }

    @GetMapping("/admin/metrics")
    public String getAdminMetricPage(Model model) {
        model.addAttribute("metrics", metricRepository.findAll());
        return "admin-metrics";
    }

    @GetMapping({"/admin/metrics/create-or-update-form/{metricId}", "/admin/metrics/create-or-update-form"})
    public String getAdminCreateUpdateMetricPage(@PathVariable(required = false) Optional<String> metricId, Model model) {
        metricId.ifPresent(s -> model.addAttribute("metric", metricRepository.findById(s)));
        return "createUpdateMetricForm";
    }

    @PostMapping("/admin/metrics/create-or-update")
    public String createUpdateMetric(Metric metric) {
        metricRepository.save(metric);
        return "redirect:/admin/metrics";
    }

    @GetMapping("/admin/answers")
    public String getAdminAnswersPage(Model model) {
        model.addAttribute("answers", answerRepository.findAll());
        return "admin-answers";
    }
}
