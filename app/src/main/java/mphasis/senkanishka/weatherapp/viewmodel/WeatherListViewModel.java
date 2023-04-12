package mphasis.senkanishka.weatherapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import mphasis.senkanishka.weatherapp.remote.database.model.WeatherCityModel;
import mphasis.senkanishka.weatherapp.remote.database.repository.WeatherCityListRepository;

public class WeatherListViewModel extends AndroidViewModel {

    // creating a new variable for city repository.
    private WeatherCityListRepository repository;

    // below line is to create a variable for live
    // data where all the city are present.
    private LiveData<List<WeatherCityModel>> allCityWeather;

    // constructor for our view modal.
    public WeatherListViewModel(@NonNull Application application) {
        super(application);
        repository = new WeatherCityListRepository(application);
        allCityWeather = repository.getAllCityWeather();
    }

    // below method is use to insert the data to our repository.
    public void insert(WeatherCityModel model) {
        repository.insert(model);
    }

    // below line is to delete the data in our repository.
    public void delete(WeatherCityModel model) {
        repository.delete(model);
    }

    // below method is to get all the city in our list.
    public LiveData<List<WeatherCityModel>> getAllCityWeather() {
        return allCityWeather;
    }
}
