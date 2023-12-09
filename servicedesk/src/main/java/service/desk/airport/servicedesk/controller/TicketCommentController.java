package service.desk.airport.servicedesk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import service.desk.airport.servicedesk.dto.ticketcomment.TicketCommentCreateRequest;
import service.desk.airport.servicedesk.dto.ticketcomment.TicketCommentResponse;
import service.desk.airport.servicedesk.security.service.JwtService;
import service.desk.airport.servicedesk.service.TicketCommentService;

import java.security.InvalidParameterException;
import java.util.List;

@CrossOrigin(origins = "*", methods = {RequestMethod.POST,RequestMethod.DELETE,RequestMethod.GET,RequestMethod.PUT})
@RestController
@RequestMapping(path="/ticket-comment")
public class TicketCommentController {
    @Autowired
    JwtService jwtService;

    @Autowired
    TicketCommentService ticketCommentService;

    @PostMapping("/create")
    public ResponseEntity<TicketCommentResponse> createTicketComment(
            @RequestBody TicketCommentCreateRequest request,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {

        var email = jwtService.extractUsername(token.substring(7));
        request.setUserEmail(email);
        try {
            return ResponseEntity.ok(ticketCommentService.createTicketComment(request));
        } catch (InvalidParameterException e) {
            return  ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }
         catch (Exception e) {
            return new  ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/ticket/{id}")
    public ResponseEntity<List<TicketCommentResponse>> getTicketCommentsForTicket(
            @PathVariable("id") Integer ticketId,@RequestHeader(HttpHeaders.AUTHORIZATION) String token
    ) {
        var email = jwtService.extractUsername(token.substring(7));

        try {
            return ResponseEntity.ok(ticketCommentService.getTicketComments(ticketId,email));
        } catch (Exception e) {
            return  ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }
    }



}
