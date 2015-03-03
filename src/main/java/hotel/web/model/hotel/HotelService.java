/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.web.model.hotel;

import hotel.web.model.db.DBAccessor;
import hotel.web.model.db.DB_MYSQL;
import java.util.List;

/**
 *
 * @author Mitch
 */
public class HotelService {
    private HotelDAO hotelDao;
    
    public HotelService() {
        DBAccessor db = new DB_MYSQL();
        hotelDao = new HotelDAO(db);
    }
    
    public List<Hotel> getAllHotels() throws Exception {
        return hotelDao.getAllHotels();
    }
    
    public Hotel getHotelById(String id) throws Exception{
        return hotelDao.findHotelById(id);
    }
    
    public void saveHotel(Hotel hotel) throws Exception{
        hotelDao.save(hotel);
    }
    
    public void deleteHotel(Hotel hotel) throws Exception{
        hotelDao.deleteHotel(hotel);
    }

    public static void main(String[] args) throws Exception {
        HotelService hr = new HotelService();
        
        System.out.println("Getting all Hotels...\n");
        System.out.println(hr.getAllHotels());
        
        System.out.println("\nGetting hotel 1...\n");
        System.out.println(hr.getHotelById("3"));
    }
}

