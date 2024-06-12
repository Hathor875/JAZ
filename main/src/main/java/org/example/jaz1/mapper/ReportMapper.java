package org.example.jaz1.mapper;

import org.example.jaz1.Entity.Report;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.openapitools.model.ReportResponse;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR, builder = @Builder(disableBuilder = true))
public interface ReportMapper {
    ReportMapper INSTANCE = Mappers.getMapper(ReportMapper.class);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "date", source = "date")
    @Mapping(target = "title", source = "title")
    @Mapping(target = "description", source = "description")
    ReportResponse toResponse(Report report);

    default OffsetDateTime map(LocalDateTime date) {
        return date == null ? null : date.atOffset(ZoneOffset.UTC);
    }
}
