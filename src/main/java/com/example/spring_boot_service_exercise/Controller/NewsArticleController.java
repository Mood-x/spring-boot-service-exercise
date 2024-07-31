package com.example.spring_boot_service_exercise.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring_boot_service_exercise.API.ApiResponse;
import com.example.spring_boot_service_exercise.Model.NewsArticle;
import com.example.spring_boot_service_exercise.Service.NewsArticleService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/news")
@RequiredArgsConstructor
public class NewsArticleController {
    private final NewsArticleService newsService; 


    @GetMapping()
    public ResponseEntity getNewsArticles(){
        List<NewsArticle> newsArticle = newsService.getNewsArticles(); 
        
        if(newsArticle.isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("News Article is empty!")); 
        }
        
        return ResponseEntity.status(200).body(newsArticle);
    }

    @PostMapping("/add")
    public ResponseEntity addNewsArticle(@Valid @RequestBody NewsArticle newsArticle, Errors err){
        if(err.hasErrors()){
            String message = err.getFieldError().getDefaultMessage(); 
            return ResponseEntity.status(400).body(message); 
        }

        newsService.addNewsArticle(newsArticle);
        return ResponseEntity.status(200).body(new ApiResponse( newsArticle.getTitle() + " Added")); 
    }

    @PutMapping("/{id}/update")
    public ResponseEntity updateNewsArticle(@PathVariable int id, @Valid @RequestBody NewsArticle newsArticle, Errors err){
        if(err.hasErrors()){
            String message = err.getFieldError().getDefaultMessage(); 
            return ResponseEntity.status(400).body(message); 
        }

        boolean isFound =  newsService.updateNewsArticle(id, newsArticle);
        if(isFound){
            return ResponseEntity.status(200).body(new ApiResponse("News article with this id (" + id + ") updated")); 
        }

        return ResponseEntity.status(400).body(new ApiResponse("This id (" + id + ") not found"));
    }
    
    @DeleteMapping("/{id}/delete")
    public ResponseEntity deleteNewsArticle(@PathVariable int id){

        boolean isFound =  newsService.deleteNewsArticle(id);
        if(isFound){
            return ResponseEntity.status(200).body(new ApiResponse("News article with this id (" + id + ") deleted")); 
        }
        return ResponseEntity.status(400).body(new ApiResponse("This id (" + id + ") not found"));
    }

    @PutMapping("/{id}/publish")
    public ResponseEntity publishNewsArticle(@PathVariable int id){
        boolean isFound =  newsService.publishNewsArticle(id);
        if(isFound){
            return ResponseEntity.status(200).body(new ApiResponse("News article with this id (" + id + ") published")); 
        }

        return ResponseEntity.status(400).body(new ApiResponse("This id (" + id + ") not found"));
    }


    @GetMapping("/newsPublished")
    public ResponseEntity getAllPublishedArticles(){
        List<NewsArticle> newspublished = newsService.getAllPublishedArticles(); 
        
        if(newspublished.isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("News Article published is empty!")); 
        }
        
        return ResponseEntity.status(200).body(newspublished);
    }

    @GetMapping("/search/{category}")
    public ResponseEntity searchByCategory(@PathVariable String category){
        List<NewsArticle> categories = newsService.searchByCategory(category); 
        
        if(categories.isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("News Article whit this " + category + " not published")); 
        }
        
        return ResponseEntity.status(200).body(categories);
    }
}
