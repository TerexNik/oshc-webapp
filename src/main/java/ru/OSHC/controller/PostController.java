package ru.OSHC.controller;

import javafx.geometry.Pos;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.OSHC.entity.Post;
import ru.OSHC.service.PostService;

import java.util.List;

/**
 * Контроллер поста
 */
@RestController
@RequestMapping("/posts")
public class PostController {

    private static final Logger log = Logger.getLogger(PostController.class);
    private BaseCRUDController<Post> baseCRUDController;

    @Autowired
    public PostController(PostService postService) {
        baseCRUDController = new BaseCRUDController<Post>(postService);
    }

    /**
     * Добавление нового поста.
     * @param post - пост
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void addPost(@RequestBody Post post) {
        baseCRUDController.add(post);
    }

    /**
     * Изменение данных о посте
     * @param post - пост
     */
    @RequestMapping(method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void update(@RequestBody Post post) {
        baseCRUDController.update(post);
    }

    /**
     * Получение списка всех постов
     * @return возвращает список всех постов
     */
    @RequestMapping(method = RequestMethod.GET)
    List getAll() {
        return baseCRUDController.getList("getPostsList");
    }

    /**
     * Получение информации о посте с идентификатором {@link Post#id}
     * @param id - идентификатор поста
     * @return возвращает выбранный пост
     */
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    Post getPostById(@PathVariable Long id) {
        return baseCRUDController.getById(id, "getPostById");
    }

    /**
     * Удаление поста с выбранным идентификатором {@link Post#id}
     * @param id - идентификатор поста
     */
    @RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteById(@PathVariable Long id) {
        baseCRUDController.deleteById(id, "getPostById");
    }
}
