package crocobob.SISO.Kiosk.Controller;

import crocobob.SISO.Kiosk.Domain.Gallery.MediaInfo;
import crocobob.SISO.Kiosk.Service.Gallery.MediaFileService;
import crocobob.SISO.Kiosk.Service.Gallery.MediaInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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
    public ResponseEntity<List<MediaInfo>> getAllValidMedia(){
        logger.info("GET /medias/all request received.");
        return ResponseEntity.ok().body(infoService.getAllMediaInfo());
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
}
