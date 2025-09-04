package com.st.sa.repositories;

import com.st.sa.TypeSentiment;
import com.st.sa.entities.Sentiment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SentimentRepository extends JpaRepository<Sentiment, Integer> {

    List<Sentiment> findByType(TypeSentiment typeSentiment);
}
