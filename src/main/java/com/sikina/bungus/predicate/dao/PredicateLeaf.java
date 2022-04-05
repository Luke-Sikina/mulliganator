package com.sikina.bungus.predicate.dao;

import java.util.List;

public record PredicateLeaf(Range range, List<Card> requiredCards) implements CardPredicate {}
