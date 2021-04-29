package eu.deltasource.trainings.springboottestingdemo.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.deltasource.trainings.springboottestingdemo.model.Book;
import eu.deltasource.trainings.springboottestingdemo.repositories.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;

import java.util.Arrays;
import java.util.List;

import static eu.deltasource.trainings.springboottestingdemo.model.Book.newBook;
import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Slf4j
@WebMvcTest(RestBookController.class)
class WebMVCBookControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BookRepository bookRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void mockRepository() {
        given(bookRepository.getBooks()).willReturn(asList(
                newBook("Clean Code", "Educational"),
                newBook("Code", "Educational"),
                newBook("Linux Bible", "Religion")));
    }

    @Test
    void getAllBooks__statusOk__threeBooksReturned() throws Exception {
        //given
        List<Book> expectedBooks = asList(
                newBook("Clean Code", "Educational"),
                newBook("Code", "Educational"),
                newBook("Linux Bible", "Religion"));

        //when
        String jsonString = mvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();


        //then
        List<Book> books = objectMapper.readValue(jsonString, new TypeReference<List<Book>>() {});
        assertNotNull(books);
        assertEquals(books.size(), expectedBooks.size());
        assertTrue(expectedBooks.containsAll(books));
    }

    @Test
    void getBookByBookName_statusOk_CodeBookReturned() throws Exception {
        //given
        Book expectedBook = newBook("Code", "Educational");

        //when
        RequestBuilder request = get("/books?bookName=Code");
        MvcResult result = mvc.perform(request).andReturn();
        Book actualBook = objectMapper.readValue(result.getResponse().getContentAsString(), Book.class);

        //then
        assertNotNull(actualBook);
        assertEquals(expectedBook, actualBook);
    }

    @Test
    void getBookByBookName_expectationSetByMockWebMVC() throws Exception {
        //given

        //when
        RequestBuilder request = get("/books?bookName=Code");

        //then
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Code"))
                .andExpect(jsonPath("$.tags").value("Educational"));
    }

    @Test
    void getBookName__returnsBookName() throws Exception {
        //given
        Book expectedBook = newBook("Clean Code", "Educational");

        //when
        mvc.perform(get("/books?pattern=ux"))
                .andExpect(content().string("Linux Bible"));
    }

    @Test
    void getBookByPattern__statusIsBadRequest__emptyPattern() throws Exception {
        //given
        Book expectedBook = newBook("Clean Code", "Educational");

        //when
        mvc.perform(get("/books?pattern="))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void getBookByPattern__statusIsNotFound__bookNotFoundInLibrary() throws Exception {
        //given
        Book expectedBook = newBook("Clean Code", "Educational");

        //when
        mvc.perform(get("/books?bookName=Bad Code"))
                .andExpect(status().is(404));
    }

}