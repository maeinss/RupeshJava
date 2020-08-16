package com.tts.techtalenttwitter.controllers;

import com.tts.techtalenttwitter.entities.Tweet;
import com.tts.techtalenttwitter.entities.UserEntity;
import com.tts.techtalenttwitter.services.TweetService;
import com.tts.techtalenttwitter.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private TweetService tweetService;

    @GetMapping(value = "/users/{username}")
    public String getUser(@PathVariable(value="username") String username, Model model) {
        UserEntity user = userService.findByUsername(username);
        List<Tweet> tweets = tweetService.findAllByUser(user);
        model.addAttribute("tweetList", tweets);
        model.addAttribute("user", user);
        UserEntity loggedInUser = userService.getLoggedInUser();
        List<UserEntity> following = loggedInUser.getFollowing();
        boolean isFollowing = false;
        for (UserEntity followedUser : following) {
            if (followedUser.getUsername().equals(username)) {
                isFollowing = true;
            }
        }
        model.addAttribute("following", isFollowing);
        boolean isSelfPage = loggedInUser.getUsername().equals(username);
        model.addAttribute("isSelfPage", isSelfPage);
        return "user";
    }
    @GetMapping(value = "/users")
    public String getUsers(Model model) {
        List<UserEntity> users = userService.findAll();
        model.addAttribute("users", users);
        SetTweetCounts(users, model);
        UserEntity loggedInUser = userService.getLoggedInUser();
        List<UserEntity> usersFollowing = loggedInUser.getFollowing();
        SetFollowingStatus(users, usersFollowing, model);
        return "users";
    }

    private void SetFollowingStatus(List<UserEntity> users, List<UserEntity> usersFollowing, Model model) {
        HashMap<String,Boolean> followingStatus = new HashMap<>();
        String username = userService.getLoggedInUser().getUsername();
        for (UserEntity user : users) {
            if(usersFollowing.contains(user)) {
                followingStatus.put(user.getUsername(), true);
            }else if (!user.getUsername().equals(username)) {
                followingStatus.put(user.getUsername(), false);
            }
        }
        model.addAttribute("followingStatus", followingStatus);
    }

    private void SetTweetCounts(List<UserEntity> users, Model model) {
        HashMap<String, Integer> tweetCounts = new HashMap<>();
        for (UserEntity user : users) {
            List<Tweet> tweets = tweetService.findAllByUser(user);
            tweetCounts.put(user.getUsername(), tweets.size());
        }
        model.addAttribute("tweetCounts", tweetCounts);
    }

}