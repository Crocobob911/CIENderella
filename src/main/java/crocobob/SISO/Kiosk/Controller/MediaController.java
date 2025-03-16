package crocobob.SISO.Kiosk.Controller;

import crocobob.SISO.Kiosk.Domain.Gallery.MediaInfo;
import crocobob.SISO.Kiosk.Service.Gallery.MediaFileService;
import crocobob.SISO.Kiosk.Service.Gallery.MediaInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Tag(name = "Gallery Media", description = "갤러리에 띄울 영상, 이미지")
@RestController
public class MediaController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final MediaFileService fileService;
    private final MediaInfoService infoService;

    public MediaController(MediaFileService fileService, MediaInfoService infoService) {
        this.fileService = fileService;
        this.infoService = infoService;
    }

    @Operation(
            summary = "만료되지 않은 미디어 정보들",
            description = "만료되지 않은 MediaInfo 들을 조회해요. 실제 파일은 id를 기반으로 하나하나 따로 Get 요청을 해주세요. \n" +
                    "저도 이 요청의 Response로 mp4, jpg 등을 바로 드리고 싶었는데, 그게 쉽지 않더라구요."
    )
    @GetMapping(path="/medias")
    public ResponseEntity<List<MediaInfo>> getAllValidMedia(){
        logger.info("GET /medias/all/valid request received.");
        return ResponseEntity.ok().body(infoService.getAllValidMediaInfo());
    }

    @Operation(
            summary = "모든 미디어 정보들",
            description = "만료된 MediaInfo 들까지 모두 뱉어내요."
    )
    @GetMapping(path="/medias/all")
    public ResponseEntity<List<MediaInfo>> getAllMediaInfo(){
        logger.info("GET /medias/all request received.");
        return ResponseEntity.ok().body(infoService.getAllMediaInfo());
    }

    @GetMapping(path="/medias/{id}")
    public ResponseEntity<Resource> getMediaById(@PathVariable("id") Long id) {
        logger.info("GET /medias/" + id + " request received.");

        var mediaInfo = infoService.getMediaInfo(id);
        var resource = fileService.getResource(mediaInfo.getFileName());

        if(resource.exists()){
            return ResponseEntity.ok()
                    .contentType(MediaType.valueOf(infoService.getMediaInfo(id).getMediaType()))
                    .body(resource);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(path="medias/{id}/prolong-due-date")
    public ResponseEntity<MediaInfo> extendDueDate(@PathVariable("id") Long id) {
        logger.info("POST /medias/" + id + "/extendDueDate request received.");
        return ResponseEntity.ok().body(infoService.extendDueDate(id));
    }


    @PostMapping(path="/medias/upload")
    public ResponseEntity<MediaInfo> saveFile(@RequestParam("file") MultipartFile file) {
        logger.info("POST /medias/upload request received.");

        MediaInfo mediaInfo = fileService.processFile(file);
        return ResponseEntity.ok().body(mediaInfo);
    }

    @DeleteMapping(path="/medias/{id}")
    public ResponseEntity<Boolean> deleteFile(@PathVariable("id") Long id) {
        logger.info("DELETE /medias/" + id + " request received.");
        return ResponseEntity.ok().body(fileService.deleteFile(id));
    }
}
