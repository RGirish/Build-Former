package com.girish.example.buildformer;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.LinearLayout;

import com.girish.library.buildformer.activity.FormBuilder;

import java.io.IOException;

public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout layout = (LinearLayout) findViewById(R.id.mainLayout);

        FormBuilder builder = new FormBuilder(this, layout);
        builder.createTextView("Girish Here", 20);
        builder.createEditText("Enter your name here", FormBuilder.EDIT_TEXT_MODE_HINT, true);
        builder.createTimePicker();
        builder.createDatePicker();
        try {
            builder.exportAsJson("myjson.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
