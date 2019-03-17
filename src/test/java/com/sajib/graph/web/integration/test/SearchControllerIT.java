package com.sajib.graph.web.integration.test;

import com.sajib.graph.Application;
import com.sajib.graph.web.controller.SearchController;
import com.sajib.graph.web.util.JsonResponses;
import com.sajib.graph.web.util.JsonUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by sajib on 2/22/19.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = Application.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application.properties")
public class SearchControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void SearchParams1_SearchRoute_SearchResult() throws Exception {
        // Act
        ResultActions result = mockMvc.perform(get("/api/v1/routes")
                .param(JsonUtil.PARAM_FROM, "Vasastan")
                .param(JsonUtil.PARAM_TO, "Liseberg")
                .param(JsonUtil.PARAM_TRANSPORT_TYPES, "Road")
                .param(JsonUtil.PARAM_CONTAINER, "20")
                .contentType(MediaType.APPLICATION_JSON));

        // Assert
        result.andExpect(status().isOk())
                .andExpect(content().json(JsonResponses.JSON_RESPONSE_1))
                .andExpect(jsonPath("$.routes[0].costOfRoute", is(480)))
                .andExpect(jsonPath("$.routes[0].durationOfRoute", is(1)));
    }

    @Test
    public void SearchParams2_SearchRoute_SearchResult() throws Exception {
        // Act
        ResultActions result = mockMvc.perform(get("/api/v1/routes")
                .param(JsonUtil.PARAM_FROM, "Himchari")
                .param(JsonUtil.PARAM_TO, "Banani")
                .param(JsonUtil.PARAM_TRANSPORT_TYPES, "Road")
                .param(JsonUtil.PARAM_CONTAINER, "20")
                .contentType(MediaType.APPLICATION_JSON));

        // Assert
        result.andExpect(status().isOk())
                .andExpect(content().json(JsonResponses.JSON_RESPONSE_2))
                .andExpect(jsonPath("$.routes[0].costOfRoute", is(215)))
                .andExpect(jsonPath("$.routes[0].durationOfRoute", is(2)))
                .andExpect(jsonPath("$.routes[1].costOfRoute", is(212)))
                .andExpect(jsonPath("$.routes[1].durationOfRoute", is(2)));
    }

    @Test
    public void SearchParams3_SearchRoute_SearchResult() throws Exception {
        // Act
        ResultActions result = mockMvc.perform(get("/api/v1/routes")
                .param(JsonUtil.PARAM_FROM, "Vasastan")
                .param(JsonUtil.PARAM_TO, "Maitland")
                .param(JsonUtil.PARAM_TRANSPORT_TYPES, "Road")
                .param(JsonUtil.PARAM_TRANSPORT_TYPES, "Ocean")
                .param(JsonUtil.PARAM_CONTAINER, "20")
                .param(JsonUtil.PARAM_MIN_DAYS, "20")
                .param(JsonUtil.PARAM_MAX_DAYS, "25")
                .param(JsonUtil.PARAM_MIN_COST, "2500")
                .param(JsonUtil.PARAM_MAX_COST, "4000")
                .contentType(MediaType.APPLICATION_JSON));

        // Assert
        result.andExpect(status().isOk())
                .andExpect(content().json(JsonResponses.JSON_RESPONSE_3))
                .andExpect(jsonPath("$.routes[0].costOfRoute", is(2803)))
                .andExpect(jsonPath("$.routes[0].durationOfRoute", is(24)))
                .andExpect(jsonPath("$.routes[1].costOfRoute", is(2945)))
                .andExpect(jsonPath("$.routes[1].durationOfRoute", is(25)))
                .andExpect(jsonPath("$.routes[2].costOfRoute", is(3803)))
                .andExpect(jsonPath("$.routes[2].durationOfRoute", is(22)));
    }


    @Test
    public void SearchParams4_SearchRoute_SearchResult() throws Exception {
        // Act
        ResultActions result = mockMvc.perform(get("/api/v1/routes")
                .param(JsonUtil.PARAM_FROM, "Liseberg")
                .param(JsonUtil.PARAM_TO, "Orlando")
                .param(JsonUtil.PARAM_TRANSPORT_TYPES, "Road")
                .param(JsonUtil.PARAM_TRANSPORT_TYPES, "Ocean")
                .param(JsonUtil.PARAM_CONTAINER, "20")
                .contentType(MediaType.APPLICATION_JSON));

        // Assert
        result.andExpect(status().isOk())
                .andExpect(content().json(JsonResponses.JSON_RESPONSE_4))
                .andExpect(jsonPath("$.routes[0].costOfRoute", is(2323)))
                .andExpect(jsonPath("$.routes[0].durationOfRoute", is(23)));

    }

    @Test
    public void SearchParams5_SearchRoute_SearchResult() throws Exception {
        // Act
        ResultActions result = mockMvc.perform(get("/api/v1/routes")
                .param(JsonUtil.PARAM_FROM, "Vasastan")
                .param(JsonUtil.PARAM_TO, "Maitland")
                .param(JsonUtil.PARAM_TRANSPORT_TYPES, "Road")
                .param(JsonUtil.PARAM_TRANSPORT_TYPES, "Ocean")
                .param(JsonUtil.PARAM_TRANSPORT_TYPES, "Air")
                .param(JsonUtil.PARAM_CONTAINER, "20")
                .contentType(MediaType.APPLICATION_JSON));

        // Assert
        result.andExpect(status().isOk())
                .andExpect(content().json(JsonResponses.JSON_RESPONSE_5))
                .andExpect(jsonPath("$.routes[0].costOfRoute", is(2803)))
                .andExpect(jsonPath("$.routes[0].durationOfRoute", is(24)))
                .andExpect(jsonPath("$.routes[1].costOfRoute", is(4373)))
                .andExpect(jsonPath("$.routes[1].durationOfRoute", is(24)));

    }

    @Test
    public void MissingParam_SearchRoute_ReturnErrorMsg() throws Exception {
        // Act
        ResultActions result = mockMvc.perform(get("/api/v1/routes")
                .param(JsonUtil.PARAM_FROM, "Vasastan")
                .param(JsonUtil.PARAM_TO, "Liseberg")
                .contentType(MediaType.APPLICATION_JSON));

        // Assert
        result.andExpect(status().isBadRequest());
    }
}
