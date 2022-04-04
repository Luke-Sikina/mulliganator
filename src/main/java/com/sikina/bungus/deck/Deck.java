package com.sikina.bungus.deck;

import java.util.List;

public record Deck(int id, String name, List<Card> cards) {
    public Deck(int id, String name) {
        this(id, name, List.of());
    }
}
