package com.sikina.bungus.deck;

import com.sikina.bungus.deck.dao.Deck;
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
        @Arg(javaType = List.class, select = "com.sikina.bungus.deck.CardMapper.selectCardsForDeck", column = "id"),
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

    @Insert(value = "INSERT INTO deck (name) VALUES (#{name})")
    public void addDeck(String name);
}
