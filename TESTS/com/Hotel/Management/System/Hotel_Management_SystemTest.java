package com.Hotel.Management.System;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Hotel_Management_SystemTest {

    @Test
    void testAddRoom() {
        Hotel_Management_System h = new Hotel_Management_System();

        h.addRoom();
        h.addRoom();

        assertEquals(2,h.getAvailble_rooms_count());
    }

    @Test
    void testSearchGuest() {

        Hotel_Management_System h = new Hotel_Management_System();

        h.setting_Avaialble_rooms();

        h.reserveRoom("henty",4);
        h.reserveRoom("hentyt",3);
        h.reserveRoom("henry",7);
        h.reserveRoom("james",10);
        h.reserveRoom("geo",1);

        h.bookRoom("mash",8);
        h.bookRoom("hili",5);
        h.bookRoom("hasnd",2);

        assertEquals(true,h.searchGuest(5));
    }
}