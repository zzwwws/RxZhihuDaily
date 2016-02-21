
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
    "body",
    "image_source",
    "title",
    "image",
    "share_url",
    "js",
    "ga_prefix",
    "type",
    "theme_name",
    "editor_name",
    "theme_id",
    "id",
    "css"
})
public class StoryDetail {

    @JsonProperty("body")
    private String body;
    @JsonProperty("image_source")
    private String imageSource;
    @JsonProperty("title")
    private String title;
    @JsonProperty("image")
    private String image;
    @JsonProperty("share_url")
    private String shareUrl;
    @JsonProperty("js")
    private List<Object> js = new ArrayList<Object>();
    @JsonProperty("ga_prefix")
    private String gaPrefix;
    @JsonProperty("type")
    private Integer type;
    @JsonProperty("theme_name")
    private String themeName;
    @JsonProperty("editor_name")
    private String editorName;
    @JsonProperty("theme_id")
    private Integer themeId;
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("css")
    private List<String> css = new ArrayList<String>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The body
     */
    @JsonProperty("body")
    public String getBody() {
        return body;
    }

    /**
     * 
     * @param body
     *     The body
     */
    @JsonProperty("body")
    public void setBody(String body) {
        this.body = body;
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

    /**
     * 
     * @return
     *     The title
     */
    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    /**
     * 
     * @param title
     *     The title
     */
    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
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
     *     The shareUrl
     */
    @JsonProperty("share_url")
    public String getShareUrl() {
        return shareUrl;
    }

    /**
     * 
     * @param shareUrl
     *     The share_url
     */
    @JsonProperty("share_url")
    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }

    /**
     * 
     * @return
     *     The js
     */
    @JsonProperty("js")
    public List<Object> getJs() {
        return js;
    }

    /**
     * 
     * @param js
     *     The js
     */
    @JsonProperty("js")
    public void setJs(List<Object> js) {
        this.js = js;
    }

    /**
     * 
     * @return
     *     The gaPrefix
     */
    @JsonProperty("ga_prefix")
    public String getGaPrefix() {
        return gaPrefix;
    }

    /**
     * 
     * @param gaPrefix
     *     The ga_prefix
     */
    @JsonProperty("ga_prefix")
    public void setGaPrefix(String gaPrefix) {
        this.gaPrefix = gaPrefix;
    }

    /**
     * 
     * @return
     *     The type
     */
    @JsonProperty("type")
    public Integer getType() {
        return type;
    }

    /**
     * 
     * @param type
     *     The type
     */
    @JsonProperty("type")
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 
     * @return
     *     The themeName
     */
    @JsonProperty("theme_name")
    public String getThemeName() {
        return themeName;
    }

    /**
     * 
     * @param themeName
     *     The theme_name
     */
    @JsonProperty("theme_name")
    public void setThemeName(String themeName) {
        this.themeName = themeName;
    }

    /**
     * 
     * @return
     *     The editorName
     */
    @JsonProperty("editor_name")
    public String getEditorName() {
        return editorName;
    }

    /**
     * 
     * @param editorName
     *     The editor_name
     */
    @JsonProperty("editor_name")
    public void setEditorName(String editorName) {
        this.editorName = editorName;
    }

    /**
     * 
     * @return
     *     The themeId
     */
    @JsonProperty("theme_id")
    public Integer getThemeId() {
        return themeId;
    }

    /**
     * 
     * @param themeId
     *     The theme_id
     */
    @JsonProperty("theme_id")
    public void setThemeId(Integer themeId) {
        this.themeId = themeId;
    }

    /**
     * 
     * @return
     *     The id
     */
    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The css
     */
    @JsonProperty("css")
    public List<String> getCss() {
        return css;
    }

    /**
     * 
     * @param css
     *     The css
     */
    @JsonProperty("css")
    public void setCss(List<String> css) {
        this.css = css;
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
