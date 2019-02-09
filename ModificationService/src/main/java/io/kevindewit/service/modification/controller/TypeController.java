package io.kevindewit.service.modification.controller;

import io.kevindewit.service.modification.models.Modification_Status;
import io.kevindewit.service.modification.models.Modification_Type;
import io.kevindewit.service.modification.service.TypeService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
            value = "Add a modification of a Type",
            tags = {
                    "Admin",
                    "System"
            },
            response = Modification_Type.class
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successfully retrieved Types", response = Modification_Type.class),
            @ApiResponse(code = 201, message = "Successfully created  a Type"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "You are forbidden from accessing the resource"),
            @ApiResponse(code = 404, message = "not found")
    })
    @RequestMapping(
            value = "/putType",
            method = RequestMethod.PUT
    )
    public ResponseEntity<?> putModification(Modification_Type type) {

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(
            value = "Add modifications of a collection of Types",
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
            value = "/putTypes",
            method = RequestMethod.PUT
    )
    public ResponseEntity<?> putTypes(List<Modification_Type> types) {
        for(Modification_Type type : types) {
            logger.debug("saving Type ...");
            typeService.save(type);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(
            value = "Add modifications of a collection of Types",
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
            value = "/getTypesByStatus",
            method = RequestMethod.GET
    )
    public ResponseEntity<?> putTypes(@RequestParam(value = "status") @ApiParam(allowableValues = "CREATE, UPDATE") Modification_Status status) {
        logger.debug("Receiving all Prototypes ...");
        List<Modification_Type> types = typeService.findAllByStatusOrderByName(status);

        logger.debug("Checking for empty collection ...");
        if (types.isEmpty()) {

            logger.debug("Empty collection found, returning NOT_FOUND ...");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        logger.debug("Collection created, returning Type collection ...");
        return new ResponseEntity<>(types, HttpStatus.OK);
    }

    @ApiOperation(
            value = "Add modifications of a collection of Types",
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
            value = "/getTypesByStatusAndName",
            method = RequestMethod.GET
    )
    public ResponseEntity<?> putTypes(@RequestParam(value = "status") @ApiParam(allowableValues = "CREATE, UPDATE") Modification_Status status, @RequestParam(value = "name") String name) {
        logger.debug("Receiving all Prototypes ...");
        List<Modification_Type> types = typeService.findAllByStatusOrderByName(status);

        logger.debug("Checking for empty collection ...");
        if (types.isEmpty()) {

            logger.debug("Empty collection found, returning NOT_FOUND ...");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        logger.debug("Collection created, returning Type collection ...");
        return new ResponseEntity<>(types, HttpStatus.OK);
    }

}
