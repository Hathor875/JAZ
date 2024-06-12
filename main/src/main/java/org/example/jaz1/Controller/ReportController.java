package org.example.jaz1.Controller;

import org.example.jaz1.Service.ReportService;
import org.openapitools.api.ReportsApi;
import org.openapitools.model.ReportCreateRequest;
import org.openapitools.model.ReportResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/reports")
public class ReportController implements ReportsApi {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }


    @PostMapping
    public ResponseEntity<ReportResponse> createReport(@RequestBody ReportCreateRequest reportCreateRequest) {
        return ResponseEntity.ok(reportService.createReport(reportCreateRequest));
    }


    @GetMapping
    public ResponseEntity<List<ReportResponse>> getAllReports() {
        return ResponseEntity.ok(reportService.getAllReports());
    }


    @GetMapping("/{id}")
    public ResponseEntity<ReportResponse> getReportById(@PathVariable UUID id) {
        return ResponseEntity.ok(reportService.getReportById(id));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> reportsIdDelete(@PathVariable UUID id) {
        reportService.deleteReport(id);
        return ResponseEntity.noContent().build();
    }


    @PutMapping("/{id}")
    public ResponseEntity<ReportResponse> updateReport(@PathVariable UUID id, @RequestBody ReportCreateRequest reportCreateRequest) {
        return ResponseEntity.ok(reportService.updateReport(id, reportCreateRequest));
    }
}
