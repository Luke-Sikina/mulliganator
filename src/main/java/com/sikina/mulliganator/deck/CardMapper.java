package com.sikina.mulliganator.deck;

import com.sikina.mulliganator.deck.dao.Card;
import org.apache.ibatis.annotations.Arg;
import org.apache.ibatis.annotations.ConstructorArgs;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CardMapper {
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
}
