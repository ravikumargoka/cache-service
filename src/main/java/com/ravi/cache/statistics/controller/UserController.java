package com.ravi.cache.statistics.controller;

import com.ravi.cache.statistics.entity.User;
import com.ravi.cache.statistics.exception.RecordNotFoundException;
import com.ravi.cache.statistics.manager.UserServiceManager;
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


    private final UserServiceManager userServiceManager;

    public UserController(UserServiceManager userServiceManager) {
        this.userServiceManager = userServiceManager;
    }

    @Operation(summary = "Gets all the users data", description = "Gets all the users data. If it is not available in cache, retrieves from DB and updates the cache")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieve the data",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = User.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content)})
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> userEntities = userServiceManager.getAllUsers();
        return new ResponseEntity<>(userEntities, new HttpHeaders(), HttpStatus.OK);
    }

    @Operation(summary = "Gets the user data for the given id", description = "Get the user data. If it is not available in cache, retrieves from DB and updates the cache")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieve the data",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = User.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content)})
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        User user = userServiceManager.getUserById(id);
        return new ResponseEntity<>(user, new HttpHeaders(), HttpStatus.OK);
    }

    @Operation(summary = "Creates the user", description = "Creates the user. It updates the cache with newly created user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieve the data",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = User.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content)})
    @PostMapping
    public ResponseEntity<User> createOrUpdateUser(User user) throws RecordNotFoundException {
        User updated = userServiceManager.createOrUpdateUser(user);
        return new ResponseEntity<>(updated, new HttpHeaders(), HttpStatus.OK);

    }

    @Operation(summary = "Deletes the user", description = "Deletes the user. It updates the cache to reflect the deleted user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieve the data",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content)})
    @DeleteMapping("/{id}")
    public HttpStatus deleteUserById(@PathVariable("id") Long id)
            throws RecordNotFoundException {
        userServiceManager.deleteUserById(id);
        return HttpStatus.OK;
    }
}
