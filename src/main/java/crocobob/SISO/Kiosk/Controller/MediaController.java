package crocobob.SISO.Kiosk.Controller;

import crocobob.SISO.Kiosk.Domain.Gallery.MediaInfo;
import crocobob.SISO.Kiosk.Service.Gallery.MediaFileService;
import crocobob.SISO.Kiosk.Service.Gallery.MediaInfoService;
import crocobob.SISO.Kiosk.Service.Gallery.MediaThumbnailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Locale;

@Tag(name = "Gallery Media", description = "갤러리에 띄울 영상, 이미지")
@RestController
public class MediaController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final MediaFileService fileService;
    private final MediaInfoService infoService;
    private final MediaThumbnailService thumbnailService;

    public MediaController(MediaFileService fileService, MediaInfoService infoService, MediaThumbnailService thumbnailService) {
        this.fileService = fileService;
        this.infoService = infoService;
        this.thumbnailService = thumbnailService;
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
            summary = "id로 조회해요."
    )
    @GetMapping(path="/medias/{id}")
    public ResponseEntity<Resource> getMediaById(@PathVariable("id") Long id) {
        logger.info("GET /medias/" + id + " request received.");

        var mediaInfo = infoService.getMediaInfo(id);
        var resource = fileService.getResource(mediaInfo.getFileName());

        if(resource.exists()){
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + mediaInfo.getFileName() + "\"")
                    .contentType(MediaType.valueOf(mediaInfo.getMediaType()))
                    .body(resource);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(
            summary = "id로 썸네일 조회",
            description = "썸네일을 조회해요."
    )
    @GetMapping(path="/medias/{id}/thumbnail")
    public ResponseEntity<Resource> getThumbnail(@PathVariable("id") Long id) {
        logger.info("GET /medias/" + id + "/thumbnail request received.");

        var thumbnail = fileService.getResource(thumbnailService.getThumbnailFilePath(id));
        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(thumbnail);
    }

    @Operation(
            summary = "기간을 연장해요.",
            description = "만료기간을 7일 연장해요. Body 없이 POST만 보내주면, 백에서 알아서 할게요."
    )
    @PostMapping(path="medias/{id}/prolong-due-date")
    public ResponseEntity<MediaInfo> extendDueDate(@PathVariable("id") Long id) {
        logger.info("POST /medias/" + id + "/extendDueDate request received.");
        return ResponseEntity.ok().body(infoService.extendDueDate(id));
    }

    @Operation(
            summary = "파일을 업로드해요.",
            description = "그... swagger에선 'file'을 string이라고 소개하는데, 그건 무시해주시구요." +
                    " 프론트에서 어떻게 파일을 POST Request에 담는지 제가 잘 몰라요." +
                    " '파라미터'로 전달하는 거 같은데... 뭔가 정보가 더 필요하면 연락주세요."
    )
    @PostMapping(path="/medias/upload")
    public ResponseEntity<MediaInfo> uploadFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam("uploader") String uploader) {
        logger.info("POST /medias/upload request received.");

        MediaInfo mediaInfo = fileService.processFile(file, uploader);
        return ResponseEntity.ok().body(mediaInfo);
    }

    @Operation(
            summary = "파일을 업로드해요. URL로."
    )
    @PostMapping(path="/medias/upload/url")
    public ResponseEntity<MediaInfo> uploadFileWithURL(
            @RequestParam("fileURL") String urlString,
            @RequestParam("uploader") String uploader) {
        logger.info("POST /medias/upload/url request received.");
        logger.info("URL = " + urlString);

        MediaInfo mediaInfo = fileService.processFile(urlString, uploader);
        return ResponseEntity.ok().body(mediaInfo);
    }

    @Operation(
            summary = "순서를 위로 올려요.",
            description = "리스트 상에서 상단으로 올려요." +
                    "(참고 : orderNum은 작아져요. 작을수록 상단이거든요.) || 다른 파일의 순서는 상관하지 마세요. 백에서 알아서 해요."
    )
    @PostMapping(path="/medias/{id}/order/up")
    public ResponseEntity<MediaInfo> orderUp(@PathVariable("id") Long id) {
        logger.info("POST /medias/{}/order/up request received.", id);
        return ResponseEntity.ok().body(infoService.changeOrderNum(id, -1));
    }

    @Operation(
            summary = "순서를 아래로 내려요.",
            description = "리스트 상에서 하단으로 내려요.\n" +
                    "(참고 : orderNum는 커져요. 클수록 하단이거든요.) || 다른 파일의 순서는 상관하지 마세요. 백에서 알아서 해요."
    )
    @PostMapping(path="medias/{id}/order/down")
    public ResponseEntity<MediaInfo> orderDown(@PathVariable("id") Long id) {
        logger.info("POST /medias/{}/order/down request received.", id);
        return ResponseEntity.ok().body(infoService.changeOrderNum(id, 1));
    }

    @Operation(
            summary = "지워요.",
            description = "지운답니다."
    )
    @DeleteMapping(path="/medias/{id}")
    public ResponseEntity<String> deleteFile(@PathVariable("id") Long id) {
        logger.info("DELETE /medias/" + id + " request received.");
        return ResponseEntity.ok().body(fileService.deleteFile(id));
    }
}
