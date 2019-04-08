package io.kevindewit.service.entity.controller;

import io.kevindewit.service.entity.models.Entity_Type;
import io.kevindewit.service.entity.service.TypeService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TypeController {
    private Logger logger = LoggerFactory.getLogger(TypeController.class);

    private final TypeService typeService;

    @Autowired
    public TypeController(TypeService typeService) {
        this.typeService = typeService;
    }

    @ApiOperation(
            value = "Get a specific entity by name.",
            tags = {
                    "Guest",
                    "User",
                    "System"
            },
            response = Entity_Type.class
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successfully retrieved Type", response = Entity_Type.class),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "You are forbidden from accessing the resource"),
            @ApiResponse(code = 404, message = "not found")
    })
    @RequestMapping(
            value = "/Type",
            method = RequestMethod.GET
    )
    public ResponseEntity<?> getTypeByName(@RequestParam(value = "name") String name) {
        logger.debug("Checking if Entity_Type exists ...");
        if (!existTypeByName(name)) {
            logger.debug("Entity_Type not found, returning NOT_FOUND ...");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        logger.debug("Entity_Type found, returning Entity_Type ...");
        Entity_Type entityType = typeService.findEntity_TypeByName(name);
        return new ResponseEntity<>(entityType, HttpStatus.OK);
    }

    @ApiOperation(
            value = "Get all types",
            tags = {
                    "Guest",
                    "User",
                    "System"
            }
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successfully retrieved a collection of Types"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "You are forbidden from accessing the resource"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    @RequestMapping(
            value = "/getTypes",
            method = RequestMethod.GET
    )
    public ResponseEntity<?> getTypes() {
        logger.debug("Receiving all entityTypes ...");
        List<Entity_Type> entityTypes = typeService.findAll();

        logger.debug("Checking for empty collection ...");
        if (entityTypes.isEmpty()) {

            logger.debug("Empty collection found, returning NOT_FOUND ...");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        logger.debug("Collection created, returning Entity_Type collection ...");
        return new ResponseEntity<>(entityTypes, HttpStatus.OK);
    }

    @ApiOperation(
            value = "Add a Type",
            tags = {
                    "System"
            }
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 201, message = "Successfully created a collection of Types"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "You are forbidden from accessing the resource"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    @RequestMapping(
            value = "/putType",
            method = RequestMethod.PUT
    )
    public ResponseEntity<?> putType(Entity_Type type) {
        logger.debug("Checking if Entity_Type exists ...");
        if(existTypeByName(type.getName())) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        logger.debug("saving Entity_Type ...");
        saveType(type);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(
            value = "Add a collection of Types",
            tags = {
                    "System"
            }
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 201, message = "Successfully created / added a collection of Types"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "You are forbidden fsrom accessing the resource"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    @RequestMapping(
            value = "/putTypes",
            method = RequestMethod.PUT
    )
    public ResponseEntity<?> putTypes(List<Entity_Type> types) {
        for(Entity_Type entityType : types) {
            logger.debug("saving Entity_Type ...");
            saveType(entityType);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    private boolean existTypeByName(String name) {
        return typeService.existsEntity_TypeByName(name);
    }

    private void saveType(Entity_Type entityType){
        typeService.save(entityType);
    }
}
