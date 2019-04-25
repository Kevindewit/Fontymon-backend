package io.kevindewit.service.entity.controller;

import io.kevindewit.service.entity.config.jwt.InvalidJwtAuthenticationException;
import io.kevindewit.service.entity.model.Entity_Type;
import io.kevindewit.service.entity.service.TypeService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException;

import javax.persistence.EntityNotFoundException;
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
            value = "/type",
            method = RequestMethod.GET
    )
    public ResponseEntity<?> getTypeByName(@RequestParam(value = "name") String name) {
        logger.debug("Checking if Entity_Type exists ...");
        if (!typeService.existsEntity_TypeByName(name)) {
            logger.debug("Entity_Type not found, returning BAD_REQUEST ...");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
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
            value = "/types",
            method = RequestMethod.GET
    )
    public ResponseEntity<?> getTypes() {
        logger.debug("Receiving a collection of entity_Types ...");
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
            value = "/type",
            method = RequestMethod.PUT
    )
    @PreAuthorize("hasRole('ROLE_SYSTEM')")
    public ResponseEntity<?> putType(Entity_Type type) {
        logger.debug("Checking if Entity_Type exists ...");
        if(!typeService.existsEntity_TypeByName(type.getName())) {
            logger.debug("saving new Entity_Type ...");
            typeService.save(type);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        else {
            logger.debug("updating existing Entity_Type ...");
            typeService.save(type);
            return new ResponseEntity<>(HttpStatus.OK);
        }
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
            @ApiResponse(code = 403, message = "You are forbidden from accessing the resource"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    @RequestMapping(
            value = "/types",
            method = RequestMethod.PUT
    )
    @PreAuthorize("hasRole('ROLE_SYSTEM')")
    public ResponseEntity<?> putTypes(List<Entity_Type> types) {
        for(Entity_Type type : types) {
            if (!typeService.existsEntity_TypeByName(type.getName())) {
                logger.debug("saving new Entity_Type ...");
                typeService.save(type);
            }
            else {
                logger.debug("updating existing Entity_Type ...");
                typeService.save(type);
            }
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handleEntityNotFoundException(EntityNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidJwtAuthenticationException.class)
    public ResponseEntity<?> handleEntityNotFoundException(InvalidJwtAuthenticationException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
    }
}
