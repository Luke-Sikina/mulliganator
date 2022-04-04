package com.sikina.bungus.deck;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

@Testcontainers
@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
@Sql(scripts = {"/seed.sql"})
class DeckMapperTest {
    @Container
    static final MySQLContainer<?> databaseContainer = new MySQLContainer<>("mysql:8.0");

    @DynamicPropertySource
    static void mySQLProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", databaseContainer::getJdbcUrl);
        registry.add("spring.datasource.username", databaseContainer::getUsername);
        registry.add("spring.datasource.password", databaseContainer::getPassword);
    }

    @Autowired
    DeckMapper deckMapper;

    @Test
    void shouldGetDeckDetails() {
        Deck actual = deckMapper.getDeckById(1);
        Deck expected = new Deck(
            1, "GUR Wavetron",
            List.of(
                new Card(2,1,4, "Ancient Stirrings"),
                new Card(4,1,2, "Crop Rotation"),
                new Card(5,1,4, "Expedition Map"),
                new Card(7,1,4, "Urza's Mine"),
                new Card(8,1,4, "Urza's Power Plant"),
                new Card(6,1,4, "Urza's Tower"),
                new Card(1,1,4, "Wavesifter")
            )
        );

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldGetAllDecks() {
        List<Deck> actual = deckMapper.getAllDeckNames();
        List<Deck> expected = List.of(
            new Deck(1, "GUR Wavetron", List.of()),
            new Deck(2, "U Faeries", List.of())
        );

        Assertions.assertEquals(expected, actual);
    }
}