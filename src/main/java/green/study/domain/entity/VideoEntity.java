package green.study.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "Video")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VideoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long videoNumber;

    @Column(nullable = false)
    private String videoTitle;

    @Column(nullable = false)
    private Long courseNumber; // 강의 넘버
}
