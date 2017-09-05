package ru.OSHC.service;

import org.hibernate.Session;
import org.springframework.stereotype.Service;
import ru.OSHC.entity.Post;

import java.sql.SQLException;

@Service
public class PostService extends BaseService<Post> {
    public void add(Post post) throws SQLException {
        openTransactionSession();
        Session session = getSession();
        session.save("Post", post);
        closeTransactionSession();
    }
}
