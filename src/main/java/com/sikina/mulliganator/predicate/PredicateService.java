package com.sikina.mulliganator.predicate;

import com.sikina.mulliganator.predicate.dao.BooleanOperator;
import com.sikina.mulliganator.predicate.dao.CardPredicate;
import com.sikina.mulliganator.predicate.dao.PredicateLeaf;
import com.sikina.mulliganator.predicate.dao.PredicateNode;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class PredicateService {
    public boolean resolve(List<String> cards, CardPredicate root) {
        return switch (root) {
            case PredicateNode n -> resolveNode(cards, n);
            case PredicateLeaf l -> resolveLeaf(cards, l);
        };
    }

    private boolean resolveNode(List<String> cards, PredicateNode node) {
        if (node.operator() == BooleanOperator.Or) {
            return node.predicates().stream()
                .anyMatch(childPredicate -> resolve(cards, childPredicate));
        } else {
            return node.predicates().stream()
                .allMatch(childPredicate -> resolve(cards, childPredicate));
        }

    }

    private boolean resolveLeaf(List<String> cards, PredicateLeaf leaf) {
        Map<String, Long> cardCounts = cards.subList(leaf.range().startIncl(), leaf.range().endIncl() + 1)
            .stream()
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        return leaf.requiredCards().stream()
            .mapToInt(card -> {
                int realCount = cardCounts.getOrDefault(card.name(), 0L).intValue();
                return realCount - card.count();
            })
            .allMatch(i -> i >= 0);
    }
}
