package com.example.foodorderingsystem.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {com.example.foodorderingsystem.model.AdminTest.class})
class AdminTest {


        private static final String MESSAGE_ONE = "Admin properties are not set correctly in the constructor";
        private static final String MESSAGE_TWO = "Admin details returned by toString() are not correct";

        private Admin admin;

        @BeforeEach
        public void setUp() {
            admin = new Admin(1,"Pavan","DG","pavandg@gmail.com","qpalzm@111","9380017737","kallupete","Bangalore","561009");
        }

        @AfterEach
        public void tearDown() {
            admin = null;
        }

        @Test
        public void givenValidUserValuesWhenCreatedThenSetProperties() {
            assertEquals(1, admin.getId(), MESSAGE_ONE);
            assertEquals("Pavan", admin.getFirstname(), MESSAGE_ONE);
            assertEquals("9380017737", admin.getMobileno(), MESSAGE_ONE);
            assertEquals("pavandg@gmail.com", admin.getEmailid(), MESSAGE_ONE);
        }

        @Test
        public void givenValidUserValuesThenReturnUserDetails() {
            String details = admin.toString();
            assertTrue(details.contains("id=1"), MESSAGE_TWO);
            assertTrue(details.contains("password=qpalzm@111"), MESSAGE_TWO);
        }
    }


