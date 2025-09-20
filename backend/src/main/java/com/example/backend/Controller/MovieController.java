package com.example.backend.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api")
public class MovieController {
    public List topMovies = new ArrayList<String>();
    MovieController(){

        topMovies.add("Movie 1");
        topMovies.add("Movie 2");
        topMovies.add("Movie 3");
        topMovies.add("Movie 4");
        topMovies.add("Movie 5");
        topMovies.add("Movie 6");
        topMovies.add("Movie 7");
        topMovies.add("Movie 8");
        topMovies.add("Movie 9");
        topMovies.add("Movie 10");
    }

    @GetMapping("/topMovies")
    public List getTopMovies(){
        return topMovies;
    }
    
}
