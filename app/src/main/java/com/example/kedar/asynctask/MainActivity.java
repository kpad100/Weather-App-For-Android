package com.example.kedar.asynctask;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {
    EditText zipCode;
    String zip;
    Button enter;
    TextView quote;
    TextView currentTemp;
    ImageView currentWeather;
    TextView time;
    TextView time2;
    TextView time3;
    TextView time4;
    TextView time5;
    ImageView image;
    ImageView image2;
    ImageView image3;
    ImageView image4;
    ImageView image5;
    TextView high;
    TextView high2;
    TextView high3;
    TextView high4;
    TextView high5;
    TextView low;
    TextView low2;
    TextView low3;
    TextView low4;
    TextView low5;
    TextView currentTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        zipCode = (EditText)(findViewById(R.id.zipCode));
        enter = (Button)(findViewById(R.id.enter));
        quote = (TextView)(findViewById(R.id.quote));
        currentTemp = (TextView)(findViewById(R.id.currentTemp));
        currentTime = (TextView)(findViewById(R.id.currentTime));
        currentWeather = (ImageView)(findViewById(R.id.currentWeather));
        time = (TextView)(findViewById(R.id.time));
        time2 = (TextView)(findViewById(R.id.time2));
        time3 = (TextView)(findViewById(R.id.time3));
        time4 = (TextView)(findViewById(R.id.time4));
        time5 = (TextView)(findViewById(R.id.time5));
        image = (ImageView)(findViewById(R.id.image));
        image2 = (ImageView)(findViewById(R.id.image2));
        image3 = (ImageView)(findViewById(R.id.image3));
        image4 = (ImageView)(findViewById(R.id.image4));
        image5 = (ImageView)(findViewById(R.id.image5));
        high = (TextView)(findViewById(R.id.high));
        high2 = (TextView)(findViewById(R.id.high2));
        high3 = (TextView)(findViewById(R.id.high3));
        high4 = (TextView)(findViewById(R.id.high4));
        high5 = (TextView)(findViewById(R.id.high5));
        low = (TextView)(findViewById(R.id.low));
        low2 = (TextView)(findViewById(R.id.low2));
        low3 = (TextView)(findViewById(R.id.low3));
        low4 = (TextView)(findViewById(R.id.low4));
        low5 = (TextView)(findViewById(R.id.low5));

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zip = zipCode.getText().toString();
                AsyncThread weatherThread = new AsyncThread();
                weatherThread.execute();
            }
        });
    }

    public class AsyncThread extends AsyncTask<Void, Void, Void>{
        JSONObject forecast;
        @Override
        protected Void doInBackground(Void... params) {

            try {
                URL forecastURL = new URL("http://api.openweathermap.org/data/2.5/forecast?zip="+zip+"&APPID=cb20b40421444ab2a3cb868244a264a8");
                URLConnection connection = forecastURL.openConnection();
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                forecast = new JSONObject(reader.readLine());
                reader.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
                try {
                    //forecast1 and current weather
                    if (forecast.getJSONArray("list").getJSONObject(0).getJSONArray("weather").getJSONObject(0).getString("main").equals("Clear")) {
                        if (forecast.getJSONArray("list").getJSONObject(0).getJSONObject("sys").getString("pod").equals("d")) {
                            image.setImageResource(R.drawable.sunny);
                            currentWeather.setImageResource(R.drawable.sunny);
                            quote.setText("Sunnier than a day on Tatooine");
                        } else {
                            image.setImageResource(R.drawable.moon);
                            currentWeather.setImageResource(R.drawable.moon);
                            quote.setText("Darker than on the surface of Umbara");
                        }
                        image.setVisibility(View.VISIBLE);
                        currentWeather.setVisibility(View.VISIBLE);
                    }
                    if (forecast.getJSONArray("list").getJSONObject(0).getJSONArray("weather").getJSONObject(0).getString("main").equals("Clouds")) {
                        image.setImageResource(R.drawable.clouds);
                        image.setVisibility(View.VISIBLE);
                        currentWeather.setImageResource(R.drawable.clouds);
                        currentWeather.setVisibility(View.VISIBLE);
                        quote.setText("Cloudy like Cloud City");
                    }
                    if (forecast.getJSONArray("list").getJSONObject(0).getJSONArray("weather").getJSONObject(0).getString("main").equals("Snow")) {
                        image.setImageResource(R.drawable.snow);
                        image.setVisibility(View.VISIBLE);
                        currentWeather.setImageResource(R.drawable.snow);
                        currentWeather.setVisibility(View.VISIBLE);
                        quote.setText("Snowing as hard as a day on Hoth");
                    }
                    if (forecast.getJSONArray("list").getJSONObject(0).getJSONArray("weather").getJSONObject(0).getString("main").equals("Fog")) {
                        image.setImageResource(R.drawable.fog);
                        image.setVisibility(View.VISIBLE);
                        currentWeather.setImageResource(R.drawable.fog);
                        currentWeather.setVisibility(View.VISIBLE);
                        quote.setText("Foggy like the planet Coruscant");
                    }
                    if (forecast.getJSONArray("list").getJSONObject(0).getJSONArray("weather").getJSONObject(0).getString("main").equals("Rain")) {
                        image.setImageResource(R.drawable.rain);
                        image.setVisibility(View.VISIBLE);
                        currentWeather.setImageResource(R.drawable.rain);
                        currentWeather.setVisibility(View.VISIBLE);
                        quote.setText("Raining harder on the aquatic planet Kamino");
                    }
                    if (forecast.getJSONArray("list").getJSONObject(0).getJSONArray("weather").getJSONObject(0).getString("main").equals("Thunderstorms")) {
                        image.setImageResource(R.drawable.thunderstorm);
                        image.setVisibility(View.VISIBLE);
                        currentWeather.setImageResource(R.drawable.thunderstorm);
                        currentWeather.setVisibility(View.VISIBLE);
                        quote.setText("Thunderstorms as severe as on the planet Corbos");
                    }
                    if (forecast.getJSONArray("list").getJSONObject(0).getJSONArray("weather").getJSONObject(0).getString("main").equals("Mist")) {
                        image.setImageResource(R.drawable.fog);
                        image.setVisibility(View.VISIBLE);
                        currentWeather.setImageResource(R.drawable.fog);
                        currentWeather.setVisibility(View.VISIBLE);
                        quote.setText("Misty like the planet Coruscant");
                    }
                    int highNum = forecast.getJSONArray("list").getJSONObject(0).getJSONObject("main").getInt("temp_max");
                    high.setText((int) (9.0 / 5 * (highNum - 273) + 32) + "°F");
                    int lowNum = forecast.getJSONArray("list").getJSONObject(0).getJSONObject("main").getInt("temp_min");
                    low.setText((int)(9.0 / 5 * (lowNum - 273) + 32) + "°F");
                    int currentTempNum = forecast.getJSONArray("list").getJSONObject(0).getJSONObject("main").getInt("temp");
                    currentTemp.setText((int) (9.0 / 5 * (currentTempNum - 273) + 32) + "°F");
                    String timeAPI = forecast.getJSONArray("list").getJSONObject(0).getString("dt_txt");
                    String timeUTC = timeAPI.substring(11, 13);
                    int timeNum = Integer.parseInt(timeUTC);
                    int timeEST = timeNum-5;
                    if(timeEST>0 && timeEST<12)
                    {
                        time.setText(timeEST + ":00 AM");
                        currentTime.setText(timeEST + ":00 AM");
                    }
                    else if(timeEST<0) {
                        time.setText(timeEST + 12 + ":00 PM");
                        currentTime.setText(timeEST + 12 + ":00 PM");
                    }
                    else if (timeEST > 12) {
                        time.setText(timeEST - 12 + ":00 PM");
                        currentTime.setText(timeEST - 12 + ":00 PM");
                    }

                    //forecast 2
                    if (forecast.getJSONArray("list").getJSONObject(1).getJSONArray("weather").getJSONObject(0).getString("main").equals("Clear")) {
                        if (forecast.getJSONArray("list").getJSONObject(1).getJSONObject("sys").getString("pod").equals("d"))
                            image2.setImageResource(R.drawable.sunny);
                        else
                            image2.setImageResource(R.drawable.moon);
                        image2.setVisibility(View.VISIBLE);
                    }
                    if (forecast.getJSONArray("list").getJSONObject(1).getJSONArray("weather").getJSONObject(0).getString("main").equals("Clouds")) {
                        image2.setImageResource(R.drawable.clouds);
                        image2.setVisibility(View.VISIBLE);
                    }
                    if (forecast.getJSONArray("list").getJSONObject(1).getJSONArray("weather").getJSONObject(0).getString("main").equals("Snow")) {
                        image2.setImageResource(R.drawable.snow);
                        image2.setVisibility(View.VISIBLE);
                    }
                    if (forecast.getJSONArray("list").getJSONObject(1).getJSONArray("weather").getJSONObject(0).getString("main").equals("Fog")) {
                        image2.setImageResource(R.drawable.fog);
                        image2.setVisibility(View.VISIBLE);
                    }
                    if (forecast.getJSONArray("list").getJSONObject(1).getJSONArray("weather").getJSONObject(0).getString("main").equals("Rain")) {
                        image2.setImageResource(R.drawable.rain);
                        image2.setVisibility(View.VISIBLE);
                    }
                    if (forecast.getJSONArray("list").getJSONObject(1).getJSONArray("weather").getJSONObject(0).getString("main").equals("Thunderstorms")) {
                        image2.setImageResource(R.drawable.thunderstorm);
                        image2.setVisibility(View.VISIBLE);
                    }
                    if (forecast.getJSONArray("list").getJSONObject(1).getJSONArray("weather").getJSONObject(0).getString("main").equals("Mist")) {
                        image2.setImageResource(R.drawable.fog);
                        image2.setVisibility(View.VISIBLE);
                    }
                    int highNum2 = forecast.getJSONArray("list").getJSONObject(1).getJSONObject("main").getInt("temp_max");
                    high2.setText((int) (9.0 / 5 * (highNum2 - 273) + 32) + "°F");
                    int lowNum2 = forecast.getJSONArray("list").getJSONObject(1).getJSONObject("main").getInt("temp_min");
                    low2.setText((int) (9.0 / 5 * (lowNum2 - 273) + 32) + "°F");
                    String timeAPI2 = forecast.getJSONArray("list").getJSONObject(1).getString("dt_txt");
                    String timeUTC2 = timeAPI2.substring(11, 13);
                    int timeNum2 = Integer.parseInt(timeUTC2);
                    int timeEST2 = timeNum2-5;
                    if(timeEST2>0 && timeEST2<12)
                        time2.setText(timeEST2 + ":00 AM");
                    else if(timeEST2<0)
                        time2.setText(timeEST2 + 12 + ":00 PM");
                    else if (timeEST2 > 12)
                        time2.setText(timeEST2 - 12 + ":00 PM");

                    //forecast 3
                    if (forecast.getJSONArray("list").getJSONObject(2).getJSONArray("weather").getJSONObject(0).getString("main").equals("Clear")) {
                        if (forecast.getJSONArray("list").getJSONObject(2).getJSONObject("sys").getString("pod").equals("d"))
                            image3.setImageResource(R.drawable.sunny);
                        else
                            image3.setImageResource(R.drawable.moon);
                        image3.setVisibility(View.VISIBLE);
                    }
                    if (forecast.getJSONArray("list").getJSONObject(2).getJSONArray("weather").getJSONObject(0).getString("main").equals("Clouds")) {
                        image3.setImageResource(R.drawable.clouds);
                        image3.setVisibility(View.VISIBLE);
                    }
                    if (forecast.getJSONArray("list").getJSONObject(2).getJSONArray("weather").getJSONObject(0).getString("main").equals("Snow")) {
                        image3.setImageResource(R.drawable.snow);
                        image3.setVisibility(View.VISIBLE);
                    }
                    if (forecast.getJSONArray("list").getJSONObject(2).getJSONArray("weather").getJSONObject(0).getString("main").equals("Fog")) {
                        image3.setImageResource(R.drawable.fog);
                        image3.setVisibility(View.VISIBLE);
                    }
                    if (forecast.getJSONArray("list").getJSONObject(2).getJSONArray("weather").getJSONObject(0).getString("main").equals("Rain")) {
                        image3.setImageResource(R.drawable.rain);
                        image3.setVisibility(View.VISIBLE);
                    }
                    if (forecast.getJSONArray("list").getJSONObject(2).getJSONArray("weather").getJSONObject(0).getString("main").equals("Thunderstorms")) {
                        image3.setImageResource(R.drawable.thunderstorm);
                        image3.setVisibility(View.VISIBLE);
                    }
                    if (forecast.getJSONArray("list").getJSONObject(2).getJSONArray("weather").getJSONObject(0).getString("main").equals("Mist")) {
                        image3.setImageResource(R.drawable.fog);
                        image3.setVisibility(View.VISIBLE);
                    }
                    int highNum3 = forecast.getJSONArray("list").getJSONObject(2).getJSONObject("main").getInt("temp_max");
                    high3.setText((int) (9.0 / 5 * (highNum3 - 273) + 32) + "°F");
                    int lowNum3 = forecast.getJSONArray("list").getJSONObject(2).getJSONObject("main").getInt("temp_min");
                    low3.setText((int) (9.0 / 5 * (lowNum3 - 273) + 32) + "°F");
                    String timeAPI3 = forecast.getJSONArray("list").getJSONObject(2).getString("dt_txt");
                    String timeUTC3 = timeAPI3.substring(11, 13);
                    int timeNum3 = Integer.parseInt(timeUTC3);
                    int timeEST3 = timeNum3-5;
                    if(timeEST3>0 && timeEST3<12)
                        time3.setText(timeEST3 + ":00 AM");
                    else if(timeEST2<0)
                        time3.setText(timeEST3 + 12 + ":00 PM");
                    else if (timeEST2 > 12)
                        time3.setText(timeEST3 - 12 + ":00 PM");

                    //forecast 4
                    if (forecast.getJSONArray("list").getJSONObject(3).getJSONArray("weather").getJSONObject(0).getString("main").equals("Clear")) {
                        if (forecast.getJSONArray("list").getJSONObject(3).getJSONObject("sys").getString("pod").equals("d"))
                            image4.setImageResource(R.drawable.sunny);
                        else
                            image4.setImageResource(R.drawable.moon);
                        image4.setVisibility(View.VISIBLE);
                    }
                    if (forecast.getJSONArray("list").getJSONObject(3).getJSONArray("weather").getJSONObject(0).getString("main").equals("Clouds")) {
                        image4.setImageResource(R.drawable.clouds);
                        image4.setVisibility(View.VISIBLE);
                    }
                    if (forecast.getJSONArray("list").getJSONObject(3).getJSONArray("weather").getJSONObject(0).getString("main").equals("Snow")) {
                        image4.setImageResource(R.drawable.snow);
                        image4.setVisibility(View.VISIBLE);
                    }
                    if (forecast.getJSONArray("list").getJSONObject(3).getJSONArray("weather").getJSONObject(0).getString("main").equals("Fog")) {
                        image4.setImageResource(R.drawable.fog);
                        image4.setVisibility(View.VISIBLE);
                    }
                    if (forecast.getJSONArray("list").getJSONObject(3).getJSONArray("weather").getJSONObject(0).getString("main").equals("Rain")) {
                        image4.setImageResource(R.drawable.rain);
                        image4.setVisibility(View.VISIBLE);
                    }
                    if (forecast.getJSONArray("list").getJSONObject(3).getJSONArray("weather").getJSONObject(0).getString("main").equals("Thunderstorms")) {
                        image4.setImageResource(R.drawable.thunderstorm);
                        image4.setVisibility(View.VISIBLE);
                    }
                    if (forecast.getJSONArray("list").getJSONObject(3).getJSONArray("weather").getJSONObject(0).getString("main").equals("Mist")) {
                        image4.setImageResource(R.drawable.fog);
                        image4.setVisibility(View.VISIBLE);
                    }
                    int highNum4 = forecast.getJSONArray("list").getJSONObject(3).getJSONObject("main").getInt("temp_max");
                    high4.setText((int) (9.0 / 5 * (highNum4 - 273) + 32) + "°F");
                    int lowNum4 = forecast.getJSONArray("list").getJSONObject(3).getJSONObject("main").getInt("temp_min");
                    low4.setText((int) (9.0 / 5 * (lowNum4 - 273) + 32) + "°F");
                    String timeAPI4 = forecast.getJSONArray("list").getJSONObject(3).getString("dt_txt");
                    String timeUTC4 = timeAPI4.substring(11, 13);
                    int timeNum4 = Integer.parseInt(timeUTC4);
                    int timeEST4 = timeNum4-5;
                    if(timeEST4>0 && timeEST4<12)
                        time4.setText(timeEST4 + ":00 AM");
                    else if(timeEST4<0)
                        time4.setText(timeEST4 + 12 + ":00 PM");
                    else if (timeEST4 > 12)
                        time4.setText(timeEST4 - 12 + ":00 PM");

                    //forecast 5
                    if (forecast.getJSONArray("list").getJSONObject(4).getJSONArray("weather").getJSONObject(0).getString("main").equals("Clear")) {
                        if (forecast.getJSONArray("list").getJSONObject(4).getJSONObject("sys").getString("pod").equals("d"))
                            image5.setImageResource(R.drawable.sunny);
                        else
                            image5.setImageResource(R.drawable.moon);
                        image5.setVisibility(View.VISIBLE);
                    }
                    if (forecast.getJSONArray("list").getJSONObject(4).getJSONArray("weather").getJSONObject(0).getString("main").equals("Clouds")) {
                        image5.setImageResource(R.drawable.clouds);
                        image5.setVisibility(View.VISIBLE);
                    }
                    if (forecast.getJSONArray("list").getJSONObject(4).getJSONArray("weather").getJSONObject(0).getString("main").equals("Snow")) {
                        image5.setImageResource(R.drawable.snow);
                        image5.setVisibility(View.VISIBLE);
                    }
                    if (forecast.getJSONArray("list").getJSONObject(4).getJSONArray("weather").getJSONObject(0).getString("main").equals("Fog")) {
                        image5.setImageResource(R.drawable.fog);
                        image5.setVisibility(View.VISIBLE);
                    }
                    if (forecast.getJSONArray("list").getJSONObject(4).getJSONArray("weather").getJSONObject(0).getString("main").equals("Rain")) {
                        image5.setImageResource(R.drawable.rain);
                        image5.setVisibility(View.VISIBLE);
                    }
                    if (forecast.getJSONArray("list").getJSONObject(4).getJSONArray("weather").getJSONObject(0).getString("main").equals("Thunderstorms")) {
                        image5.setImageResource(R.drawable.thunderstorm);
                        image5.setVisibility(View.VISIBLE);
                    }
                    if (forecast.getJSONArray("list").getJSONObject(4).getJSONArray("weather").getJSONObject(0).getString("main").equals("Mist")) {
                        image5.setImageResource(R.drawable.fog);
                        image5.setVisibility(View.VISIBLE);
                    }
                    int highNum5 = forecast.getJSONArray("list").getJSONObject(4).getJSONObject("main").getInt("temp_max");
                    high5.setText((int) (9.0 / 5 * (highNum5 - 273) + 32) + "°F");
                    int lowNum5 = forecast.getJSONArray("list").getJSONObject(4).getJSONObject("main").getInt("temp_min");
                    low5.setText((int) (9.0 / 5 * (lowNum5 - 273) + 32) + "°F");
                    String timeAPI5 = forecast.getJSONArray("list").getJSONObject(4).getString("dt_txt");
                    String timeUTC5 = timeAPI5.substring(11, 13);
                    int timeNum5 = Integer.parseInt(timeUTC5);
                    int timeEST5 = timeNum5-5;
                    if(timeEST5>0 && timeEST5<12)
                        time5.setText(timeEST5 + ":00 AM");
                    else if(timeEST5<0)
                        time5.setText(timeEST5 + 12 + ":00 PM");
                    else if (timeEST5 > 12)
                        time5.setText(timeEST5 - 12 + ":00 PM");

                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (NullPointerException e){
                Context context = getApplicationContext();
                Toast toast = Toast.makeText(context,"Invalid Zip Code", 5);
                toast.show();
            }
        }
    }
}
