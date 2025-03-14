package crocobob.SISO.Kiosk.Controller;

import crocobob.SISO.Kiosk.Domain.Gallery.MediaInfo;
import crocobob.SISO.Kiosk.Service.Gallery.MediaFileService;
import crocobob.SISO.Kiosk.Service.Gallery.MediaInfoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
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

    @GetMapping(path="/medias/all")
    public ResponseEntity<List<MediaInfo>> getAllMediaInfo(){
        logger.info("GET /medias/all request received.");
        return ResponseEntity.ok().body(infoService.getAllMediaInfo());
    }

    @GetMapping(path="/medias/all/valid")
    public ResponseEntity<List<MediaInfo>> getAllValidMedia(){
        logger.info("GET /medias/all/valid request received.");
        return ResponseEntity.ok().body(infoService.getAllValidMediaInfo());
    }

    @GetMapping(path="/medias/id/{id}")
    public ResponseEntity<Resource> getMediaById(@PathVariable("id") Long id) {
        logger.info("GET /medias/" + id + " request received.");
        return fileService.getResponseEntityWithResource(id);
    }

    @GetMapping(path="/medias/fileName/{fileName}")
    public ResponseEntity<Resource> getMediaByFileName(@PathVariable("fileName") String fileName) {
        logger.info("GET /medias/" + fileName + " request received.");
        return fileService.getResponseEntityWithResource(fileName);
    }

    @PostMapping(path="/medias/upload")
    public ResponseEntity<MediaInfo> saveFile(@RequestParam("file") MultipartFile file) {
        logger.info("POST /medias/upload request received.");

        MediaInfo mediaInfo = fileService.processFile(file);
        return ResponseEntity.ok().body(mediaInfo);
    }

    @DeleteMapping(path="/medias/id/{id}")
    public ResponseEntity<Boolean> deleteFile(@PathVariable("id") Long id) {
        logger.info("DELETE /medias/" + id + " request received.");
        return ResponseEntity.ok().body(fileService.deleteFile(id));
    }
}
