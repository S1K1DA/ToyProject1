package green.study.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "Course")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseNumber;

    @Column(nullable = false)
    private String courseTitle;

    @Column(nullable = false, unique = true)
    private String courseImageName;

    @Column(nullable = false)
    private String courseImagePath;

    @Column(nullable = false)
    private Long userNumber;



}
