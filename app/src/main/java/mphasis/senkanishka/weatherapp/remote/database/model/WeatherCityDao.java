package mphasis.senkanishka.weatherapp.remote.database.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

//Adding annotation to our Dao class
@Dao
public interface WeatherCityDao {

    //below method is use to add data to database.
    @Insert
    void insert(WeatherCityModel model);

    //below method is use to update the data in our database.
    @Update
    void update(WeatherCityModel model);

    //below line is use to delete a specific city in our database.
    @Delete
    void delete(WeatherCityModel model);

    //on below line we are making query to delete all city from our databse.
    @Query("DELETE FROM region_table")
    void deleteAllCityWeather();

    //beloe line is to read all the city from our database.
    //in this we are ordering our city in ascending order with our city name.
    @Query("SELECT * FROM region_table ORDER BY modified_date DESC")
    LiveData<List<WeatherCityModel>> getAllCityWeather();

}
