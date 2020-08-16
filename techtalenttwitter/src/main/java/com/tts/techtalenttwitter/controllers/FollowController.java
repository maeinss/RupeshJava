package com.tts.techtalenttwitter.controllers;

import com.tts.techtalenttwitter.entities.UserEntity;
import com.tts.techtalenttwitter.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class FollowController {
    @Autowired
    private UserService userService;
    @PostMapping(value = "/follow/{username}")
    public String follow(@PathVariable(value="username") String username,
                         HttpServletRequest request) {
        UserEntity loggedInUser = userService.getLoggedInUser();
        UserEntity userToFollow = userService.findByUsername(username);
        List<UserEntity> followers = userToFollow.getFollowers();
        followers.add(loggedInUser);
        userToFollow.setFollowers(followers);
        userService.save(userToFollow);
        return "redirect:" + request.getHeader("Referer");
    }
    @PostMapping(value = "/unfollow/{username}")
    public String unfollow(@PathVariable(value="username") String username, HttpServletRequest request) {
        UserEntity loggedInUser = userService.getLoggedInUser();
        UserEntity userToUnfollow = userService.findByUsername(username);
        List<UserEntity> followers = userToUnfollow.getFollowers();
        followers.remove(loggedInUser);
        userToUnfollow.setFollowers(followers);
        userService.save(userToUnfollow);
        return "redirect:" + request.getHeader("Referer");
    }
}
