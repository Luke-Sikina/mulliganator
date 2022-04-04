package com.sikina.bungus.deck;

import com.sikina.bungus.deck.Deck;
import com.sikina.bungus.deck.DeckMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeckService {
    @Autowired
    DeckMapper deckMapper;

    public List<Deck> getAllDecks() {
        return deckMapper.getAllDeckNames();
    }

    public Optional<Deck> getDeck(int deckID) {
        try {
            return Optional.of(deckMapper.getDeckById(deckID));
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
