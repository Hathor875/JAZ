package org.example.jaz1.Service;

import org.example.jaz1.Entity.Report;
import org.example.jaz1.Repository.ReportRepository;
import org.example.jaz1.mapper.ReportMapper;
import org.openapitools.model.ReportCreateRequest;
import org.openapitools.model.ReportResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ReportService {

    private final ReportRepository reportRepository;
    private final ReportMapper mapper;

    public ReportService(ReportRepository reportRepository, ReportMapper mapper) {
        this.reportRepository = reportRepository;
        this.mapper = mapper;
    }


    public List<ReportResponse> getAllReports() {
        return reportRepository
                .findAll()
                .stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }


    public ReportResponse getReportById(UUID id) {
        Report report = reportRepository.findById(id)
                .orElseThrow(() -> new ReportNotFoundException(id));
        return mapper.toResponse(report);
    }


    public ReportResponse createReport(ReportCreateRequest reportCreateRequest) {
        Report report = new Report();
        report.setDate(LocalDateTime.now());
        report.setTitle(reportCreateRequest.getTitle());
        report.setDescription(reportCreateRequest.getDescription());
        reportRepository.save(report);
        return mapper.toResponse(report);
    }


    public ReportResponse updateReport(UUID id, ReportCreateRequest reportCreateRequest) {
        Report report = reportRepository.findById(id)
                .orElseThrow(() -> new ReportNotFoundException(id));
        report.setDate(LocalDateTime.now());
        report.setTitle(reportCreateRequest.getTitle());
        report.setDescription(reportCreateRequest.getDescription());
        reportRepository.save(report);
        return mapper.toResponse(report);
    }


    public void deleteReport(UUID id) {
        reportRepository.deleteById(id);
    }
}
