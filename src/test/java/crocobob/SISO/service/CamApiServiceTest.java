package crocobob.SISO.service;

import crocobob.SISO.Cienderella.Service.CamApiService;
import org.junit.jupiter.api.Test;

class CamApiServiceTest {

    CamApiService service = new CamApiService();

    @Test
    public void apiRequestTest(){
        try {
            System.out.println(service.getCamApiResponse().toString());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}