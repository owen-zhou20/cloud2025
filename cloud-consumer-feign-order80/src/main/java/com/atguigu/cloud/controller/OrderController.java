package com.atguigu.cloud.controller;

import cn.hutool.core.date.DateUtil;
import com.atguigu.cloud.apis.PayFeignApi;
import com.atguigu.cloud.entities.PayDTO;
import com.atguigu.cloud.resp.ResultData;
import com.atguigu.cloud.resp.ReturnCodeEnum;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

    @Resource
    private PayFeignApi payFeignApi;

    @PostMapping(value = "/feign/pay/add")
    public ResultData addOrder(@RequestBody PayDTO payDTO){
        ResultData<String> resultData = payFeignApi.addPay(payDTO);
        return resultData;
    }

    @GetMapping(value = "/feign/pay/get/{id}")
    public ResultData getPayInfo(@PathVariable("id") Integer id){
        ResultData resultData=null;
        try{
            System.out.println("------invoke start"+ DateUtil.now());
            resultData = payFeignApi.getById(id);
        }catch (Exception e){
            System.out.println("------invoke end"+ DateUtil.now());
            e.printStackTrace();
            return ResultData.fail(ReturnCodeEnum.RC500.getCode(), e.getMessage());
        }
        return resultData;
    }

    @GetMapping(value = "/feign/pay/mylb")
    public String mylb(){
        return payFeignApi.mylb();
    }
}
