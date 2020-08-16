package com.tts.services;

import com.tts.entities.Tweet1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Operations2 {
    public static void  create(List tweets){
//        Tweet tweet1 = new Tweet();
        Scanner userInput = new Scanner(System.in);
        System.out.println("Enter tweet1 username and message separated by comma:");
        String[] inputs = userInput.nextLine().split(",");
        Tweet1 tweet1 = new Tweet1(1, inputs[0], inputs[1]);
        tweets.add(tweet1);
    }
    public static void  delete( int idVal, List tweets){
        tweets.remove(idVal);
    }
    public static void  replace( int idVal, List tweets){
        Scanner userInput = new Scanner(System.in);
        System.out.println("new username and message separated by comma:");
        String[] inputs = userInput.nextLine().split(",");
        Tweet1 tweet2 = new Tweet1(1, inputs[0], inputs[1]);
        tweets.set(idVal,tweet2);
    }
}
