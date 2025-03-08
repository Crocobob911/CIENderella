package crocobob.CIENderella.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import crocobob.CIENderella.domain.Form;
import crocobob.CIENderella.service.CienderellaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Form", description = "최종 본문")
public class FormController {

    private final CienderellaService service;

    public FormController(CienderellaService service) {
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
        // 여기서 form 만들어 갖다주기
        return service.getForm();
    }
}
