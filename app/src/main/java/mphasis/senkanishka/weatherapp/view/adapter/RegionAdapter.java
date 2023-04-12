package mphasis.senkanishka.weatherapp.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import mphasis.senkanishka.weatherapp.R;
import mphasis.senkanishka.weatherapp.constant.AppConstant;
import mphasis.senkanishka.weatherapp.remote.database.model.WeatherCityModel;

public class RegionAdapter extends RecyclerView.Adapter<RegionAdapter.ViewHolder> {

    private Context context;
    List<WeatherCityModel> mRegionList;

    public RegionAdapter(Context context, List<WeatherCityModel> mRegionList) {
        this.context = context;
        this.mRegionList = mRegionList;
    }

    @NonNull
    @Override
    public RegionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_region,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RegionAdapter.ViewHolder viewHolder, int i) {
        WeatherCityModel region =mRegionList.get(i);
        Locale l = new Locale("en", region.getSys());
        String countryName = l.getDisplayCountry();

        viewHolder.tvName.setText(region.getName());
        viewHolder.tvCountryName.setText(countryName);
        viewHolder.tvHumidity.setText("Humidity " + region.getHumidity() + "%");
        viewHolder.tvSpeed.setText(String.format(context.getString(R.string.speed), (region.getWindSpeed() * 3.6)));
        viewHolder.tvBetween.setText("" + Math.round(region.getTempMin()) + " / " + Math.round(region.getTempMax()) + "\u2103");
        viewHolder.tvCurrentTemp.setText("" + Math.round(region.getTemp()) + "℃");

        Date date = new Date(region.getSunriseTime() * 1000L);
        SimpleDateFormat format = new SimpleDateFormat("hh:mm", Locale.getDefault());
        String mSunRiseTime = format.format(date);
        viewHolder.tvSunRise.setText(String.format(context.getString(R.string.sun_rise), mSunRiseTime));
        date = new Date(region.getSunsetTime() * 1000L);
        String mSunSetTime = format.format(date);
        viewHolder.tvSunSet.setText(String.format(context.getString(R.string.sun_set), mSunSetTime));
        viewHolder.tvPressure.setText(String.format(context.getString(R.string.pressure), region.getPressure() / 1.333));

        System.out.println(region.getImageURL());
        Glide.with(context).load(region.getImageURL()).into(viewHolder.ivIcon);
    }

    @Override
    public int getItemCount() {
        return mRegionList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        AppCompatTextView tvName;
        AppCompatTextView tvCountryName;
        AppCompatTextView tvHumidity;
        AppCompatTextView tvCurrentTemp;
        AppCompatTextView tvSpeed;
        AppCompatTextView tvBetween;
        AppCompatTextView tvSunRise;
        AppCompatTextView tvSunSet;
        AppCompatTextView tvPressure;
        AppCompatImageView ivIcon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tvName);
            tvCountryName = itemView.findViewById(R.id.tvCountryName);
            tvHumidity = itemView.findViewById(R.id.tvHumidity);
            tvSpeed = itemView.findViewById(R.id.tvSpeed);
            tvCurrentTemp = itemView.findViewById(R.id.tvCurrent);
            tvBetween = itemView.findViewById(R.id.tvBetween);
            tvSunRise = itemView.findViewById(R.id.tvSunRise);
            tvSunSet = itemView.findViewById(R.id.tvSunSet);
            tvPressure = itemView.findViewById(R.id.tvPressure);
            ivIcon = itemView.findViewById(R.id.ivIcon);
        }
    }
}
