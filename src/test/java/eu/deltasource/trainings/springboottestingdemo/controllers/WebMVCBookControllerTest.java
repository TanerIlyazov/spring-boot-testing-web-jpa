package eu.deltasource.trainings.springboottestingdemo.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import eu.deltasource.trainings.springboottestingdemo.SpringBootTestingDemoApplication;
import eu.deltasource.trainings.springboottestingdemo.model.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;

import static eu.deltasource.trainings.springboottestingdemo.model.Book.newBook;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(SpringBootTestingDemoApplication.class)
class WebMVCBookControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void getBookByName_returnsJsonAndMapToBook() throws Exception {
        //given
        Book expectedBook = newBook("Code", "Educational");

        //when
        RequestBuilder request = get("/books?name=Code");
        MvcResult result = mvc.perform(request).andReturn();
        Book actualBook = new ObjectMapper().readValue(result.getResponse().getContentAsString(), Book.class);

        //then
        assertEquals(expectedBook, actualBook);
    }

    @Test
    void getBookByName_v2() throws Exception {
        //given
        Book expectedBook = newBook("Code", "Educational");

        //when
        RequestBuilder request = get("/books?name=Code");
        MvcResult result = mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Code"))
                .andReturn();

        Book actualBook = new ObjectMapper().readValue(result.getResponse().getContentAsString(), Book.class);

        //then
        assertEquals(expectedBook, actualBook);
    }

    @Test
    void getBookName__returnsBookName() throws Exception {
        //given
        Book expectedBook = newBook("Clean Code", "Educational");

        //when
        mvc.perform(get("/books?pattern=Code"))
                .andExpect(content().string("Code"));
    }

}