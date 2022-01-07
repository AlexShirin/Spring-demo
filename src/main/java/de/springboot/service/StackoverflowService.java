package de.springboot.service;

import de.springboot.model.StackoverflowWebsite;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StackoverflowService {
    private static List<StackoverflowWebsite> items = new ArrayList<>();
    static {
        items.add(new StackoverflowWebsite("1", "http", "icon", "title", "desc"));
        items.add(new StackoverflowWebsite("2", "http2", "icon2", "title2", "desc2"));
        items.add(new StackoverflowWebsite("3", "http3", "icon3", "title3", "desc3"));
    }

    public List<StackoverflowWebsite> findAll() {
        return items;
    }
}
