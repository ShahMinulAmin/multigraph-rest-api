package com.sajib.graph.web.controller;

import com.sajib.graph.Application;
import org.junit.Before;
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

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by sajib on 2/18/19.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = Application.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application.properties")
public class CityControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setup() throws Exception {



    }

    @Test
    public void GetRequest_GetAllCities_ListAllCities() throws Exception {
        ResultActions result = mockMvc.perform(get("/api/v1/cities")
                .contentType(MediaType.APPLICATION_JSON));
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].name", is("Vasastan")))
                .andExpect(jsonPath("$[1].name", is("Liseberg")))
                .andExpect(jsonPath("$[2].name", is("Georgia")));
    }

    @Test
    public void CityId_GetCity_ReturnCity() throws Exception {
        ResultActions result = mockMvc.perform(get("/api/v1/cities/{id}", "1").
                contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Vasastan")));
    }
}
