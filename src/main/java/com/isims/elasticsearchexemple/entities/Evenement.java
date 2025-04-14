package com.isims.elasticsearchexemple.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "evenements")
public class Evenement {
    @Id
    private String id;
    private String titre;
    private Date date;
    private String lieu;
    private String description;
    private int nbMaxParticipants;
    private Categorie categorie;
    private List<Participant> participants;
    private List<Personnel> personnels;
}
