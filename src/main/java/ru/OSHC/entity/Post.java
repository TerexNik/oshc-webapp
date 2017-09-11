package ru.OSHC.entity;

import javax.persistence.*;

@NamedQueries({
        @NamedQuery(
                name = "getPostsWithNames",
                query = "select p.name, e.name, e.surname" +
                        " from Employee e" +
                        " left join Post p on p.id = e.post.id"
        ),
        @NamedQuery(
                name = "getPostsList",
                query = "from Post p"
        ),
        @NamedQuery(
                name = "getPostById",
                query = "from Post p where p.id = :id"
        ),
})

@Entity
@Table(name = "POSTS")
public class Post {
    @Id
    private long id;

    @Column(nullable = false)
    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
