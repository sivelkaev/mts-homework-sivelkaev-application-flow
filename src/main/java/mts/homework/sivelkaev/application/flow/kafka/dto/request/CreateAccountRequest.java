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
public class CreateAccountRequest {
    @NotBlank
    @JsonProperty(value = "applicationId", required = true)
    private Long applicationId;

    @NotBlank
    @JsonProperty(value = "clientId", required = true)
    private Long clientId;
}
