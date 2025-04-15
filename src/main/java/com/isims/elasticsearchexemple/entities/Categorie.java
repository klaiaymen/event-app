package com.isims.elasticsearchexemple.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Document;

@Getter
@Setter
@Document(indexName = "categories")
public class Categorie {
    private String id;
    private String nomCategorie;
}
