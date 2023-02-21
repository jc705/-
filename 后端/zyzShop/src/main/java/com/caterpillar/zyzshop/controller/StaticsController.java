package com.caterpillar.zyzshop.controller;


import com.caterpillar.zyzshop.controller.Code.Result;
import com.caterpillar.zyzshop.domain.Trolley;
import com.caterpillar.zyzshop.service.TrolleyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/statics")
@CrossOrigin
public class StaticsController {
    @Autowired
    private TrolleyService trolleyService;

    @PutMapping
    public Result updataById(@RequestBody Trolley trolley){
        return trolleyService.updateStaticAll(trolley);
    }
    @PutMapping("/one")
    public Result updataOne(@RequestBody Trolley trolley){
        return trolleyService.updataById(trolley);
    }

}

