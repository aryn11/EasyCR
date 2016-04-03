package com.aryn.easycr;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

import android.content.Context;

/**
 * Created by user on 03.04.2016.
 */
public class EventJSONSerializer {
    private Context mContext;
    private String mFilename;

    public EventJSONSerializer(Context c, String f) {
        mContext = c;
        mFilename = f;
    }

    public ArrayList<Event> loadEvents() throws IOException, JSONException {
        ArrayList<Event> events = new ArrayList<Event>();
        BufferedReader reader = null;
        try {
// Открытие и чтение файла в StringBuilder
            InputStream in = mContext.openFileInput(mFilename);
            reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder jsonString = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
// Line breaks are omitted and irrelevant
                jsonString.append(line);
            }
// Разбор JSON с использованием JSONTokener
            JSONArray array = (JSONArray) new JSONTokener(jsonString.toString())
                    .nextValue();
            // Построение массива объектов Crime по данным JSONObject
            for (int i = 0; i < array.length(); i++) {
                events.add(new Event(array.getJSONObject(i)));
            }
        } catch (FileNotFoundException e) {
// Происходит при начале "с нуля"; не обращайте внимания
        } finally {
            if (reader != null)
                reader.close();
        }
        return events;
    }

    public void saveEvents(ArrayList<Event> events) throws JSONException, IOException {
        // build an array in JSON
        JSONArray array = new JSONArray();
        for (Event c : events)
            array.put(c.toJSON());

        // write the file to disk
        Writer writer = null;
        try {
            OutputStream out = mContext.openFileOutput(mFilename, Context.MODE_PRIVATE);
            writer = new OutputStreamWriter(out);
            writer.write(array.toString());
        } finally {
            if (writer != null)
                writer.close();
        }
    }
}
