package com.st.sa.entities;

import com.st.sa.TypeSentiment;
import jakarta.persistence.*;

@Entity
@Table(name = "SENTIMENT")
public class Sentiment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String texte;
    private TypeSentiment type;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    //PERSIST : Sauvgarde le client, récupère son id et injecte-le dans le CLIENT_ID
    //MERGE : fusionne si existe
    @JoinColumn(name = "CLIENT_ID") //optionnel si même nom
    private Client client;

    public Sentiment() {
    }

    public Sentiment(int id, String texte, TypeSentiment type, Client client) {
        this.id = id;
        this.texte = texte;
        this.type = type;
        this.client = client;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTexte() {
        return texte;
    }

    public void setTexte(String texte) {
        this.texte = texte;
    }

    public TypeSentiment getType() {
        return type;
    }

    public void setType(TypeSentiment type) {
        this.type = type;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }


}
