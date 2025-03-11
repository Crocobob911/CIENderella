package crocobob.SISO.Kiosk.Controller;

import crocobob.SISO.Kiosk.Service.MediaFileService;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MediaFileController {
    private final MediaFileService service;

    public MediaFileController(MediaFileService service) {
        this.service = service;
    }

    @GetMapping(path="/medias/{fileName}")
    public ResponseEntity<Resource> getMedia(@PathVariable("fileName") String fileName) {
        return service.getFile(fileName);
    }
}
