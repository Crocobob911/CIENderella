package crocobob.SISO.Kiosk.Controller;

import crocobob.SISO.Kiosk.Domain.Gallery.MediaInfo;
import crocobob.SISO.Kiosk.Service.Gallery.MediaFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class MediaFileController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final MediaFileService service;

    public MediaFileController(MediaFileService service) {
        this.service = service;
    }

    @GetMapping(path="/medias/{fileName}")
    public ResponseEntity<Resource> getMedia(@PathVariable("fileName") String fileName) {
        logger.info("GET /medias/" + fileName + " request received.");
        return service.getFile(fileName);
    }

    @PostMapping(path="/medias/upload")
    public ResponseEntity<MediaInfo> saveFile(@RequestParam("file") MultipartFile file) {
        logger.info("POST /medias/upload request received.");

        MediaInfo mediaInfo = service.processFile(file);
        return ResponseEntity.ok().body(mediaInfo);
    }
}
