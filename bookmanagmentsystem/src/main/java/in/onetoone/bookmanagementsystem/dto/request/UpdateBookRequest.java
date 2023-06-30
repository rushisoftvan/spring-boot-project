package in.onetoone.bookmanagementsystem.dto.request;

import in.onetoone.bookmanagementsystem.enums.StatusEnum;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UpdateBookRequest {
    private String BookTitle;

    private Integer price;

    private Integer publishedYear;

    private String authorFirstName;

    private String authorLastName;

    private Integer authorExperience;

    private StatusEnum status;
}
