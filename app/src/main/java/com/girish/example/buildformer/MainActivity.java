package com.girish.example.buildformer;

import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.LinearLayout;

import com.girish.library.buildformer.FormBuilder;

import java.io.File;
import java.io.IOException;

public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout layout = (LinearLayout) findViewById(R.id.mainLayout);

        File file = new File(Environment.getExternalStorageDirectory() + File.separator + "myjson.json");
        FormBuilder builder = new FormBuilder(this, layout);
        try {
            builder.createFromJson(file);
        } catch (IOException e) {
            Log.e(getClass().getSimpleName(), e.toString());
        }
    }
}
