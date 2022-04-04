package com.sikina.bungus.deck;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Optional;

@Controller
public class DeckController {
    @Autowired
    DeckService deckService;

    @RequestMapping(path = "/decks", method = RequestMethod.GET)
    public ResponseEntity<List<Deck>> getAllDecks() {
        return new ResponseEntity<>(deckService.getAllDecks(), HttpStatus.OK);
    }

    @RequestMapping(path = "/decks/{id}", method = RequestMethod.GET)
    public ResponseEntity<Deck> getDeckById(@PathVariable("id") int id) {
        Optional<Deck> maybeDeck = deckService.getDeck(id);
        return maybeDeck
            .map(deck -> new ResponseEntity<>(deck, HttpStatus.OK))
            .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
