package ru.OSHC.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.OSHC.entity.Post;
import ru.OSHC.service.PostService;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController extends BaseCRUDController<Post>{

    private static final Logger log = Logger.getLogger(PostController.class);

    @Autowired
    public PostController(PostService postService) {
        setService(postService);
    }

    @RequestMapping(value = "/getClear", method = RequestMethod.GET)
    List getWithNames() {
        return getList("getPostsWithNames");
    }

    @RequestMapping(method = RequestMethod.GET)
    List getAll() {
        return getList("getPostsList");
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    Post getPostById(@PathVariable Long id) {
        return getById(id, "getPostById");
    }

}
