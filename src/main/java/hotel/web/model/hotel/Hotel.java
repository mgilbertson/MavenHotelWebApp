/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.web.model.hotel;

/**
 *
 * @author Mitch
 */
public class Hotel {
    
    private Long hotelId;
    private String hotelName;
    private String streetAddress;
    private String city;
    private String state;
    private String postalCode;
    private String notes;

    public Hotel(){
        
    }

    public Hotel(String hotelName) {
        this.hotelName = hotelName;
    }

    public Hotel(Long hotelId, String hotelName, String streetAddress, String city, String state, String postalCode, String notes) {
        this.hotelId = hotelId;
        this.hotelName = hotelName;
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.notes = notes;
    }
    
    public Long getHotelId() {
        return hotelId;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "Hotel{" + "hotelId=" + hotelId + ", hotelName=" + hotelName + ", streetAddress=" + streetAddress + ", city=" + city + ", state=" + state + ", postalCode=" + postalCode + ", notes=" + notes + '}';
    }
    
}
