package org.example.jaz1.Service;

import org.example.jaz1.Entity.Report;
import org.example.jaz1.Entity.ReportResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interfac ReportMapper {

    @Mapping(target = "id", source = "report.id")
    @Mapping(target = "date", source = "report.date")
    @Mapping(target = "title", source = "report.title")
    @Mapping(target = "description", source = "report.description")
    ReportResponse convertToResponse(Report report);
}