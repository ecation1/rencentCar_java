package com.example.demo.config;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 支付宝接口配置类
 */
@Configuration
public class PayConfig {
    // 请填写您的AppId，例如：2019091767145019（必填）
    private static final String appID = "2021000119670070";
    //应用私钥，这里修改生成的私钥即可（必填）
    private static final String privateKey = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQC7PzGE0iRn+nwd8dITZ7XW2sgeabKylQMwrSNg8TB/CRlWJNHTpGt7XVhq+tP547kUhhRSBHOomhlKgFEDHaNyYY3E/GYznVbw9m53E61RPD/Oya/7fDuvlqakZYKKxCAmGPIoi0pkPakZkl0rVjRb7kRPAJPigwhzLBeicDme1Y4lQCsgopSTYrC1RFpOufP5q9mdCdRokEdTwR9mMZdyPso0h+9jtfOkNgAKqOz//wTYmrEWd+Mjm160dCMJucjO43k1KV3DNU1TCO3ssmiPB8Y6E1fzEA519vgtVrpORb+L8/nF4QbHzXSujLUzh4+qcrcojykMzcNmX2Y3quWzAgMBAAECggEAdWSpJxyYtpfUOPHjQ0dCOfHwhSC/aDcESQBDrgOI5BxQRjHPW2E6AZ4EUQ3w2DptTvDAzKr92DbEpHDOWaW7U3rV5ypW6QU7/NaDayRrBQNA/eR08LZytSM+VtNNhLJxdXEuYTzy5uXBnsakowbozLoVGXLDRL/iDFMYfj7d4TXvrPIZoSmSAz0a2/hH0117pNbP1PSsMdAifdl8eFfTqY+4aeSBRFWpEEMW1s/dm5rEIVcY3hXUtdyOlq9tiK/iXsQUsc8QZkp7TcsmqWKd3MnwWl1nbzTwIu8iBOjlS/KqjEBAUfbCYG4dlyLqh5aSi1ndmVKRy4CqcIy8teFMAQKBgQDjeaiuy4Y2/yjCqBBHdj91Hz0zddS008t+znnLiT1PYKr19Ij/D5JGplz9z+qLm7MdDAPb/cEEy2A1D3jALZzqmNGzs4wYFqf5WR9xF3ikky5ZUDKToLXH5MXAf7lBeapDkFMfnSEqKkZZcl4HvVTpWKoAbbsDvz8Arvb63MWmJQKBgQDSuiKcHN3XUYpKaAADOL6Ww5aYUqTEbOqJ1EHHyhYZzfe0Y7QaV5XV07QUaSgfU5iq0Gg229ASgjEqhX4msnWFmLiHXRrdqmQMDJz/shPWupq9PFWXLxXkuUWF6JAM1nOrCum+7jAvA05+5A34KaFXJ6Nf4ZtBB0MPXopuIUm49wKBgQCFrSvctIvF7AKXCc6vQCUgfeAoSplaPpB6j3nxi0/1QriU20Rap1rtm7xQB4wsyK3kZvgpoqB4Yc/CvrnWLbfmATJSqTOtwDqsBJy+h+2EwV1ebQubx7irpJw1eJl0ibvuAnVmwZ8IuVkoHVZ2s+OfUVRt9g4NsKikjS1k1LJt5QKBgQCUmWvkmLqbp0WU+Mfhfbdwy5Y77jOAzKmL7mFAyTJSmhsuWwzbcp4XvoWDyy/e/21uLy7+WH3fP236ZU6zbS9QuYSO+EoEvK52jMJ90Y8J4mdSQOS+ixauRzROnNRQKT5CBJ0W5hKWZIeLFbkAIiNDOZGNAKQh9EQG541I4jKBuwKBgQCXlHkrr/Ho8CPf2YGdjVb7nAuEQCDl0RPtGRgUfCMoXCGrQ8FU+eT8rVKoPuWYpFq4ftOxNunyT/4u5JmbHt+Y+c7jO3eV/QuQ2nWx4CG0uCMJVTqqegi9dEZzHEhmiYV35rcwgMFjAtPsyqrJk7/Nk2hDvv5zyuqr2yFT33KnlA==";
    //支付宝公钥，而非应用公钥（必填）
    public static final String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAoBU8w09v4qhIlSOJkEz7FGCZopS3Ttze499rLYVM/bByNbm876FYPax3JL/Xc6wgRGjiMeut74J9x7CFdSizfI17qepHIjHNPHNxnemGE1AqnkeuFbbX4T1GaeQ1JmwqC7715IG7CgsOJNd5d5V3JP52BaJFOo1T/J2vqFJrSX1gPavnad9dNSoL6un04mXVJ8Z9p8KiaIy790uVwmBOR0nHQy7XgRb8xSZeQPE7Z0+NFds7QWoCvUbIpnespixJ37lIOKWGI5W913z/JrnX5fMjJFltbFr4TTujareh5T43bRZGX4GXne+LnRlWTIM6CNJ839fNsS/5+blUivQ/cwIDAQAB";
    //默认即可（必填）
    public static final String charset = "utf-8";
    //默认即可（必填）
    public static final String signType = "RSA2";
    @Bean
    public AlipayClient alipayClient(){
        //沙箱环境使用https://openapi.alipaydev.com/gateway.do，线上环境使用https://openapi.alipay.com/gateway.do
        return new DefaultAlipayClient("https://openapi.alipaydev.com/gateway.do", appID, privateKey, "json", charset, publicKey, signType);
    }
    /**
     * 验签，是否正确
     */
    public static boolean checkSign(HttpServletRequest request){
        Map<String, String[]> requestMap = request.getParameterMap();
        Map<String, String> paramsMap = new HashMap<>();
        requestMap.forEach((key, values) -> {
            String strs = "";
            for(String value : values) {
                strs = strs + value;
            }
            System.out.println(key +"===>"+strs);
            paramsMap.put(key, strs);
        });
        System.out.println();
        //调用SDK验证签名
        try {
            return  AlipaySignature.rsaCheckV1(paramsMap, PayConfig.publicKey, PayConfig.charset, PayConfig.signType);
        } catch (AlipayApiException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("*********************验签失败********************");
            return false;
        }

    }
}

