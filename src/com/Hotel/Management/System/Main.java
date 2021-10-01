package com.Hotel.Management.System;

import java.lang.annotation.Target;
import java.util.*;
import java.lang.Exception;
import java.io.File;
import java.io.FileWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

class nameCannotBeNull extends Exception{

    public nameCannotBeNull(String s){
        super(s);
    }
}
class Hotel_room{

    private String guest_name;
    private static int count;
    private int key;


    public Hotel_room(){
        guest_name="";
        count++;
        key=count;

    }

    //The constructor only to be used when making the arrays other Available_rooms
    public Hotel_room(String name,int k){
        guest_name=name;
        key=k;

    }

    //The constructor only to be used when making the arrays other Available_rooms
    public Hotel_room(int k){
        key=k;
    }

    String getGuest_name(){
        return guest_name;
    }

    int getKey(){
        return key;
    }

    void setGuest_name(String name){
        if(name!=""){
            guest_name=name;
            return;
        }

        System.out.println("Invalid Setting! The name cannot be NULL");
    }

    void setName2(String name2) throws nameCannotBeNull{

        if(guest_name==""){
            throw new nameCannotBeNull("The name of the guest cannot be NULL");
        }

        guest_name=name2;

    }
}

class Hotel_Management_System{

    private ArrayList<Hotel_room> Available_rooms=new ArrayList<Hotel_room>();
    private ArrayList<Hotel_room> Booked_rooms = new ArrayList<Hotel_room>();
    private ArrayList<Hotel_room> Reserved_rooms = new ArrayList<Hotel_room>();
    private int availble_rooms_count;
    private int booked_rooms_count;
    private int reserved_rooms_count;

    Hotel_Management_System(){
        availble_rooms_count=0;
        booked_rooms_count=0;
        reserved_rooms_count=0;
    }

    //Here you can change the number of Available rooms
    public void setting_Avaialble_rooms(){

        for (int i = 0; i < 10; i++) {
            Available_rooms.add(new Hotel_room());
            availble_rooms_count+=1;
        }

    }

    public void addRoom(){
        //function for adding new rooms to the number of available rooms
        Available_rooms.add(new Hotel_room());
        availble_rooms_count++;
    }

    void bookRoom(String name,int k){

        boolean found=false;

        int remove_index=0;

        for (int i = 0; i < availble_rooms_count; i++) {
            if(Available_rooms.get(i).getKey()==k){
                found=true;
                remove_index=i;
                i=availble_rooms_count;
            }
        }

        if(found==false){
            System.out.println("The key of the Room You Have entered is not FOUND in the Available Rooms");
            return;
        }

        //keeping the record of the Booked rooms in the file
        try {
            System.out.println("Writing to the file");
            File myObj = new File("Booked Rooms Record.txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }

            //code for writing in the file starts from here
            FileWriter writer = new FileWriter("Booked Rooms Record.txt", true);

            String out= "Guest Name: "+name+", Key: "+String.valueOf(k);
            writer.write(out);
            writer.write("\r\n");

            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        Available_rooms.remove(remove_index);
        availble_rooms_count--;

        System.out.println("BOOKING THE ROOM");
        System.out.println();

        Booked_rooms.add(new Hotel_room(name,k));

        booked_rooms_count++;
        System.out.println("The room with key "+k+" is Booked");
        System.out.println();
    }

    void unBookRoom(int k){

        int remove_index=0;
        boolean found=false;

        for (int i = 0; i < booked_rooms_count; i++) {
            if(Booked_rooms.get(i).getKey()==k){
                found=true;
                remove_index=i;
                i=booked_rooms_count;
            }
        }

        if(found==false){
            System.out.println("The key of the Room You Have Entered in not FOUND in the Booked Rooms");
            return;
        }

        System.out.println("UNBOOKING THE ROOM");
        System.out.println();

        //Adding the room again in the available rooms list
        Available_rooms.add(new Hotel_room(k));
        availble_rooms_count+=1;

        //Removing the room from the list
        Booked_rooms.remove(remove_index);
        booked_rooms_count--;

        System.out.println("The room having KEY "+k+" is AVAILABLE again");
    }

    void reserveRoom(String name,int k){

        boolean found=false;

        int remove_index=0;

        for (int i = 0; i < availble_rooms_count; i++) {
            if(Available_rooms.get(i).getKey()==k){
                found=true;
                remove_index=i;
                i=availble_rooms_count;
            }
        }

        if(found==false){
            System.out.println("The key of the Room You Have entered is not FOUND in the Available Rooms");
            return;
        }

        //keeping the record of the Booked rooms in the file
        try {
            System.out.println("Writing to the file");
            File myObj = new File("Reserved Rooms Record.txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }

            //code for writing in the file starts from here
            FileWriter writer = new FileWriter("Reserved Rooms Record.txt", true);

            String out= "Guest Name: "+name+", Key: "+String.valueOf(k);
            writer.write(out);
            writer.write("\r\n");

            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        Available_rooms.remove(remove_index);
        availble_rooms_count--;

        System.out.println("RESERVING THE ROOM");
        System.out.println();

        Reserved_rooms.add(new Hotel_room(name,k));

        reserved_rooms_count++;
        System.out.println("The room with key "+k+" is Reserved");
        System.out.println();
    }

    void unReserveRoom(int k){

        int remove_index=0;
        boolean found=false;

        for (int i = 0; i < reserved_rooms_count; i++) {
            if(Reserved_rooms.get(i).getKey()==k){
                found=true;
                remove_index=i;
                i=reserved_rooms_count;
                System.out.println("Found");
                System.out.println(remove_index);
            }
            System.out.println(i);
        }

        if(found==false){
            System.out.println("The key of the Room You Have Entered in not FOUND in the Reserved Rooms");
            return;
        }

        System.out.println("UNRESERVING THE ROOM");
        System.out.println();

        //Adding the room again in the available rooms list
        Available_rooms.add(new Hotel_room(k));
        availble_rooms_count+=1;

        //Removing the room from the list
        Reserved_rooms.remove(remove_index);
        reserved_rooms_count--;

        System.out.println("The room having KEY "+k+" is AVAILABLE again");
    }


    public boolean searchGuest(int k){

        for (int i = 0; i < reserved_rooms_count; i++) {
            if(Reserved_rooms.get(i).getKey()==k){
                System.out.println("Guest Found");
                System.out.println("Guest Name: "+Reserved_rooms.get(i).getGuest_name());
                return true;
            }
        }

        for (int i = 0; i < booked_rooms_count; i++) {
            if(Booked_rooms.get(i).getKey()==k){
                System.out.println("Guest Found");
                System.out.println("Guest Name: "+Booked_rooms.get(i).getGuest_name());
                return true;
            }
        }

        System.out.println("Guest Not Found");

        return false;
    }

    public void displayAvailableRooms(){

        System.out.println("-----------------------------------------------------------------------------------------------------------------------");
        System.out.println("Displaying the Available Rooms");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------");
        System.out.println("");

        if(availble_rooms_count<1){
            System.out.println("NO ROOM IS AVAILABLE");
            System.out.println("-----------------------------------------------------------------------------------------------------------------------");
            System.out.println("");
            return;
        }

        for (int i = 0; i < availble_rooms_count; i++) {
            System.out.println("Room key: "+Available_rooms.get(i).getKey());

        }

        System.out.println("");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------");
        System.out.println("Number of Available Rooms: "+availble_rooms_count);
        System.out.println("-----------------------------------------------------------------------------------------------------------------------");

        System.out.println();
        System.out.println();
    }

    public void displayBookedRooms(){

        System.out.println("-----------------------------------------------------------------------------------------------------------------------");
        System.out.println("Displaying the Booked Rooms");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------");
        System.out.println("");

        if(booked_rooms_count<1){
            System.out.println("NO ROOMS BOOKED AT THE MOMENT");
            System.out.println("-----------------------------------------------------------------------------------------------------------------------");
            System.out.println("");
            return;
        }


        for (int i = 0; i < booked_rooms_count; i++) {

            System.out.println("Guest Name: "+Booked_rooms.get(i).getGuest_name());
            System.out.println("Room key: "+Booked_rooms.get(i).getKey());

        }
        System.out.println("");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------");
        System.out.println("Number of Booked Rooms: "+booked_rooms_count);
        System.out.println("-----------------------------------------------------------------------------------------------------------------------");
        System.out.println();
        System.out.println();
    }

    public void displayReservedRooms(){

        System.out.println("-----------------------------------------------------------------------------------------------------------------------");
        System.out.println("Displaying the Reserved Rooms");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------");
        System.out.println("");

        if(reserved_rooms_count<1){
            System.out.println("NO ROOMS RESERVED AT THE MOMENT");
            System.out.println("-----------------------------------------------------------------------------------------------------------------------");
            System.out.println("");
            return;
        }


        for (int i = 0; i < reserved_rooms_count; i++) {

            System.out.println("Guest Name: "+Reserved_rooms.get(i).getGuest_name());
            System.out.println("Room key: "+Reserved_rooms.get(i).getKey());

        }
        System.out.println("");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------");
        System.out.println("Number of Reserved Rooms: "+reserved_rooms_count);
        System.out.println("-----------------------------------------------------------------------------------------------------------------------");
        System.out.println();
        System.out.println();
    }

    public int getAvailble_rooms_count(){
        return availble_rooms_count;
    }

    public ArrayList<Hotel_room> getAvaiable_roomsArray(){
        return Available_rooms;
    }

}

public class Main {

    public static void main(String[] args) {
        // write your code here


    Hotel_Management_System h=new Hotel_Management_System();

    h.setting_Avaialble_rooms();

    h.displayAvailableRooms();

    h.reserveRoom("henty",4);
        h.reserveRoom("hentyt",3);
        h.reserveRoom("henry",7);
        h.reserveRoom("james",10);
        h.reserveRoom("geo",1);

        h.bookRoom("mash",8);
        h.bookRoom("hili",5);
        h.bookRoom("hasnd",2);

        System.out.println(h.searchGuest(6));


    h.displayReservedRooms();
    h.displayAvailableRooms();

    //Checkiing the exception
        try{
            Hotel_room ho = new Hotel_room();
            ho.setName2("");
        }
        catch(nameCannotBeNull e){
            System.out.println(e.getMessage());
        }

    }
}

