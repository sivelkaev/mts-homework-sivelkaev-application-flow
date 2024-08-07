package mts.homework.sivelkaev.application.flow.feign.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class CheckApplicationRequest {
    @NotBlank
    @JsonProperty(value = "id", required = true)
    private Long id;
}
