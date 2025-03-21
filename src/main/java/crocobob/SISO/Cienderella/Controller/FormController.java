package crocobob.SISO.Cienderella.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import crocobob.SISO.Cienderella.Domain.Form;
import crocobob.SISO.Cienderella.Service.FormService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Form", description = "최종 본문")
public class FormController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final FormService service;

    public FormController(FormService service) {
        this.service = service;
    }

    @GetMapping(path = "/form")
    @Operation(
            summary = "최종 신청글 생성",
            description = "저장된 데이터들을 기반으로, 야간 사용 신청글을 생성해요"
    )
    @ApiResponse(
            responseCode = "200",
            description = "성공"
    )
    public Form generateNewForm(Model model) throws JsonProcessingException {
        logger.info("GET /form request received.");
        return service.getForm();
    }
}
