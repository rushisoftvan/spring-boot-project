package in.onetoone.bookmanagementsystem.service;

import in.onetoone.bookmanagementsystem.Exception.RecordNotFountException;
import in.onetoone.bookmanagementsystem.dto.request.BooksPagedRequest;
import in.onetoone.bookmanagementsystem.dto.request.CreateBookRequest;
import in.onetoone.bookmanagementsystem.dto.request.UpdateBookRequest;
import in.onetoone.bookmanagementsystem.dto.response.BookPagedListResponse;
import in.onetoone.bookmanagementsystem.dto.response.BookResponse;
import in.onetoone.bookmanagementsystem.entity.BookEntity;
import in.onetoone.bookmanagementsystem.enums.StatusEnum;
import in.onetoone.bookmanagementsystem.mapper.BookMapper;
import in.onetoone.bookmanagementsystem.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class BookServiceImp implements BookService {

    private  final BookRepository bookRepository;
    private final BookMapper bookMapper;


     public BookResponse saveBook(CreateBookRequest createBookRequest) {
        log.debug("<<<<<<<<< saveBook()");
         checkBookRequestNull(createBookRequest);
         BookEntity bookEntity = this.bookMapper.toEntity(createBookRequest);
         BookEntity savedBook = this.bookRepository.save(bookEntity);
         BookResponse bookResponse = this.bookMapper.toDto(savedBook);
         log.debug("saveBook() >>>>>>>");
         return bookResponse;
    }

    @Override
    public BookResponse fetchBookById(Integer id) {
        log.debug("<<<<<<<<< fetchBookById()");
        BookEntity bookEntity = getBookEntity(id);
        log.debug("fetchBookById() >>>>>>>");
        return this.bookMapper.toDto(bookEntity);
    }
    public String deleteBookById(Integer id) {
        log.debug("<<<<<<<<< deleteBookById()");
        BookEntity bookEntity = getBookEntity(id);
        bookEntity.setStatus(StatusEnum.IN_ACTIVE);
        log.debug("deleteBookById() >>>>>>>");
        return "Book has been deleted for id :"+id ;
    }

    @Override
    public BookResponse updateBook(Integer id,UpdateBookRequest updateBookRequest) {
         log.debug("<<<<<<<<< updateBook()");
        BookEntity bookEntity = getBookEntity(id);
        BookEntity updatedBook = updateBook(updateBookRequest, bookEntity);
        log.debug("updateBook >>>>>>>");
        return  this.bookMapper.toDto(updatedBook);
    }

    @Transactional
    private BookEntity updateBook(UpdateBookRequest updateBookRequest, BookEntity bookEntity) {
        bookEntity.setTitle(updateBookRequest.getBookTitle());
        bookEntity.setPublishedYear(updateBookRequest.getPublishedYear());
        bookEntity.setStatus(updateBookRequest.getStatus());

        bookEntity.getAuthor().setFirstName(updateBookRequest.getAuthorFirstName());
        bookEntity.getAuthor().setLastName(updateBookRequest.getAuthorLastName());
        bookEntity.getAuthor().setAuthorExperience(updateBookRequest.getAuthorExperience());
        BookEntity updatedBook = this.bookRepository.save(bookEntity);
        return updatedBook;
    }

    private BookEntity getBookEntity(Integer id) {
         log.debug("<<<<<<<<< getBookEntity()");
        Optional<BookEntity> book = this.bookRepository.findById(id);
        if(book.isPresent()){
            log.debug("getBookEntity >>>>>>>");
           return    book.get();
        }
        throw new RecordNotFountException("Record Not Fount for this id :"+ id);
    }
    private static void checkBookRequestNull(CreateBookRequest createBookRequest) {
        if(createBookRequest ==null){
            throw new NullPointerException("BookRequest object should not be null");
        }
    }


    public BookPagedListResponse fetchAllBookDetails(BooksPagedRequest booksPagedRequest) {
        log.debug("<<<<<<<<< fetchAllBookDetails()");
        Integer pageNumber = booksPagedRequest.getPageNo()-1;
        Integer pageSize = booksPagedRequest.getPageSize();
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<BookEntity> bookspage  = this.bookRepository.findAll(pageable);
        if(bookspage.isEmpty()){
          throw new RecordNotFountException("Book are not available");
        }
        List<BookEntity> listOfBookEntity = bookspage.getContent();
        log.debug("fetchAllBookDetails() >>>>>>>");
        return  getBookPagedListResponse(bookspage, listOfBookEntity);

    }

    private BookPagedListResponse getBookPagedListResponse(Page<BookEntity> bookspage, List<BookEntity> listOfBookEntity) {
        log.debug("<<<<<<<<< getBookPagedListResponse()");
        List<BookResponse> dtoList = this.bookMapper.toDtoList(listOfBookEntity);
        BookPagedListResponse bookPagedListResponse = new BookPagedListResponse();
        bookPagedListResponse.setListOfBooks(dtoList);
        bookPagedListResponse.setFirstPage(bookspage.isFirst());
        bookPagedListResponse.setLastPage(bookspage.isLast());
        bookPagedListResponse.setHasNext(bookspage.hasNext());
        bookPagedListResponse.setHasPrevious(bookspage.hasPrevious());
        bookPagedListResponse.setTotalRecords(bookPagedListResponse.getTotalRecords());
        log.debug("getBookPagedListResponse() >>>>>>>");
        return bookPagedListResponse;
    }

}
