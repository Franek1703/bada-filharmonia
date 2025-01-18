package com.bada_project.filharmonia;
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
        registry.addViewController("/user/login").setViewName("user_login");
        registry.addViewController("/admin").setViewName("admin");
        registry.addViewController("/reservation").setViewName("reservation");
        registry.addViewController("/reservation/event").setViewName("event_details");
        registry.addViewController("/admin/logout").setViewName("main");
        registry.addViewController("/user/logout/main").setViewName("main");
        registry.addViewController("/user/logout/reservation").setViewName("reservation");
        registry.addViewController("/user/logout/event_details").setViewName("event_details");
        registry.addViewController("/reservation/success").setViewName("reservation_success");
        registry.addViewController("/admin/event/add").setViewName("admin_add_event");
        registry.addViewController("/user").setViewName("user");
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