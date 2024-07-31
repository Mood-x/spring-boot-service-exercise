package com.example.spring_boot_service_exercise.Model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NewsArticle {

    @NotNull(message = "id should be not empty!")
    private int id; 
    
    @NotEmpty(message = "Title should be not empty!")
    @Size(min = 5, max = 100, message = "Title min characters 4 and max characters 100.")
    private String title; 

    @NotEmpty(message = "author should be not empty!")
    @Size(min = 4, max = 20, message = "Author min characters 4 and max characters 20.")
    private String author; 

    @NotEmpty(message = "content should be not empty!")
    @Size(min = 10, max = 200, message = "Content min characters 10 and max characters 200.")
    private String content; 

    @NotEmpty(message = "category should be not empty!")
    @Pattern(regexp = "^(politics|sports|technology)$", message = "Category must be either 'politics', or 'sports', or 'technology'.")
    private String category; 

    @NotEmpty(message = "Image url should be not empty!")
    private String imageUrl; 

    private boolean isPublished = false;
    
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate publishedDate;
    

}
