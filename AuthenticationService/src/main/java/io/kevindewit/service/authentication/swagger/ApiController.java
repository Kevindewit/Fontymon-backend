package io.kevindewit.service.authentication.swagger;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@Controller
public class ApiController {

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @RequestMapping(value = "/api")
    public String home() {
        return "redirect:/swagger-ui.html";
    }
}
