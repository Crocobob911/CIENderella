package crocobob.CIENderella.controller;

import crocobob.CIENderella.Exception.DBEntityNotFoundException;
import crocobob.CIENderella.domain.cienderella.Reason;
import crocobob.CIENderella.domain.cienderella.ReasonDTO;
import crocobob.CIENderella.service.CienderellaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@Tag(name = "Reason", description = "사유")
public class ReasonController {

    private final CienderellaService service;

    public ReasonController(CienderellaService service) {
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
        // 여기서 reason(사유)들 리스트 갖다주기
        try {
            return service.getAllReasons();
        } catch (DBEntityNotFoundException e) {
            throw e;
        }
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
        service.deleteReason(id);
    }

}
