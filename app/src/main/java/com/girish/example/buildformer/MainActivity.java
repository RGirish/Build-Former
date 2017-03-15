package com.girish.example.buildformer;

import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.girish.library.buildformer.FormBuilder;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity {

    private LinearLayout layout;
    private FormBuilder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layout = (LinearLayout) findViewById(R.id.mainLayout);
        builder = new FormBuilder(this, layout);
        createAllViews();
    }

    private void createAllViews() {
        builder.createTextView("Sample Text View", 20);
        builder.createTextView("Sample Text View");
        builder.createEditText("Edit Text in HINT mode - single line", FormBuilder.EDIT_TEXT_MODE_HINT, true);
        builder.createEditText("Edit Text in HINT mode - multiline", FormBuilder.EDIT_TEXT_MODE_HINT, false);
        builder.createEditText("Edit Text in non HINT mode - single line", FormBuilder.EDIT_TEXT_MODE_SEPARATE, true);
        builder.createEditText("Edit Text in non HINT mode - multiline", FormBuilder.EDIT_TEXT_MODE_SEPARATE, false);
        List<String> options = new ArrayList<>();
        options.add("One");
        options.add("Two");
        options.add("Three");
        builder.createRadioGroup("Choose one among these:", options);
        builder.createRatingsGroup("Rate this library:", 1, 1, 5);
        builder.createCheckbox("Do you like this library?");
        builder.createCheckboxGroup("Choose any number of these:", options);
        builder.createSwitch("What do you prefer?", "Option 1", "Option 2");
        builder.createDropDownList("Choose one among these:", options);
        builder.createDatePicker();
        builder.createTimePicker();
        builder.createSectionBreak();
    }


    public void saveForm(View view) {
        try {
            builder.exportAsJson("buildformer.json");
        } catch (IOException e) {
            Log.e(getClass().getSimpleName(), e.toString());
        }
        findViewById(R.id.recreateFormButton).setVisibility(View.VISIBLE);
        findViewById(R.id.saveFormButton).setVisibility(View.GONE);
        layout.removeAllViews();
    }

    public void recreateForm(View view) {
        File file = new File(Environment.getExternalStorageDirectory() + "/buildformer.json");
        try {
            builder.createFromJson(file);
        } catch (IOException e) {
            Log.e(getClass().getSimpleName(), e.toString());
        }
        findViewById(R.id.recreateFormButton).setVisibility(View.INVISIBLE);
    }
}
