package com.tts.services;

import com.tts.entities.Tweet;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TwitterService1 {
    public static void run() {
        List<Tweet> tweets = new ArrayList<>();
        Scanner userInput = new Scanner(System.in);
            for (int i=0; i<=10; i++){
            System.out.println(String.format("Select operation:\n1. create\n2. delete \n3. replace\n4. done"));
            int opt = userInput.nextInt();
            switch (opt) {
                case 1:
                    Operations2.create(tweets);
                    break;
                case 2:
                    System.out.println("Please enter the id:");
                    int idVal = userInput.nextInt();
                    Operations2.delete(idVal,tweets);
                    break;
                case 3:
                    System.out.println("Please enter the id:");
                    int idVal1 = userInput.nextInt();
                    Operations2.replace(idVal1,tweets);
                    break;
                case 4:
                    System.out.println(tweets);
                    return;
            }
        }
    System.out.println(tweets);
    }
}
