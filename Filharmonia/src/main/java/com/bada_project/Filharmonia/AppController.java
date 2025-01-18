package com.bada_project.Filharmonia;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
    import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
@Configuration
public class AppController implements WebMvcConfigurer {
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("main");
        registry.addViewController("/main").setViewName("main");
        registry.addViewController("/admin/login").setViewName("login");
        registry.addViewController("/admin").setViewName("admin");
        registry.addViewController("/reservation").setViewName("reservation");
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