package com.example.spring_boot_service_exercise.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.spring_boot_service_exercise.Model.NewsArticle;

@Service
public class NewsArticleService {


    List<NewsArticle> newsArticles = new ArrayList<>();

    public List<NewsArticle> getNewsArticles(){
        return newsArticles; 
    }

    public void addNewsArticle(NewsArticle newsArticle){
        newsArticles.add(newsArticle); 
    }

    public boolean updateNewsArticle(int id, NewsArticle newsArticle){
        for(int i = 0; i < newsArticles.size(); i++){
            if(newsArticles.get(i).getId() == id){
                newsArticles.set(i, newsArticle); 
                return true; 
            }
        }
        return false; 
    } 

    public boolean deleteNewsArticle(int id){
        for(int i = 0; i < newsArticles.size(); i++){
            if(newsArticles.get(i).getId() == id){
                newsArticles.remove(i); 
                return true; 
            }
        }
        return false; 
    } 

    public boolean publishNewsArticle(int id){
        for(int i = 0; i < newsArticles.size(); i++){
            if(newsArticles.get(i).getId() == id){
                newsArticles.get(i).setPublished(true); 
                return true; 
            }
        }
        return false; 
    } 
    

    public List<NewsArticle> getAllPublishedArticles(){
        List newspublished = new ArrayList<>(); 
        for(NewsArticle published : newsArticles){
            if(published.isPublished() == true){
                newspublished.add(published); 
            }
        }
        return newspublished; 
    }

    public List<NewsArticle> searchByCategory(String category){
        List categories = new ArrayList<>();

        for(NewsArticle cat : newsArticles){
            if(cat.getCategory().equalsIgnoreCase(category)){
                categories.add(cat); 
            }
        }
        return categories; 
    }
}
