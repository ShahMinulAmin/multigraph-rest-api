package com.sajib.graph.web.controller;

import com.sajib.graph.Application;
import com.sajib.graph.service.SearchService;
import com.sajib.graph.types.ResultPath;
import com.sajib.graph.types.ResultRoute;
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

    final String jsonResponse = "{\n" +
            "    \"routes\": [\n" +
            "        {\n" +
            "            \"route\": [\n" +
            "                {\n" +
            "                    \"start\": \"Vasastan\",\n" +
            "                    \"end\": \"Liseberg\",\n" +
            "                    \"modeOfTransport\": \"Road\",\n" +
            "                    \"cost\": 480,\n" +
            "                    \"daysTaken\": 1\n" +
            "                }\n" +
            "            ],\n" +
            "            \"costOfRoute\": 480,\n" +
            "            \"durationOfRoute\": 1\n" +
            "        }\n" +
            "    ]\n" +
            "}";

    @Test
    public void SearchParams1_SearchRoute_SearchResult() throws Exception {
        SearchController.SearchParams searchParams = new SearchController.SearchParams();
        searchParams.from = "Vasastan";
        searchParams.to = "Liseberg";
        searchParams.transportTypes = new ArrayList<>();
        searchParams.transportTypes.add("Road");
        searchParams.container = 20;

        List<ResultPath> route = new ArrayList<>();
        route.add(new ResultPath("Vasastan", "Liseberg", 480, 1, "Road"));
        ResultRoute resultRoute = new ResultRoute();
        resultRoute.setRoute(route);
        resultRoute.setCostOfRoute(480);
        resultRoute.setDurationOfRoute(1);
        List<ResultRoute> resultRouteList = new ArrayList<>();
        resultRouteList.add(resultRoute);

        // Arrange
        when(searchService.searchFromLocation(searchParams.from, searchParams.to,
                searchParams.transportTypes, searchParams.container, null, null, null, null))
                .thenReturn(resultRouteList);

        // Act
        ResultActions result = mockMvc.perform(post("/api/v1/routes").contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.toJson(searchParams)));

        // Assert
        result.andExpect(status().isOk())
                .andExpect(content().json(jsonResponse))
                .andExpect(jsonPath("$.routes[0].costOfRoute", is(480)))
                .andExpect(jsonPath("$.routes[0].durationOfRoute", is(1)));
    }

}
