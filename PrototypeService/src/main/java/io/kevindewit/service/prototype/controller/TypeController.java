package io.kevindewit.service.prototype.controller;

import io.kevindewit.service.prototype.models.Prototype_Status;
import io.kevindewit.service.prototype.models.Prototype_Type;
import io.kevindewit.service.prototype.service.TypeService;
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
            value = "Get a specific entity by name.",
            tags = {
                    "Admin",
                    "System"
            },
            response = Prototype_Type.class
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successfully retrieved Types", response = Prototype_Type.class),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "You are forbidden from accessing the resource"),
            @ApiResponse(code = 404, message = "not found")
    })
    @RequestMapping(
            value = "/getTypesByStatus",
            method = RequestMethod.GET
    )
    public ResponseEntity<?> getTypesByStatus(@RequestParam(value = "status") @ApiParam(allowableValues = "PROTOTYPE, PUBLISHED") Prototype_Status status) {
        logger.debug("Receiving all Prototypes ...");
        List<Prototype_Type> types = typeService.findAllByStatusOrderByName(status);

        logger.debug("Checking for empty collection ...");
        if (types.isEmpty()) {

            logger.debug("Empty collection found, returning NOT_FOUND ...");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        logger.debug("Collection created, returning Type collection ...");
        return new ResponseEntity<>(types, HttpStatus.OK);
    }

    @ApiOperation(
            value = "Get a specific entity by name.",
            tags = {
                    "Admin",
                    "System"
            },
            response = Prototype_Type.class
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successfully retrieved Type", response = Prototype_Type.class),
            @ApiResponse(code = 201, message = "Successfully created  a Type"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "You are forbidden from accessing the resource"),
            @ApiResponse(code = 404, message = "not found")
    })
    @RequestMapping(
            value = "/publishType",
            method = RequestMethod.PUT
    )
    public ResponseEntity<?> publishType (@RequestParam(value = "type") Prototype_Type type) {
        type.setStatus(Prototype_Status.PUBLISHED);
        typeService.save(type);
        //TODO: send type to EntityService.
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
