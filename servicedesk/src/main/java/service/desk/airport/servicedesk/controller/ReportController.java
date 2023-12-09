package service.desk.airport.servicedesk.controller;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import service.desk.airport.servicedesk.dto.report.ReportCreateRequest;
import service.desk.airport.servicedesk.dto.report.ReportResponse;
import service.desk.airport.servicedesk.dto.report.ReportShortResponse;
import service.desk.airport.servicedesk.security.service.JwtService;
import service.desk.airport.servicedesk.service.ReportService;

import java.util.List;

@CrossOrigin(origins = "*", methods = {RequestMethod.POST,RequestMethod.DELETE,RequestMethod.GET,RequestMethod.PUT})
@RestController
@RequestMapping("/report")
public class ReportController {

    @Autowired
    JwtService jwtService;

    @Autowired
    ReportService reportService;

    @PreAuthorize("hasRole('sd_agent')")
    @PostMapping("/add")
    public ResponseEntity<ReportResponse> createReport(@RequestBody ReportCreateRequest request,
                                                       @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        var email = jwtService.extractUsername(token.substring(7));
        request.setResolvedByEmail(email);

        return ResponseEntity.ok(reportService.createReport(request));
    }

    @PreAuthorize("hasRole('sd_agent')")
    @GetMapping("/{id}")
    public ResponseEntity<ReportResponse> getReport(@PathVariable("id") Integer id) {

        return ResponseEntity.ok(reportService.getReportById(id));
    }

    @PreAuthorize("hasRole('sd_agent')")
    @GetMapping("/all")
    public ResponseEntity<List<ReportShortResponse>> getAllReports() {
        return ResponseEntity.ok(reportService.getAllReports());
    }
}
