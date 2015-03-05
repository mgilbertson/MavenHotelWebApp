/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.web.model.hotel;

import hotel.web.model.db.DBAccessor;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Mitch
 */
public class HotelDAO {

    private DBAccessor db;

    public HotelDAO() {
    }

    public HotelDAO(DBAccessor db) {
        this.db = db;
    }

    private void openLocalDbConnection() throws Exception {
        db.openConnection(
                "com.mysql.jdbc.Driver",
                "jdbc:mysql://localhost:3306/hotel_lab",
                "root", "admin");
    }

    public void save(Hotel hotel) throws Exception {
        openLocalDbConnection();

        String tableName = "hotel";
        List<String> fieldNames = new ArrayList<String>();
        fieldNames.add("hotel_name");
        fieldNames.add("street_address");
        fieldNames.add("city");
        fieldNames.add("state");
        fieldNames.add("postal_code");
        fieldNames.add("notes");
        List fieldValues = new ArrayList();
        fieldValues.add(hotel.getHotelName());
        fieldValues.add(hotel.getStreetAddress());
        fieldValues.add(hotel.getCity());
        fieldValues.add(hotel.getState());
        fieldValues.add(hotel.getPostalCode());
        fieldValues.add(hotel.getNotes());

        if (hotel.getHotelId() == null) {
            db.insertRecord(
                    tableName, fieldNames,
                    fieldValues);
        } else {
            db.updateRecords(
                    tableName, fieldNames,
                    fieldValues, "hotel_id", hotel.getHotelId());
        }

    }

    public void deleteHotel(Hotel hotel) throws Exception {
        openLocalDbConnection();
        db.deleteRecords("hotel", "hotel_id", hotel.getHotelId());

    }

    public List<Hotel> getAllHotels() throws Exception {
        openLocalDbConnection();
        List<Map> rawData;
        List<Hotel> records = new ArrayList<>();
        rawData = db.findAllRecordsInTable("hotel");
        Hotel hotel;
        for (Map m : rawData) {
            hotel = new Hotel();
            String id = m.get("hotel_id").toString();
            hotel.setHotelId(new Long(id));
            String name = m.get("hotel_name").toString();
            hotel.setHotelName(name);
            String address = m.get("street_address").toString();
            hotel.setStreetAddress(address);
            String city = m.get("city").toString();
            hotel.setCity(city);
            String state = m.get("state").toString();
            hotel.setState(state);
            String postalCode = m.get("postal_code").toString();
            hotel.setPostalCode(postalCode);
            String notes = m.get("notes").toString();
            hotel.setNotes(notes);
            records.add(hotel);
        }
        return records;
    }

    public Hotel findHotelById(String id) throws Exception {
        openLocalDbConnection();
        
        Map rec;
        rec = db.getRecordById("Hotel", "hotel_id", new Long(id));

        Hotel hotel = new Hotel();
        hotel.setHotelId(new Long(rec.get("hotel_id").toString()));
        hotel.setHotelName(rec.get("hotel_name").toString());
        hotel.setStreetAddress(rec.get("street_address").toString());
        hotel.setCity(rec.get("city").toString());
        hotel.setState(rec.get("state").toString());
        hotel.setPostalCode(rec.get("postal_code").toString());
        hotel.setNotes(rec.get("notes").toString());

        return hotel;
    }

    public DBAccessor getDb() {
        return this.db;
    }

    public void setDb(DBAccessor db) {
        this.db = db;
    }
}

