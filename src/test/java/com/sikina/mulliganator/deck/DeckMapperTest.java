package com.sikina.mulliganator.deck;

import com.sikina.mulliganator.DatabaseTest;
import com.sikina.mulliganator.deck.dao.Card;
import com.sikina.mulliganator.deck.dao.Deck;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

class DeckMapperTest extends DatabaseTest {
    @Autowired
    DeckMapper deckMapper;

    @Test
    void shouldGetDeckDetails() {
        Deck actual = deckMapper.getDeckById(1);
        Deck expected = new Deck(
            1, "GUR Wavetron",
            List.of(
                new Card(2, 1, 4, "Ancient Stirrings"),
                new Card(19, 1, 2, "Cave of Temptation"),
                new Card(13, 1, 4, "Chromatic Sphere"),
                new Card(12, 1, 4, "Chromatic Star"),
                new Card(4, 1, 2, "Crop Rotation"),
                new Card(5, 1, 4, "Expedition Map"),
                new Card(10, 1, 4, "Fangren Marauder"),
                new Card(16, 1, 4, "Fire // Ice"),
                new Card(17, 1, 5, "Forest"),
                new Card(14, 1, 1, "Guild Globe"),
                new Card(18, 1, 1, "Haunted Fengraf"),
                new Card(11, 1, 4, "Maelstrom Colossus"),
                new Card(15, 1, 1, "Network Terminal"),
                new Card(9, 1, 4, "Self-Assembler"),
                new Card(7, 1, 4, "Urza's Mine"),
                new Card(8, 1, 4, "Urza's Power Plant"),
                new Card(6, 1, 4, "Urza's Tower"),
                new Card(1, 1, 4, "Wavesifter")
            )
        );

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldGetAllDecks() {
        List<Deck> actual = deckMapper.getAllDeckNames();
        List<Deck> expected = List.of(
            new Deck(1, "GUR Wavetron", List.of()),
            new Deck(2, "U Faeries", List.of())
        );

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldInsertDeck() {
        Deck toAdd = new Deck(-1, "G Stompy");

        deckMapper.addDeck(toAdd.name());
        Deck actual = deckMapper.getDeckById(3);
        Deck expected = new Deck(3, "G Stompy");

        Assertions.assertEquals(expected, actual);
    }
}