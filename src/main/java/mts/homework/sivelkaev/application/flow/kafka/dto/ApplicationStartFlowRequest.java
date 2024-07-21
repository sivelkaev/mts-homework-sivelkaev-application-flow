package mts.homework.sivelkaev.application.flow.kafka.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApplicationStartFlowRequest {
    @NotBlank
    @JsonProperty(value = "id", required = true)
    private Long id;

    @NotBlank
    @JsonProperty(value = "type", required = true)
    private String type;

    @NotBlank
    @JsonProperty(value = "status", required = true)
    private String status;

    @NotBlank
    @JsonProperty(value = "firstName", required = true)
    private String firstName;

    @JsonProperty(value = "middleName")
    private String middleName;

    @NotBlank
    @JsonProperty(value = "lastName", required = true)
    private String lastName;

    @NotBlank
    @JsonProperty(value = "birthDate", required = true)
    private String birthDate;

    @NotBlank
    @JsonProperty(value = "passportNumber", required = true)
    private String passportNumber;
}
