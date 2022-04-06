package com.sikina.mulliganator.simulator;

import com.sikina.mulliganator.deck.dao.Deck;

public record SimulationsSummary(int total, int success, Deck deck) {}
