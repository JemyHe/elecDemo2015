/*
package com.xingxue.class11.controller;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.xingxue.class11.constants.AlipayConfig;
import com.xingxue.class11.entity.Address;
import com.xingxue.class11.entity.Cart;
import com.xingxue.class11.entity.Deal;
import com.xingxue.class11.framework.dto.CartDTO;
import com.xingxue.class11.framework.entity.Pair;
import com.xingxue.class11.framework.entity.WebUser;
import com.xingxue.class11.service.AddressService;
import com.xingxue.class11.service.CartService;
import com.xingxue.class11.service.DealService;
import com.xingxue.class11.service.OrderService;
import com.xingxue.class11.util.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

*/
/**
 * 支付宝支付
 *//*

@Controller
public class AlipayController{

    @Autowired private CartService cartService;

    @Autowired private DealService dealService;

    @Autowired private OrderService orderService;

    @Autowired private AddressService addressService;

    //测试退款接口，将付款的订单号存在静态变量中，省略了数据库操作；真实项目不能这么做
    private static String trade_no= "";

    @RequestMapping(value="/alipay", method = RequestMethod.POST)
    public void alipay(HttpServletRequest request, HttpServletResponse response, String carts, Long addressId,
                       Integer payType, Integer totalPrice) throws Exception {

        WebUser webUser = CookieUtil.getLoginUser(request);

        //保存订单
        List<CartDTO> cartDTOS = JSON.parseArray(carts, CartDTO.class);
        List<Pair<Cart,Deal>> cartDTOs = new ArrayList<>();
        for(CartDTO cart:cartDTOS){
            cartDTOs.add(new Pair<>(cart.getCart(),cart.getDeal()));
        }
        Address address = addressService.getById(addressId);
        Long orderId = orderService.order(Long.valueOf(webUser.getId()), cartDTOs, address, totalPrice, payType);

        //调用支付宝接口
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);

        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

        //商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = "Road_" + orderId + "_" + webUser.getUsername() + "_" + new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
        //付款金额，必填
        String total_amount = "0.01";
        //订单名称，必填
        String subject = "Road_" + webUser.getUsername();
        //商品描述，可空
        String body = "Road_test_order";
        alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
                + "\"total_amount\":\""+ total_amount +"\","
                + "\"subject\":\""+ subject +"\","
                + "\"body\":\""+ body +"\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

        //若想给BizContent增加其他可选请求参数，以增加自定义超时时间参数timeout_express来举例说明
        //alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
        //		+ "\"total_amount\":\""+ total_amount +"\","
        //		+ "\"subject\":\""+ subject +"\","
        //		+ "\"body\":\""+ body +"\","
        //		+ "\"timeout_express\":\"10m\","
        //		+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        //请求参数可查阅【电脑网站支付的API文档-alipay.trade.page.pay-请求参数】章节

        //请求
        String result = alipayClient.pageExecute(alipayRequest).getBody();
        //方便退款使用
        trade_no = out_trade_no;
        //输出
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().println(result);

    }

    */
/**
     * 退款接口
     * @param request
     * @param response
     * @throws Exception
     *//*

    @RequestMapping("/alipay/refund")
    public void alipayRefund(HttpServletRequest request,HttpServletResponse response)throws Exception{
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);

        //设置请求参数
        AlipayTradeRefundRequest alipayRequest = new AlipayTradeRefundRequest();

        //商户订单号，商户网站订单系统中唯一订单号
        String out_trade_no = trade_no;
        //需要退款的金额，该金额不能大于订单金额，必填
        String refund_amount = "0.01";
        //退款的原因说明
        String refund_reason = "正常退款";
        //标识一次退款请求，同一笔交易多次退款需要保证唯一，如需部分退款，则此参数必传
        String out_request_no = out_trade_no+"_1";

        alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
                + "\"refund_amount\":\""+ refund_amount +"\","
                + "\"refund_reason\":\""+ refund_reason +"\","
                + "\"out_request_no\":\""+ out_request_no +"\"}");

        //请求
        String result = alipayClient.execute(alipayRequest).getBody();
        //输出
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().println(result);

    }


    */
/**
     * 支付后异步回调方法
     * @param request
     * @param model
     * @return
     * @throws Exception
     *//*

    @RequestMapping(value="/alipay/return")
    public String alipayReturn(HttpServletRequest request, Model model) throws Exception {
        //获取支付宝POST过来反馈信息
        Map<String,String> params = new HashMap<String,String>();
        Map<String,String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
            valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }

        boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type); //调用SDK验证签名

        //——请在这里编写您的程序（以下代码仅作参考）——

        */
/* 实际验证过程建议商户务必添加以下校验：
            1、需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
            2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
            3、校验通知中的seller_id（或者seller_email) 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email）
            4、验证app_id是否为该商户本身。
        *//*

        if(signVerified) {//验证成功
            //商户订单号
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");

            //支付宝交易号
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

            //交易状态
            String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");

            //请在这里加上商户的业务逻辑程序代码
            //更新订单状态为待发货
            String orderIdStr = out_trade_no.split("_")[1];
            orderService.payed(Long.parseLong(orderIdStr));

            if(trade_status.equals("TRADE_FINISHED")){
                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                //如果有做过处理，不执行商户的业务程序

                //注意：
                //退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
            }else if (trade_status.equals("TRADE_SUCCESS")){
                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                //如果有做过处理，不执行商户的业务程序

                //注意：
                //付款完成后，支付宝系统发送该交易状态通知

            }

        }else {//验证失败
            //调试用，写文本函数记录程序运行情况是否正常
            //String sWord = AlipaySignature.getSignCheckContentV1(params);
            //AlipayConfig.logResult(sWord);
        }

        return "/cart/settlement_ok";
    }
}
*/
