package in.onetoone.bookmanagementsystem.controller;

import in.onetoone.bookmanagementsystem.Exception.RecordNotFountException;
import in.onetoone.bookmanagementsystem.dto.request.BooksPagedRequest;
import in.onetoone.bookmanagementsystem.dto.request.CreateBookRequest;
import in.onetoone.bookmanagementsystem.dto.request.UpdateBookRequest;
import in.onetoone.bookmanagementsystem.dto.response.ApiResponse;
import in.onetoone.bookmanagementsystem.dto.response.BookPagedListResponse;
import in.onetoone.bookmanagementsystem.dto.response.BookResponse;
import in.onetoone.bookmanagementsystem.enums.StatusEnum;
import in.onetoone.bookmanagementsystem.service.BookService;
import in.onetoone.bookmanagementsystem.service.BookServiceImp;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.validation.ConstraintViolationException;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookRestControllerTest {
    @MockBean
    private BookServiceImp bookServiceImp;

    @Autowired
    private BookRestController bookRestController;

    @Test
    public void testSaveBook() {
        BookServiceImp BookServicemock = Mockito.mock(BookServiceImp.class);
        CreateBookRequest createBookRequest = new CreateBookRequest();
        createBookRequest.setBookTitle("spring");
        createBookRequest.setPrice(200);
        createBookRequest.setPublishedYear(1942);
        createBookRequest.setAuthorFirstName("Kate");
        createBookRequest.setAuthorLastName("Brennan");
        createBookRequest.setAuthorExperience(20);

        BookResponse bookResponse = new BookResponse();
        bookResponse.setBookId(1);
        bookResponse.setBookTitle("spring");
        bookResponse.setPrice(200);
        bookResponse.setAuthorExperience(20);

        Mockito.when(BookServicemock.saveBook(Mockito.any(CreateBookRequest.class))).thenReturn(bookResponse);


        BookRestController bookRestController = new BookRestController(BookServicemock);
        ApiResponse apiResponse = bookRestController.saveBook(createBookRequest);
        assertEquals(201, apiResponse.getStatusCode());
        assertEquals(bookResponse, apiResponse.getData());

    }

    @Test
    public void testSaveBookBookNameNull() {

        CreateBookRequest createBookRequest = new CreateBookRequest();
        createBookRequest.setBookTitle(null);
        createBookRequest.setPrice(200);
        createBookRequest.setPublishedYear(1942);
        createBookRequest.setAuthorFirstName("Kate");
        createBookRequest.setAuthorLastName("Brennan");
        createBookRequest.setAuthorExperience(20);
    }

    @Test
    public void testFetchBookById() {
        Integer id = 1;
        BookServiceImp bookServicemock = Mockito.mock(BookServiceImp.class);
        BookResponse bookResponse = new BookResponse();
        bookResponse.setBookId(1);
        bookResponse.setBookTitle("spring");
        bookResponse.setPrice(200);
        bookResponse.setAuthorExperience(20);

        Mockito.when(bookServicemock.fetchBookById(id)).thenReturn(bookResponse);
        ApiResponse<BookResponse> bookResponseApiResponse = new BookRestController(bookServicemock).fetchBookById(id);
        assertEquals(HttpStatus.OK.value(), bookResponseApiResponse.getStatusCode());
        assertEquals(bookResponse, bookResponseApiResponse.getData());

    }

    @Test
    public void testFetchBookByIdIfBookNotavailableForId() {
        Mockito.when(bookServiceImp.fetchBookById(2)).thenThrow(RecordNotFountException.class);
        assertThrows(RecordNotFountException.class, () -> this.bookRestController.fetchBookById(2));
    }

    @Test
    public void testFetchBookByIdZero() {
        Integer id = 0;
        assertThrows(ConstraintViolationException.class, () -> bookRestController.fetchBookById(id));
    }

    @Test
    public void testdeleteBookById() {
        Integer id = 1;
        BookResponse bookResponse = new BookResponse();
        bookResponse.setBookId(1);
        bookResponse.setBookTitle("spring");
        bookResponse.setPrice(200);
        bookResponse.setAuthorExperience(20);
        Mockito.when(bookServiceImp.deleteBookById(id)).thenReturn("book is deleted for id :" + id);
        ApiResponse<String> stringApiResponse = this.bookRestController.deleteBookById(id);
        assertEquals(HttpStatus.OK.value(), stringApiResponse.getStatusCode());
        assertEquals("book is deleted for id :" + id, stringApiResponse.getData());
    }

    @Test
    public void testdeleteBookByIdIfIdNull() {
        Integer id = null;
        assertThrows(ConstraintViolationException.class, () -> bookRestController.deleteBookById(id));
    }

    @Test
    public void testBookPagedList() {
        ArrayList<BookResponse> listOfBookResponse = new ArrayList<>();
        BookResponse bookResponse = new BookResponse();
        bookResponse.setBookId(1);
        bookResponse.setPrice(200);
        bookResponse.setAuthorExperience(12);
        bookResponse.setBookTitle("java");

        BooksPagedRequest booksPagedRequest = new BooksPagedRequest();
        booksPagedRequest.setPageNo(1);
        booksPagedRequest.setPageSize(2);
        listOfBookResponse.add(bookResponse);

        BookPagedListResponse bookPagedListResponse = new BookPagedListResponse();
        bookPagedListResponse.setListOfBooks(listOfBookResponse);

        Mockito.when(bookServiceImp.fetchAllBookDetails(booksPagedRequest)).thenReturn(bookPagedListResponse);
        ApiResponse apiResponse = bookRestController.BookPagedList(booksPagedRequest);
        assertEquals(HttpStatus.OK.value(), apiResponse.getStatusCode());
        assertEquals(bookPagedListResponse, apiResponse.getData());
    }

    @Test
    public void testUpdateBook() {
        Integer id = 1;

        UpdateBookRequest updateBookRequest = new UpdateBookRequest();
        updateBookRequest.setBookTitle("springBoot");
        BookResponse bookResponse = new BookResponse();
        bookResponse.setBookId(1);
        bookResponse.setBookTitle("springBoot");
        bookResponse.setStatus(StatusEnum.IN_ACTIVE);
        bookResponse.setAuthorExperience(12);
        Mockito.when(bookServiceImp.updateBook(id, updateBookRequest)).thenReturn(bookResponse);
        ApiResponse apiResponse = this.bookRestController.updateBook(id, updateBookRequest);
        assertEquals(bookResponse, apiResponse.getData());
    }

    @Test
    public void testUpdateBookIfIdNull() {
       Integer id = null;
        UpdateBookRequest updateBookRequest = new UpdateBookRequest();
        updateBookRequest.setBookTitle("springBoot");

       Mockito.when(this.bookServiceImp.updateBook(id,updateBookRequest)).thenThrow(NullPointerException.class);
      assertThrows(NullPointerException.class,()->this.bookRestController.updateBook(id,updateBookRequest));
    }


}









