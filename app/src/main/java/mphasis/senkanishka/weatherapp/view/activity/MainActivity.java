package mphasis.senkanishka.weatherapp.view.activity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import mphasis.senkanishka.weatherapp.R;
import mphasis.senkanishka.weatherapp.constant.AppConstant;
import mphasis.senkanishka.weatherapp.remote.database.model.WeatherCityModel;
import mphasis.senkanishka.weatherapp.remote.model.Region1;
import mphasis.senkanishka.weatherapp.view.adapter.RegionAdapter;
import mphasis.senkanishka.weatherapp.view.fragment.AddFragment;
import mphasis.senkanishka.weatherapp.viewmodel.WeatherListViewModel;
import mphasis.senkanishka.weatherapp.viewmodel.WeatherViewModel;

public class MainActivity extends AppCompatActivity {
    private WeatherViewModel viewModel;
    private WeatherListViewModel weatherListViewModel;

    private FloatingActionButton fabAdd;
    private RecyclerView mRecyclerView;
    private RegionAdapter mRegionAdapter;
    private AppCompatTextView tvNotFound;
    private ArrayList<List<WeatherCityModel>> mRegionList = new ArrayList<List<WeatherCityModel>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        inits();
        actions();
    }

    private void actions() {
        mRecyclerView.setVisibility(View.GONE);
        tvNotFound.setVisibility(View.VISIBLE);

        fabAdd.setOnClickListener(view -> {
            AddFragment fragment = AddFragment.newInstance();
            fragment.setCancelable(false);
            fragment.show(getSupportFragmentManager(), "kukku");
        });

        fetch();


    }

    private void fetch() {
        weatherListViewModel.getAllCityWeather().observe(this, weatherCityModels -> {
            if (weatherCityModels.size() == 0) {
                tvNotFound.setVisibility(View.VISIBLE);
                mRecyclerView.setVisibility(View.GONE);
            } else {
                mRegionList.add(weatherCityModels);
                mRegionAdapter = new RegionAdapter(MainActivity.this, weatherCityModels);
                mRecyclerView.setAdapter(mRegionAdapter);
                mRegionAdapter.notifyDataSetChanged();
                tvNotFound.setVisibility(View.GONE);
                mRecyclerView.setVisibility(View.VISIBLE);
                System.out.println(weatherCityModels);
            }
        });
    }

    private void inits() {
        fabAdd = findViewById(R.id.fabAdd);
        mRecyclerView = findViewById(R.id.recyclerView);
        tvNotFound = findViewById(R.id.tvNotFound);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setHasFixedSize(true);

        viewModel = new ViewModelProvider(this).get(WeatherViewModel.class);
        weatherListViewModel = new ViewModelProvider(this).get(WeatherListViewModel.class);
    }

    public void search(String mRegionName) {
        viewModel.getWeatherDetailsFromServer(mRegionName);
        viewModel.getWeatherResponseLiveData().observe(this, weatherResponse -> {
            if (weatherResponse != null) {
                String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
                WeatherCityModel weatherCityModel = new WeatherCityModel(weatherResponse.name, weatherResponse.sys.country,
                        weatherResponse.getMain().getHumidity(), weatherResponse.getWind().speed * 3.6, weatherResponse.getMain().getTemp_max(),
                        weatherResponse.getMain().getTemp_min(), weatherResponse.getMain().getTemp(), weatherResponse.sys.getSunrise(),
                        weatherResponse.sys.getSunset(), weatherResponse.getMain().getPressure(),
                        AppConstant.BASE_IMAGE_URL + weatherResponse.getWeather()[0].icon + ".png", timeStamp);
                weatherListViewModel.insert(weatherCityModel);
            }
        });
    }
}