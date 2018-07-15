package com.test.controllers;

import com.test.services.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    private final MainService mainService;

    @Autowired
    public MainController(final MainService mainService) {
        this.mainService = mainService;
    }

    @RequestMapping(value = "/command", method = RequestMethod.POST)
    private void postComman(@RequestBody String command) {
        mainService.update(command);
    }
}
