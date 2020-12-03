/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longtpmse140775.cart;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author ACER
 */
public class CartDTO implements Serializable {

    private int detaiId;
    private int roomId;
    private String roomName;
    private int hotelId;
    private String hotelName;
    private int cateId;
    private String cateName;
    private int quantity;
    private float price;
    private float total;
    private Date checkin;
    private Date checkout;
    private int bookId;
    private String userId;

    public CartDTO() {
    }

    public CartDTO(int cateId, Date checkin, int bookId) {
        this.cateId = cateId;
        this.checkin = checkin;
        this.bookId = bookId;
    }


    
    public CartDTO(Date checkin, int bookId, String userId) {
        this.checkin = checkin;
        this.bookId = bookId;
        this.userId = userId;
    }

    public CartDTO(int roomId, String hotelName, String cateName, int quantity, float price) {
        this.roomId = roomId;
        this.hotelName = hotelName;
        this.cateName = cateName;
        this.quantity = quantity;
        this.price = price;
    }

    public CartDTO(int roomId, String hotelName, String cateName, int quantity, float price, Date checkin, Date checkout) {
        this.roomId = roomId;
        this.hotelName = hotelName;
        this.cateName = cateName;
        this.quantity = quantity;
        this.price = price;
        this.checkin = checkin;
        this.checkout = checkout;
    }

    public CartDTO(int detaiId, int roomId, String hotelName, String cateName, int quantity, float price, Date checkin, Date checkout) {
        this.detaiId = detaiId;
        this.roomId = roomId;
        this.hotelName = hotelName;
        this.cateName = cateName;
        this.quantity = quantity;
        this.price = price;
        this.checkin = checkin;
        this.checkout = checkout;
    }

    public CartDTO(int detaiId, int roomId, String hotelName, String cateName, int quantity, float price, Date checkin, Date checkout, int bookId) {
        this.detaiId = detaiId;
        this.roomId = roomId;
        this.hotelName = hotelName;
        this.cateName = cateName;
        this.quantity = quantity;
        this.price = price;
        this.checkin = checkin;
        this.checkout = checkout;
        this.bookId = bookId;
    }

        public CartDTO(int detaiId, int roomId, String hotelName, String cateName, int quantity, float price, Date checkin, Date checkout, int cateId, int bookId) {
        this.detaiId = detaiId;
        this.roomId = roomId;
        this.hotelName = hotelName;
        this.cateName = cateName;
        this.quantity = quantity;
        this.price = price;
        this.checkin = checkin;
        this.checkout = checkout;
        this.cateId = cateId;
        this.bookId = bookId;
    }
    
    public CartDTO(int roomId, int quantity, float price, Date checkin, Date checkout) {
        this.roomId = roomId;
        this.quantity = quantity;
        this.price = price;
        this.checkin = checkin;
        this.checkout = checkout;
    }

    public CartDTO(int detaiId, int roomId, String roomName, int hotelId, String hotelName, int cateId, String cateName, int quantity, float price, float total, Date checkin, Date checkout, int bookId, String userId) {
        this.detaiId = detaiId;
        this.roomId = roomId;
        this.roomName = roomName;
        this.hotelId = hotelId;
        this.hotelName = hotelName;
        this.cateId = cateId;
        this.cateName = cateName;
        this.quantity = quantity;
        this.price = price;
        this.total = total;
        this.checkin = checkin;
        this.checkout = checkout;
        this.bookId = bookId;
        this.userId = userId;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public int getCateId() {
        return cateId;
    }

    public void setCateId(int cateId) {
        this.cateId = cateId;
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public Date getCheckin() {
        return checkin;
    }

    public void setCheckin(Date checkin) {
        this.checkin = checkin;
    }

    public Date getCheckout() {
        return checkout;
    }

    public void setCheckout(Date checkout) {
        this.checkout = checkout;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getDetaiId() {
        return detaiId;
    }

    public void setDetaiId(int detaiId) {
        this.detaiId = detaiId;
    }

}
