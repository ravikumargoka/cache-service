package com.ravi.cache.cachestatistics.home;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/")
public class RootResource {
    @GetMapping
    public RedirectView swaggerUi() {
        return new RedirectView("/swagger-ui.html");
    }
}
