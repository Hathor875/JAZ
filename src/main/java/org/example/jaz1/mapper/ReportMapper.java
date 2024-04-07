package org.example.jaz1.mapper;

import org.example.jaz1.Entity.Report;
import org.example.jaz1.Entity.ReportResponse;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR, componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface ReportMapper {
    @Mapping(target = "id", source = "report.id")
    @Mapping(target = "date", source = "report.date")
    @Mapping(target = "title", source = "report.title")
    @Mapping(target = "description", source = "report.description")
    ReportResponse toEntity(Report report);
}
