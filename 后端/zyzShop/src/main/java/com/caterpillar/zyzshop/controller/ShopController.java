package com.caterpillar.zyzshop.controller;


import com.baomidou.mybatisplus.extension.api.R;
import com.caterpillar.zyzshop.controller.Code.Result;
import com.caterpillar.zyzshop.domain.Shop;
import com.caterpillar.zyzshop.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/shops")
@CrossOrigin
public class ShopController {
    @Autowired
    private ShopService shopService;

    @GetMapping
    public Result getAll(){
        return shopService.getAll();
    }
    @GetMapping("/{id}")
    private Result getById(@PathVariable("id") Long id){
        return shopService.getById(id);
    }

    @GetMapping("/electronic")
    public Result getElectronicList(){
        return shopService.getElectronicList();
    }
    @GetMapping("/clothing")
    public Result getClothingList(){
        return shopService.getClothingList();
    }
    @GetMapping("/others")
    public Result getOthersList(){
        return shopService.getOthersList();
    }

    @PostMapping
    public Result insert(@RequestBody Shop shop){
        System.out.println(shop);
        return shopService.insert(shop);
    }

    @DeleteMapping("/{id}")
    public Result delId(@PathVariable("id") Long id){
        return shopService.delId(id);
    }

    @GetMapping("/likes")
    public Result getByLikes(@RequestParam("str")String str,@RequestParam("page")Integer page){
        return shopService.getByLikes(str,page);
    }

}

