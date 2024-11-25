package com.ravi.cache.statistics.controller;

import com.ravi.cache.statistics.delegator.TeacherDelegator;
import com.ravi.cache.statistics.dto.TeacherDTO;
import com.ravi.cache.statistics.exception.RecordNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/teachers/v1")
public class TeacherController {

    private final TeacherDelegator teacherDelegator;

    public TeacherController(TeacherDelegator teacherDelegator) {
        this.teacherDelegator = teacherDelegator;
    }

    @Operation(summary = "Gets all the teachers data", description = "Gets all the teachers data. If it is not available in cache, retrieves from DB and updates the cache")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the data",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = TeacherDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content)})
    @GetMapping
    public ResponseEntity<List<TeacherDTO>> getAllTeachers() {
        List<TeacherDTO> teacherList = teacherDelegator.getAllTeachers();
        return new ResponseEntity<>(teacherList, new HttpHeaders(), HttpStatus.OK);
    }

    @Operation(summary = "Gets the teacher data for the given id", description = "Get the teacher data. If it is not available in cache, retrieves from DB and updates the cache")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the data",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = TeacherDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content)})
    @GetMapping("/{id}")
    public ResponseEntity<TeacherDTO> getTeacherById(@PathVariable("id") Long id) {
        TeacherDTO teacherDTO = teacherDelegator.getTeacherById(id);
        return new ResponseEntity<>(teacherDTO, new HttpHeaders(), HttpStatus.OK);
    }

    @Operation(summary = "Creates the teacher", description = "Creates the teacher. It updates the cache with newly created teacher")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully created the data",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = TeacherDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content)})
    @PostMapping
    public ResponseEntity<TeacherDTO> createTeacher(TeacherDTO teacher) throws RecordNotFoundException {
        TeacherDTO updated = teacherDelegator.createTeacher(teacher);
        return new ResponseEntity<>(updated, new HttpHeaders(), HttpStatus.OK);

    }

    @Operation(summary = "Updates the teacher", description = "Updates the teacher. It updates the cache with the updated teacher")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated the data",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = TeacherDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content)})
    @PatchMapping
    public ResponseEntity<TeacherDTO> updateTeacher(TeacherDTO teacher) throws RecordNotFoundException {
        TeacherDTO updated = teacherDelegator.updateTeacher(teacher);
        return new ResponseEntity<>(updated, new HttpHeaders(), HttpStatus.OK);

    }

    @Operation(summary = "Deletes the teacher", description = "Deletes the teacher. It updates the cache to reflect the deleted teacher")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted the data",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content)})
    @DeleteMapping("/{id}")
    public HttpStatus deleteTeacherById(@PathVariable("id") Long id)
            throws RecordNotFoundException {
        teacherDelegator.deleteTeacherById(id);
        return HttpStatus.OK;
    }
}
