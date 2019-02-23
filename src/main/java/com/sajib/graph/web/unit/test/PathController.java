package com.sajib.graph.web.unit.test;

import com.sajib.graph.entity.EntityBuilder;
import com.sajib.graph.entity.Path;
import com.sajib.graph.exception.ResourceNotFoundException;
import com.sajib.graph.service.PathService;
import com.sajib.graph.web.model.DtoBuilder;
import com.sajib.graph.web.model.PathDto;
import com.sajib.graph.web.validator.PathDtoValidator;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by sajib on 2/19/19.
 */
@RestController
@RequestMapping("/api/v1/paths")
public class PathController {

    @InitBinder("pathDto")
    protected void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.setValidator(new PathDtoValidator());
    }

    @Autowired
    EntityBuilder entityBuilder;

    @Autowired
    private PathService pathService;

    @ApiOperation(value = "Returns list of all paths")
    @GetMapping()
    public List<PathDto> getAllPaths() {
        List<Path> pathList = pathService.getAllPaths();
        List<PathDto> pathDtos = pathList.stream()
                .map(DtoBuilder::buildPathDto)
                .collect(Collectors.toList());
        return pathDtos;
    }

    @ApiOperation(value = "Returns a path by id")
    @GetMapping("/{id}")
    public ResponseEntity<PathDto> getOnePath(@PathVariable(value = "id") Integer id)
            throws ResourceNotFoundException {
        Path path = pathService.getPath(id);
        if (path == null) {
            return ResponseEntity.notFound().build();
        }
        PathDto pathDto = DtoBuilder.buildPathDto(path);
        return ResponseEntity.ok().body(pathDto);
    }

    @ApiOperation(value = "Stores a new path")
    @PostMapping()
    public ResponseEntity<PathDto> addPath(@Valid @RequestBody PathDto pathDto) {
        Path path = entityBuilder.buildPathEntity(pathDto);
        Path createdPath = pathService.saveOrUpdatePath(path);
        if (createdPath == null) {
            return ResponseEntity.notFound().build();
        }
        PathDto createdPathDto = DtoBuilder.buildPathDto(createdPath);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPathDto);
    }

    @ApiOperation(value = "Updates an existing path")
    @PutMapping("/{id}")
    public ResponseEntity<PathDto> updatePath(@PathVariable(value = "id") Integer id,
                                              @Valid @RequestBody PathDto pathDtoToUpdate)
            throws ResourceNotFoundException {
        Path pathEntity = pathService.getPath(id);
        if (pathEntity == null) {
            return ResponseEntity.notFound().build();
        }
        Path pathEntityToUpdate = entityBuilder.buildUpdatedPathEntity(pathEntity, pathDtoToUpdate);
        Path updatedPath = pathService.saveOrUpdatePath(pathEntityToUpdate);
        PathDto updatedPathDto = DtoBuilder.buildPathDto(updatedPath);

        return ResponseEntity.ok().body(updatedPathDto);
    }

}
