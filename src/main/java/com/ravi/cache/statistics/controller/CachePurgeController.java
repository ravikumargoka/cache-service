package com.ravi.cache.statistics.controller;


import com.ravi.cache.statistics.service.CachePurgeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cache/purge/v1")
@Slf4j
public class CachePurgeController {

    private final CachePurgeService cachePurgeService;

    public CachePurgeController(CachePurgeService cachePurgeService) {
        this.cachePurgeService = cachePurgeService;
    }

    @DeleteMapping(path = "/{cacheAliasName}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> clearCache(@PathVariable("cacheAliasName") String cacheAliasName) {
        if (log.isDebugEnabled()) {
            log.debug("START :: removing the cache with name: {}", cacheAliasName);
        }
        cachePurgeService.purgeCache(cacheAliasName);
        String responseString = "The cache " + cacheAliasName + " is purged.";
        if (log.isDebugEnabled()) {
            log.debug("START :: removing the cache with name: {}", cacheAliasName);
        }
        return new ResponseEntity<>(responseString, HttpStatus.OK);
    }

    @DeleteMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> clearAllCache() {
        if (log.isDebugEnabled()) {
            log.debug("START :: removing all caches.");
        }
        cachePurgeService.purgeAllCache();
        String responseString = "All cache were purged.";
        if (log.isDebugEnabled()) {
            log.debug("END :: removing all caches.");
        }
        return new ResponseEntity<>(responseString, HttpStatus.OK);
    }
}
