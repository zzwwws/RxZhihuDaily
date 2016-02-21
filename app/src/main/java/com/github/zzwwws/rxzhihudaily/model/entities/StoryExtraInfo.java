package com.github.zzwwws.rxzhihudaily.model.entities;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "post_reasons",
        "long_comments",
        "popularity",
        "normal_comments",
        "comments",
        "short_comments"
})
public class StoryExtraInfo {

    @JsonProperty("post_reasons")
    private Integer postReasons;
    @JsonProperty("long_comments")
    private Integer longComments;
    @JsonProperty("popularity")
    private Integer popularity;
    @JsonProperty("normal_comments")
    private Integer normalComments;
    @JsonProperty("comments")
    private Integer comments;
    @JsonProperty("short_comments")
    private Integer shortComments;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The postReasons
     */
    @JsonProperty("post_reasons")
    public Integer getPostReasons() {
        return postReasons;
    }

    /**
     *
     * @param postReasons
     * The post_reasons
     */
    @JsonProperty("post_reasons")
    public void setPostReasons(Integer postReasons) {
        this.postReasons = postReasons;
    }

    /**
     *
     * @return
     * The longComments
     */
    @JsonProperty("long_comments")
    public Integer getLongComments() {
        return longComments;
    }

    /**
     *
     * @param longComments
     * The long_comments
     */
    @JsonProperty("long_comments")
    public void setLongComments(Integer longComments) {
        this.longComments = longComments;
    }

    /**
     *
     * @return
     * The popularity
     */
    @JsonProperty("popularity")
    public Integer getPopularity() {
        return popularity;
    }

    /**
     *
     * @param popularity
     * The popularity
     */
    @JsonProperty("popularity")
    public void setPopularity(Integer popularity) {
        this.popularity = popularity;
    }

    /**
     *
     * @return
     * The normalComments
     */
    @JsonProperty("normal_comments")
    public Integer getNormalComments() {
        return normalComments;
    }

    /**
     *
     * @param normalComments
     * The normal_comments
     */
    @JsonProperty("normal_comments")
    public void setNormalComments(Integer normalComments) {
        this.normalComments = normalComments;
    }

    /**
     *
     * @return
     * The comments
     */
    @JsonProperty("comments")
    public Integer getComments() {
        return comments;
    }

    /**
     *
     * @param comments
     * The comments
     */
    @JsonProperty("comments")
    public void setComments(Integer comments) {
        this.comments = comments;
    }

    /**
     *
     * @return
     * The shortComments
     */
    @JsonProperty("short_comments")
    public Integer getShortComments() {
        return shortComments;
    }

    /**
     *
     * @param shortComments
     * The short_comments
     */
    @JsonProperty("short_comments")
    public void setShortComments(Integer shortComments) {
        this.shortComments = shortComments;
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
