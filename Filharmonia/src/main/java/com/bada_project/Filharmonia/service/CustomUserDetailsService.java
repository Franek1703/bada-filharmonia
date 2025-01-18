//package com.bada_project.filharmonia.service;
//
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//public class CustomUserDetailsService implements UserDetailsService {
//
//    @Override
//    public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
//        // TODO: Pobierz użytkownika z bazy danych
//        if (!phoneNumber.equals("123456789")) { // Przykładowy numer
//            throw new UsernameNotFoundException("User not found");
//        }
//        return org.springframework.security.core.userdetails.User.builder()
//                .username("John Doe") // Imię i nazwisko
//                .password("password") // Hasło (niezalecane w plain text)
//                .roles("USER")
//                .build();
//    }
//}
