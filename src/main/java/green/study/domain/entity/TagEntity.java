package green.study.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "Tag")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TagEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tagNumber;

    private Long parentsKey;

    @Column(nullable = false)
    private Long courseNumber;

    @Column(nullable = false)
    private String tagName;

}
