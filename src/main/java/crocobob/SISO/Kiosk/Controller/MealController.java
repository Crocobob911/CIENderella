package crocobob.SISO.Kiosk.Controller;

import crocobob.SISO.Exception.InvalidMealParameterException;
import crocobob.SISO.Kiosk.Domain.MealInfo_AfterProcess;
import crocobob.SISO.Kiosk.Service.Meal.MealOutputService;
import crocobob.SISO.Kiosk.Service.Meal.MealParseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "Meal", description = "학식 정보")
public class MealController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public MealController(MealParseService saveService, MealOutputService readService) {
        this.saveService = saveService;
        this.readService = readService;
    }

    private MealParseService saveService;
    private MealOutputService readService;


    @Operation(
            summary = "앞으로 일주일의 모든 식단 조회",
            description = "모든 식당의 일주일 식단을 조회해요."
    )
    @ApiResponse(
            responseCode = "200",
            description = "성공"
    )
    @GetMapping(path="/meals/all")
    public List<MealInfo_AfterProcess> getMealOfNow() {
        logger.info("GET /meals/all request received.");
        return readService.findAllMeals();
    }

    @Operation(
            summary = "특정 시간의 식단 조회",
            description = "특정 시간의 모든 식당의 식단을 조회해요. day에는 \"today\", \"tomorrow\", mealType에는 \"morning\", \"lunch\", \"dinner\"를 입력할 수 있어요."
    )
    @ApiResponse(
            responseCode = "200",
            description = "성공"
    )
    @ApiResponse(
            responseCode = "404",
            description = "경로를 올바르게 적었나요?  옳은 예) /meals/today/lunch  /meals/tomorrow/dinner | 나쁜 예 /meals/yesterday/dinner  /meals/tommmorrrrrow/mornin"
    )
    @GetMapping(path="/meals/{day}/{mealType}")
    public List<MealInfo_AfterProcess> getMeal(
            @Parameter(description = "today, tomorrow") @PathVariable("day") String day,
            @Parameter(description = "morning, lunch, dinner") @PathVariable("mealType") String mealType) {
        logger.info("GET /meals/" + day + "/" + mealType + " request received.");

        if(!day.equals("today") && !day.equals("tomorrow")) throw new InvalidMealParameterException("<<Invalid Day Parameter.>>");
        if(!mealType.equals("morning") && !mealType.equals("lunch") && !mealType.equals("dinner")) throw new InvalidMealParameterException("<<Invalid MealType Parameter.>>");

        return readService.findMeals(day, mealType);
    }

    @Operation(
            summary = "식단 정보 파싱",
            description = "크롤링 봇이 사용할 거예요. 다른 분들은 필요할 때만 요청해주세요. 주(week)가 넘어갔는데도 식단이 바뀌지 않았다면, 한 번 호출해봐도 좋아요."
    )
    @ApiResponse(
            responseCode = "200",
            description = "성공"
    )
    @GetMapping(path="/meals/parse")
    public ResponseEntity<String> parseWeeklyMealData(){
        logger.info("GET /meals/parse request received.");
        return ResponseEntity.ok().body(saveService.createWeeklyMealData());
    }
}
