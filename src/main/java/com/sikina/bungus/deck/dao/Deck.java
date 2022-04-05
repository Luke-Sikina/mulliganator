package com.sikina.bungus.deck.dao;

import java.util.List;

public record Deck(int id, String name, List<Card> cards) {
    public static final int UNASSIGNED = -1;

    public Deck(String name) {
        this(UNASSIGNED, name, List.of());
    }

    public Deck(int id, String name) {
        this(id, name, List.of());
    }

    public boolean idUnassigned() {
        return id() == UNASSIGNED;
    }
}
