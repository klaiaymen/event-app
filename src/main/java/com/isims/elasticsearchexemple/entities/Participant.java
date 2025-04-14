package com.isims.elasticsearchexemple.entities;

import java.util.List;

public class Participant extends User {
    private List<String> participations; // List of Event IDs
    private Preferences preferences;
}
