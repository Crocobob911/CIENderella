package crocobob.SISO.Kiosk.Controller;


import crocobob.SISO.Kiosk.Domain.Notice.Notice;
import crocobob.SISO.Kiosk.Domain.Notice.NoticeDTO;
import crocobob.SISO.Kiosk.Service.NoticeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@Tag(name = "Discord Notice", description = "디스코드 공지")
public class NoticeController {
    private final NoticeService service;

    public NoticeController(NoticeService service) {
        this.service = service;
    }

    @GetMapping(path="/notices")
    @Operation(
            summary = "공지 3개 조회",
            description = "1: 2달 내에 작성된. 2: 최신 순으로. 3: 최대 3개."
    )
    @ApiResponse(
            responseCode = "200",
            description = "성공"
    )
    public List<Notice> getNotice() {
        return service.getNotices();
    }


    @PostMapping(path="/notices")
    @Operation(
            summary = "공지 추가",
            description = "크롤링 봇이 사용할 거예요. 다른 분들은 필요할 때만 요청해주세요."
    )
    @ApiResponse(
            responseCode = "200",
            description = "성공"
    )
    public ResponseEntity createNotice(@RequestBody NoticeDTO dto) {
        var notice = service.save(dto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("{id}")
                .buildAndExpand(notice.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }
}
