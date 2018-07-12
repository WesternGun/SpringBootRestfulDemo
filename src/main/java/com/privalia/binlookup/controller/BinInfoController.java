package com.privalia.binlookup.controller;

import java.awt.MediaTracker;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.privalia.binlookup.model.BinInfo;
import com.privalia.binlookup.repo.BinInfoRepository;

@RestController
@RequestMapping(value="/")
public class BinInfoController {
    
    @Autowired
    private BinInfoRepository repository;
    
    @RequestMapping(value="/search/{bin}", method=RequestMethod.GET)
    public ResponseEntity<BinInfo> searchBIN(@Valid @PathVariable String bin) {
        List<BinInfo> found = repository.findAllByBin(bin);
        BodyBuilder builder = ResponseEntity.status(HttpStatus.OK);
        builder.contentType(MediaType.APPLICATION_JSON_UTF8);
        return (found.isEmpty() ? builder.body(null) : builder.body(found.get(0)));
    }
    
    @RequestMapping(value="/insert", method=RequestMethod.POST)
    public ResponseEntity<BinInfo> insertBIN(@Valid @RequestBody BinInfo bin_info) {
        repository.save(bin_info);
        BodyBuilder builder = (BodyBuilder) ResponseEntity.status(HttpStatus.CREATED);
        return builder.contentType(MediaType.APPLICATION_JSON_UTF8).body(bin_info);
    }

}
