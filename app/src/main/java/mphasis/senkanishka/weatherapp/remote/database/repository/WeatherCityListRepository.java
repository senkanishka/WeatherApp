package mphasis.senkanishka.weatherapp.remote.database.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

import mphasis.senkanishka.weatherapp.remote.database.AppDatabase;
import mphasis.senkanishka.weatherapp.remote.database.model.WeatherCityDao;
import mphasis.senkanishka.weatherapp.remote.database.model.WeatherCityModel;

public class WeatherCityListRepository {

    // below line is the create a variable
    // for dao and list for all city.
    private WeatherCityDao dao;
    private LiveData<List<WeatherCityModel>> allCityWeather;

    // creating a constructor for our variables
    // and passing the variables to it.
    public WeatherCityListRepository(Application application) {
        AppDatabase database = AppDatabase.getInstance(application);
        dao = (WeatherCityDao) database.weatherCityDao();
        allCityWeather = dao.getAllCityWeather();
    }

    // creating a method to insert the data to our database.
    public void insert(WeatherCityModel model) {
        new InsertCityWeatherAsyncTask(dao).execute(model);
    }

    // creating a method to delete the data in our database.
    public void delete(WeatherCityModel model) {
        new DeleteCityWeatherAsyncTask((WeatherCityDao) dao).execute(model);
    }

    // below method is to read all the weather.
    public LiveData<List<WeatherCityModel>> getAllCityWeather() {
        return allCityWeather;
    }

    // we are creating a async task method to insert new weather.
    private static class InsertCityWeatherAsyncTask extends AsyncTask<WeatherCityModel, Void, Void> {
        private WeatherCityDao weatherCityDao;

        public InsertCityWeatherAsyncTask(WeatherCityDao weatherCityDao) {
            this.weatherCityDao = weatherCityDao;
        }

        @Override
        protected Void doInBackground(WeatherCityModel... model) {
            // below line is use to insert our modal in dao.
            weatherCityDao.insert(model[0]);
            return null;
        }
    }

    // we are creating a async task method to delete city weather.
    private static class DeleteCityWeatherAsyncTask extends AsyncTask<WeatherCityModel, Void, Void> {
        private WeatherCityDao weatherCityDao;

        private DeleteCityWeatherAsyncTask(WeatherCityDao weatherCityDao) {
            this.weatherCityDao = weatherCityDao;
        }

        @Override
        protected Void doInBackground(WeatherCityModel... models) {
            // below line is use to delete
            // our weather modal in dao.
            weatherCityDao.delete(models[0]);
            return null;
        }
    }

}
