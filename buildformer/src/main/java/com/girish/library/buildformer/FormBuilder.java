/*
 * @author Girish Raman
 * http://github.com/rgirish
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.girish.library.buildformer;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Color;
import android.os.Environment;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.girish.library.buildformer.fragment.DatePickerFragment;
import com.girish.library.buildformer.fragment.TimePickerFragment;
import com.girish.library.buildformer.model.BFCheckbox;
import com.girish.library.buildformer.model.BFCheckboxGroup;
import com.girish.library.buildformer.model.BFDatePicker;
import com.girish.library.buildformer.model.BFDropDownList;
import com.girish.library.buildformer.model.BFEditText;
import com.girish.library.buildformer.model.BFRadioGroup;
import com.girish.library.buildformer.model.BFRadioGroupRatings;
import com.girish.library.buildformer.model.BFSectionBreak;
import com.girish.library.buildformer.model.BFSwitch;
import com.girish.library.buildformer.model.BFTextView;
import com.girish.library.buildformer.model.BFTimePicker;
import com.girish.library.buildformer.model.BFView;
import com.girish.library.buildformer.model.JSONFeed;
import com.girish.library.buildformer.utils.Constants;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * FormBuilder is the main point of entry for this library.
 * <p>
 * It takes in the Context of the Application using the library and a LinearLayout object in its constructors.
 * It contains methods in it to dynamically construct views inside the LinearLayout provided.
 *
 * @author Girish Raman
 */
public class FormBuilder extends ContextWrapper {
    private LinearLayout parentLayout;
    public static final int EDIT_TEXT_MODE_HINT = 1;
    public static final int EDIT_TEXT_MODE_SEPARATE = 2;
    private List<BFView> views;

    public FormBuilder(Context context, LinearLayout parentLayout) {
        super(context);
        this.parentLayout = parentLayout;
        this.views = new ArrayList<>();
    }

    /**
     * Creates a TextView in the previously set Parent Layout using the given parameters
     *
     * @param text     the text to display
     * @param textSize the size of the text to be displayed
     */
    public void createTextView(String text, int textSize) {
        TextView textView = new TextView(this);
        textView.setTextSize(textSize);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, getDimension(R.dimen.dp20), 0, getDimension(R.dimen.dp20));
        textView.setLayoutParams(params);
        textView.setText(text);
        parentLayout.addView(textView);

        BFTextView bfTextView = new BFTextView();
        bfTextView.setType(Constants.TYPE_TEXT_VIEW);
        bfTextView.setText(text);
        bfTextView.setTextSize(textSize);
        views.add(bfTextView);
    }

    /**
     * Creates a TextView in the previously set Parent Layout displaying the given text in the default size of 16sp
     *
     * @param text the text to display
     */
    public void createTextView(String text) {
        TextView textView = new TextView(this);
        textView.setTextSize(16);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, getDimension(R.dimen.dp20), 0, getDimension(R.dimen.dp20));
        textView.setLayoutParams(params);
        textView.setText(text);
        parentLayout.addView(textView);

        BFTextView bfTextView = new BFTextView();
        bfTextView.setType(Constants.TYPE_TEXT_VIEW);
        bfTextView.setText(text);
        bfTextView.setTextSize(16);
        views.add(bfTextView);
    }

    /**
     * Creates an EditText in the previously set Parent Layout displaying the description as either a hint
     * or as a separate TextView above it.
     *
     * @param description a description that informs the user of the purpose of this EditText.
     * @param mode        EDIT_TEXT_MODE_HINT for display as a hint or EDIT_TEXT_MODE_SEPARATE for display as a separate TextView.
     * @param singleLine  true, if the EditText needs to be restricted to a single line. false, if the EditText needs to contain
     *                    paragraphs
     */
    public void createEditText(String description, int mode, boolean singleLine) {
        EditText editText = new EditText(this);
        LinearLayout.LayoutParams params = null;
        if (mode == EDIT_TEXT_MODE_HINT) {
            editText.setHint(description);
            params = new LinearLayout.LayoutParams(getDimension(R.dimen.dp200), ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(0, getDimension(R.dimen.dp20), 0, getDimension(R.dimen.dp20));
        } else if (mode == EDIT_TEXT_MODE_SEPARATE) {
            TextView textView = new TextView(this);
            params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(0, getDimension(R.dimen.dp20), 0, getDimension(R.dimen.dp10));
            textView.setLayoutParams(params);
            textView.setText(description);
            parentLayout.addView(textView);

            params = new LinearLayout.LayoutParams(getDimension(R.dimen.dp200), ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(0, 0, 0, getDimension(R.dimen.dp20));
        }
        if (!singleLine) editText.setLines(5);
        editText.setLayoutParams(params);
        parentLayout.addView(editText);

        BFEditText bfEditText = new BFEditText();
        bfEditText.setType(Constants.TYPE_EDIT_TEXT);
        bfEditText.setDescription(description);
        bfEditText.setMode(mode);
        bfEditText.setSingleLine(singleLine);
        views.add(bfEditText);
    }

    /**
     * Creates a RadioGroup in the previously set Parent Layout displaying the options given by the List parameter.
     *
     * @param description a description of what the Radio Group stands for. This is displayed above the RadioGroup itself.
     * @param options     a List of Strings that represent the options that are to be part of the RadioGroup.
     */
    public void createRadioGroup(String description, List<String> options) {
        TextView textView = new TextView(this);
        textView.setText(description);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, getDimension(R.dimen.dp20), 0, getDimension(R.dimen.dp10));
        textView.setLayoutParams(params);

        RadioGroup radioGroup = new RadioGroup(this);
        params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(getDimension(R.dimen.dp20), 0, getDimension(R.dimen.dp20), getDimension(R.dimen.dp20));
        radioGroup.setOrientation(LinearLayout.VERTICAL);
        radioGroup.setLayoutParams(params);

        RadioButton radioButton;
        for (String option : options) {
            radioButton = new RadioButton(this);
            radioButton.setText(option);
            radioGroup.addView(radioButton);
        }

        parentLayout.addView(textView);
        parentLayout.addView(radioGroup);

        BFRadioGroup bfRadioGroup = new BFRadioGroup();
        bfRadioGroup.setType(Constants.TYPE_RADIO_GROUP);
        bfRadioGroup.setDescription(description);
        bfRadioGroup.setOptions(options);
        views.add(bfRadioGroup);
    }

    /**
     * Creates a RadioGroup for Ratings in the previously set Parent Layout.
     *
     * @param description     a description of the radio group
     * @param minRating       the minimum ratings number to display
     * @param inStepsOf       the difference between successive ratings
     * @param numberOfRatings the total number of ratings to provide
     */
    public void createRatingsGroup(String description, int minRating, int inStepsOf, int numberOfRatings) {
        TextView textView = new TextView(this);
        textView.setText(description);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, getDimension(R.dimen.dp20), 0, getDimension(R.dimen.dp10));
        textView.setLayoutParams(params);

        RadioGroup radioGroup = new RadioGroup(this);
        params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(getDimension(R.dimen.dp20), 0, getDimension(R.dimen.dp20), getDimension(R.dimen.dp20));
        radioGroup.setOrientation(LinearLayout.HORIZONTAL);
        radioGroup.setLayoutParams(params);

        params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(getDimension(R.dimen.dp20), 0, getDimension(R.dimen.dp20), 0);
        for (int index = minRating; index <= inStepsOf * numberOfRatings; index += inStepsOf) {
            RadioButton radioButton = new RadioButton(this);
            radioButton.setLayoutParams(params);
            radioButton.setText(String.valueOf(index));
            radioGroup.addView(radioButton);
        }
        parentLayout.addView(textView);
        parentLayout.addView(radioGroup);

        BFRadioGroupRatings bfRadioGroupRatings = new BFRadioGroupRatings();
        bfRadioGroupRatings.setType(Constants.TYPE_RADIO_GROUP_RATINGS);
        bfRadioGroupRatings.setDescription(description);
        bfRadioGroupRatings.setMinRating(minRating);
        bfRadioGroupRatings.setInStepsOf(inStepsOf);
        bfRadioGroupRatings.setNumberOfRatings(numberOfRatings);
        views.add(bfRadioGroupRatings);
    }

    /**
     * Creates a Checkbox in the previously set Parent Layout.
     *
     * @param description a description of the Checkbox to display before the Checkbox.
     */
    public void createCheckbox(String description) {
        CheckBox checkBox = new CheckBox(this);
        checkBox.setText(description);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, getDimension(R.dimen.dp20), 0, getDimension(R.dimen.dp20));
        checkBox.setLayoutParams(params);
        parentLayout.addView(checkBox);

        BFCheckbox bfCheckbox = new BFCheckbox();
        bfCheckbox.setType(Constants.TYPE_CHECKBOX);
        bfCheckbox.setDescription(description);
        views.add(bfCheckbox);
    }

    /**
     * Creates a Checkbox Group in the previously set Parent Layout.
     *
     * @param description a description of the Checkbox Group to display before it.
     * @param options     a List of String that contains the options to display as part of the Checkbox Group.
     */
    public void createCheckboxGroup(String description, List<String> options) {
        TextView textView = new TextView(this);
        textView.setText(description);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, getDimension(R.dimen.dp20), 0, getDimension(R.dimen.dp10));
        textView.setLayoutParams(params);
        parentLayout.addView(textView);

        params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        for (int i = 0; i < options.size(); ++i) {
            CheckBox checkBox = new CheckBox(this);
            checkBox.setText(options.get(i));
            if (i == options.size() - 1)
                params.setMargins(0, 0, 0, getDimension(R.dimen.dp20));
            checkBox.setLayoutParams(params);
            parentLayout.addView(checkBox);
        }

        BFCheckboxGroup bfCheckboxGroup = new BFCheckboxGroup();
        bfCheckboxGroup.setType(Constants.TYPE_CHECKBOX_GROUP);
        bfCheckboxGroup.setDescription(description);
        bfCheckboxGroup.setOptions(options);
        views.add(bfCheckboxGroup);
    }

    /**
     * Creates a Switch in the previously set Parent Layout.
     *
     * @param description  a description of the Switch to display before it.
     * @param firstChoice  the choice to display to the left of the switch.
     * @param secondChoice the choice to display to the right of the switch.
     */
    public void createSwitch(String description, String firstChoice, String secondChoice) {
        TextView textView = new TextView(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, getDimension(R.dimen.dp20), 0, getDimension(R.dimen.dp10));
        textView.setLayoutParams(params);
        textView.setText(description);
        parentLayout.addView(textView);

        LinearLayout ll = new LinearLayout(this);
        ll.setOrientation(LinearLayout.HORIZONTAL);
        params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 0, 0, getDimension(R.dimen.dp20));
        ll.setLayoutParams(params);

        textView = new TextView(this);
        params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textView.setLayoutParams(params);
        textView.setText(firstChoice);
        ll.addView(textView);

        SwitchCompat switchCompat = new SwitchCompat(this);
        params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(getDimension(R.dimen.dp10), 0, getDimension(R.dimen.dp10), 0);
        switchCompat.setLayoutParams(params);
        ll.addView(switchCompat);

        textView = new TextView(this);
        params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textView.setLayoutParams(params);
        textView.setText(secondChoice);
        ll.addView(textView);
        parentLayout.addView(ll);

        BFSwitch bfSwitch = new BFSwitch();
        bfSwitch.setType(Constants.TYPE_SWITCH);
        bfSwitch.setDescription(description);
        bfSwitch.setFirstChoice(firstChoice);
        bfSwitch.setSecondChoice(secondChoice);
        views.add(bfSwitch);
    }

    /**
     * Creates a Dropdown in the previously set Parent Layout.
     *
     * @param description a description for the dropdown list to display before it
     * @param options     a List of String that represents the options in the drop down list.
     */
    public void createDropDownList(String description, List<String> options) {
        TextView textView = new TextView(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(getDimension(R.dimen.dp20), 0, getDimension(R.dimen.dp10), 0);
        textView.setLayoutParams(params);
        textView.setText(description);
        parentLayout.addView(textView);

        Spinner spinner = new Spinner(this);
        params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 0, 0, getDimension(R.dimen.dp20));
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.buildformer_spinner_item);
        for (String option : options) {
            adapter.add(option);
        }
        spinner.setAdapter(adapter);
        parentLayout.addView(spinner);

        BFDropDownList bfDropDownList = new BFDropDownList();
        bfDropDownList.setType(Constants.TYPE_DROP_DOWN_LIST);
        bfDropDownList.setDescription(description);
        bfDropDownList.setOptions(options);
        views.add(bfDropDownList);
    }

    /**
     * Creates a DatePicker in the previously set Parent Layout.
     */
    public void createDatePicker() {
        TextView textView = new TextView(this);
        textView.setText(R.string.bf_enter_a_date);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        Log.e("DATE PICKER", String.valueOf(getDimension(R.dimen.dp20)));
        params.setMargins(0, getDimension(R.dimen.dp20), 0, getDimension(R.dimen.dp10));
        textView.setLayoutParams(params);
        textView.setTag("date");
        parentLayout.addView(textView);

        Button button = new Button(this);
        params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 0, 0, getDimension(R.dimen.dp20));
        button.setLayoutParams(params);
        button.setText(R.string.bf_pick_a_date);
        final Activity activity = (Activity) getBaseContext();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerFragment datePickerFragment = new DatePickerFragment();
                datePickerFragment.setParentLayout(parentLayout);
                datePickerFragment.show(activity.getFragmentManager(), "datePicker");
            }
        });
        parentLayout.addView(button);

        BFDatePicker bfDatePicker = new BFDatePicker();
        bfDatePicker.setType(Constants.TYPE_DATE_PICKER);
        views.add(bfDatePicker);
    }

    /**
     * Creates a TimePicker in the previously set Parent Layout.
     */
    public void createTimePicker() {
        TextView textView = new TextView(this);
        textView.setText(R.string.bf_enter_a_time);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, getDimension(R.dimen.dp20), 0, getDimension(R.dimen.dp10));
        textView.setLayoutParams(params);
        textView.setTag("time");
        parentLayout.addView(textView);

        Button button = new Button(this);
        params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 0, 0, getDimension(R.dimen.dp20));
        button.setLayoutParams(params);
        button.setText(R.string.bf_pick_a_time);
        final Activity activity = (Activity) getBaseContext();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerFragment timePickerFragment = new TimePickerFragment();
                timePickerFragment.setParentLayout(parentLayout);
                timePickerFragment.show(activity.getFragmentManager(), "timePicker");
            }
        });
        parentLayout.addView(button);

        BFTimePicker bfTimePicker = new BFTimePicker();
        bfTimePicker.setType(Constants.TYPE_TIME_PICKER);
        views.add(bfTimePicker);
    }

    /**
     * Adds a section break to the screen.
     */
    public void createSectionBreak() {
        Button button = new Button(this);
        button.setBackgroundColor(Color.GRAY);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getDimension(R.dimen.dp1));
        params.setMargins(0, getDimension(R.dimen.dp20), 0, getDimension(R.dimen.dp20));
        button.setLayoutParams(params);
        parentLayout.addView(button);

        BFSectionBreak bfSectionBreak = new BFSectionBreak();
        bfSectionBreak.setType(Constants.TYPE_SECTION_BREAK);
        views.add(bfSectionBreak);
    }

    /**
     * Exports the information about the views added so far into the a file in JSON format.
     *
     * @param filename the name of the file to be created.
     * @throws IOException possibly thrown when creating the file in the ExternalStorageDirectory.
     */
    public void exportAsJson(String filename) throws IOException {
        File file = new File(Environment.getExternalStorageDirectory().getPath() + File.separator + filename);
        ObjectMapper mapper = new ObjectMapper();
        JSONFeed jsonFeed = new JSONFeed(views);
        Log.i(getClass().getSimpleName(), mapper.writeValueAsString(jsonFeed));
        mapper.writeValue(file, jsonFeed);
    }

    /**
     * Creates an entire form from the information contained in the JSON file.
     *
     * @param json the JSON file.
     * @throws IOException possibly thrown when accessing the JSON file.
     */
    public void createFromJson(File json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JSONFeed feed = mapper.readValue(json, JSONFeed.class);
        List<BFView> views = feed.getViews();
        for (BFView view : views) {
            String type = view.getType();
            if (type.equals(Constants.TYPE_TEXT_VIEW)) {
                BFTextView bfTextView = (BFTextView) view;
                createTextView(bfTextView.getText(), bfTextView.getTextSize());
            } else if (type.equals(Constants.TYPE_EDIT_TEXT)) {
                BFEditText bfEditText = (BFEditText) view;
                createEditText(bfEditText.getDescription(), bfEditText.getMode(), bfEditText.isSingleLine());
            } else if (type.equals(Constants.TYPE_CHECKBOX)) {
                BFCheckbox bfCheckbox = (BFCheckbox) view;
                createCheckbox(bfCheckbox.getDescription());
            } else if (type.equals(Constants.TYPE_CHECKBOX_GROUP)) {
                BFCheckboxGroup bfCheckboxGroup = (BFCheckboxGroup) view;
                createCheckboxGroup(bfCheckboxGroup.getDescription(), bfCheckboxGroup.getOptions());
            } else if (type.equals(Constants.TYPE_RADIO_GROUP)) {
                BFRadioGroup bfRadioGroup = (BFRadioGroup) view;
                createRadioGroup(bfRadioGroup.getDescription(), bfRadioGroup.getOptions());
            } else if (type.equals(Constants.TYPE_RADIO_GROUP_RATINGS)) {
                BFRadioGroupRatings bfRadioGroupRatings = (BFRadioGroupRatings) view;
                createRatingsGroup(bfRadioGroupRatings.getDescription(), bfRadioGroupRatings.getMinRating(),
                        bfRadioGroupRatings.getInStepsOf(), bfRadioGroupRatings.getNumberOfRatings());
            } else if (type.equals(Constants.TYPE_DROP_DOWN_LIST)) {
                BFDropDownList bfDropDownList = (BFDropDownList) view;
                createDropDownList(bfDropDownList.getDescription(), bfDropDownList.getOptions());
            } else if (type.equals(Constants.TYPE_SWITCH)) {
                BFSwitch bfSwitch = (BFSwitch) view;
                createSwitch(bfSwitch.getDescription(), bfSwitch.getFirstChoice(), bfSwitch.getSecondChoice());
            } else if (type.equals(Constants.TYPE_DATE_PICKER)) {
                createDatePicker();
            } else if (type.equals(Constants.TYPE_TIME_PICKER)) {
                createTimePicker();
            } else if (type.equals(Constants.TYPE_SECTION_BREAK)) {
                createSectionBreak();
            }
        }
    }

    /**
     * This method extracts the dimension associated with the ID passed on to it from the resources directory.
     *
     * @param id the ID of the dimension
     * @return the value of the dimension
     */
    private int getDimension(int id) {
        return (int) getResources().getDimension(id);
    }
}
