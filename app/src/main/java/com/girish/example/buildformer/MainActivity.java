package com.girish.example.buildformer;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.girish.library.buildformer.FormBuilder;

public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FormBuilder builder = new FormBuilder(this);
        builder.setParentLayout(R.id.mainLayout);
        builder.createTextView("Girish Here", 20);
        builder.createEditText("Enter your name here", FormBuilder.EDIT_TEXT_MODE_HINT, true);
        builder.createRadioGroup("Select one among these:", "Vegan", "Fish", "Shrimp");
        builder.createTimePicker();
        builder.createDatePicker();
    }
}
