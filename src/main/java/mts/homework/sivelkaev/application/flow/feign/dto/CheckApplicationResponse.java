package mts.homework.sivelkaev.application.flow.feign.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CheckApplicationResponse {
    @NotBlank
    @JsonProperty(value = "id", required = true)
    private Long id;

    @NotBlank
    @JsonProperty(value = "result", required = true)
    private Boolean result;
}
