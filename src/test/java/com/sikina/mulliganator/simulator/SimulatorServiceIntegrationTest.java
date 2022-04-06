package com.sikina.mulliganator.simulator;

import com.sikina.mulliganator.DatabaseTest;
import com.sikina.mulliganator.deck.DeckService;
import com.sikina.mulliganator.deck.dao.Deck;
import com.sikina.mulliganator.predicate.dao.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

class SimulatorServiceIntegrationTest extends DatabaseTest {
    
    @Autowired
    SimulatorService subject;

    @Autowired
    DeckService deckService;

    @Test
    void shouldSimulateDeck() {
        Deck deck = deckService.getDeck(1).orElseThrow();
        PredicateNode rootPredicate = new PredicateNode(List.of(
            // natural tron
            new PredicateLeaf(new Range(0, 6), List.of(new Card("Urza's Tower", 1), new Card("Urza's Power Plant", 1), new Card("Urza's Mine", 1))),
            // any two unique tron lands + a tutor for the third
            new PredicateNode(List.of(
                // any two unique tron lands
                new PredicateNode(List.of(
                    new PredicateLeaf(new Range(0, 6), List.of(new Card("Urza's Tower", 1), new Card("Urza's Mine", 1))),
                    new PredicateLeaf(new Range(0, 6), List.of(new Card("Urza's Tower", 1), new Card("Urza's Power Plant", 1))),
                    new PredicateLeaf(new Range(0, 6), List.of(new Card("Urza's Power Plant", 1), new Card("Urza's Mine", 1)))
                ), BooleanOperator.Or),
                // and a way to find the third
                new PredicateNode(List.of(
                    // via expedition map
                    new PredicateLeaf(new Range(0, 6), List.of(new Card("Expedition Map", 1))),
                    // via crop rotation
                    new PredicateNode(List.of(
                        // a way to produce green mana and a third land
                        new PredicateNode(List.of(
                            // green producing land
                            new PredicateLeaf(new Range(0, 6), List.of(new Card("Forest", 1))),
                            new PredicateLeaf(new Range(0, 6), List.of(new Card("Cave of Temptation", 1))),
                            // any third land and an egg
                            new PredicateNode(List.of(
                                // star or sphere or globe or terminal
                                new PredicateNode(List.of(
                                    new PredicateLeaf(new Range(0, 6), List.of(new Card("Chromatic Star", 1))),
                                    new PredicateLeaf(new Range(0, 6), List.of(new Card("Chromatic Sphere", 1))),
                                    new PredicateLeaf(new Range(0, 6), List.of(new Card("Guild Globe", 1))),
                                    new PredicateLeaf(new Range(0, 6), List.of(new Card("Network Terminal", 1)))
                                ), BooleanOperator.Or),
                                // and any third colorless land
                                new PredicateNode(List.of(
                                    new PredicateLeaf(new Range(0, 6), List.of(new Card("Haunted Fengraf", 1))),
                                    // a duplicate tron land. We have to repeat the check for the first two here
                                    new PredicateLeaf(new Range(0, 6), List.of(new Card("Urza's Tower", 1), new Card("Urza's Mine", 2))),
                                    new PredicateLeaf(new Range(0, 6), List.of(new Card("Urza's Tower", 2), new Card("Urza's Mine", 1))),
                                    new PredicateLeaf(new Range(0, 6), List.of(new Card("Urza's Tower", 1), new Card("Urza's Power Plant", 2))),
                                    new PredicateLeaf(new Range(0, 6), List.of(new Card("Urza's Tower", 2), new Card("Urza's Power Plant", 1))),
                                    new PredicateLeaf(new Range(0, 6), List.of(new Card("Urza's Power Plant", 2), new Card("Urza's Mine", 1))),
                                    new PredicateLeaf(new Range(0, 6), List.of(new Card("Urza's Power Plant", 1), new Card("Urza's Mine", 2)))
                                ), BooleanOperator.Or)
                            ), BooleanOperator.And)
                        ), BooleanOperator.Or),
                        // and a crop rotation
                        new PredicateLeaf(new Range(0, 6), List.of(new Card("Crop Rotation", 1)))
                    ), BooleanOperator.Or),
                    // via ancient stirrings
                    new PredicateNode(List.of(
                        // green source
                        new PredicateNode(List.of(
                            // green producing land
                            new PredicateLeaf(new Range(0, 6), List.of(new Card("Forest", 1))),
                            new PredicateLeaf(new Range(0, 6), List.of(new Card("Cave of Temptation", 1))),
                            // an egg
                            new PredicateNode(List.of(
                                new PredicateLeaf(new Range(0, 6), List.of(new Card("Chromatic Star", 1))),
                                new PredicateLeaf(new Range(0, 6), List.of(new Card("Chromatic Sphere", 1))),
                                new PredicateLeaf(new Range(0, 6), List.of(new Card("Guild Globe", 1))),
                                new PredicateLeaf(new Range(0, 6), List.of(new Card("Network Terminal", 1)))
                            ), BooleanOperator.Or)
                        ), BooleanOperator.Or)
                        // ancient stirrings
                    ), BooleanOperator.And)
                ), BooleanOperator.Or)
            ), BooleanOperator.And),
            // any tron land, and a way to find the other two
            new PredicateNode(List.of(
                // any tron land
                new PredicateNode(List.of(
                    new PredicateLeaf(new Range(0, 6), List.of(new Card("Urza's Tower", 1))),
                    new PredicateLeaf(new Range(0, 6), List.of(new Card("Urza's Power Plant", 1))),
                    new PredicateLeaf(new Range(0, 6), List.of(new Card("Urza's Mine", 1)))
                ), BooleanOperator.Or),
                // and a way to find the second and third
                new PredicateNode(List.of(
                    // via expedition map and crop rotation
                    new PredicateNode(List.of(
                        // map
                        new PredicateLeaf(new Range(0, 6), List.of(new Card("Expedition Map", 1))),
                        // and rotation
                        new PredicateLeaf(new Range(0, 6), List.of(new Card("Crop Rotation", 1))),
                        // and a second land plus a way to produce green
                        new PredicateNode(List.of(
                            // a second land that produces green
                            new PredicateLeaf(new Range(0, 6), List.of(new Card("Forest", 1))),
                            new PredicateLeaf(new Range(0, 6), List.of(new Card("Cave of Temptation", 1))),
                            // or a second colorless land and an egg
                            new PredicateNode(List.of(
                                // a second colorless land
                                new PredicateNode(List.of(
                                    // non tron land
                                    new PredicateLeaf(new Range(0, 6), List.of(new Card("Haunted Fengraf", 1))),
                                    // or duplicate tron land (recheck for first tron land)
                                    new PredicateLeaf(new Range(0, 6), List.of(new Card("Urza's Tower", 2))),
                                    new PredicateLeaf(new Range(0, 6), List.of(new Card("Urza's Power Plant", 2))),
                                    new PredicateLeaf(new Range(0, 6), List.of(new Card("Urza's Mine", 2)))
                                    ), BooleanOperator.Or),
                                // and an egg
                                new PredicateNode(List.of(
                                    new PredicateLeaf(new Range(0, 6), List.of(new Card("Chromatic Star", 1))),
                                    new PredicateLeaf(new Range(0, 6), List.of(new Card("Chromatic Sphere", 1)))
                                ), BooleanOperator.Or)
                            ), BooleanOperator.And)
                        ), BooleanOperator.Or)
                    ), BooleanOperator.And),
                    // via ancient stirrings and either map or crop rotation
                    new PredicateNode(List.of(
                        // stirrings
                        new PredicateLeaf(new Range(0, 6), List.of(new Card("Ancient Stirrings", 1))),
                        // and rotation or map
                        new PredicateNode(List.of(
                            new PredicateLeaf(new Range(0, 6), List.of(new Card("Expedition Map", 1))),
                            new PredicateLeaf(new Range(0, 6), List.of(new Card("Crop Rotation", 1)))
                            ), BooleanOperator.Or),
                        // and a second land plus a way to produce green twice
                        new PredicateNode(List.of(
                            // a second land that produces green
                            new PredicateLeaf(new Range(0, 6), List.of(new Card("Forest", 1))),
                            new PredicateLeaf(new Range(0, 6), List.of(new Card("Cave of Temptation", 1))),
                            // or a second colorless land and an egg
                            new PredicateNode(List.of(
                                // a second colorless land
                                new PredicateNode(List.of(
                                    // non tron land
                                    new PredicateLeaf(new Range(0, 6), List.of(new Card("Haunted Fengraf", 1))),
                                    // or duplicate tron land (recheck for first tron land)
                                    new PredicateLeaf(new Range(0, 6), List.of(new Card("Urza's Tower", 2))),
                                    new PredicateLeaf(new Range(0, 6), List.of(new Card("Urza's Power Plant", 2))),
                                    new PredicateLeaf(new Range(0, 6), List.of(new Card("Urza's Mine", 2)))
                                ), BooleanOperator.Or),
                                // and two eggs
                                new PredicateNode(List.of(
                                    new PredicateLeaf(new Range(0, 6), List.of(new Card("Chromatic Star", 2))),
                                    new PredicateLeaf(new Range(0, 6), List.of(new Card("Chromatic Star", 1), new Card("Chromatic Sphere", 1))),
                                    new PredicateLeaf(new Range(0, 6), List.of(new Card("Chromatic Sphere", 2)))
                                ), BooleanOperator.Or)
                            ), BooleanOperator.And)
                        ), BooleanOperator.Or)
                    ), BooleanOperator.And),
                    // via two ancient stirrings
                    new PredicateNode(List.of(
                        // stirrings
                        new PredicateLeaf(new Range(0, 6), List.of(new Card("Ancient Stirrings", 2))),
                        // and a way to produce green twice
                        new PredicateNode(List.of(
                            // two eggs
                            new PredicateNode(List.of(
                                new PredicateLeaf(new Range(0, 6), List.of(new Card("Chromatic Star", 2))),
                                new PredicateLeaf(new Range(0, 6), List.of(new Card("Chromatic Star", 1), new Card("Chromatic Sphere", 1))),
                                new PredicateLeaf(new Range(0, 6), List.of(new Card("Chromatic Sphere", 2)))
                            ), BooleanOperator.Or),
                            // or a second land that produces green
                            new PredicateLeaf(new Range(0, 6), List.of(new Card("Forest", 1))),
                            new PredicateLeaf(new Range(0, 6), List.of(new Card("Cave of Temptation", 1)))
                        ), BooleanOperator.Or)
                    ), BooleanOperator.And)
                ), BooleanOperator.Or)
            ), BooleanOperator.And)
        ), BooleanOperator.Or);

        Assertions.assertNotNull(deck);

        SimulationsSummary summary = subject.simulate(rootPredicate, deck, 10000);

        Assertions.assertNotNull(summary);
    }
}