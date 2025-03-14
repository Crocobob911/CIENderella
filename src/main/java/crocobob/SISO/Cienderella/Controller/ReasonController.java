package crocobob.SISO.Cienderella.Controller;

import crocobob.SISO.Cienderella.Service.ReasonService;
import crocobob.SISO.Exception.DBEntityNotFoundException;
import crocobob.SISO.Cienderella.Domain.Reason;
import crocobob.SISO.Cienderella.Domain.ReasonDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@Tag(name = "Reason", description = "사유")
public class ReasonController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final ReasonService service;

    public ReasonController(ReasonService service) {
        this.service = service;
    }

    @GetMapping(path = "/reasons")
    @Operation(
            summary = "사유 전체 조회",
            description = "저장된 모든 reason들을 갖다줄게요."
    )
    @ApiResponse(
            responseCode = "200",
            description = "성공"
    )
    public List<Reason> getReasonList() {
        logger.info("GET /reasons request received.");
        return service.getAllReasons();
    }

    @GetMapping(path = "/reasons/{id}")
    @Operation(
            summary = "id로 사유 조회",
            description = "그 id의 reason을 갖다줄게요."
    )
    @ApiResponse(
            responseCode = "200",
            description = "성공"
    )
    public Reason getReason(@PathVariable("id") long id) throws DBEntityNotFoundException {
        logger.info("GET /reasons/" + id + " request received.");
        return service.getReason(id);
    }

    @PostMapping("/reasons")
    @Operation(
            summary = "사유 추가",
            description = "갖다준 reason을 저장할게요."
    )
    @ApiResponse(
            responseCode = "200",
            description = "성공"
    )
    public ResponseEntity<Object> createReason(@RequestBody ReasonDTO dto) {
        logger.info("POST /reasons request received.");

        Reason reason = service.saveReason(dto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(reason.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PatchMapping("/reasons/{id}")
    @Operation(
            summary = "사유 수정",
            description = "그 id의 reason을 수정할게요."
    )
    @ApiResponse(
            responseCode = "200",
            description = "성공"
    )
    public void updateReason(@PathVariable("id") long id, @RequestBody Reason reason) {
        logger.info("PATCH /reasons/" + id + " request received.");
        service.patchUpdateReason(id, reason);
    }

    @DeleteMapping(path = "/reasons/{id}")
    @Operation(
            summary = "사유 삭제",
            description = "그 id의 reason을 삭제할게요."
    )
    @ApiResponse(
            responseCode = "200",
            description = "성공"
    )
    public void deleteReason(@PathVariable("id") long id) {
        logger.info("DELETE /reasons/" + id + " request received.");
        service.deleteReason(id);
    }

}
