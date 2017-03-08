package com.girish.library.buildformer;

import android.app.Activity;
import android.graphics.Color;
import android.support.v7.widget.SwitchCompat;
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

import com.girish.library.buildformer.fragments.DatePickerFragment;
import com.girish.library.buildformer.fragments.TimePickerFragment;

import java.util.List;

public class FormBuilder {
    private Activity activity;
    private LinearLayout parentLayout;
    public static final int EDIT_TEXT_MODE_HINT = 1;
    public static final int EDIT_TEXT_MODE_SEPARATE = 2;

    public FormBuilder(Activity activity) {
        this.activity = activity;
    }

    public void setParentLayout(LinearLayout parentLayout) {
        this.parentLayout = parentLayout;
    }

    public void setParentLayout(int parentLayoutId) {
        this.parentLayout = (LinearLayout) activity.findViewById(parentLayoutId);
    }

    /**
     * Creates a TextView in the previously set Parent Layout using the given parameters
     *
     * @param text     the text to display
     * @param textSize the size of the text to be displayed
     */
    public void createTextView(String text, int textSize) {
        TextView textView = new TextView(activity);
        textView.setTextSize(textSize);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, getDimension(R.dimen.dp20), 0, getDimension(R.dimen.dp20));
        textView.setLayoutParams(params);
        textView.setText(text);
        parentLayout.addView(textView);
    }

    /**
     * Creates a TextView in the previously set Parent Layout displaying the given text in the default size of 16sp
     *
     * @param text the text to display
     */
    public void createTextView(String text) {
        TextView textView = new TextView(activity);
        textView.setTextSize(16);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, getDimension(R.dimen.dp20), 0, getDimension(R.dimen.dp20));
        textView.setLayoutParams(params);
        textView.setText(text);
        parentLayout.addView(textView);
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
        EditText editText = new EditText(activity);
        LinearLayout.LayoutParams params = null;
        if (mode == EDIT_TEXT_MODE_HINT) {
            editText.setHint(description);
            params = new LinearLayout.LayoutParams(getDimension(R.dimen.dp200), ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(0, getDimension(R.dimen.dp20), 0, getDimension(R.dimen.dp20));
        } else if (mode == EDIT_TEXT_MODE_SEPARATE) {
            TextView textView = new TextView(activity);
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
    }

    /**
     * Creates a RadioGroup in the previously set Parent Layout displaying the options given by the List parameter.
     *
     * @param description a description of what the Radio Group stands for. This is displayed above the RadioGroup itself.
     * @param options     a List of Strings that represent the options that are to be part of the RadioGroup.
     */
    public void createRadioGroup(String description, String... options) {
        TextView textView = new TextView(activity);
        textView.setText(description);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, getDimension(R.dimen.dp20), 0, getDimension(R.dimen.dp10));
        textView.setLayoutParams(params);

        RadioGroup radioGroup = new RadioGroup(activity);
        params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(getDimension(R.dimen.dp20), 0, getDimension(R.dimen.dp20), getDimension(R.dimen.dp20));
        radioGroup.setOrientation(LinearLayout.VERTICAL);
        radioGroup.setLayoutParams(params);

        RadioButton radioButton;
        for (String option : options) {
            radioButton = new RadioButton(activity);
            radioButton.setText(option);
            radioGroup.addView(radioButton);
        }

        parentLayout.addView(textView);
        parentLayout.addView(radioGroup);
    }

    /**
     * Creates a RadioGroup for Ratings in the previously set Parent Layout.
     *
     * @param description   a description of the radio group
     * @param minRating     the minimum ratings number to display
     * @param inStepsOf     the difference between successive ratings
     * @param numberOfSteps the total number of ratings to provide
     */
    public void createRatingsGroup(String description, int minRating, int inStepsOf, int numberOfSteps) {
        TextView textView = new TextView(activity);
        textView.setText(description);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, getDimension(R.dimen.dp20), 0, getDimension(R.dimen.dp10));
        textView.setLayoutParams(params);

        RadioGroup radioGroup = new RadioGroup(activity);
        params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(getDimension(R.dimen.dp20), 0, getDimension(R.dimen.dp20), getDimension(R.dimen.dp20));
        radioGroup.setOrientation(LinearLayout.HORIZONTAL);
        radioGroup.setLayoutParams(params);

        params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(getDimension(R.dimen.dp20), 0, getDimension(R.dimen.dp20), 0);
        for (int index = minRating; index <= inStepsOf * numberOfSteps; index += inStepsOf) {
            RadioButton radioButton = new RadioButton(activity);
            radioButton.setLayoutParams(params);
            radioButton.setText(String.valueOf(index));
            radioGroup.addView(radioButton);
        }
        parentLayout.addView(textView);
        parentLayout.addView(radioGroup);
    }

    /**
     * Creates a Checkbox in the previously set Parent Layout.
     *
     * @param description a description of the Checkbox to display before the Checkbox.
     */
    public void createCheckbox(String description) {
        CheckBox checkBox = new CheckBox(activity);
        checkBox.setText(description);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, getDimension(R.dimen.dp20), 0, getDimension(R.dimen.dp20));
        checkBox.setLayoutParams(params);
        parentLayout.addView(checkBox);
    }

    /**
     * Creates a Checkbox Group in the previously set Parent Layout.
     *
     * @param description a description of the Checkbox Group to display before it.
     * @param options     a List of String that contains the options to display as part of the Checkbox Group.
     */
    public void createCheckboxGroup(String description, List<String> options) {
        TextView textView = new TextView(activity);
        textView.setText(description);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, getDimension(R.dimen.dp20), 0, getDimension(R.dimen.dp10));
        textView.setLayoutParams(params);
        parentLayout.addView(textView);

        params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        for (int i = 0; i < options.size(); ++i) {
            CheckBox checkBox = new CheckBox(activity);
            checkBox.setText(options.get(i));
            if (i == options.size() - 1)
                params.setMargins(0, 0, 0, getDimension(R.dimen.dp20));
            checkBox.setLayoutParams(params);
            parentLayout.addView(checkBox);
        }
    }

    /**
     * Creates a Switch in the previously set Parent Layout.
     *
     * @param description  a description of the Switch to display before it.
     * @param firstChoice  the choice to display to the left of the switch.
     * @param secondChoice the choice to display to the right of the switch.
     */
    public void createSwitch(String description, String firstChoice, String secondChoice) {
        TextView textView = new TextView(activity);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, getDimension(R.dimen.dp20), 0, getDimension(R.dimen.dp10));
        textView.setLayoutParams(params);
        textView.setText(description);
        parentLayout.addView(textView);

        LinearLayout ll = new LinearLayout(activity);
        ll.setOrientation(LinearLayout.HORIZONTAL);
        params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 0, 0, getDimension(R.dimen.dp20));
        ll.setLayoutParams(params);

        textView = new TextView(activity);
        params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textView.setLayoutParams(params);
        textView.setText(firstChoice);
        ll.addView(textView);

        SwitchCompat switchCompat = new SwitchCompat(activity);
        params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(getDimension(R.dimen.dp10), 0, getDimension(R.dimen.dp10), 0);
        switchCompat.setLayoutParams(params);
        ll.addView(switchCompat);

        textView = new TextView(activity);
        params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textView.setLayoutParams(params);
        textView.setText(secondChoice);
        ll.addView(textView);
        parentLayout.addView(ll);
    }

    /**
     * Creates a Dropdown in the previously set Parent Layout.
     *
     * @param description a description for the dropdown list to display before it
     * @param options     a List of String that represents the options in the drop down list.
     */
    public void createDropDownList(String description, List<String> options) {
        TextView textView = new TextView(activity);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(getDimension(R.dimen.dp20), 0, getDimension(R.dimen.dp10), 0);
        textView.setLayoutParams(params);
        textView.setText(description);
        parentLayout.addView(textView);

        Spinner spinner = new Spinner(activity);
        params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 0, 0, getDimension(R.dimen.dp20));
        ArrayAdapter<String> adapter = new ArrayAdapter<>(activity, R.layout.spinner_item);
        for (String option : options) {
            adapter.add(option);
        }
        spinner.setAdapter(adapter);
        parentLayout.addView(spinner);
    }

    /**
     * Creates a DatePicker in the previously set Parent Layout.
     */
    public void createDatePicker() {
        TextView textView = new TextView(activity);
        textView.setText(R.string.enter_a_date);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, getDimension(R.dimen.dp20), 0, getDimension(R.dimen.dp10));
        textView.setLayoutParams(params);
        textView.setTag("date");
        parentLayout.addView(textView);

        Button button = new Button(activity);
        params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 0, 0, getDimension(R.dimen.dp20));
        button.setLayoutParams(params);
        button.setText(R.string.pick_a_date);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerFragment datePickerFragment = new DatePickerFragment();
                datePickerFragment.setParentLayout(parentLayout);
                datePickerFragment.show(activity.getFragmentManager(), "datePicker");
            }
        });
        parentLayout.addView(button);
    }

    /**
     * Creates a TimePicker in the previously set Parent Layout.
     */
    public void createTimePicker() {
        TextView textView = new TextView(activity);
        textView.setText(R.string.enter_a_time);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, getDimension(R.dimen.dp20), 0, getDimension(R.dimen.dp10));
        textView.setLayoutParams(params);
        textView.setTag("time");
        parentLayout.addView(textView);

        Button button = new Button(activity);
        params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 0, 0, getDimension(R.dimen.dp20));
        button.setLayoutParams(params);
        button.setText(R.string.pick_a_time);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerFragment timePickerFragment = new TimePickerFragment();
                timePickerFragment.setParentLayout(parentLayout);
                timePickerFragment.show(activity.getFragmentManager(), "timePicker");
            }
        });
        parentLayout.addView(button);
    }

    /**
     * Adds a section break to the screen.
     */
    public void addSectionBreak() {
        Button button = new Button(activity);
        button.setBackgroundColor(Color.GRAY);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getDimension(R.dimen.dp1));
        params.setMargins(0, getDimension(R.dimen.dp20), 0, getDimension(R.dimen.dp20));
        button.setLayoutParams(params);
        parentLayout.addView(button);
    }

    private int getDimension(int id) {
        return (int) activity.getResources().getDimension(id);
    }
}
