package lt.verbus.svblog.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "{post.title.not.blank}")
    private String title;

    @NotBlank(message = "{post.url.not.blank}")
    @URL(message = "{post.url.not.valid}")
    private String link;

    @NotBlank(message = "{post.type.not.blank}")
    @Size(min=3, max = 30, message = "{post.type.size}")
    private String type;

    @NotBlank(message = "{post.image-url.not.blank}")
    @URL(message = "{post.image-url.url}")
    private String imageUrl;

    @NotBlank(message = "{post.body.not.blank}")
    @Size(min = 50, max = 950, message = "{post.body.size}")
    @Lob
    private String body;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> comments;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Agree> agrees;

    @CreationTimestamp
    private LocalDateTime creationTimeStamp;

    @UpdateTimestamp
    private LocalDateTime updateTimeStamp;

}
