package cmput301w18t22.com.tenner;

/**
 * Created by Schoolpost on 2018-02-26.
 */

/**
 * The Location class represents a task's location with latitude, longitude, and a physical address
 * description.
 *
 * @author Team 22
 * @version 1.1
 * @see Location
 */

public class Location {

    private float latitude;
    private float longitude;
    private String address;

    public Location(float latitude, float longitude, String address) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
    }

    /**
     * @return this location's latitude
     * @see Location # setLatitude
     */
    public float getLatitude() {
        return latitude;
    }

    /**
     * Set the latitude of this location
     * @param latitude, new float value for this location
     */
    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    /**
     * @return this location's longitude
     * @see Location #setLongitude
     */
    public float getLongitude() {
        return longitude;
    }

    /**
     * Set the longitude of this location
     * @param longitude, new float value for this location
     */
    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    /**
     * @return this location's address
     * @see Location # setAddress
     */
    public String getAddress() {
        return address;
    }

    /**
     * Set the address of this location
     * @param address, new String value for this latitude
     */
    public void setAddress(String address) {
        this.address = address;
    }
}