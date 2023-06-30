package in.onetoone.bookmanagementsystem.entity;


import in.onetoone.bookmanagementsystem.enums.StatusEnum;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name="book")
@DynamicUpdate
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", updatable = false, nullable = false)
    private Integer id;
    @Column(name = "TITLE")
    private String title;

    @Column(name = "PRICE")
    private Integer price;

    @Column(name = "PUBLISH_YEAR")
    private Integer publishedYear;

    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private StatusEnum status;

    @Column(name = "CREATED_DATE_TIME")
    private LocalDateTime createdDateTime;

    @Column(name = "UPDATED_DATE_TIME")
    private LocalDateTime updatedDateTime;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name= "AUTHOR_ID")
    private AuthorEntity author;

    @PrePersist
    public void beforePersist() {
        this.createdDateTime = LocalDateTime.now();
        this.updatedDateTime = LocalDateTime.now();
        this.status = StatusEnum.ACTIVE;
    }

    @PreUpdate
    public void beforeUpdate() {
        this.updatedDateTime = LocalDateTime.now();
    }


}
