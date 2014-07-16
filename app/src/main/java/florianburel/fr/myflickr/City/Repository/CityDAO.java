package florianburel.fr.myflickr.City.Repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import florianburel.fr.myflickr.City.City;

/**
 * Created by fl0 on 16/07/2014.
 */
public class CityDAO extends SQLiteOpenHelper {
    private static final int CURRENT_DB_VERSION = 1;

    /*
    SINGLETON
     */

    private static CityDAO sInstance;


    public static CityDAO getInstance(Context context) {
        if (sInstance == null) {
            //Always pass in the Application Context
            sInstance = new CityDAO(context.getApplicationContext());
        }

        return sInstance;
    }

    private CityDAO(Context context) {
        super(context, "Flickr.sqlite", null, CURRENT_DB_VERSION);
    }


    /*
    DATABASE CREATION
     */

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String stmt = "create table City\n" +
                "(\n" +
                "  id        INTEGER not null,\n" +
                "  name      \"VARCHAR()\",\n" +
                "  longitude DOUBLE,\n" +
                "  latitude  DOUBLE,\n" +
                "  primary key (id)\n" +
                ");";

        sqLiteDatabase.execSQL(stmt);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {

    }

    /*
    DAO
     */

    public ArrayList<City> getAllCities()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM City;", null);

        ArrayList<City> cities = new ArrayList<City>();
        c.moveToFirst();
        while (!c.isAfterLast())
        {
            int id = c.getInt(0);
            String name = c.getString(1);
            double longitude = c.getDouble(2);
            double latitude = c.getDouble(3);

            City city = new City(id);
            city.setName(name);
            city.setLatitude(latitude);
            city.setLongitude(longitude);

            cities.add(city);
        }

        c.close();

        return cities;
    }

    public City createCity() {

        int id = getMaxId() + 1;

        City city = new City(id);
        city.setName("");
        city.setLatitude(0);
        city.setLongitude(0);


        ContentValues cv = new ContentValues();
        cv.put("id", city.getId());
        cv.put("name", city.getName());
        cv.put("longitude", city.getLongitude());
        cv.put("latitude", city.getLatitude());

        this.getWritableDatabase().insert("City", null, cv);

        return city;
    }


    public void updateCity(City city)
    {
        ContentValues cv = new ContentValues();
        cv.put("id", city.getId());
        cv.put("name", city.getName());
        cv.put("longitude", city.getLongitude());
        cv.put("latitude", city.getLatitude());

        this.getWritableDatabase().update("City", cv, "id = " + city.getId(), null);
    }

    public void deleteCity(City city)
    {
        this.getWritableDatabase().delete("City", "id = " + city.getId(), null);
    }

    public int getMaxId() {

        String sql = "SELECT MAX(id) FROM City";
        Cursor c = this.getReadableDatabase().rawQuery(sql, null);


        c.moveToFirst();

        int id = c.getInt(0);

        c.close();

        return id;

    }
}
