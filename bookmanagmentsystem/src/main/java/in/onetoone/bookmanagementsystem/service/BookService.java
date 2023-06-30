package in.onetoone.bookmanagementsystem.service;

import in.onetoone.bookmanagementsystem.dto.request.CreateBookRequest;
import in.onetoone.bookmanagementsystem.dto.request.UpdateBookRequest;
import in.onetoone.bookmanagementsystem.dto.response.BookResponse;

public interface BookService {

    public BookResponse saveBook(CreateBookRequest createBookRequest);

    public BookResponse fetchBookById(Integer id);

    public String deleteBookById(Integer id);

   public BookResponse updateBook(Integer id,UpdateBookRequest updateBookRequest);


}
