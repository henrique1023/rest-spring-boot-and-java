package br.com.erudio.controllers;

import br.com.erudio.data.vo.v1.PersonVO;
import br.com.erudio.services.PersonServices;
import br.com.erudio.util.MediaType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin
@RestController
@RequestMapping("/api/person/v1")
@Tag(name = "People", description = "Endpoint for managing people")
public class PersonController {

    @Autowired
    private PersonServices service;

    @GetMapping(produces = {MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.APPLICATION_YML})
    @Operation(summary = "finds all people", description =  "finds all people",
            tags = {"People"},
            responses = {
            @ApiResponse(description = "Sucess", responseCode = "200",
                    content = {
                        @Content(
                                mediaType = "application/json",
                                array = @ArraySchema(schema = @Schema(implementation = PersonVO.class))
                        )
                    }),
            @ApiResponse(description = "Bad request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public List<PersonVO> findByAll() {
        return service.findByAll();
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping(value = "/{id}",
            produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,MediaType.APPLICATION_YML})
    @Operation(summary = "finds a person", description =  "finds a person",
            tags = {"People"},
            responses = {
                    @ApiResponse(description = "Sucess", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = PersonVO.class))),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public PersonVO findById(@PathVariable(value = "id") Long id) throws Exception {
        return service.findById(id);
    }

    @CrossOrigin(origins = {"http://localhost:8080", "https://localhost:8080"})
    @PostMapping(produces = {MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.APPLICATION_YML},
            consumes = {MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.APPLICATION_YML})
    @Operation(summary = "Create a person", description =  "Create a person",
            tags = {"People"},
            responses = {
                    @ApiResponse(description = "Sucess", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = PersonVO.class))),
                    @ApiResponse(description = "Bad request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public PersonVO create(@RequestBody PersonVO person) throws Exception {
        return service.create(person);
    }

    @PutMapping(produces = {MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.APPLICATION_YML},
            consumes = {MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.APPLICATION_YML})
    @Operation(summary = "Update a person", description =  "Update a person",
            tags = {"People"},
            responses = {
                    @ApiResponse(description = "Updated", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = PersonVO.class))),
                    @ApiResponse(description = "Bad request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public PersonVO update(@RequestBody PersonVO person) throws Exception {
        return service.update(person);
    }

    @PatchMapping(value = "/{id}",
            produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,MediaType.APPLICATION_YML})
    @Operation(summary = "disable a specific person by your ID",
            description =  "disable a specific person by your ID",
            tags = {"People"},
            responses = {
                    @ApiResponse(description = "Sucess", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = PersonVO.class))),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public PersonVO disablePerson(@PathVariable(value = "id") Long id) throws Exception {
        return service.disablePerson(id);
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Delete a person", description =  "Delete a person",
            tags = {"People"},
            responses = {
                    @ApiResponse(description = "No Content", responseCode = "204",content = @Content()),
                    @ApiResponse(description = "Bad request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id){
        service.delete(id);

        return ResponseEntity.noContent().build();
    }


}
