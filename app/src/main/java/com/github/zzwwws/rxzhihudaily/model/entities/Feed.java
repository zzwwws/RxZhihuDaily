
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
    "date",
    "stories",
    "top_stories"
})
public class Feed {

    @JsonProperty("date")
    private String date;
    @JsonProperty("stories")
    private List<Story> stories = new ArrayList<Story>();
    @JsonProperty("top_stories")
    private List<TopStory> topStories = new ArrayList<TopStory>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The date
     */
    @JsonProperty("date")
    public String getDate() {
        return date;
    }

    /**
     * 
     * @param date
     *     The date
     */
    @JsonProperty("date")
    public void setDate(String date) {
        this.date = date;
    }

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
     *     The topStories
     */
    @JsonProperty("top_stories")
    public List<TopStory> getTopStories() {
        return topStories;
    }

    /**
     * 
     * @param topStories
     *     The top_stories
     */
    @JsonProperty("top_stories")
    public void setTopStories(List<TopStory> topStories) {
        this.topStories = topStories;
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
