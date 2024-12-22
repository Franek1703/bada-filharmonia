package com.bada_project.filharmonia;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
    import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
@Configuration
public class AppController implements WebMvcConfigurer {
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/").setViewName("main");
        registry.addViewController("/main").setViewName("main");
//        registry.addViewController("/login").setViewName("login");
    }
}

//@Controller
//public class AppController {
//    @GetMapping({"/", "/index"})
//    public String index() {
//        return "index";
//    }
//
//    @GetMapping("/main")
//    public String main() {
//        return "main";
//    }
//}