package com.Hotel.Management.System;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Hotel_roomTest {

    @Test
    void testGetGuest_name() {
        Hotel_room h = new Hotel_room("Henry",1);

        assertEquals("Henry",h.getGuest_name());
    }

    @Test
    void testGetKey() {
        Hotel_room h = new Hotel_room();

        assertEquals(1,h.getKey());
    }

    @Test
    void testSetGuest_name() {
        Hotel_room h = new Hotel_room("Henry",1);

        h.setGuest_name("geo");

        assertEquals("geo",h.getGuest_name());
    }
}