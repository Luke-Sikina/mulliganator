package com.sikina.bungus.simulator;

import com.sikina.bungus.deck.dao.Deck;
import com.sikina.bungus.predicate.PredicateService;
import com.sikina.bungus.predicate.dao.CardPredicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

@Service
public class SimulatorService {

    @Autowired
    PredicateService predicateService;

    public SimulationsSummary simulate(CardPredicate predicate, Deck deck, int iterations) {
        List<String> library = deck.cards().stream()
            // enumerate cards using their respective counts
            .flatMap(card -> IntStream.range(0, card.count()).boxed().map(i -> card.name()))
            .toList();

        // Do not parallelize this stream. We are reusing the deck
        long successes = IntStream.range(0, iterations).boxed()
            .filter(i -> {
                Collections.shuffle(library);
                return predicateService.resolve(library, predicate);
            })
            .count();

        return new SimulationsSummary(iterations, (int) successes, deck);
    }
}
