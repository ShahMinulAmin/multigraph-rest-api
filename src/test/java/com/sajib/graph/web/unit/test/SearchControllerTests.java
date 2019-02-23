package com.sajib.graph.web.unit.test;

import com.sajib.graph.Application;
import com.sajib.graph.service.SearchService;
import com.sajib.graph.types.ResultPath;
import com.sajib.graph.types.ResultRoute;
import com.sajib.graph.web.util.JsonResponses;
import com.sajib.graph.web.util.JsonUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by sajib on 2/21/19.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = Application.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application.properties")
public class SearchControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SearchService searchService;

    @Test
    public void SearchParams1_SearchRoute_SearchResult() throws Exception {
        SearchController.SearchParams searchParams = new SearchController.SearchParams();
        searchParams.from = "Vasastan";
        searchParams.to = "Liseberg";
        searchParams.transportTypes = new ArrayList<>();
        searchParams.transportTypes.add("Road");
        searchParams.container = 20;

        List<ResultRoute> resultRouteList = new ArrayList<>();
        List<ResultPath> route1 = new ArrayList<>();
        route1.add(new ResultPath("Vasastan", "Liseberg", 480, 1, "Road"));
        ResultRoute resultRoute1 = new ResultRoute();
        resultRoute1.setRoute(route1);
        resultRoute1.setCostOfRoute(480);
        resultRoute1.setDurationOfRoute(1);
        resultRouteList.add(resultRoute1);

        // Arrange
        when(searchService.searchFromLocation(searchParams.from, searchParams.to,
                searchParams.transportTypes, searchParams.container, null, null, null, null))
                .thenReturn(resultRouteList);

        // Act
        ResultActions result = mockMvc.perform(post("/api/v1/routes").contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.toJson(searchParams)));

        // Assert
        result.andExpect(status().isOk())
                .andExpect(content().json(JsonResponses.JSON_RESPONSE_1))
                .andExpect(jsonPath("$.routes[0].costOfRoute", is(480)))
                .andExpect(jsonPath("$.routes[0].durationOfRoute", is(1)));
    }

    @Test
    public void SearchParams2_SearchRoute_SearchResult() throws Exception {

        SearchController.SearchParams searchParams = new SearchController.SearchParams();
        searchParams.from = "Himchari";
        searchParams.to = "Banani";
        searchParams.transportTypes = new ArrayList<>();
        searchParams.transportTypes.add("Road");
        searchParams.container = 20;

        List<ResultRoute> resultRouteList = new ArrayList<>();
        List<ResultPath> route1 = new ArrayList<>();
        route1.add(new ResultPath("Himchari", "Kaptai", 100, 1, "Road"));
        route1.add(new ResultPath("Kaptai", "Karail", 115, 1, "Road"));
        ResultRoute resultRoute1 = new ResultRoute();
        resultRoute1.setRoute(route1);
        resultRoute1.setCostOfRoute(215);
        resultRoute1.setDurationOfRoute(2);
        resultRouteList.add(resultRoute1);

        List<ResultPath> route2 = new ArrayList<>();
        route2.add(new ResultPath("Himchari", "Kaptai", 100, 1, "Road"));
        route2.add(new ResultPath("Kaptai", "Agargaon", 112, 1, "Road"));
        ResultRoute resultRoute2 = new ResultRoute();
        resultRoute2.setRoute(route2);
        resultRoute2.setCostOfRoute(212);
        resultRoute2.setDurationOfRoute(2);
        resultRouteList.add(resultRoute2);


        // Arrange
        when(searchService.searchFromLocation(searchParams.from, searchParams.to,
                searchParams.transportTypes, searchParams.container, null, null, null, null))
                .thenReturn(resultRouteList);

        // Act
        ResultActions result = mockMvc.perform(post("/api/v1/routes").contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.toJson(searchParams)));

        // Assert
        result.andExpect(status().isOk())
                .andExpect(content().json(JsonResponses.JSON_RESPONSE_2))
                .andExpect(jsonPath("$.routes[0].costOfRoute", is(215)))
                .andExpect(jsonPath("$.routes[0].durationOfRoute", is(2)))
                .andExpect(jsonPath("$.routes[1].costOfRoute", is(212)))
                .andExpect(jsonPath("$.routes[1].durationOfRoute", is(2)));

    }

}
