package com.etammag.ieat.controller;

import com.etammag.ieat.common.utils.BaseContext;
import com.etammag.ieat.entity.ShoppingCart;
import com.etammag.ieat.entity.dto.Result;
import com.etammag.ieat.service.ShoppingCartService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 购物车 前端控制器
 * </p>
 *
 * @author etammag
 * @since 2023-10-03
 */
@RestController
@RequestMapping("/shoppingCart")
public class ShoppingCartController {

    @Resource
    private ShoppingCartService shoppingCartService;

    @GetMapping("/list")
    public Result<List<ShoppingCart>> list(@RequestBody ShoppingCart shoppingCart) {
        shoppingCart.setUserId(BaseContext.getCurrentLogin().getId());
        return Result.success(shoppingCartService.queryAllA(shoppingCart));
    }

    @PostMapping("/add")
    public Result<String> add(@RequestBody ShoppingCart shoppingCart) {
        shoppingCart.setUserId(BaseContext.getCurrentLogin().getId());
        shoppingCartService.add(shoppingCart);
        return Result.success("添加成功");
    }

    @DeleteMapping("/clean")
    public Result<String> clean(@RequestBody ShoppingCart shoppingCart) {
        shoppingCart.setUserId(BaseContext.getCurrentLogin().getId());
        shoppingCartService.removeAllA(shoppingCart);
        return Result.success("清空成功");
    }

}
