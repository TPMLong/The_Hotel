/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longtpmse140775.room;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author ACER
 */
public class RoomDTO implements Serializable{
    private int roomId;
    private int cateId;
    private int hotelID;
    private int quantity;
    private String hotelName;
    private String cateName;
    private Date inDate;
    private Date outDate;
    private float price;
    private String location;
    
    public RoomDTO() {
    }

    public RoomDTO(int hotelID, String hotelName, String location) {
        this.hotelID = hotelID;
        this.hotelName = hotelName;
        this.location = location;
    }
    
    public RoomDTO(int cateId, int roomId, Date inDate, Date outDate) {
        this.cateId = cateId;
        this.roomId = roomId;
        this.inDate = inDate;
        this.outDate = outDate;
    }

    public RoomDTO(int hotelID, int quantity, String hotelName, String location) {
        this.hotelID = hotelID;
        this.quantity = quantity;
        this.hotelName = hotelName;
        this.location = location;
    }
    
    public RoomDTO(int quantity, String hotelName, String cateName, float price, String location) {
        this.quantity = quantity;
        this.hotelName = hotelName;
        this.cateName = cateName;
        this.price = price;
        this.location = location;
    }
    
        public RoomDTO(int roomId, int quantity, String hotelName, String cateName, float price, String location) {
        this.roomId = roomId;
        this.quantity = quantity;
        this.hotelName = hotelName;
        this.cateName = cateName;
        this.price = price;
        this.location = location;
    }
    
    public RoomDTO(int roomId, int cateId, int hotelID, int quantity, String hotelName, String cateName, Date inDate, Date outDate, float price, String location) {
        this.roomId = roomId;
        this.cateId = cateId;
        this.hotelID = hotelID;
        this.quantity = quantity;
        this.hotelName = hotelName;
        this.cateName = cateName;
        this.inDate = inDate;
        this.outDate = outDate;
        this.price = price;
        this.location = location;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getCateId() {
        return cateId;
    }

    public void setCateId(int cateId) {
        this.cateId = cateId;
    }

    public int getHotelID() {
        return hotelID;
    }

    public void setHotelID(int hotelID) {
        this.hotelID = hotelID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }

    public Date getInDate() {
        return inDate;
    }

    public void setInDate(Date inDate) {
        this.inDate = inDate;
    }

    public Date getOutDate() {
        return outDate;
    }

    public void setOutDate(Date outDate) {
        this.outDate = outDate;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    
}
