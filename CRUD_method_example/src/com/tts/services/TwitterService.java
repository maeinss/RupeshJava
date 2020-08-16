package com.tts.services;

import com.tts.entities.Tweet;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class TwitterService {
    public static void run() {
        Scanner userInput = new Scanner(System.in);
        List<Tweet> tweets = new ArrayList<>();

        Tweet tweet1 = new Tweet();

        System.out.println("Enter tweet1 username:");
        tweet1.setUserName(userInput.nextLine());

        System.out.println("Enter tweet1 message:");
        tweet1.setMessage(userInput.nextLine());

        tweets.add(tweet1);

        Tweet tweet2 = new Tweet();

        System.out.println("Enter tweet2 username:");
        tweet2.setUserName(userInput.nextLine());

        System.out.println("Enter tweet2 message:");
        tweet2.setMessage(userInput.nextLine());

        tweets.add(tweet2);

        tweets.forEach(tweet -> {
            System.out.println(tweet.toString());
        });
    }
}