package tech.orangeblue.springboottutorial.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
@JsonInclude(value = JsonInclude.Include.NON_NULL, content = JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ImageModel {
  private int height;
  private int width;

  @SerializedName("mime_type")
  @JsonProperty("mime_type")
  private String mimeType;

  private String description;

  @SerializedName("source_url")
  @JsonProperty("source_url")
  private String sourceUrl;

}
