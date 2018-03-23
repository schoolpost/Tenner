package cmput301w18t22.com.tenner.classes;

/**
 * The location class defines a location in latitude and longitude
 * and as a simple address string. Facilitates map views.
 *
 * @author Team 22
 * @version 1.1
 */
public class Location {

    private float latitude;
    private float longitude;
    private String address;

    public void Location(float latitude, float longitude, String address) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
    }

    /**
     * @return the latitude of this location
     * @see Location#setLatitude
     */
    public float getLatitude() {
        return latitude;
    }


    /**
     * Set the latitude for this location.
     *
     * @param latitude Float value of latitude for location
     */
    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }


    /**
     * @return the longitude of this location
     * @see Location#setLongitude
     */
    public float getLongitude() {
        return longitude;
    }


    /**
     * Set the longitude for this location.
     *
     * @param longitude Float value of longitude for location
     */
    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }


    /**
     * @return String representing the address of this location
     * @see Location#setAddress
     */
    public String getAddress() {
        return address;
    }


    /**
     * Set the address for this location. Once we address the map use cases we will define limits.
     *
     *
     * @param address String representing the address of this location
     */
    public void setAddress(String address) {
        this.address = address;
    }

}
