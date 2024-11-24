package com.ravi.cache.statistics.controller;

import com.ravi.cache.statistics.delegator.UserDelegator;
import com.ravi.cache.statistics.dto.UserDTO;
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
@RequestMapping("/users/v1")
public class UserController {


    private final UserDelegator userDelegator;

    public UserController(UserDelegator userDelegator) {
        this.userDelegator = userDelegator;
    }

    @Operation(summary = "Gets all the users data", description = "Gets all the users data. If it is not available in cache, retrieves from DB and updates the cache")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the data",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UserDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content)})
    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> userEntities = userDelegator.getAllUsers();
        return new ResponseEntity<>(userEntities, new HttpHeaders(), HttpStatus.OK);
    }

    @Operation(summary = "Gets the user data for the given id", description = "Get the user data. If it is not available in cache, retrieves from DB and updates the cache")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the data",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UserDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content)})
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("id") Long id) {
        UserDTO userDTO = userDelegator.getUserById(id);
        return new ResponseEntity<>(userDTO, new HttpHeaders(), HttpStatus.OK);
    }

    @Operation(summary = "Creates the user", description = "Creates the user. It updates the cache with newly created user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully created the data",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UserDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content)})
    @PostMapping
    public ResponseEntity<UserDTO> createUser(UserDTO userDTO) throws RecordNotFoundException {
        UserDTO updated = userDelegator.createUser(userDTO);
        return new ResponseEntity<>(updated, new HttpHeaders(), HttpStatus.OK);

    }

    @Operation(summary = "Updates the user", description = "Updates the user. It updates the cache with newly created user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated the data",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UserDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content)})
    @PatchMapping
    public ResponseEntity<UserDTO> updateUser(UserDTO userDTO) throws RecordNotFoundException {
        UserDTO updated = userDelegator.updateUser(userDTO);
        return new ResponseEntity<>(updated, new HttpHeaders(), HttpStatus.OK);

    }

    @Operation(summary = "Deletes the user", description = "Deletes the user. It updates the cache to reflect the deleted user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted the data",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content)})
    @DeleteMapping("/{id}")
    public HttpStatus deleteUserById(@PathVariable("id") Long id)
            throws RecordNotFoundException {
        userDelegator.deleteUserById(id);
        return HttpStatus.OK;
    }
}
