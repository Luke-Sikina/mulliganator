package com.sikina.mulliganator.predicate.dao;

import java.util.List;

public record PredicateNode(List<CardPredicate> predicates, BooleanOperator operator) implements CardPredicate {}
