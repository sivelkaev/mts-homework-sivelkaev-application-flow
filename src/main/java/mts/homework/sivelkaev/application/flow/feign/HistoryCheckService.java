package mts.homework.sivelkaev.application.flow.feign;

import mts.homework.sivelkaev.application.flow.feign.dto.CheckApplicationRequest;
import mts.homework.sivelkaev.application.flow.feign.dto.CheckApplicationResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@FeignClient(name = "history-check", url = "${integration.history-check.url}")
public interface HistoryCheckService {
    @GetMapping(value = "/check", produces = APPLICATION_JSON_VALUE)
    CheckApplicationResponse checkHistory(@RequestBody CheckApplicationRequest req);
}
