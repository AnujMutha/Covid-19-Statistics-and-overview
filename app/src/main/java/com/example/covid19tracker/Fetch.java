package com.example.covid19tracker;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Fetch
{

    String line="";
    String data="";
    String dataToParsed="";
    String COUNTRY="india";

    String whichCountry,active,recovered,dead,total,today,diedToday,critical;

    public void getData()
    {
        try {
            URL url = new URL("https://coronavirus-19-api.herokuapp.com/countries/india");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            /*Reading data from http API source*/
            while (line != null) {
                line = bufferedReader.readLine();
                data = data + line;
            }
            /*Reading Of Data Ended*/

            /*Parsing JSON Data To java String*/
            dataToParsed = "[" + data + "]";
            dataToParsed = dataToParsed.replace("null", "");

            JSONArray JA = new JSONArray(dataToParsed);
            JSONObject JO = (JSONObject) JA.get(0);
            /*Parsing of data ended*/

            whichCountry = JO.get("country").toString();
            total = JO.get("cases").toString();
            today = JO.get("todayCases").toString();
            dead = JO.get("deaths").toString();
            diedToday = JO.get("todayDeaths").toString();
            recovered = JO.get("recovered").toString();
            active = JO.get("active").toString();
            critical = JO.get("critical").toString();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        TextFragment.data.setText(this.whichCountry);
    }
}
