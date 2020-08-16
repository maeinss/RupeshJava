package com.tts.techtalenttwitter.controllers;



import com.tts.techtalenttwitter.entities.UserEntity;
import com.tts.techtalenttwitter.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class AuthorizationController {
    @Autowired
    private UserService userService;

    @Autowired
    public AuthorizationController(UserService userService){
        this.userService = userService;
    }
    @RequestMapping(value = "/login",method = RequestMethod.GET)
//    @GetMapping(value = "/login")
    public String login(){ return "login";}
//    @GetMapping(value="/login")
//    public String login(){
//        return "login";
//    }
    @GetMapping(value="/signup")
    public String registration(Model model){
        UserEntity user = new UserEntity();
        model.addAttribute("user", user);
        return "registration";
    }
    @PostMapping(value = "/signup")
    public String createNewUser(@Valid UserEntity user, BindingResult bindingResult, Model model) {
        UserEntity userExists = userService.findByUsername(user.getUsername());
        if (userExists != null) {
            bindingResult.rejectValue("username", "error.user", "Username is already taken");
        }
        if (!bindingResult.hasErrors()) {
            userService.create(user);
            model.addAttribute("success", "Sign up successful!");
            model.addAttribute("user", new UserEntity());
        }
        return "registration";
    }
}
