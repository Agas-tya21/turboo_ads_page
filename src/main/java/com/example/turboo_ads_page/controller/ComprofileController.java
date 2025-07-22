package com.example.turboo_ads_page.controller;

import com.example.turboo_ads_page.model.Comprofile;
import com.example.turboo_ads_page.service.ComprofileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comprofiles")
@Tag(name = "Comprofile", description = "API for Company Profile CRUD") // Tag untuk grouping di Swagger
public class ComprofileController {

    @Autowired
    private ComprofileService comprofileService;

    @Operation(summary = "Create a new company profile")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Profile created successfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Comprofile.class)) })
    })
    @PostMapping
    public ResponseEntity<Comprofile> createComprofile(@RequestBody Comprofile comprofile) {
        Comprofile newComprofile = comprofileService.createComprofile(comprofile);
        return new ResponseEntity<>(newComprofile, HttpStatus.CREATED);
    }

    @Operation(summary = "Get all company profiles")
    @GetMapping
    public ResponseEntity<List<Comprofile>> getAllComprofiles() {
        List<Comprofile> comprofiles = comprofileService.getAllComprofiles();
        return new ResponseEntity<>(comprofiles, HttpStatus.OK);
    }

    @Operation(summary = "Get a company profile by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the profile",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Comprofile.class)) }),
            @ApiResponse(responseCode = "404", description = "Profile not found", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<Comprofile> getComprofileById(@PathVariable String id) {
        return comprofileService.getComprofileById(id)
                .map(comprofile -> new ResponseEntity<>(comprofile, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Update an existing company profile")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Profile updated successfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Comprofile.class)) }),
            @ApiResponse(responseCode = "404", description = "Profile not found", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<Comprofile> updateComprofile(@PathVariable String id, @RequestBody Comprofile comprofileDetails) {
        try {
            Comprofile updatedComprofile = comprofileService.updateComprofile(id, comprofileDetails);
            return new ResponseEntity<>(updatedComprofile, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Delete a company profile")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Profile deleted successfully", content = @Content),
            @ApiResponse(responseCode = "404", description = "Profile not found", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteComprofile(@PathVariable String id) {
        try {
            comprofileService.deleteComprofile(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}