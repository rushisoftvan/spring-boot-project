package in.onetoone.bookmanagementsystem.dto.response;


import in.onetoone.bookmanagementsystem.enums.StatusEnum;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BookResponse {

    private Integer BookId;

    private String BookTitle;

    private Integer price;

    private Integer publishedYear;

    private String authorFirstName;

    private String authorLastName;

    private Integer authorExperience;

    private StatusEnum status;

    private LocalDateTime bookCreatedDateTime;

    private LocalDateTime bookUpdatedDateTime;
}
