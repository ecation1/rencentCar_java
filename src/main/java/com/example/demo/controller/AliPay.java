package com.example.demo.controller;



import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.alipay.api.response.AlipayTradePrecreateResponse;


import com.example.demo.config.PayWebSocket;
import com.example.demo.pojo.AliReturnPay;

import com.example.demo.pojo.Cars;
import com.example.demo.pojo.Order;
import com.example.demo.service.CarService;
import com.example.demo.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;


@Controller
@Slf4j
public class AliPay {
    Order order=new Order();
    @Autowired
    OrderService orderService;
    @Autowired
    CarService carService;
    @Autowired
    private PayWebSocket payWebSocket;	// 导入刚刚写好的 WebSocket 工具类
    // 支付宝网关：沙箱环境 (真实环境的话改外：https://openapi.alipay.com/gateway.do)
    private static final String URL = "https://openapi.alipaydev.com/gateway.do";
    // APPID (请自行填写，真实环境请做对应修改)
    private static final String APP_ID = "2021000119670070";
    // 应用私钥 (请自行填写，真实环境请做对应修改)
    private static final String APP_PRIVATE_KEY = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQC7PzGE0iRn+nwd8dITZ7XW2sgeabKylQMwrSNg8TB/CRlWJNHTpGt7XVhq+tP547kUhhRSBHOomhlKgFEDHaNyYY3E/GYznVbw9m53E61RPD/Oya/7fDuvlqakZYKKxCAmGPIoi0pkPakZkl0rVjRb7kRPAJPigwhzLBeicDme1Y4lQCsgopSTYrC1RFpOufP5q9mdCdRokEdTwR9mMZdyPso0h+9jtfOkNgAKqOz//wTYmrEWd+Mjm160dCMJucjO43k1KV3DNU1TCO3ssmiPB8Y6E1fzEA519vgtVrpORb+L8/nF4QbHzXSujLUzh4+qcrcojykMzcNmX2Y3quWzAgMBAAECggEAdWSpJxyYtpfUOPHjQ0dCOfHwhSC/aDcESQBDrgOI5BxQRjHPW2E6AZ4EUQ3w2DptTvDAzKr92DbEpHDOWaW7U3rV5ypW6QU7/NaDayRrBQNA/eR08LZytSM+VtNNhLJxdXEuYTzy5uXBnsakowbozLoVGXLDRL/iDFMYfj7d4TXvrPIZoSmSAz0a2/hH0117pNbP1PSsMdAifdl8eFfTqY+4aeSBRFWpEEMW1s/dm5rEIVcY3hXUtdyOlq9tiK/iXsQUsc8QZkp7TcsmqWKd3MnwWl1nbzTwIu8iBOjlS/KqjEBAUfbCYG4dlyLqh5aSi1ndmVKRy4CqcIy8teFMAQKBgQDjeaiuy4Y2/yjCqBBHdj91Hz0zddS008t+znnLiT1PYKr19Ij/D5JGplz9z+qLm7MdDAPb/cEEy2A1D3jALZzqmNGzs4wYFqf5WR9xF3ikky5ZUDKToLXH5MXAf7lBeapDkFMfnSEqKkZZcl4HvVTpWKoAbbsDvz8Arvb63MWmJQKBgQDSuiKcHN3XUYpKaAADOL6Ww5aYUqTEbOqJ1EHHyhYZzfe0Y7QaV5XV07QUaSgfU5iq0Gg229ASgjEqhX4msnWFmLiHXRrdqmQMDJz/shPWupq9PFWXLxXkuUWF6JAM1nOrCum+7jAvA05+5A34KaFXJ6Nf4ZtBB0MPXopuIUm49wKBgQCFrSvctIvF7AKXCc6vQCUgfeAoSplaPpB6j3nxi0/1QriU20Rap1rtm7xQB4wsyK3kZvgpoqB4Yc/CvrnWLbfmATJSqTOtwDqsBJy+h+2EwV1ebQubx7irpJw1eJl0ibvuAnVmwZ8IuVkoHVZ2s+OfUVRt9g4NsKikjS1k1LJt5QKBgQCUmWvkmLqbp0WU+Mfhfbdwy5Y77jOAzKmL7mFAyTJSmhsuWwzbcp4XvoWDyy/e/21uLy7+WH3fP236ZU6zbS9QuYSO+EoEvK52jMJ90Y8J4mdSQOS+ixauRzROnNRQKT5CBJ0W5hKWZIeLFbkAIiNDOZGNAKQh9EQG541I4jKBuwKBgQCXlHkrr/Ho8CPf2YGdjVb7nAuEQCDl0RPtGRgUfCMoXCGrQ8FU+eT8rVKoPuWYpFq4ftOxNunyT/4u5JmbHt+Y+c7jO3eV/QuQ2nWx4CG0uCMJVTqqegi9dEZzHEhmiYV35rcwgMFjAtPsyqrJk7/Nk2hDvv5zyuqr2yFT33KnlA==";
    // 数据返回的格式 (只支持json格式)
    private static final String FORMAT = "json";
    // 验签编码 (根据需要修改)
    private static final String CHARSET = "UTF-8";
    // 支付宝公钥 (请自行填写，真实环境请做对应修改)
    private static final String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAoBU8w09v4qhIlSOJkEz7FGCZopS3Ttze499rLYVM/bByNbm876FYPax3JL/Xc6wgRGjiMeut74J9x7CFdSizfI17qepHIjHNPHNxnemGE1AqnkeuFbbX4T1GaeQ1JmwqC7715IG7CgsOJNd5d5V3JP52BaJFOo1T/J2vqFJrSX1gPavnad9dNSoL6un04mXVJ8Z9p8KiaIy790uVwmBOR0nHQy7XgRb8xSZeQPE7Z0+NFds7QWoCvUbIpnespixJ37lIOKWGI5W913z/JrnX5fMjJFltbFr4TTujareh5T43bRZGX4GXne+LnRlWTIM6CNJ839fNsS/5+blUivQ/cwIDAQAB";
    // 验签加密方法 (根据需要修改)
    private static final String SIGN_TYPE = "RSA2";


    @RequestMapping("/sandboxPay")
    @ResponseBody
    public SaResult sandboxPay(@RequestBody Order order) throws AlipayApiException{
        String orderNum= UUID.randomUUID().toString();
        order.setUid(StpUtil.getLoginId().toString());
        order.setOid(orderNum);
        System.out.println("StpUtil.getLoginId()"+StpUtil.getLoginId());
        this.order=order;
//        System.out.println(order);
//        orderService.addOrder(order);
        AlipayClient alipayClient = new DefaultAlipayClient(URL,APP_ID,APP_PRIVATE_KEY,FORMAT,CHARSET,ALIPAY_PUBLIC_KEY,SIGN_TYPE);
        AlipayTradePrecreateRequest alipayRequest = new AlipayTradePrecreateRequest();
        // 设置支付宝异步通知回调地址 (注意：这个网址必须是可以通过外网访问的网址)
        alipayRequest.setNotifyUrl("http://ecation1.free.idcfengye.com/call");
//        (int) Math.random()

        System.out.println(orderNum+"num");
        String bizContent="{\"out_trade_no\":\""+orderNum+"\",\"total_amount\":\""+order.getAllPrice()+"\",\"subject\":\"测试\",\"store_id\":\"公司名\",\"timeout_express\":\"90m\"}";
        alipayRequest.setBizContent(bizContent);
//                ( "{"   +
//                "\"out_trade_no\":\"123456\"," + // 商户订单号
//                "\"total_amount\":\"88.88\"," +	// 商品价格
//                "\"subject\":\"测试\"," +	// 商品标题
//                "\"store_id\":\"公司名\"," +	// 组织或公司名
//                "\"timeout_express\":\"90m\"}" );	// 订单有效时间
        AlipayTradePrecreateResponse response = alipayClient.execute (alipayRequest);
        // 返回支付宝支付网址，用于生成二维码
        String qr = response.getQrCode();
        return SaResult.data(qr);
    }




    @RequestMapping("/call")
    public void call(HttpServletRequest request, HttpServletResponse response, AliReturnPay aliReturnPay) throws IOException {
        // 通知返回的数据会封装到 AliReturnPay 类中


        response.setContentType("type=text/html;charset=UTF-8");
        String orderNo = aliReturnPay.getOut_trade_no(); // 获得订单号，对数据进行修改

//        Order order=new Order();
//        order.setOid(orderNo);
//        order.setStatus(1);
//        orderService.updateOrder(order);
        System.out.println(aliReturnPay.getTrade_status());
        // 支付成功的返回码
        if (("TRADE_SUCCESS").equals(aliReturnPay.getTrade_status())){
            this.order.setStatus(1);
            orderService.addOrder(this.order);
            Cars cars=new Cars();
            cars.setCid(this.order.getCid());
            cars.setStatus(2);
            carService.updateCar(cars);
            // 向前端发送一条支付成功的通知
            payWebSocket.sendMessage("true");
        }else if(("WAIT_BUYER_PAY").equals(aliReturnPay.getTrade_status())){

        }


    }

}
