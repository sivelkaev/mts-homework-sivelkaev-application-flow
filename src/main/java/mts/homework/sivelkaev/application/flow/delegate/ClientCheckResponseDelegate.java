package mts.homework.sivelkaev.application.flow.delegate;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mts.homework.sivelkaev.application.flow.service.UpdateStatusService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ClientCheckResponseDelegate implements JavaDelegate {
    private static final Integer SUCCESS_CHECK_STATUS = 0;

    private final UpdateStatusService updateStatusService;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        log.info("Start client check response");

        var applicationId = execution.getVariable("id").toString();

        int checkStatus;
        if (execution.getVariable("checkStatus") != null) {
            checkStatus = Integer.parseInt(execution.getVariable("checkStatus").toString());
        } else {
            checkStatus = 999;
            execution.setVariable("checkStatus", checkStatus);
        }

        String applicationStatus = SUCCESS_CHECK_STATUS.equals(checkStatus) ? "CLIENT_CHECK_SUCCESS" : "CLIENT_CHECK_FAILED";

        updateStatusService.updateStatus(applicationId, applicationStatus);
    }
}
