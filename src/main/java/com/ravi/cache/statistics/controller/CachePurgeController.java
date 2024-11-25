package com.ravi.cache.statistics.controller;


import com.ravi.cache.statistics.service.CachePurgeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cache/v1")
@Slf4j
public class CachePurgeController {

    private final CachePurgeService cachePurgeService;

    public CachePurgeController(CachePurgeService cachePurgeService) {
        this.cachePurgeService = cachePurgeService;
    }

    @Operation(summary = "Clears the cache for the given cache", description = "Clears the cache for the given cache")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully cleared the cache",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content)})
    @DeleteMapping(path = "/purge/{cacheAliasName}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> clearCache(@PathVariable("cacheAliasName") String cacheAliasName) {
        if (log.isDebugEnabled()) {
            log.debug("START :: removing the cache with name: {}", cacheAliasName);
        }
        cachePurgeService.purgeCache(cacheAliasName);
        String responseString = "The cache " + cacheAliasName + " is purged.";
        if (log.isDebugEnabled()) {
            log.debug("START :: removing the cache with name: {}", cacheAliasName);
        }
        return ResponseEntity.ok(responseString);
    }

    @Operation(summary = "Clears all cache data", description = "Clears all the data cached")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully cleared the cache",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content)})
    @DeleteMapping(path = "/purge/all", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> clearAllCache() {
        if (log.isDebugEnabled()) {
            log.debug("START :: removing all caches.");
        }
        cachePurgeService.purgeAllCache();
        String responseString = "All cache were purged.";
        if (log.isDebugEnabled()) {
            log.debug("END :: removing all caches.");
        }
        return ResponseEntity.ok(responseString);
    }

    @Operation(summary = "Gets all the cache alias names", description = "Gets all the cache alias names for the cache created")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the data",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content)})
    @GetMapping(path = "/aliases", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<String>> getAllTeachers() {
        List<String> allCacheAlias = cachePurgeService.getAllCacheAlias();
        return ResponseEntity.ok(allCacheAlias);
    }
}
