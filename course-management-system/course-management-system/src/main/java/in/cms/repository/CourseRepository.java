package in.cms.repository;

import in.cms.dto.request.CoursePageRequest;
import in.cms.entity.CourseEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Objects;

@Repository
@RequiredArgsConstructor
@Slf4j
public class CourseRepository {

    private final EntityManager em;

    @Transactional
    public CourseEntity save(CourseEntity courseEntity) {
        log.info("<<<<<<<<< createProduct()");
        this.em.persist(courseEntity);
        log.info("saveCourse() >>>>>>>>");
        return courseEntity;
    }

    public List<CourseEntity> getPaginatedCourses(CoursePageRequest coursePageRequest) {

//        String jpql = "SELECT c FROM CourseEntity c WHERE c.status='ACTIVE' " +
//                "AND c.title LIKE  '%sp%' ORDER by c.updatedOn DESC";

        StringBuilder jpqlBuilder = new
                StringBuilder("SELECT c FROM CourseEntity c WHERE c.status='ACTIVE'");

        if (Objects.nonNull(coursePageRequest) && Objects.nonNull(coursePageRequest.getSearchText())){
            jpqlBuilder.append("AND c.title LIKE :searchText");
        }

        String sortBy = coursePageRequest.getSortBy();
        String sortOrder = coursePageRequest.getSortOrder();

        jpqlBuilder.append(" ORDER by c."+sortBy+ " " +sortOrder);



        TypedQuery<CourseEntity> q = this.em.createQuery(jpqlBuilder.toString(), CourseEntity.class);

        if (Objects.nonNull(coursePageRequest) && Objects.nonNull(coursePageRequest.getSearchText())){
            q.setParameter("searchText" , '%' + coursePageRequest.getSearchText() + '%');
        }

        Integer pageNumber = coursePageRequest.getPageNumber();
        Integer pageSize = coursePageRequest.getPageSize();
        int skipRecords = (pageNumber * pageSize) - pageSize;
        q.setFirstResult(skipRecords); // offset
        q.setMaxResults(pageSize); // limit
        List<CourseEntity> courses = q.getResultList();
        return courses;

    }

}
