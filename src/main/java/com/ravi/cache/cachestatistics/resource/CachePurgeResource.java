package com.ravi.cache.cachestatistics.resource;

import com.ravi.cache.cachestatistics.service.CachePurgeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cache/purge/v1")
@Slf4j
public class CachePurgeResource {

    @Autowired
    private CachePurgeService cachePurgeService;

    @DeleteMapping(path = "/{cacheAliasName}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> clearCache(@PathVariable("cacheAliasName") String cacheAliasName) {
        if(log.isDebugEnabled()) {
            log.debug("START :: removing the cache with name: {}", cacheAliasName);
        }
        cachePurgeService.purgeCache(cacheAliasName);
        String responseString = "The cache "+cacheAliasName+" is purged.";
        if(log.isDebugEnabled()) {
            log.debug("START :: removing the cache with name: {}", cacheAliasName);
        }
        return new ResponseEntity<>(responseString, HttpStatus.OK);
    }

    @DeleteMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> clearAllCache() {
        if(log.isDebugEnabled()) {
            log.debug("START :: removing all caches.");
        }
        cachePurgeService.purgeAllCache();
        String responseString = "All cache were purged.";
        if(log.isDebugEnabled()) {
            log.debug("END :: removing all caches.");
        }
        return new ResponseEntity<>(responseString, HttpStatus.OK);
    }

}
