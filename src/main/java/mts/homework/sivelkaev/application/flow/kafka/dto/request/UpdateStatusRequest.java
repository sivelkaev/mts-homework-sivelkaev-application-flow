package mts.homework.sivelkaev.application.flow.kafka.dto.request;

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
public class UpdateStatusRequest {
    @NotBlank
    @JsonProperty(value = "id", required = true)
    private Long id;

    @NotBlank
    @JsonProperty(value = "status", required = true)
    private String status;
}
