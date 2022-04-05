package com.sikina.bungus.predicate.dao;

import java.util.List;

public record PredicateNode(List<CardPredicate> predicates, BooleanOperator operator) implements CardPredicate {}
