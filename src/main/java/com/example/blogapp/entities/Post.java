package com.example.blogapp.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name="post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name="post_title", length=100, nullable = false)
    private String title;
    @Column(length=1000)
    private String content;
  //  private String imageName;
    private Date date;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
   @JoinColumn(name = "user_id")
    private User user;

      @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
     private Set<Comment> comments= new HashSet<>();


//    public Integer getId() {
//        return id;
//    }

//    public void setId(Integer id) {
//        this.id = id;
//    }
}
