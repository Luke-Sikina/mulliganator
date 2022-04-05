package com.sikina.bungus.simulator;

import com.sikina.bungus.deck.DeckService;
import com.sikina.bungus.predicate.dao.PredicateNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class SimulatorController {
    @Autowired
    SimulatorService simulatorService;

    @Autowired
    DeckService deckService;

    @RequestMapping(path = "/simulations/{deck_id}", method = RequestMethod.GET)
    public ResponseEntity<SimulationsSummary> simulateDeck(
        @PathVariable("deck_id") int deckId,
        @RequestParam(value = "simulations", defaultValue = "10000") int simulations,
        @RequestBody() PredicateNode predicate
    ) {
        return deckService.getDeck(deckId)
            .map(deck -> {
                SimulationsSummary summary = simulatorService.simulate(predicate, deck, simulations);
                return new ResponseEntity<>(summary, HttpStatus.OK);
            }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
