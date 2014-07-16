package florianburel.fr.myflickr.City.Localisation;

import android.content.ContentValues;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

import florianburel.fr.myflickr.City.City;

/**
 * Created by fl0 on 16/07/2014.
 */
public class Localiser implements LocationListener
{

    private static Localiser sInstance;

    public static Localiser getInstance(Context context) {
        if (sInstance == null) {
            //Always pass in the Application Context
            sInstance = new Localiser(context.getApplicationContext());
        }

        return sInstance;
    }

    private Context context;
    private LocationManager locationManager;

    private Localiser(Context context) {
        this.context = context;
        this.locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
    }

    private City city;

    public void startLocalizingCity(City city)
    {
        this.city = city;
        this.locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, this);
    }

    @Override
    public void onLocationChanged(Location location) {

        locationManager.removeUpdates(this);
        this.city.setLatitude(location.getLatitude());
        this.city.setLongitude(location.getLongitude());

        Geocoder geocoder= new Geocoder(this.context);

        try {

            //Place your latitude and longitude
            List<Address> addresses = geocoder.getFromLocation(city.getLatitude(),city.getLongitude(), 1);

            if(addresses != null) {

                Address fetchedAddress = addresses.get(0);
                this.city.setName(fetchedAddress.getLocality());

            }

            else
                this.city.setName("No location found..!");

        }
        catch (IOException e) {

            Toast.makeText(this.context, "Could not get address..!", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}