package org.choongang.tour.services;

import lombok.RequiredArgsConstructor;
import org.choongang.global.rests.gov.api.ApiItem;
import org.choongang.global.rests.gov.api.ApiResult;
import org.choongang.tour.controllers.TourPlaceSearch;
import org.choongang.tour.entities.TourPlace;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TourPlaceInfoService {

    private final RestTemplate restTemplate;
    private String serviceKey = "CHrWrFoNSLs09ec0iaNNKpj3VuYnP%2BJA5WAtHyPqdDVCdF%2Fn1NB46%2Bfd2NDjlTiNm%2Fw48BE9guQbOo12k2a6wA%3D%3D";

    public List<TourPlace> getList(TourPlaceSearch search) {
        double lat = search.getLatitude();
        double lon = search.getLongitude();
        int radius = search.getRadius();

        String url = String.format("https://apis.data.go.kr/B551011/KorService1/locationBasedList1?MobileOS=AND&MobileApp=test&mapX=%f&mapY=%f&radius=%d&numOfRows=5000&serviceKey=%s&_type=json", lon, lat, radius, serviceKey);
        try {
            ResponseEntity<ApiResult> response = restTemplate.getForEntity(URI.create(url), ApiResult.class);

            if (response.getStatusCode().is2xxSuccessful() && response.getBody().getResponse().getHeader().getResultCode().equals("0000")) {

                List<Long> ids = response.getBody().getResponse().getBody().getItems().getItem().stream().map(ApiItem::getContentid).toList();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}