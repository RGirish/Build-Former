# BuildFormer
This is a dynamic form builder library for Android. Given a LinearLayout to build on, dynamically creating views such as Switches, TextViews, EditTexts and lots more,
becomes as easy as calling the methods in this library. Each of the supported views (listed below) has one or more methods within the library that can be called to create
the view in the previously set LinearLayout.
<br><br>
The library is for Android developers who want to create any of these supported views dynamically. One kind of application where this library could prove to be immensely helpful would be in creating
survey forms.


## An example - Creating an EditText
```
LinearLayout layout = (LinearLayout) findViewById(R.id.myLinearLayout);

FormBuilder builder = new FormBuilder(this, layout);
builder.createEditText("Enter your name", FormBuilder.EDIT_TEXT_MODE_HINT, true);

// the first parameter is the description.
// the second parameter is a flag that tells the library to display the description as a hint.
// the third parameter if 'true', makes the EditText single-line.
```


## JSON Export/Import
The library also provides methods to export the meta data of a dynamically created form into a JSON representation.
<br><br>
This way, a user is able to create a form with the help of this library and is then able to save it in an internal representation. The following is how it is done:

```
FormBuilder builder = new FormBuilder(this, layout);
builder.createTextView(...);
builder.createCheckbox(...);
builder.createRadioGroup(...);
builder.createEditText(...);
...
builder.exportAsJson("filename.json");
```
The JSON file is saved in the root directory of the ExternalStorage.
<br><br>
Once this is done, if we need to dynamically create the same form again, all we need to do is this:
```
FormBuilder builder = new FormBuilder(this, layout);
File file = new File(Environment.getExternalStorageDirectory() + File.separator + "filename.json");

try {
    builder.createFromJson(file);
} catch (IOException e) {
    ...
}
```

## Supported views
1. TextView
2. EditText
3. RadioGroup
3. RadioGroupRatings - a special kind of customized RadioGroup
4. Checkbox
5. CheckboxGroup
6. Switch
7. DropdownList
8. DatePicker
9. TimePicker
10. SectionBreak

## Adding a dependency to this Library
The library is available in the jCenter() repository only:
```
compile 'com.girish.library.buildformer:buildformer:0.9.4'
```
