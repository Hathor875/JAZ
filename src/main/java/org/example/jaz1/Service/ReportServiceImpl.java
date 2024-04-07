package org.example.jaz1.Service;

import org.example.jaz1.Entity.Report;
import org.example.jaz1.Entity.ReportCreateRequest;
import org.example.jaz1.Entity.ReportResponse;
import org.example.jaz1.Repository.ReportRepository;
import org.example.jaz1.mapper.ReportMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;
    private final ReportMapper mapper;

    public ReportServiceImpl(ReportRepository reportRepository, ReportMapper reportMapper, ReportMapper mapper) {
        this.reportRepository = reportRepository;
        this.mapper = mapper;
    }

    @Override
    public List<ReportResponse> getAllReports() {
        return reportRepository
                .findAll()
                .stream()
                .map(mapper::toEntity)
                .collect(Collectors.toList());
    }

    @Override
    public ReportResponse getReportById(UUID id) {
        Report report = reportRepository.findById(id).orElseThrow(() -> new RuntimeException("Report not found"));
        return convertToResponse(report);
    }

    @Override
    public ReportResponse createReport(ReportCreateRequest reportCreateRequest) {
        Report report = new Report();
        report.setDate(LocalDateTime.now());
        report.setTitle(reportCreateRequest.getTitle());
        report.setDescription(reportCreateRequest.getDescription());
        reportRepository.save(report);
        return convertToResponse(report);
    }

    @Override
    public ReportResponse updateReport(UUID id, ReportCreateRequest reportCreateRequest) {
        Report report = reportRepository.findById(id).orElseThrow(() -> new RuntimeException("Report not found"));
        report.setDate(LocalDateTime.now());
        report.setTitle(reportCreateRequest.getTitle());
        report.setDescription(reportCreateRequest.getDescription());
        reportRepository.save(report);
        return convertToResponse(report);
    }
@Override
    public void deleteReport(UUID id) {
        reportRepository.deleteById(id);
    }


    public ReportResponse convertToResponse(Report report) {
        ReportResponse response = new ReportResponse();
        response.setId(report.getId());
        response.setDate(report.getDate());
        response.setTitle(report.getTitle());
        response.setDescription(report.getDescription());
        return response;
    }
}
