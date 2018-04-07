package cmput301w18t22.com.tenner.helpers;

public class MapsHelper {
    private static final MapsHelper ourInstance = new MapsHelper();

    static MapsHelper getInstance() {
        return ourInstance;
    }

    private MapsHelper() {
    }

    public double getDistance(float sourceLat, float sourceLong, float destLat, float destLong){
        int radius = 6371;

        //Haversine formula
        //https://bigdatanerd.wordpress.com/2011/11/03/java-implementation-of-haversine-formula-for-distance-calculation-between-two-points/

        double latitudeDegrees = degreesToRadians(destLat - sourceLat);
        double longitudeDegrees = degreesToRadians(destLong - sourceLong);

        double angle =
                Math.sin(latitudeDegrees/2) * Math.sin(latitudeDegrees/2) +
                        Math.cos(degreesToRadians(sourceLat)) * Math.cos(degreesToRadians(destLat)) *
                                Math.sin(longitudeDegrees/2) * Math.sin(longitudeDegrees/2);

        double c = 2 * Math.atan2(Math.sqrt(angle), Math.sqrt(1-angle));
        double distance = radius * c;

        return distance;
    }

    double degreesToRadians(float degrees) {
        return  degrees * (Math.PI/180);
    }
}
