package ru.OSHC.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.OSHC.entity.Post;
import ru.OSHC.exception.FileNotFoundException;
import ru.OSHC.service.PostService;

import javax.persistence.NoResultException;
import java.sql.SQLException;
import java.util.List;

/**
 * Контроллер поста
 */
@RestController
@RequestMapping("/posts")
public class PostController {

    private static final Logger log = Logger.getLogger(PostController.class);
    private PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    /**
     * Добавление нового поста.
     *
     * @param post - пост
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void addPost(@RequestBody Post post) throws SQLException {
        postService.add(post);
    }

    /**
     * Изменение данных о посте
     *
     * @param post - пост
     */
    @RequestMapping(method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void update(@RequestBody Post post) throws SQLException {
        postService.update(post);
    }

    /**
     * Получение списка всех постов
     *
     * @return возвращает список всех постов
     */
    @RequestMapping(method = RequestMethod.GET)
    List getAll() throws SQLException {
        return postService.getAll("getPostsList");
    }

    /**
     * Получение информации о посте с идентификатором {@link Post#id}
     *
     * @param id - идентификатор поста
     * @return возвращает выбранный пост
     */
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    Post getPostById(@PathVariable Long id) throws SQLException {
        try {
            return postService.getById(id, "getPostById");
        } catch (NoResultException e) {
            log.error("NoResultException in getPostById", e);
            throw new FileNotFoundException("Такого поста не существует");
        }
    }

    /**
     * Удаление поста с выбранным идентификатором {@link Post#id}
     *
     * @param id - идентификатор поста
     */
    @RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteById(@PathVariable Long id) throws SQLException {
        try {
            postService.removeById(id, "getPostById");
        } catch (NoResultException e) {
            log.error("NoResultException in removePostById", e);
            throw new FileNotFoundException("Такого поста не существует");
        }

    }
}
