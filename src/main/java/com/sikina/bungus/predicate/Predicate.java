package com.sikina.bungus.predicate;

import java.util.List;

public record Predicate(BooleanOperator operator, List<String> cards, List<Predicate> children) {
    public boolean qualifies(CountingMap<String> cardCounts) {
        boolean childrenQualify = true;
        if (children != null && !children.isEmpty()) {
            if (operator == BooleanOperator.And) {
                childrenQualify = allChildrenQualify(cardCounts);
            } else {
                childrenQualify = anyChildrenQualify(cardCounts);
            }
        }

        boolean thisQualifies = true;
        if (cards != null && !cards.isEmpty()) {
            if (operator == BooleanOperator.And) {
                thisQualifies = allCardsQualify(cardCounts);
            } else {
                thisQualifies = anyCardsQualify(cardCounts);
            }
        }

        return operator == BooleanOperator.And ? childrenQualify && thisQualifies : childrenQualify || thisQualifies;
    }

    private boolean anyChildrenQualify(CountingMap<String> cardCounts) {
        return children.stream()
            .anyMatch(child -> child.qualifies(new CountingMap<>(cardCounts)));
    }

    private boolean allChildrenQualify(CountingMap<String> cardCounts) {
        return children.stream()
            .allMatch(child -> child.qualifies(new CountingMap<>(cardCounts)));
    }

    private boolean allCardsQualify(CountingMap<String> cardCounts) {
        cards.forEach(cardCounts::decrement);
        return cardCounts.isEmpty();
    }

    private boolean anyCardsQualify(CountingMap<String> cardCounts) {
        return cards.stream().anyMatch(cardCounts::containsKey);
    }
}
