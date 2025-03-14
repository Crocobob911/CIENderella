package crocobob.SISO.Cienderella.Controller;

import crocobob.SISO.Cienderella.Service.ContentService;
import crocobob.SISO.Exception.DBEntityNotFoundException;
import crocobob.SISO.Cienderella.Domain.Content;
import crocobob.SISO.Cienderella.Domain.ContentDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Content", description = "본문 양식")
public class ContentController {

    private final ContentService service;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public ContentController(ContentService service) {
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
        logger.info("GET /content request received.");
        return service.getContent();
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
    public ResponseEntity<Content> updateContent(@RequestBody ContentDTO dto) {
        logger.info("PATCH /content request received.");
        return ResponseEntity.ok().body(service.patchUpdateContent(dto));
    }

}
