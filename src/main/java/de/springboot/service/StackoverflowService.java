package de.springboot.service;

import de.springboot.model.StackoverflowWebsite;
import de.springboot.persistence.StackoverflowWebsiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class StackoverflowService {
    @Autowired
    private StackoverflowWebsiteRepository repository;

    private static List<StackoverflowWebsite> items = new ArrayList<>();

    // Add items to mongo db - Run once!
//    static {
//        items.add(new StackoverflowWebsite("1", "http", "icon", "title", "desc"));
//        items.add(new StackoverflowWebsite("2", "http2", "icon2", "title2", "desc2"));
//        items.add(new StackoverflowWebsite("3", "http3", "icon3", "title3", "desc3"));
//    }
//    @PostConstruct
//    public void init() {
//        repository.save(items);
//    }

    public List<StackoverflowWebsite> findAll() {
        return repository.findAll();
    }
}
