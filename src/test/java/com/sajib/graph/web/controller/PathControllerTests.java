package com.sajib.graph.web.controller;

import com.sajib.graph.Application;
import com.sajib.graph.entity.City;
import com.sajib.graph.entity.Path;
import com.sajib.graph.service.PathService;
import com.sajib.graph.web.model.PathDto;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by sajib on 2/19/19.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = Application.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application.properties")
public class PathControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PathService pathService;

    @Before
    public void setup() throws Exception {
        City vasastan = new City(1, "Vasastan", 59.3442327, 18.0456211);
        City liseberg = new City(2, "Liseberg", 57.6952191, 11.9924641);
        City oakland = new City(3, "Oakland", 26.1723065, -80.1319893);

        Path path1 = new Path(1, vasastan, liseberg, 20, "Road", 1, 480);
        Path path2 = new Path(2, vasastan, liseberg, 20, "Air", 1, 2050);
        Path path3 = new Path(3, vasastan, liseberg, 40, "Road", 1, 680);
        Path path4 = new Path(4, vasastan, liseberg, 40, "Air", 1, 3050);
        List<Path> allPaths = Arrays.asList(path1, path2, path3, path4);

        Path path5 = new Path(5, liseberg, oakland, 20, "Ocean", 22, 1673);
        Path updatedPath1 = new Path(1, vasastan, liseberg, 20, "Road", 2, 280);

        when(pathService.getAllPaths()).thenReturn(allPaths);
        when(pathService.getPath(1)).thenReturn(path1);
        when(pathService.saveOrUpdatePath(any(Path.class))).thenReturn(path5);
        when(pathService.saveOrUpdatePath(path1)).thenReturn(updatedPath1);
    }

    @Test
    public void GetRequest_GetAllPaths_ListAllPaths() throws Exception {
        ResultActions result = mockMvc.perform(get("/api/v1/paths")
                .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(4)))
                .andExpect(jsonPath("$[0].fromCity", is("Vasastan")))
                .andExpect(jsonPath("$[0].toCity", is("Liseberg")))
                .andExpect(jsonPath("$[1].transportType", is("Air")))
                .andExpect(jsonPath("$[2].duration", is(1)))
                .andExpect(jsonPath("$[3].cost", is(3050)));
    }

    @Test
    public void PathId_GetPath_ReturnPath() throws Exception {
        ResultActions result = mockMvc.perform(get("/api/v1/paths/{id}", "1").
                contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.fromCity", is("Vasastan")))
                .andExpect(jsonPath("$.toCity", is("Liseberg")))
                .andExpect(jsonPath("$.transportType", is("Road")))
                .andExpect(jsonPath("$.duration", is(1)))
                .andExpect(jsonPath("$.cost", is(480)));
    }

    @Test
    public void PathToAdd_AddPath_ReturnAddedPath() throws Exception {
        PathDto pathToAdd = new PathDto(5, "Liseberg", "Oakland", 20, "Ocean", 22, 1673);
        ResultActions result = mockMvc.perform(post("/api/v1/paths")
                .content(JsonUtil.toJson(pathToAdd))
                .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isCreated())
                .andExpect(jsonPath("$.fromCity", is("Liseberg")))
                .andExpect(jsonPath("$.toCity", is("Oakland")))
                .andExpect(jsonPath("$.transportType", is("Ocean")))
                .andExpect(jsonPath("$.duration", is(22)))
                .andExpect(jsonPath("$.cost", is(1673)));
    }

    @Test
    public void PathToUpdate_UpdatePath_ReturnUpdatedPath() throws Exception {
        PathDto pathToUpdate = new PathDto(1, "Vasastan", "Liseberg", 20, "Road", 2, 280);

        ResultActions result = mockMvc.perform(put("/api/v1/paths/{id}", "1")
                .content(JsonUtil.toJson(pathToUpdate))
                .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.fromCity", is("Vasastan")))
                .andExpect(jsonPath("$.toCity", is("Liseberg")))
                .andExpect(jsonPath("$.transportType", is("Road")))
                .andExpect(jsonPath("$.duration", is(2)))
                .andExpect(jsonPath("$.cost", is(280)));
    }

}



