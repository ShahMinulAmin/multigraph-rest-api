package com.sajib.graph.web.unit.test;

import com.sajib.graph.Application;
import com.sajib.graph.entity.City;
import com.sajib.graph.service.CityService;
import com.sajib.graph.web.model.CityDto;
import com.sajib.graph.web.util.JsonUtil;
import org.junit.Before;
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

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
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

    @MockBean
    private CityService cityService;

    @Before
    public void setup() throws Exception {
        City vasastan = new City(1, "Vasastan", 59.3442327, 18.0456211);
        City liseberg = new City(2, "Liseberg", 57.6952191, 11.9924641);
        City georgia = new City(3, "Georgia", 32.1656221, -82.9000751);
        List<City> allCities = Arrays.asList(vasastan, liseberg, georgia);

        City gouda = new City(4, "Gouda", 52.0115205, 4.7104633);
        City vasastanUpdated = new City(1, "Vasastan, Stockholm", 59.3442327, 18.0456211);

        when(cityService.getAllCities()).thenReturn(allCities);
        when(cityService.getCity(1)).thenReturn(vasastan);
        when(cityService.saveOrUpdateCity(any(City.class))).thenReturn(gouda);
        when(cityService.saveOrUpdateCity(vasastan)).thenReturn(vasastanUpdated);
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

    @Test
    public void CityToAdd_AddCity_ReturnAddedCity() throws Exception {
        CityDto cityToAdd = new CityDto(4, "Gouda", 52.0115205, 4.7104633);

        ResultActions result = mockMvc.perform(post("/api/v1/cities")
                .content(JsonUtil.toJson(cityToAdd))
                .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is("Gouda")));
    }

    @Test
    public void CityToUpdate_UpdateCity_ReturnUpdatedCity() throws Exception {
        CityDto cityToUpdate = new CityDto(1, "Vasastan, Stockholm", 59.3442327, 18.0456211);

        ResultActions result = mockMvc.perform(put("/api/v1/cities/{id}", "1")
                .content(JsonUtil.toJson(cityToUpdate))
                .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Vasastan, Stockholm")));
    }

    @Test
    public void WrongCityId_GetCity_ReturnNotFound() throws Exception {
        ResultActions result = mockMvc.perform(get("/api/v1/cities/{id}", "130")
                .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isNotFound());
    }
}
