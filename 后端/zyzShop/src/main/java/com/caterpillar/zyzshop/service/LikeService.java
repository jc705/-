package com.caterpillar.zyzshop.service;

import com.caterpillar.zyzshop.controller.Code.Result;
import com.caterpillar.zyzshop.domain.Like;
import com.baomidou.mybatisplus.extension.service.IService;

public interface LikeService extends IService<Like> {
    public Result getLikes(Long id);
    public Result saveLike(Like like);
    public Result removeLike(Like like);
}
