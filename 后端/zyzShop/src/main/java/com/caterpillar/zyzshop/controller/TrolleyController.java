package com.caterpillar.zyzshop.controller;


import com.caterpillar.zyzshop.controller.Code.Result;
import com.caterpillar.zyzshop.domain.Trolley;
import com.caterpillar.zyzshop.service.TrolleyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/trolleys")
@CrossOrigin
public class TrolleyController {
    @Autowired
    private TrolleyService trolleyService;
    @GetMapping("/{id}")
    public Result getByIdList(@PathVariable("id") Long id){
        return trolleyService.getCartList(id);
    }

    @PutMapping
    public Result updataById(@RequestBody Trolley trolley){
        return trolleyService.updataById(trolley);
    }
    @DeleteMapping
    public Result deleteById(@RequestBody Trolley trolley){
        return trolleyService.deleteById(trolley);
    }
    @PostMapping
    public Result saveOne(@RequestBody Trolley trolley){
        return trolleyService.saveOne(trolley);
    }
    @DeleteMapping("/{id}")
    public Result deleteAll(@PathVariable("id") Long id){
        return trolleyService.deleteAll(id);
    }
}

