package de.springboot.web;

import com.google.common.collect.ImmutableList;
import de.springboot.Application;
import de.springboot.model.StackoverflowWebsite;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
@WebIntegrationTest
public class StackoverflowControllerIT {
    RestTemplate restTemplate = new TestRestTemplate();

    @Autowired
    private MongoTemplate mongoTemplate;

    @Before
    public void before() {
        mongoTemplate.dropCollection(StackoverflowWebsite.class);
        mongoTemplate.save(new StackoverflowWebsite("website1", "", "", "", ""));
        mongoTemplate.save(new StackoverflowWebsite("website2", "", "", "", ""));
    }

    @Test
    public void testGetListOfProviders() throws Exception {
        //test
        ResponseEntity<List<StackoverflowWebsite>> responseEntity =
                restTemplate.exchange("http://localhost:8090/api/stackoverflow",
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<StackoverflowWebsite>>() {
                        });
        List<StackoverflowWebsite> actualList = responseEntity.getBody();
        //validate
        assertThat(actualList.size(), is(2));
        List<String> actualIds = actualList.stream()
                .map(StackoverflowWebsite -> StackoverflowWebsite.getId())
                .collect(collectingAndThen(toList(), ImmutableList::copyOf));
        assertThat(actualIds, containsInAnyOrder("website1", "website2"));
    }
}