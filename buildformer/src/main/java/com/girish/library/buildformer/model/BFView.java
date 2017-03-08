package com.girish.library.buildformer.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.girish.library.buildformer.utils.Constants;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = Constants.JSON_KEY_TYPE, visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(name = Constants.TYPE_TEXT_VIEW, value = BFTextView.class),
        @JsonSubTypes.Type(name = Constants.TYPE_EDIT_TEXT, value = BFEditText.class),
        @JsonSubTypes.Type(name = Constants.TYPE_CHECKBOX, value = BFCheckbox.class),
        @JsonSubTypes.Type(name = Constants.TYPE_CHECKBOX_GROUP, value = BFCheckboxGroup.class),
        @JsonSubTypes.Type(name = Constants.TYPE_RADIO_GROUP, value = BFRadioGroup.class),
        @JsonSubTypes.Type(name = Constants.TYPE_RADIO_GROUP_RATINGS, value = BFRadioGroupRatings.class),
        @JsonSubTypes.Type(name = Constants.TYPE_DROP_DOWN_LIST, value = BFDropDownList.class),
        @JsonSubTypes.Type(name = Constants.TYPE_DATE_PICKER, value = BFDatePicker.class),
        @JsonSubTypes.Type(name = Constants.TYPE_TIME_PICKER, value = BFTimePicker.class),
        @JsonSubTypes.Type(name = Constants.TYPE_SWITCH, value = BFSwitch.class),
        @JsonSubTypes.Type(name = Constants.TYPE_SECTION_BREAK, value = BFSectionBreak.class),
})
public class BFView {
    @JsonProperty(Constants.JSON_KEY_TYPE)
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
