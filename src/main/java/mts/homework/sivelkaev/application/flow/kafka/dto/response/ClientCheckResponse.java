package mts.homework.sivelkaev.application.flow.kafka.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClientCheckResponse {
    @JsonProperty(value = "applicationId", required = true)
    private Long applicationId;

    @JsonProperty(value = "clientId")
    private Long clientId;

    @JsonProperty(value = "status", required = true)
    private Integer status;

    @JsonProperty(value = "statusDesc")
    private String statusDesc;
}
