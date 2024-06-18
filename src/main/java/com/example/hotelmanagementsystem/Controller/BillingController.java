package com.example.hotelmanagementsystem.Controller;

import com.example.hotelmanagementsystem.DTO.BillingDTO;
import com.example.hotelmanagementsystem.DTO.EmployeeDTO;
import com.example.hotelmanagementsystem.Service.Interface.BillingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "Billing")
@RestController
@RequestMapping("/api/v1/billing")

public class BillingController {

    private BillingService billingService;

    public BillingController(BillingService billingService) {
        this.billingService = billingService;
    }

    @ApiOperation(value = "Get Billing by ID")
    @GetMapping("/{id}")
    @Operation(
            security = @SecurityRequirement(name = "token"),
            description = "Get Billing by id service",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully get Billing by id!",
                            content = @Content(
                                    mediaType ="application/json",
                                    examples = {
                                            @ExampleObject(
                                                    value = "{\"code\" : 200, \"Status\" : \"Ok!\", \"Message\" :\"Successfully get Billing by id!\"}"
                                            ),
                                    }
                            )
                    )
            }
    )
    public ResponseEntity<BillingDTO> getBillingById(@PathVariable long id, Authentication auth) {

        BillingDTO billingDTO = billingService.getBillingById(id);
        return ResponseEntity.ok(billingDTO);

    }

    @ApiOperation(value = "Get all Billing")
    @GetMapping
    @Operation(
            security = @SecurityRequirement(name = "token"),
            description = "Get all Billing service",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully get all Billing!",
                            content = @Content(
                                    mediaType ="application/json",
                                    examples = {
                                            @ExampleObject(
                                                    value = "{\"code\" : 200, \"Status\" : \"Ok!\", \"Message\" :\"Successfully get all Billing!\"}"
                                            ),
                                    }
                            )
                    )
            }
    )
    public ResponseEntity<List<BillingDTO>> getAllBilling (){

        return ResponseEntity.ok().body(billingService.getAllBilling());
    }

    @ApiOperation(value = "Create a new Billing")
    @PostMapping
    @Operation(
            security = @SecurityRequirement(name = "token"),
            description = "Create Billing service",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Successfully create Billing!",
                            content = @Content(
                                    mediaType ="application/json",
                                    examples = {
                                            @ExampleObject(
                                                    value = "{\"code\" : 201, \"Status\" : \"Created!\", \"Message\" :\"Successfully create Billing!\"}"
                                            ),
                                    }
                            )
                    )
            }
    )
    public ResponseEntity<BillingDTO> createBilling(@RequestBody BillingDTO billingDTO) {

        return ResponseEntity.ok(billingService.createBilling(billingDTO));
    }

    @ApiOperation(value = "Update Billing")
    @PutMapping("/{id}")
    @Operation(
            security = @SecurityRequirement(name = "token"),
            description = "Update Billing info service",
            responses = {
                    @ApiResponse(
                            responseCode = "202",
                            description = "Successfully Update Billing info!",
                            content = @Content(
                                    mediaType ="application/json",
                                    examples = {
                                            @ExampleObject(
                                                    value = "{\"code\" : 202, \"Status\" : \"Accepted!\", \"Message\" :\"Successfully Update Billing info!\"}"
                                            ),
                                    }
                            )
                    )
            }
    )
    public ResponseEntity<BillingDTO> updateBilling(@PathVariable long id, @RequestBody BillingDTO billingDTO) {
        BillingDTO updatedBilling = billingService.updateBilling(billingDTO , id);
        return ResponseEntity.ok(updatedBilling);
    }


}
