package de.springboot.service;

import com.google.common.collect.ImmutableList;
import de.springboot.model.SiteDto;
import de.springboot.model.StackoverflowWebsite;
import de.springboot.persistence.StackoverflowWebsiteRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

@Service
public class StackoverflowService {
    @Autowired
    private StackExchangeClient stackExchangeClient;

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

//    public List<StackoverflowWebsite> findAll() {
//        return repository.findAll();
//    }

    public List<StackoverflowWebsite> findAll() {
        return stackExchangeClient.getSites().stream()
                .map(this::toStackoverflowWebsite)
                .filter(this::ignoreMeta)
                .collect(collectingAndThen(toList(), ImmutableList::copyOf));
    }

    private boolean ignoreMeta(@NonNull StackoverflowWebsite website) {
        return !website.getId().contains("meta");
    }

    private StackoverflowWebsite toStackoverflowWebsite(@NonNull SiteDto input) {
        return new StackoverflowWebsite(
                input.getSite_url().substring("http://".length()+1, input.getSite_url().length() - ".com".length()),
                input.getSite_url(),
                input.getFavicon_url(),
                input.getName(),
                input.getAudience());
    }
}
