package com.example.demo.controller;

import com.example.demo.entity.mongo.User;
import com.example.demo.service.CollectionService;
import com.example.demo.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@Component
@RequestMapping("/mongo")
public class MongoController {

    @Autowired
    private CollectionService collectionService;
    @Autowired
    private DocumentService documentService;


    @GetMapping("/collection")
    public String getMongoCollections(){
        return collectionService.getCollectionNames().toString();
    }

    @GetMapping("/addCollection")
    public String addCollection(){
        return collectionService.createLimitedCollection();
    }

    @GetMapping("/addDocuments")
    public String addDocuments(){
        documentService.insert();
        return "documents inserted";
    }

    @GetMapping("/findDocuments")
    public String findDocuments(){
        List<User> documents= documentService.findAllDocuments();
        System.out.println("documents:" + documents.size());
        List<String> userNames = new ArrayList<>();
        for (User user: documents) {
            userNames.add(user.getName());
        }
        return userNames.toString();
    }

    @GetMapping("/deleteCollection")
    public String deleteDocuments(){
        documentService.delete();
        return "documents deleted";
    }
}