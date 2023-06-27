package in.cms.runner;

import in.cms.dto.request.CoursePageRequest;
import in.cms.entity.CourseEntity;
import in.cms.enums.Status;
import in.cms.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class CourseRunnerForMe implements CommandLineRunner {

    private final CourseRepository courseRepository;

    @Override
    public void run(String... args) throws Exception {
        CoursePageRequest coursePageRequest = new CoursePageRequest();
        coursePageRequest.setPageNumber(1);
        coursePageRequest.setPageSize(2);
        coursePageRequest.setSearchText("sp");
        List<CourseEntity> courses = this.courseRepository.getPaginatedCourses(coursePageRequest);
        for (CourseEntity course : courses) {
            log.info("Course : {}" , course);
        }

    }

    public void saveDummyCourses() {
        List<String> courseTitles = List.of("Spring Boot","JAVA", "Rest API", "Micro Service", "Database", "JPA");
        for (String courseTitle : courseTitles) {
            CourseEntity courseEntity = prepareCourse(courseTitle, 500.0);
            this.courseRepository.save(courseEntity);
        }
    }

    private static CourseEntity prepareCourse(String title, Double price) {
        CourseEntity courseEntity = new CourseEntity();
        courseEntity.setTitle(title);
        courseEntity.setPrice(BigDecimal.valueOf(500));
        courseEntity.setCreatedOn(LocalDateTime.now());
        courseEntity.setUpdatedOn(LocalDateTime.now());
        courseEntity.setStatus(Status.ACTIVE);
        return courseEntity;
    }
}
