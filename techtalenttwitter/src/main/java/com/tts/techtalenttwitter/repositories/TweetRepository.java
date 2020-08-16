package com.tts.techtalenttwitter.repositories;



import com.tts.techtalenttwitter.entities.Tweet;
import com.tts.techtalenttwitter.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TweetRepository extends CrudRepository<Tweet,Long> {
    List<Tweet> findAllByOrderByCreatedAtDesc();
    List<Tweet> findAllByUserOrderByCreatedAtDesc(UserEntity user);
    List<Tweet> findAllByUserInOrderByCreatedAtDesc(List<UserEntity> users);
    List<Tweet> findByTags_PhraseOrderByCreatedAtDesc(String phrase);
}
