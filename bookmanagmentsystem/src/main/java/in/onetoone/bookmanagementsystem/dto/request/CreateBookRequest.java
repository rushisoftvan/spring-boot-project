package in.onetoone.bookmanagementsystem.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CreateBookRequest {


    @NotEmpty(message="Book Title should not be null or empaty")
    private String bookTitle;

     @NotNull(message="Book price should not be null")
    private Integer price;

     @NotNull(message="publishedYear should not be null")
    private Integer publishedYear;

     @NotEmpty(message="Authore First Name should not be null or empty")
    private String authorFirstName;

    @NotEmpty(message="Authore Last Name should not be null or empty")
    private String authorLastName;

    @NotNull(message="authorExperience should not be null")
    private Integer authorExperience;




}
