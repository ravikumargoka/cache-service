package com.ravi.cache.statistics.home;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/")
public class RootResource {
    @GetMapping
    public RedirectView swaggerUi() {
        return new RedirectView("/api/swagger-ui.html");
    }
}
