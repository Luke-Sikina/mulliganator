package com.sikina.bungus.deck;

import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeckMapper {
    @ConstructorArgs({
        @Arg(column = "id", id = true, javaType = int.class),
        @Arg(column = "name", javaType = String.class),
    })
    @Select("SELECT name, id FROM deck")
    public List<Deck> getAllDeckNames();

    @ConstructorArgs({
        @Arg(column = "id", id = true, javaType = int.class),
        @Arg(column = "name", javaType = String.class),
        @Arg(javaType = List.class, select = "selectCardsForDeck", column = "id"),
    })
    @Select("""
    SELECT
        id, name
    FROM
        deck
    WHERE
        deck.id = #{id};
    """)
    public Deck getDeckById(int id);

    @ConstructorArgs({
        @Arg(column = "id", id = true, javaType = int.class),
        @Arg(column = "deck_id", id = true, javaType = int.class),
        @Arg(column = "count", javaType = int.class),
        @Arg(column = "name", javaType = String.class),
    })
    @Select("""
        SELECT
            name, count, id, deck_id
        FROM
            deck_card
        WHERE
            deck_id = #{id}
        ORDER BY
            name ASC
    """)
    public List<Card> selectCardsForDeck(int id);

    @Select("""
        SELECT
            name, count, id, deck_id
        FROM
            deck_card
        WHERE
            FALSE
    """)
    public List<Card> selectNoCards(int id);
}
