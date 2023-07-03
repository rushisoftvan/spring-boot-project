package in.onetoone.bookmanagementsystem.service;

import in.onetoone.bookmanagementsystem.Exception.RecordNotFountException;
import in.onetoone.bookmanagementsystem.dto.request.BooksPagedRequest;
import in.onetoone.bookmanagementsystem.dto.request.CreateBookRequest;
import in.onetoone.bookmanagementsystem.dto.request.UpdateBookRequest;
import in.onetoone.bookmanagementsystem.dto.response.BookPagedListResponse;
import in.onetoone.bookmanagementsystem.dto.response.BookResponse;
import in.onetoone.bookmanagementsystem.entity.AuthorEntity;
import in.onetoone.bookmanagementsystem.entity.BookEntity;
import in.onetoone.bookmanagementsystem.enums.StatusEnum;
import in.onetoone.bookmanagementsystem.mapper.BookMapper;
import in.onetoone.bookmanagementsystem.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BookServiceImpTest {


    @Test
    public void testsaveBook() {
        BookRepository bookRepositoryMock = Mockito.mock(BookRepository.class);
        BookMapper bookMapper = new BookMapper();
        CreateBookRequest createBookRequest = new CreateBookRequest();
        createBookRequest.setBookTitle("spring");
        createBookRequest.setPrice(200);
        createBookRequest.setPublishedYear(1942);
        createBookRequest.setAuthorFirstName("Kate");
        createBookRequest.setAuthorLastName("Brennan");
        createBookRequest.setAuthorExperience(20);

        //book
        BookEntity bookEntity = new BookEntity();
        bookEntity.setId(1);
        bookEntity.setTitle("spring");
        bookEntity.setPrice(200);
        bookEntity.setPublishedYear(1942);

        //author
        AuthorEntity authorEntity = new AuthorEntity();
        authorEntity.setFirstName("Kate");
        authorEntity.setLastName("Brennan");
        authorEntity.setAuthorExperience(20);

        bookEntity.setAuthor(authorEntity);

        Mockito.when(bookRepositoryMock.save(Mockito.any(BookEntity.class))).thenReturn(bookEntity);
        BookService bookServiceImp = new BookServiceImp(bookRepositoryMock, bookMapper);
        bookServiceImp.saveBook(createBookRequest);
        assertEquals(20, bookServiceImp.saveBook(createBookRequest).getAuthorExperience());
    }

    @Test
    public void testsaveBookCraeteBookRequestNull() {
        BookRepository bookRepositoryMock = Mockito.mock(BookRepository.class);
        BookMapper bookMapper = new BookMapper();
        assertThrows(NullPointerException.class, () -> new BookServiceImp(bookRepositoryMock, bookMapper).saveBook(null));
    }

    @Test
    public void testGetBookEntityById() {
        Integer id = 1;
        BookRepository bookRepositoryMock = Mockito.mock(BookRepository.class);
        BookMapper bookMapper = new BookMapper();

        BookEntity bookEntity = new BookEntity();
        bookEntity.setId(1);
        bookEntity.setTitle("spring");
        bookEntity.setPrice(200);
        bookEntity.setPublishedYear(1942);

        //author
        AuthorEntity authorEntity = new AuthorEntity();
        authorEntity.setFirstName("Kate");
        authorEntity.setLastName("Brennan");
        authorEntity.setAuthorExperience(20);

        bookEntity.setAuthor(authorEntity);

        Mockito.when(bookRepositoryMock.findById(id)).thenReturn(Optional.of(bookEntity));
        BookServiceImp bookServiceImp = new BookServiceImp(bookRepositoryMock, bookMapper);
        BookResponse bookResponse = bookServiceImp.fetchBookById(id);
        assertEquals("Kate", bookResponse.getAuthorFirstName());
    }

    @Test
    public void testGetBookEntityByIdIfBookNotAvailableForId() {
        Integer id = 7;
        BookRepository bookRepositoryMock = Mockito.mock(BookRepository.class);
        BookMapper bookMapper = new BookMapper();
        Mockito.when(bookRepositoryMock.findById(id)).thenReturn(Optional.empty());
        BookServiceImp bookServiceImp = new BookServiceImp(bookRepositoryMock, bookMapper);
        assertThrows(RecordNotFountException.class, () -> bookServiceImp.fetchBookById(id));
    }

    @Test
    public void deleteBookById() {
        Integer id = 1;
        BookRepository bookRepositoryMock = Mockito.mock(BookRepository.class);
        BookMapper bookMapper = new BookMapper();

        BookEntity bookEntity = new BookEntity();
        bookEntity.setId(1);
        bookEntity.setTitle("spring");
        bookEntity.setPrice(200);
        bookEntity.setPublishedYear(1942);

        //author
        AuthorEntity authorEntity = new AuthorEntity();
        authorEntity.setFirstName("Kate");
        authorEntity.setLastName("Brennan");
        authorEntity.setAuthorExperience(20);

        bookEntity.setAuthor(authorEntity);

        Mockito.when(bookRepositoryMock.findById(1)).thenReturn(Optional.of(bookEntity));
        bookEntity.setStatus(StatusEnum.IN_ACTIVE);
        Mockito.when(bookRepositoryMock.save(bookEntity)).thenReturn(bookEntity);
        BookServiceImp bookServiceImp = new BookServiceImp(bookRepositoryMock, bookMapper);
        String deleteBookMessage = bookServiceImp.deleteBookById(id);
        assertEquals("Book has been deleted for id :" + id, deleteBookMessage);
    }

    @Test
    public void deleteBookByIdIdNull() {
        BookRepository bookRepositoryMock = Mockito.mock(BookRepository.class);
        BookMapper bookMapper = new BookMapper();
        assertThrows(NullPointerException.class,()-> new BookServiceImp(bookRepositoryMock,bookMapper).deleteBookById(null));
    }

    @Test
    public void fetchAllBookDetails(){
        BookRepository bookRepositoryMock = Mockito.mock(BookRepository.class);
        BookMapper bookMapper = new BookMapper();

        BooksPagedRequest bookPagedRequest = new BooksPagedRequest();
        bookPagedRequest.setPageNo(1);
        bookPagedRequest.setPageSize(2);

        List<BookEntity> books = new ArrayList();

        BookEntity bookEntity = new BookEntity();
        bookEntity.setId(1);
        bookEntity.setTitle("spring");
        bookEntity.setPrice(200);
        bookEntity.setPublishedYear(1942);

        //author
        AuthorEntity authorEntity = new AuthorEntity();
        authorEntity.setFirstName("Kate");
        authorEntity.setLastName("Brennan");
        authorEntity.setAuthorExperience(20);

        bookEntity.setAuthor(authorEntity);
        books.add(bookEntity);
        Integer pageSize =  bookPagedRequest.getPageSize();
        Integer pageNumber= bookPagedRequest.getPageNo()-1;
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        // Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<BookEntity> page=new PageImpl(books,pageable,1);
        Mockito.when(bookRepositoryMock.findAll(pageable)).thenReturn(page);
        BookServiceImp bookServiceImp = new BookServiceImp(bookRepositoryMock, bookMapper);
        BookPagedListResponse bookPagedListResponse = bookServiceImp.fetchAllBookDetails(bookPagedRequest);
        assertEquals(1,bookPagedListResponse.getListOfBooks().size());
    }

      @Test
      public void fetchAllBookDetailsIfBooksNotAvailable(){
        BookRepository bookRepositoryMock = Mockito.mock(BookRepository.class);
        BookMapper bookMapper = new BookMapper();

        BooksPagedRequest bookPagedRequest = new BooksPagedRequest();
        bookPagedRequest.setPageNo(1);
        bookPagedRequest.setPageSize(2);

        List<BookEntity> books = new ArrayList();

        BookEntity bookEntity = new BookEntity();
        bookEntity.setId(1);
        bookEntity.setTitle("spring");
        bookEntity.setPrice(200);
        bookEntity.setPublishedYear(1942);

        //author
        AuthorEntity authorEntity = new AuthorEntity();
        authorEntity.setFirstName("Kate");
        authorEntity.setLastName("Brennan");
        authorEntity.setAuthorExperience(20);

        bookEntity.setAuthor(authorEntity);
        //books.add(bookEntity);
        Integer pageSize =  bookPagedRequest.getPageSize();
        Integer pageNumber= bookPagedRequest.getPageNo()-1;
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<BookEntity> page=new PageImpl(books,pageable,1);
        Mockito.when(bookRepositoryMock.findAll(pageable)).thenReturn(page);
        BookServiceImp bookServiceImp = new BookServiceImp(bookRepositoryMock, bookMapper);
       assertThrows(RecordNotFountException.class,()->bookServiceImp.fetchAllBookDetails(bookPagedRequest));

    }


    @Test
    public void updateBook(){
        Integer id =1;
        BookRepository bookRepositoryMock = Mockito.mock(BookRepository.class);
        BookMapper bookMapper = new BookMapper();

        UpdateBookRequest updateBookRequest = new UpdateBookRequest();
        updateBookRequest.setBookTitle("java");
        updateBookRequest.setPrice(200);
        updateBookRequest.setAuthorExperience(12);

        BookEntity bookEntity = new BookEntity();
        bookEntity.setId(1);
        bookEntity.setTitle("spring");
        bookEntity.setPrice(200);
        bookEntity.setPublishedYear(1942);

        //author
        AuthorEntity authorEntity = new AuthorEntity();
        authorEntity.setFirstName("Kate");
        authorEntity.setLastName("Brennan");
        authorEntity.setAuthorExperience(20);

        bookEntity.setAuthor(authorEntity);

        Mockito.when(bookRepositoryMock.findById(id)).thenReturn(Optional.of(bookEntity));
        bookEntity.setPrice(100);
        bookEntity.setPublishedYear(1940);
        Mockito.when(bookRepositoryMock.save(Mockito.any(BookEntity.class))).thenReturn(bookEntity);
        BookServiceImp bookServiceImp = new BookServiceImp(bookRepositoryMock, bookMapper);
        BookResponse bookResponse = bookServiceImp.updateBook(id, updateBookRequest);
        assertEquals(100,bookResponse.getPrice());
    }
    @Test
    public void updateBookIdNull(){

        BookRepository bookRepositoryMock = Mockito.mock(BookRepository.class);
        BookMapper bookMapper = new BookMapper();

        UpdateBookRequest updateBookRequest = new UpdateBookRequest();
        updateBookRequest.setBookTitle("java");
        updateBookRequest.setPrice(200);
        updateBookRequest.setAuthorExperience(12);

        BookServiceImp bookServiceImp = new BookServiceImp(bookRepositoryMock, bookMapper);
        assertThrows(NullPointerException.class,()->bookServiceImp.updateBook(null,updateBookRequest));
    }


}