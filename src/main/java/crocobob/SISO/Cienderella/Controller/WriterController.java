package crocobob.SISO.Cienderella.Controller;

import crocobob.SISO.Cienderella.Domain.Writer;
import crocobob.SISO.Cienderella.Domain.WriterDTO;
import crocobob.SISO.Cienderella.Service.WriterService;
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
@Tag(name = "Writer", description = "작성자")
public class WriterController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final WriterService service;

    public WriterController(WriterService service) {
        this.service = service;
    }

    @GetMapping(path = "/writers")
    @Operation(
            summary = "작성자 전체 조회",
            description = "저장된 모든 writer들을 갖다줄게."
    )
    @ApiResponse(
            responseCode = "200",
            description = "성공"
    )
    public List<Writer> getWriterList() {
        logger.info("GET /writers request received.");
        return service.getAllWriters();
    }

    @GetMapping(path = "/writers/{id}")
    @Operation(
            summary = "id로 작성자 조회",
            description = "그 id의 작성자를 갖다줄게요."
    )
    @ApiResponse(
            responseCode = "200",
            description = "성공"
    )
    public Writer getWriter(@PathVariable("id") long id) {
        logger.info("GET /writers/" + id + " request received.");
        return service.getWriter(id);
    }

    @PostMapping("/writers")
    @Operation(
            summary = "작성자 저장",
            description = "갖다준 writer을 저장할게요."
    )
    @ApiResponse(
            responseCode = "200",
            description = "성공"
    )
    public ResponseEntity<Writer> createWriter(@RequestBody WriterDTO dto) {
        logger.info("POST /writers request received.");

        return ResponseEntity.ok().body(service.saveWriter(dto));
    }

    @PatchMapping("/writers/{id}")
    @Operation(
            summary = "작성자 수정",
            description = "그 id의 작성자를 수정할게요."
    )
    @ApiResponse(
            responseCode = "200",
            description = "성공"
    )
    public ResponseEntity<Writer> updateWriter(@PathVariable("id") long id, @RequestBody Writer writer) {
        logger.info("PATCH /writers/" + id + " request received.");
        return ResponseEntity.ok().body(service.patchUpdateWriter(id, writer));
    }


    @DeleteMapping(path = "writers/{id}")
    @Operation(
            summary = "작성자 삭제",
            description = "그 id의 writer를 삭제할게요."
    )
    @ApiResponse(
            responseCode = "200",
            description = "성공"
    )
    public void deleteWriter(@PathVariable("id") long id) {
        logger.info("DELETE /writers/" + id + " request received.");
        service.deleteWriter(id);
    }
}
