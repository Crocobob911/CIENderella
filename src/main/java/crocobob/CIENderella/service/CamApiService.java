package crocobob.CIENderella.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import crocobob.CIENderella.domain.cienderella.CamApiResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class CamApiService {
    private final RestTemplate rt = new RestTemplate();
    private final ObjectMapper mapper = new ObjectMapper();

    private final String camApiUrl = "https://api.cien.or.kr/api/system/getClubRoomPeopleCount";


    public CamApiResponse getCamApiResponse() throws JsonProcessingException {
        String response =
                rt.getForEntity(camApiUrl, String.class).getBody();
        return new CamApiResponse(
                extractCountFromResponse(response),
                extractIsPeopleThereFromResponse(response)
        );
    }

    private int extractCountFromResponse(String response) throws JsonProcessingException {
        try{
            JsonNode root = mapper.readTree(response);
            return root.path("peopleCount").asInt();
        }catch (JsonProcessingException e){
            e.printStackTrace();
            return 0;
        }
    }

    private Boolean extractIsPeopleThereFromResponse(String response){
        try{
            JsonNode root = mapper.readTree(response);
            return root.path("isPeopleThere").asBoolean();
        }catch (JsonProcessingException e){
            e.printStackTrace();
            return false;
        }
    }
}
