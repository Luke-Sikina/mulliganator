package com.sikina.bungus.simulator;

import com.sikina.bungus.deck.dao.Deck;

public record SimulationsSummary(int total, int success, Deck deck) {}
