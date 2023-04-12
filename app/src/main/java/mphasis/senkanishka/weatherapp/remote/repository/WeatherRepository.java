package mphasis.senkanishka.weatherapp.remote.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import mphasis.senkanishka.weatherapp.remote.model.Region1;
import mphasis.senkanishka.weatherapp.remote.retrofit.ApiRequest;
import mphasis.senkanishka.weatherapp.remote.retrofit.RetrofitNetworkRequest;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherRepository {
    private static final String TAG = WeatherRepository.class.getSimpleName();
    private ApiRequest apiRequest;

    public WeatherRepository() {
        apiRequest = RetrofitNetworkRequest.getRetrofitInstance().create(ApiRequest.class);
    }

    public LiveData<Region1> getWeatherDetails(String mRegionName, String key, String apiKey) {
        final MutableLiveData<Region1> data = new MutableLiveData<>();
        apiRequest.getRegion(mRegionName, key, apiKey)
                .enqueue(new Callback<Region1>() {

                    @Override
                    public void onResponse(Call<Region1> call, Response<Region1> response) {
                        Log.d(TAG, "onResponse response:: " + response);

                        if (response.body() != null) {
                            data.setValue(response.body());

                            Log.d(TAG, "Name " + response.body().name);
                            Log.d(TAG, "Pressure " + response.body().main.pressure);
                        }
                    }

                    @Override
                    public void onFailure(Call<Region1> call, Throwable t) {

                        data.setValue(null);
                    }
                });
        return data;
    }
}
