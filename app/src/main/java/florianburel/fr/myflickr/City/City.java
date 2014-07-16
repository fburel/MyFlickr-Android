package florianburel.fr.myflickr.City;

/**
 * Created by fl0 on 16/07/2014.
 */
public class City {

    private String name;
    private double longitude;
    private double latitude;
    private long id;

    @Override
    public String toString() {
        return name;
    }

    /*
   CONSTRUCTOR
    */

    public City(long id) {
        this.id = id;
    }

    /*
    ACCESSORS
     */

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }
}
