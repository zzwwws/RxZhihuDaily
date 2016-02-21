
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
    "limit",
    "subscribed",
    "other"
})
public class Topics {

    @JsonProperty("limit")
    private Integer limit;
    @JsonProperty("subscribed")
    private List<Object> subscribed = new ArrayList<Object>();
    @JsonProperty("other")
    private List<TopicWrapper> topicWrappers = new ArrayList<TopicWrapper>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The limit
     */
    @JsonProperty("limit")
    public Integer getLimit() {
        return limit;
    }

    /**
     * 
     * @param limit
     *     The limit
     */
    @JsonProperty("limit")
    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    /**
     * 
     * @return
     *     The subscribed
     */
    @JsonProperty("subscribed")
    public List<Object> getSubscribed() {
        return subscribed;
    }

    /**
     * 
     * @param subscribed
     *     The subscribed
     */
    @JsonProperty("subscribed")
    public void setSubscribed(List<Object> subscribed) {
        this.subscribed = subscribed;
    }

    /**
     * 
     * @return
     *     The topicWrappers
     */
    @JsonProperty("other")
    public List<TopicWrapper> getTopicWrappers() {
        return topicWrappers;
    }

    /**
     * 
     * @param topicWrappers
     *     The topicWrappers
     */
    @JsonProperty("other")
    public void setTopicWrappers(List<TopicWrapper> topicWrappers) {
        this.topicWrappers = topicWrappers;
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
