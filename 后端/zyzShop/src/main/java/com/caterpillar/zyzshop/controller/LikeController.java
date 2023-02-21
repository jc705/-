package com.caterpillar.zyzshop.controller;


import com.caterpillar.zyzshop.controller.Code.Result;
import com.caterpillar.zyzshop.domain.Like;
import com.caterpillar.zyzshop.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/likes")
@CrossOrigin
public class LikeController {
    @Autowired
    private LikeService likeService;

    @GetMapping("/{id}")
    public Result getLikes(@PathVariable("id")Long id){
        return likeService.getLikes(id);
    }

    @PostMapping
    public Result saveLike(@RequestBody Like like){
        return likeService.saveLike(like);
    }
    @DeleteMapping
    public Result delLike(@RequestBody Like like){
        return likeService.removeLike(like);
    }
}

