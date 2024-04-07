package org.example.jaz1.Service;

import org.example.jaz1.Entity.ReportCreateRequest;
import org.example.jaz1.Entity.ReportResponse;

import java.util.List;
import java.util.UUID;

public interface ReportService {
    List<ReportResponse> getAllReports();
    ReportResponse getReportById(UUID id);
    ReportResponse createReport(ReportCreateRequest reportCreateRequest);
    ReportResponse updateReport(UUID id, ReportCreateRequest reportCreateRequest);
    void deleteReport(UUID id);
}
