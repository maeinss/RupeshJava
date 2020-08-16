package com.tts.techtalenttwitter.services;



import com.tts.techtalenttwitter.entities.Tweet;
import com.tts.techtalenttwitter.entities.UserEntity;
import com.tts.techtalenttwitter.model.Tag;
import com.tts.techtalenttwitter.repositories.TagRepository;
import com.tts.techtalenttwitter.repositories.TweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

@Service
public class TweetService {
    @Autowired
    private TweetRepository tweetRepository;
    @Autowired
    private TagRepository tagRepository;
//    @Autowired
//    private TweetRepository tweetRepository;

    public List<Tweet>findAll(){
        List<Tweet> tweets = tweetRepository.findAllByOrderByCreatedAtDesc();
        return formatTweets(tweets);
    }
    public List<Tweet> findAllByUser(UserEntity user){
        List<Tweet> tweets = tweetRepository.findAllByUserOrderByCreatedAtDesc(user);
        return tweets;

    }
    public List<Tweet> findAllByUsers(List<UserEntity> users) {
        List<Tweet> tweets = tweetRepository.findAllByUserInOrderByCreatedAtDesc(users);
        return tweets;
    }
    public void save(Tweet tweet){
        handleTags(tweet);
        tweetRepository.save(tweet);
    }
    private void handleTags(Tweet tweet) {
        List<Tag> tags = new ArrayList<Tag>();
        Pattern pattern = Pattern.compile("#\\w+");
        Matcher matcher = pattern.matcher(tweet.getMessage());
        while (matcher.find())
        {String phrase = matcher.group().substring(1).toLowerCase();
            Tag tag = tagRepository.findByPhrase(phrase);
            if(tag == null) {
                tag = new Tag();
                tag.setPhrase(phrase);
                tagRepository.save(tag);
            }
            tags.add(tag);
        }
        tweet.setTags(tags);
    }
    private List<Tweet> formatTweets(List<Tweet> tweets) {
        addTagLinks(tweets);
        return tweets;
    }
    private void addTagLinks(List<Tweet> tweets) {
        Pattern pattern = Pattern.compile("#\\w+");
        for(Tweet tweet: tweets) {
            String message = tweet.getMessage();
            Matcher matcher = pattern.matcher(message);
            Set<String> tags = new HashSet<String>();
            while(matcher.find()) {
                tags.add(matcher.group());
            }
            for(String tag : tags) {
                message = message.replaceAll(tag,
                        "<a class=\"tag\" href=\"/tweets/" + tag.substring(1).toLowerCase() + "\">" + tag + "</a>");
            }
            tweet.setMessage(message);
        }
    }
    public List<Tweet> findAllWithTag(String tag){
        List<Tweet> tweets = tweetRepository.findByTags_PhraseOrderByCreatedAtDesc(tag);
        return formatTweets(tweets);
    }


}
