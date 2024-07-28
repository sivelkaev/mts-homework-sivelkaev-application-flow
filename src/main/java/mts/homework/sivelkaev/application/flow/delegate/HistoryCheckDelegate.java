package mts.homework.sivelkaev.application.flow.delegate;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mts.homework.sivelkaev.application.flow.feign.HistoryCheckService;
import mts.homework.sivelkaev.application.flow.feign.dto.CheckApplicationRequest;
import mts.homework.sivelkaev.application.flow.service.UpdateStatusService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class HistoryCheckDelegate implements JavaDelegate {
    private final HistoryCheckService historyCheckService;
    private final UpdateStatusService updateStatusService;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        log.info("History Check");

        var applicationId = execution.getVariable("id").toString();
        var response = historyCheckService.checkHistory(CheckApplicationRequest.builder().id(Long.valueOf(applicationId)).build());

        var successCheck = response.getResult();

        String applicationStatus = successCheck ? "SUCCESS" : "REFUSED";
        updateStatusService.updateStatus(applicationId, applicationStatus);
    }
}
