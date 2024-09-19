package com.ok.dvweb.service;

import com.ok.dvweb.dto.NewsDTO;
import com.ok.dvweb.entity.News;
import com.ok.dvweb.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsService {

    protected final NewsRepository repository;

    public void saveNews(NewsDTO dto){
        repository.save(mapToEntity(dto));
    }

    public List<News> findAllNews(){
        return repository.findAll();
    }

    private News mapToEntity(NewsDTO dto){
        News entity = new News();
        entity.setTittle(dto.getTittle());
        entity.setDescription(dto.getDescription());
        entity.setImage(dto.getImage());
        return entity;
    }
}
