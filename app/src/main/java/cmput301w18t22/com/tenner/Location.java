package cmput301w18t22.com.tenner;

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

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
