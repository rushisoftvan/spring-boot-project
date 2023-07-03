package in.onetoone.bookmanagementsystem.mapper;

import in.onetoone.bookmanagementsystem.dto.request.CreateBookRequest;
import in.onetoone.bookmanagementsystem.dto.response.BookResponse;
import in.onetoone.bookmanagementsystem.entity.AuthorEntity;
import in.onetoone.bookmanagementsystem.entity.BookEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class BookMapper {

    public BookEntity toEntity(CreateBookRequest createBookRequest){

        BookEntity bookEntity = new BookEntity();
        AuthorEntity authorEntity = new AuthorEntity();

        authorEntity.setFirstName(createBookRequest.getAuthorFirstName());
        authorEntity.setLastName(createBookRequest.getAuthorLastName());
        authorEntity.setAuthorExperience(createBookRequest.getAuthorExperience());

        bookEntity.setTitle(createBookRequest.getBookTitle());
        bookEntity.setPrice(createBookRequest.getPrice());
        bookEntity.setPublishedYear(createBookRequest.getPublishedYear());
        bookEntity.setAuthor(authorEntity);
        return bookEntity;
    }

     public BookResponse toDto(BookEntity bookEntity)
     {
         BookResponse bookResponse = new BookResponse();
         bookResponse.setBookId(bookEntity.getId());
         bookResponse.setBookTitle(bookEntity.getTitle());
         bookResponse.setPrice(bookEntity.getPrice());
         bookResponse.setPublishedYear(bookEntity.getPublishedYear());
         bookResponse.setAuthorFirstName(bookEntity.getAuthor().getFirstName());
         bookResponse.setAuthorLastName(bookEntity.getAuthor().getLastName());
         bookResponse.setAuthorExperience(bookEntity.getAuthor().getAuthorExperience());
         bookResponse.setBookCreatedDateTime(bookEntity.getCreatedDateTime());
         bookResponse.setBookUpdatedDateTime(bookEntity.getUpdatedDateTime());
         bookResponse.setStatus(bookEntity.getStatus());
         return bookResponse;
     }

     public List<BookResponse> toDtoList(List<BookEntity> bookEntities){
       List<BookResponse> dtoList = new ArrayList();
       for(BookEntity book : bookEntities)
       {
           BookResponse bookResponse = toDto(book);
           dtoList.add(bookResponse);
       }
       return dtoList;
     }
}
