package crocobob.SISO.Cienderella.Controller;

import crocobob.SISO.Cienderella.Domain.Writer;
import crocobob.SISO.Cienderella.Domain.WriterDTO;
import crocobob.SISO.Cienderella.Service.WriterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@Tag(name = "Writer", description = "작성자")
public class WriterController {

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
        // 여기서 writers(작성자)들 리스트 갖다주기
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
        // 여기서 writers(작성자)들 리스트 갖다주기
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
    public ResponseEntity<Object> createWriter(@RequestBody WriterDTO dto) {
        Writer writer = service.saveWriter(dto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(writer.getId())
                .toUri();
        return ResponseEntity.created(location).build();
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
    public void updateWriter(@PathVariable("id") long id, @RequestBody Writer writer) {
        service.patchUpdateWriter(id, writer);
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
        service.deleteWriter(id);
    }
}
