package crocobob.CIENderella.controller;

import crocobob.CIENderella.Exception.EntityNotFoundException;
import crocobob.CIENderella.domain.Reason;
import crocobob.CIENderella.repository.Reason.ReasonRepository;
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
            description = "저장된 모든 reason들을 갖다줄게."
    )
    @ApiResponse(
            responseCode = "200",
            description = "성공"
    )
    public List<Reason> getReasonList() {
        // 여기서 reason(사유)들 리스트 갖다주기
        try {
            return service.getAllReasons();
        } catch (EntityNotFoundException e) {
            throw e;
        }
    }

    @GetMapping(path = "/reasons/{id}")
    @Operation(
            summary = "id로 사유 조회",
            description = "그 id의 reason을 갖다줄게."
    )
    @ApiResponse(
            responseCode = "200",
            description = "성공"
    )
    public Reason getReason(@PathVariable long id) {
        try {
            return service.getReason(id);
        } catch (EntityNotFoundException e) {
            throw e;
        }
    }

    @PostMapping("/reasons")
    @Operation(
            summary = "사유 추가",
            description = "갖다준 reason을 저장할게."
    )
    @ApiResponse(
            responseCode = "200",
            description = "성공"
    )
    public ResponseEntity<Object> createReason(@RequestBody Reason reason) {
        service.saveReason(reason);

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
            description = "그 id의 reason을 수정할게."
    )
    @ApiResponse(
            responseCode = "200",
            description = "성공"
    )
    public void updateReason(@PathVariable long id, @RequestBody Reason reason) {
        service.patchUpdateReason(id, reason);
    }

}
