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
