
package com.github.zzwwws.rxzhihudaily.model.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "stories",
    "description",
    "background",
    "color",
    "name",
    "image",
    "editors",
    "image_source"
})
public class TopicDetail {

    @JsonProperty("stories")
    private List<Story> stories = new ArrayList<Story>();
    @JsonProperty("description")
    private String description;
    @JsonProperty("background")
    private String background;
    @JsonProperty("color")
    private Integer color;
    @JsonProperty("name")
    private String name;
    @JsonProperty("image")
    private String image;
    @JsonProperty("editors")
    private List<Editor> editors = new ArrayList<Editor>();
    @JsonProperty("image_source")
    private String imageSource;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The stories
     */
    @JsonProperty("stories")
    public List<Story> getStories() {
        return stories;
    }

    /**
     * 
     * @param stories
     *     The stories
     */
    @JsonProperty("stories")
    public void setStories(List<Story> stories) {
        this.stories = stories;
    }

    /**
     * 
     * @return
     *     The description
     */
    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    /**
     * 
     * @param description
     *     The description
     */
    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 
     * @return
     *     The background
     */
    @JsonProperty("background")
    public String getBackground() {
        return background;
    }

    /**
     * 
     * @param background
     *     The background
     */
    @JsonProperty("background")
    public void setBackground(String background) {
        this.background = background;
    }

    /**
     * 
     * @return
     *     The color
     */
    @JsonProperty("color")
    public Integer getColor() {
        return color;
    }

    /**
     * 
     * @param color
     *     The color
     */
    @JsonProperty("color")
    public void setColor(Integer color) {
        this.color = color;
    }

    /**
     * 
     * @return
     *     The name
     */
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     *     The name
     */
    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return
     *     The image
     */
    @JsonProperty("image")
    public String getImage() {
        return image;
    }

    /**
     * 
     * @param image
     *     The image
     */
    @JsonProperty("image")
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * 
     * @return
     *     The editors
     */
    @JsonProperty("editors")
    public List<Editor> getEditors() {
        return editors;
    }

    /**
     * 
     * @param editors
     *     The editors
     */
    @JsonProperty("editors")
    public void setEditors(List<Editor> editors) {
        this.editors = editors;
    }

    /**
     * 
     * @return
     *     The imageSource
     */
    @JsonProperty("image_source")
    public String getImageSource() {
        return imageSource;
    }

    /**
     * 
     * @param imageSource
     *     The image_source
     */
    @JsonProperty("image_source")
    public void setImageSource(String imageSource) {
        this.imageSource = imageSource;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
