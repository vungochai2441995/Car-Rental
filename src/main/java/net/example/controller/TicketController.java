package net.example.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import net.example.model.dto.TicketDTO;
import net.example.model.request.InsertBookingRequest;
import net.example.model.response.BookTicketResponse;
import net.example.service.ICarProductService;
import net.example.service.ITicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 2592000)
@RequestMapping("")
@Api(value = "Ticket APIs")
public class TicketController {
    @Autowired
    private ITicketService ticketService;

    @ApiOperation(value = "Tìm tất cả thông tin đặt xe", response = TicketDTO.class)
    @ApiResponses({
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Internal Server Error"),
    })
    @GetMapping("/all-ticket")
    public ResponseEntity<?> findAllTicket() {
        List<TicketDTO> tickets = ticketService.findAllTicket();
        return ResponseEntity.ok(tickets);
    }

    @ApiOperation(value = "Đặt xe", response = Long.class)
    @ApiResponses({
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Internal Server Error"),
    })
    @PostMapping("/book")
    public ResponseEntity<?> bookTicket(@RequestBody @Valid InsertBookingRequest insertBookingRequest) {
        BookTicketResponse result = ticketService.insertTicket(insertBookingRequest);
        return ResponseEntity.ok(result);
    }
}
