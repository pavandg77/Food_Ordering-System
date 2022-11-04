package com.example.foodorderingsystem.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(classes = {UserTest.class})
class UserTest {

    private static final String MESSAGE_ONE = "User properties are not set correctly in the constructor";
    private static final String MESSAGE_TWO = "User details returned by toString() are not correct";
    private User user;

    @BeforeEach
    public void setUp() {
        user = new User(1,"Venkatesh","s","venki@gmail.com","qpalzm@111","9898787867","vijaynagar","Bangalore","678765");
    }

    @AfterEach
    public void tearDown() {
        user = null;
    }

    @Test
    public void givenValidUserValuesWhenCreatedThenSetProperties() {
        assertEquals(1, user.getId(), MESSAGE_ONE);
        assertEquals("Venkatesh", user.getFirstname(), MESSAGE_ONE);
        assertEquals("9898787867", user.getMobileno(), MESSAGE_ONE);
        assertEquals("venki@gmail.com", user.getEmailid(), MESSAGE_ONE);
    }

    @Test
    public void givenValidUserValuesThenReturnUserDetails() {
        String details = user.toString();
        assertTrue(details.contains("id=1"), MESSAGE_TWO);
        assertTrue(details.contains("password=qpalzm@111"), MESSAGE_TWO);
    }
}
