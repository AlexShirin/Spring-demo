package de.springboot.web;

import com.google.common.collect.ImmutableList;
import de.springboot.model.StackoverflowWebsite;
import de.springboot.service.StackoverflowService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StackoverflowControllerTest {
    @Mock
    private StackoverflowService stackoverflowService;
    @InjectMocks
    StackoverflowController sut;

    @Test
    public void getListOfProviders() {
        //prepare
        when(stackoverflowService.findAll()).thenReturn(ImmutableList.of());
        //testing
        List<StackoverflowWebsite> listOfProviders = sut.getListOfProviders();
        //validate
        verify(stackoverflowService).findAll();
    }
}