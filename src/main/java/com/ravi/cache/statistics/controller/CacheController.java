package com.ravi.cache.statistics.controller;

import com.ravi.cache.statistics.dto.CacheData;
import com.ravi.cache.statistics.dto.CacheDetails;
import com.ravi.cache.statistics.service.CacheStatisticsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.ravi.cache.statistics.constants.CacheConstants.TEACHERS_CACHE_ALIAS;
import static com.ravi.cache.statistics.constants.CacheConstants.USERS_CACHE_ALIAS;

@RestController
@RequestMapping("/cache/data/v1")
@Slf4j
public class CacheController {
    private final CacheStatisticsService cacheStatisticsService;

    public CacheController(CacheStatisticsService cacheStatisticsService) {
        this.cacheStatisticsService = cacheStatisticsService;
    }

    @Operation(summary = "Get the cache statistics for user cache", description = "Provides the cache statics for the users cache")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved users cache details",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = CacheData.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content)})
    @GetMapping(path = "/users", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CacheData> getUserCache() {
        if (log.isDebugEnabled()) {
            log.debug("START :: Getting user cache");
        }
        CacheData cacheData = cacheStatisticsService.getStatistics(USERS_CACHE_ALIAS, String.class, Object.class);
        if (log.isDebugEnabled()) {
            log.debug("The users list: {} ", cacheData);
            log.debug("END :: Getting user cache");
        }
        return new ResponseEntity<>(cacheData, HttpStatus.OK);
    }

    @Operation(summary = "Get the cache statistics for teachers cache", description = "Provides the cache statics for the teachers cache")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved teachers cache details",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = CacheData.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content)})
    @GetMapping(path = "/teachers", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CacheData> getTeacherCache() {
        if (log.isDebugEnabled()) {
            log.debug("START :: Getting teacher cache");
        }
        CacheData cacheData = cacheStatisticsService.getStatistics(TEACHERS_CACHE_ALIAS, String.class, Object.class);
        if (log.isDebugEnabled()) {
            log.debug("The teachers list: {}", cacheData);
            log.debug("END :: Getting teacher cache");
        }
        return new ResponseEntity<>(cacheData, HttpStatus.OK);
    }

    @Operation(summary = "Get the cache statistics for all cache", description = "Provides the cache statics for all cache")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved all cache details",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = CacheDetails.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content)})
    @GetMapping(path = "/all", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CacheDetails> getAllCache() {
        if (log.isDebugEnabled()) {
            log.debug("START :: Getting all the cache");
        }
        CacheDetails cacheDetails = cacheStatisticsService.getAllCacheStatistics();
        if (log.isDebugEnabled()) {
            log.debug("The all cache list: {}", cacheDetails);
            log.debug("END :: Getting all the cache");
        }
        return new ResponseEntity<>(cacheDetails, HttpStatus.OK);
    }
}
