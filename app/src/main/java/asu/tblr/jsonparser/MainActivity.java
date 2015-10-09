package asu.tblr.jsonparser;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String json = "{" +
                "\"view1\" : {" +
                "\"required\" : true," +
                "\"type\" : \"textview\"," +
                "\"text\" : \"Hey this is a TextView, and it displays text.\"" +
                "}," +
                "\"view2\" : {" +
                "\"required\" : true," +
                "\"type\" : \"edittext-singleline\"," +
                "\"hint\" : false," +
                "\"description\" : \"Enter your name:\"" +
                "}," +
                "\"view3\" : {" +
                "\"required\" : true," +
                "\"type\" : \"edittext-paragraph\"," +
                "\"hint\" : true," +
                "\"description\" : \"Write a story:\"" +
                "}," +
                "\"view4\" : {" +
                "\"required\" : true," +
                "\"type\" : \"radio-group\"," +
                "\"description\" : \"Select one out of these:\"," +
                "\"options\" : [" +
                "\"Option 1\"," +
                "\"Option 2\"," +
                "\"Option 3\"" +
                "]" +
                "}," +
                "\"view5\" : {" +
                "\"required\" : true," +
                "\"type\" : \"radio-group-ratings\"," +
                "\"description\" : \"Select one out of these:\"," +
                "\"minrating\" : 1," +
                "\"instepsof\" : 1," +
                "\"numberofsteps\" : 5" +
                "}," +
                "\"view6\" : {" +
                "\"required\" : true," +
                "\"type\" : \"checkbox\"," +
                "\"text\" : \"It satisfies a property\"" +
                "}," +
                "\"view7\" : {" +
                "\"required\" : true," +
                "\"type\" : \"checkbox-group\"," +
                "\"description\" : \"Select one or more out of these:\"," +
                "\"options\" : [" +
                "\"Option 1\"," +
                "\"Option 2\"," +
                "\"Option 3\"" +
                "]" +
                "}," +
                "\"view8\" : {" +
                "\"required\" : true," +
                "\"type\" : \"switch\"," +
                "\"text\" : \"Which option do you wanna choose?\"," +
                "\"choice1\" : \"This One\"," +
                "\"choice2\" : \"That One\"" +
                "}," +
                "\"view9\" : {" +
                "\"required\" : true," +
                "\"type\" : \"dropdown-list\"," +
                "\"description\" : \"Select one from this list:\"," +
                "\"options\" : [" +
                "\"An Option\"," +
                "\"Another Option\"," +
                "\"One More Option\"" +
                "]," +
                "\"default\" : 3" +
                "}," +
                "\"view10\" : {" +
                "\"required\" : true," +
                "\"type\" : \"rubric\"," +
                "\"src\" : \"http://sipi.usc.edu/database/preview/misc/4.1.01.png\"" +
                "}," +
                "\"view11\" : {" +
                "\"required\" : true," +
                "\"type\" : \"date\"," +
                "\"defaultcurrentdate\" : true" +
                "}," +
                "\"view12\" : {" +
                "\"required\" : true," +
                "\"type\" : \"time\"," +
                "\"defaultcurrenttime\" : false" +
                "}," +
                "\"view13\" : {" +
                "\"type\" : \"section-break\"" +
                "}," +
                "\"view14\" : {" +
                "\"type\" : \"group\"," +
                "\"description\" : \"This is the description for the group.\"," +
                "\"members\" : {" +
                "\"view15\" : {" +
                "\"type\" : \"...\"" +
                "}," +
                "\"view16\" : {" +
                "\"type\" : \"...\"" +
                "}," +
                "\"view17\" : {" +
                "\"type\" : \"...\"" +
                "}," +
                "\"view18\" : {" +
                "\"type\" : \"...\"" +
                "}," +
                "\"view19\" : {" +
                "\"type\" : \"...\"" +
                "}," +
                "\"view20\" : {" +
                "\"type\" : \"...\"" +
                "}" +
                "}" +
                "}" +
                "}";

        try {
            JSONTokener jsonTokener = new JSONTokener(json);
            JSONObject root = (JSONObject) jsonTokener.nextValue();
            int i = 1;
            LinearLayout mainll = (LinearLayout) findViewById(R.id.mainll);

            //noinspection InfiniteLoopStatement
            while (true) {
                JSONObject object = root.getJSONObject("view" + i);
                String type = object.getString("type");

                switch (type) {

                    case "textview":

                        String text = object.getString("text");

                        TextView textView = new TextView(this);
                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        params.setMargins(0, (int) getResources().getDimension(R.dimen.dp20), 0, (int) getResources().getDimension(R.dimen.dp20));
                        textView.setLayoutParams(params);
                        textView.setText(text);
                        mainll.addView(textView);

                        Log.e("TextView", "TextView");
                        break;

                    case "edittext-singleline":

                        boolean hint = object.getBoolean("hint");
                        String description = object.getString("description");

                        EditText editText = new EditText(this);
                        if (hint) {
                            editText.setHint(description);
                            params = new LinearLayout.LayoutParams((int) getResources().getDimension(R.dimen.dp200), ViewGroup.LayoutParams.WRAP_CONTENT);
                            params.setMargins(0, (int) getResources().getDimension(R.dimen.dp20), 0, (int) getResources().getDimension(R.dimen.dp20));
                        } else {
                            textView = new TextView(this);
                            params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                            params.setMargins(0, (int) getResources().getDimension(R.dimen.dp20), 0, (int) getResources().getDimension(R.dimen.dp10));
                            textView.setLayoutParams(params);
                            textView.setText(description);
                            mainll.addView(textView);
                            params = new LinearLayout.LayoutParams((int) getResources().getDimension(R.dimen.dp200), ViewGroup.LayoutParams.WRAP_CONTENT);
                            params.setMargins(0, 0, 0, (int) getResources().getDimension(R.dimen.dp20));
                        }

                        editText.setLayoutParams(params);
                        mainll.addView(editText);

                        Log.e("edittext-singleline", "edittext-singleline");
                        break;

                    case "edittext-paragraph":

                        hint = object.getBoolean("hint");
                        description = object.getString("description");

                        editText = new EditText(this);
                        if (hint) {
                            editText.setHint(description);
                            params = new LinearLayout.LayoutParams((int) getResources().getDimension(R.dimen.dp200), ViewGroup.LayoutParams.WRAP_CONTENT);
                            params.setMargins(0, (int) getResources().getDimension(R.dimen.dp20), 0, (int) getResources().getDimension(R.dimen.dp20));
                        } else {
                            textView = new TextView(this);
                            params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                            params.setMargins(0, (int) getResources().getDimension(R.dimen.dp20), 0, (int) getResources().getDimension(R.dimen.dp10));
                            textView.setLayoutParams(params);
                            textView.setText(description);
                            mainll.addView(textView);
                            params = new LinearLayout.LayoutParams((int) getResources().getDimension(R.dimen.dp200), ViewGroup.LayoutParams.WRAP_CONTENT);
                            params.setMargins(0, 0, 0, (int) getResources().getDimension(R.dimen.dp20));
                        }

                        editText.setLines(5);
                        editText.setLayoutParams(params);
                        mainll.addView(editText);

                        Log.e("edittext-paragraph", "edittext-paragraph");

                        break;

                    case "radio-group":

                        description = object.getString("description");
                        JSONArray options = object.getJSONArray("options");

                        textView = new TextView(this);
                        textView.setText(description);
                        params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        params.setMargins(0, (int) getResources().getDimension(R.dimen.dp20), 0, (int) getResources().getDimension(R.dimen.dp10));
                        textView.setLayoutParams(params);

                        RadioGroup radioGroup = new RadioGroup(this);
                        params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        params.setMargins((int) getResources().getDimension(R.dimen.dp20), 0, (int) getResources().getDimension(R.dimen.dp20), (int) getResources().getDimension(R.dimen.dp20));
                        radioGroup.setOrientation(LinearLayout.VERTICAL);
                        radioGroup.setLayoutParams(params);

                        RadioButton radioButton;
                        for (int index = 0; index < options.length(); index++) {
                            radioButton = new RadioButton(this);
                            radioButton.setText(options.getString(index));
                            radioGroup.addView(radioButton);
                        }

                        mainll.addView(textView);
                        mainll.addView(radioGroup);


                        break;
                    case "radio-group-ratings":

                        description = object.getString("description");
                        int minRating = object.getInt("minrating");
                        int inStepsOf = object.getInt("instepsof");
                        int numberOfSteps = object.getInt("numberofsteps");

                        textView = new TextView(this);
                        textView.setText(description);
                        params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        params.setMargins(0, (int) getResources().getDimension(R.dimen.dp20), 0, (int) getResources().getDimension(R.dimen.dp10));
                        textView.setLayoutParams(params);

                        radioGroup = new RadioGroup(this);
                        params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        params.setMargins((int) getResources().getDimension(R.dimen.dp20), 0, (int) getResources().getDimension(R.dimen.dp20), (int) getResources().getDimension(R.dimen.dp20));
                        radioGroup.setOrientation(LinearLayout.HORIZONTAL);
                        radioGroup.setLayoutParams(params);

                        params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        params.setMargins((int) getResources().getDimension(R.dimen.dp20), 0, (int) getResources().getDimension(R.dimen.dp20), 0);
                        for (int index = minRating; index <= inStepsOf * numberOfSteps; index += inStepsOf) {
                            radioButton = new RadioButton(this);
                            radioButton.setLayoutParams(params);
                            radioButton.setText(String.valueOf(index));
                            radioGroup.addView(radioButton);
                        }

                        mainll.addView(textView);
                        mainll.addView(radioGroup);

                        break;
                    case "checkbox":

                        text = object.getString("text");
                        CheckBox checkBox = new CheckBox(this);
                        checkBox.setText(text);
                        params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        params.setMargins(0, (int) getResources().getDimension(R.dimen.dp20), 0, (int) getResources().getDimension(R.dimen.dp20));
                        checkBox.setLayoutParams(params);
                        mainll.addView(checkBox);

                        break;
                    case "checkbox-group":

                        description = object.getString("description");

                        textView = new TextView(this);
                        textView.setText(description);
                        params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        params.setMargins(0, (int) getResources().getDimension(R.dimen.dp20), 0, (int) getResources().getDimension(R.dimen.dp10));
                        textView.setLayoutParams(params);
                        mainll.addView(textView);

                        options = object.getJSONArray("options");
                        for (int index = 0; index < options.length(); index++) {
                            text = options.getString(index);
                            checkBox = new CheckBox(this);
                            checkBox.setText(text);
                            params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                            if (index == options.length() - 1)
                                params.setMargins(0, 0, 0, (int) getResources().getDimension(R.dimen.dp20));
                            checkBox.setLayoutParams(params);
                            mainll.addView(checkBox);
                        }

                        break;
                    case "switch":

                        text = object.getString("text");
                        String choice1 = object.getString("choice1");
                        String choice2 = object.getString("choice2");

                        textView = new TextView(this);
                        params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        params.setMargins(0, (int) getResources().getDimension(R.dimen.dp20), 0, (int) getResources().getDimension(R.dimen.dp10));
                        textView.setLayoutParams(params);
                        textView.setText(text);
                        mainll.addView(textView);

                        LinearLayout ll = new LinearLayout(this);
                        ll.setOrientation(LinearLayout.HORIZONTAL);
                        params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        params.setMargins(0, 0, 0, (int) getResources().getDimension(R.dimen.dp20));
                        ll.setLayoutParams(params);

                        textView = new TextView(this);
                        params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        textView.setLayoutParams(params);
                        textView.setText(choice1);
                        ll.addView(textView);

                        SwitchCompat switchCompat = new SwitchCompat(this);
                        params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        params.setMargins((int) getResources().getDimension(R.dimen.dp10), 0, (int) getResources().getDimension(R.dimen.dp10), 0);
                        switchCompat.setLayoutParams(params);
                        ll.addView(switchCompat);

                        textView = new TextView(this);
                        params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        textView.setLayoutParams(params);
                        textView.setText(choice2);
                        ll.addView(textView);
                        mainll.addView(ll);

                        Log.e("switch", "switch");

                        break;

                    case "dropdown-list":

                        description = object.getString("description");
                        textView = new TextView(this);
                        params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        params.setMargins((int) getResources().getDimension(R.dimen.dp20), 0, (int) getResources().getDimension(R.dimen.dp10), 0);
                        textView.setLayoutParams(params);
                        textView.setText(description);
                        mainll.addView(textView);

                        Spinner spinner = new Spinner(this);
                        params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        params.setMargins(0, 0, 0, (int) getResources().getDimension(R.dimen.dp20));
                        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spinner_item);
                        options = object.getJSONArray("options");
                        for (int index = 0; index < options.length(); index++) {
                            adapter.add(options.getString(index));
                        }
                        spinner.setAdapter(adapter);
                        mainll.addView(spinner);

                        break;

                    case "rubric":

                        ImageView imageView = new ImageView(this);
                        params = new LinearLayout.LayoutParams((int) getResources().getDimension(R.dimen.dp30), (int) getResources().getDimension(R.dimen.dp30));
                        params.setMargins(0, (int) getResources().getDimension(R.dimen.dp20), 0, (int) getResources().getDimension(R.dimen.dp20));
                        imageView.setImageResource(R.drawable.icon_info);
                        imageView.setLayoutParams(params);
                        imageView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Dialog dialog = new Dialog(MainActivity.this);
                                ImageView rubricImage = new ImageView(MainActivity.this);
                                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                                dialog.addContentView(rubricImage, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                                dialog.show();
                                new DownloadImageTask(rubricImage).execute("http://sipi.usc.edu/database/preview/misc/4.1.07.png");
                            }
                        });
                        mainll.addView(imageView);

                        break;

                    case "date":

                        textView = new TextView(this);
                        textView.setText(R.string.enter_a_date);
                        params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        params.setMargins(0, (int) getResources().getDimension(R.dimen.dp20), 0, (int) getResources().getDimension(R.dimen.dp10));
                        textView.setLayoutParams(params);
                        textView.setTag("date");
                        mainll.addView(textView);

                        Button button = new Button(this);
                        params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        params.setMargins(0, 0, 0, (int) getResources().getDimension(R.dimen.dp20));
                        button.setLayoutParams(params);
                        button.setText(R.string.pick_a_date);
                        button.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                DialogFragment newFragment = new DatePickerFragment();
                                newFragment.show(getSupportFragmentManager(), "datePicker");
                            }
                        });
                        mainll.addView(button);

                        break;

                    case "time":

                        textView = new TextView(this);
                        textView.setText(R.string.enter_a_time);
                        params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        params.setMargins(0, (int) getResources().getDimension(R.dimen.dp20), 0, (int) getResources().getDimension(R.dimen.dp10));
                        textView.setLayoutParams(params);
                        textView.setTag("time");
                        mainll.addView(textView);

                        button = new Button(this);
                        params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        params.setMargins(0, 0, 0, (int) getResources().getDimension(R.dimen.dp20));
                        button.setLayoutParams(params);
                        button.setText(R.string.pick_a_time);
                        button.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                DialogFragment newFragment = new TimePickerFragment();
                                newFragment.show(getSupportFragmentManager(), "timePicker");
                            }
                        });
                        mainll.addView(button);

                        break;

                    case "section-break":

                        button = new Button(this);
                        button.setBackgroundColor(Color.GRAY);
                        params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) getResources().getDimension(R.dimen.dp1));
                        params.setMargins(0, (int) getResources().getDimension(R.dimen.dp20), 0, (int) getResources().getDimension(R.dimen.dp20));
                        button.setLayoutParams(params);
                        mainll.addView(button);

                        Log.e("section-break", "section-break");

                        break;

                    case "group":

                        break;

                }
                i++;
            }
        } catch (Exception e) {
            Log.e("Exception", e.toString());
        }
    }

    public static class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

        @Override
        @NonNull
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            Calendar newDate = Calendar.getInstance();
            newDate.set(year, month, day);
            SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
            TextView textView = (TextView) (getActivity().findViewById(R.id.mainll)).findViewWithTag("date");
            textView.setText(getString(R.string.chosen_date, dateFormatter.format(newDate.getTime())));
        }
    }

    public static class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

        @Override
        @NonNull
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);
            return new TimePickerDialog(getActivity(), this, hour, minute, true);
        }

        public void onTimeSet(TimePicker view, int hour, int minute) {
            TextView textView = (TextView) (getActivity().findViewById(R.id.mainll)).findViewWithTag("time");
            textView.setText(getString(R.string.chosen_time, hour + ":" + minute));
        }
    }

    class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;
        ProgressDialog dialog;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected void onPreExecute() {
            dialog = ProgressDialog.show(MainActivity.this, null, "Loading...", true);
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            dialog.dismiss();
            bmImage.setImageBitmap(result);
        }
    }

}