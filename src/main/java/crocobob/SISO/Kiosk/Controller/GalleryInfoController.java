package crocobob.SISO.Kiosk.Controller;

import crocobob.SISO.Exception.DBEntityNotFoundException;
import crocobob.SISO.Kiosk.Domain.Gallery.GalleryInfoDTO;
import crocobob.SISO.Kiosk.Service.GalleryInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GalleryInfoController {
    private final GalleryInfoService service;

    public GalleryInfoController(GalleryInfoService service) {
        this.service = service;
    }

    @GetMapping(path = "/gallery/config")
    @Operation(
            summary = "갤러리 설정값 조회",
            description = "사진 재생시간, 영상 최대 재생시간, 추천 수 임계값을 조회해요."
    )
    @ApiResponse(
            responseCode = "200",
            description = "성공"
    )
    public GalleryInfoDTO getGalleryInfo() {
        try {
            return service.getGalleryInfo();
        } catch (DBEntityNotFoundException e) {
            throw e;
        }
    }

    @PatchMapping("/gallery/config")
    @Operation(
            summary = "갤러리 설정값 수정",
            description = "사진 재생시간, 영상 최대 재생시간, 추천 수 임계값을 조회해요."
    )
    @ApiResponse(
            responseCode = "200",
            description = "성공"
    )
    public void updateContent(@RequestBody GalleryInfoDTO dto) {
        service.patchUpdateGalleryInfo(dto);
    }

}
