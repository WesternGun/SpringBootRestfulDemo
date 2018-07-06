package com.privalia.mydemo.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.privalia.mydemo.model.BinInfo;
import com.privalia.mydemo.repo.BinInfoRepository;

@RestController
@RequestMapping(value="/mydemo", consumes=MediaType.APPLICATION_JSON_UTF8_VALUE, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
public class MyDemoController {
    
    @Autowired
    private BinInfoRepository repository;
    
    @Value("${greeting.message}")
    private String message;
    
    @RequestMapping("/")
    public String index() {
        return message;
    }
    
    
    @RequestMapping(value="/bin/{bin}", method=RequestMethod.GET)
    public BinInfo searchBIN(@Valid @PathVariable String bin) {
        BodyBuilder builder = ResponseEntity.status(HttpStatus.OK);
        builder.contentType(MediaType.APPLICATION_JSON_UTF8);
        List<BinInfo> found = repository.findAllByBin(bin);
        return (found.isEmpty() ? null : found.get(0));
    }
    
    @RequestMapping(value="/bin", method=RequestMethod.PUT)
    public BinInfo insertBIN(@Valid @RequestBody BinInfo bin_info) {
        repository.save(bin_info);
        BodyBuilder builder = (BodyBuilder) ResponseEntity.status(HttpStatus.OK);
        builder.contentType(MediaType.APPLICATION_JSON_UTF8);
        return bin_info;
    }

}
