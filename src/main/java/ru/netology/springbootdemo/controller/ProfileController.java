package ru.netology.springbootdemo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.netology.springbootdemo.service.SystemProfile;

@RestController
@RequestMapping("/")
public class ProfileController{
    private SystemProfile profile;

    @Value("${inst.num:1}")
    private int num;

    @GetMapping
    public int getPort(){
        return num;
    }

    public ProfileController(){}
    public ProfileController(SystemProfile profile) {
        this.profile = profile;
    }

    @GetMapping("profile")
    public String getProfile() {
        return profile.getProfile();
    }
}