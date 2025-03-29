package crocobob.SISO.Cienderella.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import crocobob.SISO.Cienderella.Domain.CamApiResponse;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


@Service
public class CamApiService {
    private final RestTemplate rt = new RestTemplate();
    private final ObjectMapper mapper = new ObjectMapper();

    private final String camApiUrl = "https://api.cien.or.kr/api/clubroom/people-count";
    private String apiKey;

    public CamApiService() {
        apiKey = readApiKeyFromLocal();
    }

    public CamApiResponse getCamApiResponse() throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiKey);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response =
                rt.exchange(camApiUrl, HttpMethod.GET, entity, String.class);
        return new CamApiResponse(
                extractCountFromResponse(response.getBody()),
                extractIsPeopleThereFromResponse(response.getBody())
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

    private String readApiKeyFromLocal(){
        ClassPathResource resource = new ClassPathResource("camApiKey.txt");
        try (BufferedReader reader = new BufferedReader(new FileReader(resource.getFile()))) {
            return reader.readLine().trim();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
