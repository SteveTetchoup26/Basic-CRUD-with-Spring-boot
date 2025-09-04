package com.st.sa.services;

import com.st.sa.TypeSentiment;
import com.st.sa.entities.Client;
import com.st.sa.entities.Sentiment;
import com.st.sa.repositories.SentimentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SentimentService {

    private ClientService clientService;
    private SentimentRepository sentimentRepository;

    public SentimentService(ClientService clientService, SentimentRepository sentimentRepository) {
        this.clientService = clientService;
        this.sentimentRepository = sentimentRepository;
    }

    public void createSentiment(Sentiment sentiment) {
        Client client = this.clientService.getOrCreate(sentiment.getClient());
        sentiment.setClient(client);

        if(sentiment.getTexte().contains("pas")) {
            sentiment.setType(TypeSentiment.NEGATIF);
        } else {
            sentiment.setType(TypeSentiment.POSITIF);
        }
        this.sentimentRepository.save(sentiment);
    }

//    public Sentiment createSentiment(Sentiment sentiment) {
//        Client client = this.clientService.getOrCreate(sentiment.getClient());
//        sentiment.setClient(client);
//
//        if(sentiment.getTexte().contains("pas")) {
//            sentiment.setType(TypeSentiment.NEGATIF);
//        } else {
//            sentiment.setType(TypeSentiment.POSITIF);
//        }
//        return this.sentimentRepository.save(sentiment);
//    }

    public List<Sentiment> getSentiments(TypeSentiment typeSentiment) {
        if(typeSentiment == null) {
            return this.sentimentRepository.findAll();
        } else {
            return this.sentimentRepository.findByType(typeSentiment);
        }
    }

    public void delete(Integer id) {
        this.sentimentRepository.deleteById(id);
    }
}
