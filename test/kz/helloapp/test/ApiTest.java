package kz.helloapp.test;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import kz.helloapp.model.domain.Campaign;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;

import static kz.helloapp.view.customer.api.ApplyDiscountServlet.Result;
import static kz.helloapp.view.customer.api.ApplyDiscountServlet.Status;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ApiTest {


    public static final String API_URL = "http://localhost:8080/helloapp/customer/api";
    public static final String TOKEN = "test_token";


    private Gson gson;

    @Before
    public void init() {
        gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
    }

    @Test
    public void testApiCall() {
        testGetCampaigns();
        testActivateDiscount();
    }


    private void testGetCampaigns() {
//        String url = RequestBuilder.create(API_URL + "/campaigns", TOKEN).build();
        String url = API_URL + "/campaigns";

        String json = ApiClient.doGet(url);

        assertNotNull(json);

        System.out.println("json:\n" + json);


        Type type = new TypeToken<Collection<Campaign>>() {
        }.getType();
        List<Campaign> campaigns = gson.fromJson(json, type);

        assertNotNull(campaigns);
        assertEquals(6, campaigns.size());

        Campaign first = campaigns.get(0);
        assertEquals(1L, first.getId().longValue());
        assertEquals("Скидон на влажный массаж", first.getTitle());
    }


    private void testActivateDiscount() {
        System.out.println("Activating discount...");

        long userId = 2L;
        long campaignId = 5L;
        String confirmerCode = "1005";

        System.out.println("userId: " + userId);
        System.out.println("campaignId: " + campaignId);
        System.out.println("confirmerCode: " + confirmerCode);

        String url = String.format("%s/apply-campaign?userId=%d&campaignId=%d&confirmerCode=%s",
                API_URL, userId, campaignId, confirmerCode);


        String json = ApiClient.doPost(url);

        System.out.println("json:\n" + json);

        assertNotNull(json);

        Result result = gson.fromJson(json, Result.class);

        assertNotNull(result);

        System.out.println("resultStatus: " + result.getStatus());
        System.out.println("applied id: " + result.getAppliedId());

        assertEquals(Status.OK, result.getStatus());

    }


}
