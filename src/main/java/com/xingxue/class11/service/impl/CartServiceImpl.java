package com.xingxue.class11.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xingxue.class11.constants.ExceptionContants;
import com.xingxue.class11.entity.Cart;
import com.xingxue.class11.entity.Deal;
import com.xingxue.class11.entity.mapper.CartMapper;
import com.xingxue.class11.entity.mapper.DealMapper;
import com.xingxue.class11.exception.BaseException;
import com.xingxue.class11.exception.DealException;
import com.xingxue.class11.service.CartService;

/**
 * 购物车
 */
@Service
public class CartServiceImpl implements CartService{

    private static final Logger logger = LoggerFactory.getLogger(CartServiceImpl.class);


    @Autowired private CartMapper cartMapper;
    @Autowired private DealMapper dealMapper;

    /*********************************网站**********************************/

    /**
     * 将Deal加入购物车
     * @param skuId 商品SkuId
     * @param userId 用户ID
     * @param count 数量
     */
    public void addDeal(Long skuId, Long userId, Integer count) throws DealException,BaseException{
        try {
            if (count == null || count < 0) {
                count = 1;
            }
            if(userId == null || skuId == null){
                throw new DealException(ExceptionContants.USER_DATA_ERROR);
            }
            Cart cart = cartMapper.selectByUserIdAndSkuId(userId, skuId);
            //如果用户之前加入过该商品，则更新购物车的数量即可
            if (null != cart) {
                //取出该商品对应的信息
                Deal deal = dealMapper.selectByPrimaryKey(cart.getDealId());
                if(cart.getCount()+count<=deal.getMaxPurchaseCount()){
                    cartMapper.updateCartDealCount(cart.getId(), count);
                }
            } else {
                cart = new Cart();
                cart.setUserId(userId);
                cart.setDealSkuId(skuId);
                cart.setDealId(dealMapper.selectIdBySkuId(skuId));
                cart.setCount(count);
                Date now = new Date();
                cart.setCreateTime(now);
                cart.setUpdateTime(now);
                cartMapper.insert(cart);
            }
        } catch (DealException e) {
            throw e;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new BaseException(ExceptionContants.INNER_ERROR);
        }
    }

    /**
     * 查询购物车商品
     * @param userId
     * @return
     */
    public List<Cart> getCartByUserId(Long userId) throws DealException,BaseException{
        try {
            if(userId == null){
                throw new DealException(ExceptionContants.USER_DATA_ERROR);
            }
            return cartMapper.selectByUserId(userId);
        } catch (DealException e) {
            throw e;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new BaseException(ExceptionContants.INNER_ERROR);
        }
    }

    /**
     * 查询购物车商品数量
     * @param userId
     * @return
     */
    public Long getCartSize(Long userId) throws DealException,BaseException{
        try {
            if(userId == null){
                throw new DealException(ExceptionContants.USER_DATA_ERROR);
            }
            return cartMapper.countCartSize(userId);
        } catch (DealException e) {
            throw e;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new BaseException(ExceptionContants.INNER_ERROR);
        }
    }

    /**
     * 购物车商品数量加1
     * @param cartId
     */
    public void increaseCartDealCount(Long cartId) throws DealException,BaseException{
        try {
            if(cartId == null){
                throw new DealException(ExceptionContants.USER_DATA_ERROR);
            }
            cartMapper.updateCartDealCount(cartId, 1);
        } catch (DealException e) {
            throw e;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new BaseException(ExceptionContants.INNER_ERROR);
        }
    }

    /**
     * 购物车商品数量减1
     * @param cartId
     */
    public void decreaseCartDealCount(Long cartId) throws DealException,BaseException{
        try {
            if(cartId == null){
                throw new DealException(ExceptionContants.USER_DATA_ERROR);
            }
            cartMapper.updateCartDealCount(cartId, -1);
        } catch (DealException e) {
            throw e;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new BaseException(ExceptionContants.INNER_ERROR);
        }
    }

    public List<Cart> getCartsByIds(List<Long> ids) throws DealException,BaseException{
        try {
            if (null == ids || ids.isEmpty()) {
                throw new DealException(ExceptionContants.USER_DATA_ERROR);
            }
            return cartMapper.selectByIds(ids);
        } catch (DealException e) {
            throw e;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new BaseException(ExceptionContants.INNER_ERROR);
        }
    }




}
