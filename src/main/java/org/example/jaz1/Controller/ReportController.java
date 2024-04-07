package org.example.jaz1.Controller;

import org.example.jaz1.Entity.ReportCreateRequest;
import org.example.jaz1.Entity.ReportResponse;
import org.example.jaz1.Service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping
    public List<ReportResponse> getAllReports() {
        return reportService.getAllReports();
    }

    @GetMapping("/{id}")
    public ReportResponse getReportById(@PathVariable UUID id) {
        return reportService.getReportById(id);
    }

    @PostMapping
    public ReportResponse createReport(@RequestBody ReportCreateRequest reportCreateRequest) {
        return reportService.createReport(reportCreateRequest);
    }

    @PutMapping("/{id}")
    public ReportResponse updateReport(@PathVariable UUID id, @RequestBody ReportCreateRequest reportCreateRequest) {
        return reportService.updateReport(id, reportCreateRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteReport(@PathVariable UUID id) {
        reportService.deleteReport(id);
    }
}
