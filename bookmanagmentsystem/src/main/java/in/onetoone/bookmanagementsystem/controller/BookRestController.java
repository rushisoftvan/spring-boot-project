package in.onetoone.bookmanagementsystem.controller;

import in.onetoone.bookmanagementsystem.dto.request.BooksPagedRequest;
import in.onetoone.bookmanagementsystem.dto.request.CreateBookRequest;
import in.onetoone.bookmanagementsystem.dto.request.UpdateBookRequest;
import in.onetoone.bookmanagementsystem.dto.response.ApiResponse;
import in.onetoone.bookmanagementsystem.dto.response.BookPagedListResponse;
import in.onetoone.bookmanagementsystem.dto.response.BookResponse;
import in.onetoone.bookmanagementsystem.service.BookServiceImp;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@Slf4j
@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/bookapi")
public class BookRestController {

    private final BookServiceImp bookServiceImp;

    @PostMapping("/books")
    public ApiResponse saveBook(@Valid @RequestBody CreateBookRequest createBookRequest) {
        log.debug("<<<<<<<<< controllersaveBook()");
        BookResponse bookResponse = this.bookServiceImp.saveBook(createBookRequest);
        log.debug("controllersaveBook >>>>>>>");
        return new ApiResponse(bookResponse, HttpStatus.CREATED.value());
    }

    @GetMapping("books/{id}")
    public ApiResponse<BookResponse> fetchBookById(@PathVariable("id") @Positive(message = "id should be greter then zero") Integer id){
        log.debug("<<<<<<<<< controller-fetchBookById()");
        BookResponse bookResponse = this.bookServiceImp.fetchBookById(id);
        log.debug("controller-fetchBookById() >>>>>>>");
        return new ApiResponse(bookResponse,HttpStatus.OK.value());
    }

    @DeleteMapping
    public ApiResponse<String> deleteBookById(@PathVariable("id") @Positive(message = "id should be greter then zero") Integer id){
        log.debug("<<<<<<<<< controller deleteBookById()");
        String deleteMessage = this.bookServiceImp.deleteBookById(id);
        log.debug("controller-deleteBookById() >>>>>>>");
        return new ApiResponse<>(deleteMessage,HttpStatus.OK.value());
    }

    @PutMapping("books/{id}")
    public ApiResponse updateBook(@PathVariable("id") Integer id, @RequestBody UpdateBookRequest updateBookRequest){
        log.debug("<<<<<<<<< controller-updateBook()");
        BookResponse bookResponse = this.bookServiceImp.updateBook(id, updateBookRequest);
        log.debug("controller-updateBook() >>>>>>>");
       return new ApiResponse(bookResponse,HttpStatus.CREATED.value());
    }

    @PostMapping("/bookList")
    public ApiResponse BookPagedList(@RequestBody BooksPagedRequest booksPagedRequest){
        log.debug("<<<<<<<<< BookPagedList()");
        BookPagedListResponse bookPagedListResponse = this.bookServiceImp.fetchAllBookDetails(booksPagedRequest);
        log.debug("BookPagedList() >>>>>>>");
        return  new ApiResponse(bookPagedListResponse,HttpStatus.OK.value());
    }




}
