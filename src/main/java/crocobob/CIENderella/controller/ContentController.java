package crocobob.CIENderella.controller;

import crocobob.CIENderella.Exception.DBEntityNotFoundException;
import crocobob.CIENderella.domain.cienderella.Content;
import crocobob.CIENderella.domain.cienderella.ContentDTO;
import crocobob.CIENderella.service.CienderellaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Content", description = "본문 양식")
public class ContentController {

    private final CienderellaService service;

    public ContentController(CienderellaService service) {
        this.service = service;
    }

    @GetMapping(path = "/content")
    @Operation(
            summary = "신청글 양식 조회",
            description = "야간 사용 신청글 양식을 조회해요."
    )
    @ApiResponse(
            responseCode = "200",
            description = "성공"
    )
    public Content getContent() {
        try {
            return service.getContent();
        } catch (DBEntityNotFoundException e) {
            throw e;
        }
    }

    @PatchMapping("/content")
    @Operation(
            summary = "신청글 양식 수정",
            description = "야간 사용 신청글 양식을 바꿔요."
    )
    @ApiResponse(
            responseCode = "200",
            description = "성공"
    )
    public void updateContent(@RequestBody ContentDTO dto) {
        service.patchUpdateContent(dto);
    }

}
