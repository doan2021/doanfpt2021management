package com.doanfpt.management.application.controller.advice;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.doanfpt.management.application.controller.DashboardManagementController;

@ControllerAdvice(assignableTypes = { DashboardManagementController.class })
public class DashboardManagementControllerAdvice {

    @ModelAttribute("classActiveDashboardTab")
    public String cssActivePage() {
        return "active";
    }
}
