package com.xingxue.class11.service.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import com.xingxue.class11.constants.ExceptionContants;
import com.xingxue.class11.entity.User;
import com.xingxue.class11.entity.UserBasicInfo;
import com.xingxue.class11.entity.mapper.UserBasicInfoMapper;
import com.xingxue.class11.entity.mapper.UserMapper;
import com.xingxue.class11.exception.BaseException;
import com.xingxue.class11.exception.UserException;
import com.xingxue.class11.service.UserService;

/**
 * Created by Administrator on 2017/6/29.
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired private UserMapper userMapper;
    @Autowired private UserBasicInfoMapper userBasicInfoMapper;

    @Override
    public User login(User user) throws UserException,BaseException{

        try {
            //参数合法性的判断放在service层
            if(null == user || StringUtils.isEmpty(user.getName()) || StringUtils.isEmpty(user.getPassword())){
                throw new UserException(ExceptionContants.USER_DATA_ERROR);
            }
            User dbUser = userMapper.selectByName(user.getName());
            if(null == dbUser){
                throw  new UserException(ExceptionContants.USER_NOT_EXIST);
            }
            //校验密码
            String password = user.getPassword();
            //md5加密-->变大写
            String md5Password = DigestUtils.md5DigestAsHex(password.getBytes()).toUpperCase();
            if(dbUser.getPassword().equals(md5Password)){
                return dbUser;
            }else{
                throw new UserException(ExceptionContants.USER_PASSWORD_WRONG);
            }
        }catch (UserException e) {
            throw e;
        }catch (Exception e) {
            //把所有java运行期的异常转化成自定义异常，方便处理
            //日志级别 ： error warn debug info
            logger.error(e.getMessage()+"["+e.getStackTrace()+"]");
            throw new BaseException(ExceptionContants.INNER_ERROR);
        }


    }

    @Override
    public User register(User user)throws UserException,BaseException {
        try {
            if(null == user || StringUtils.isEmpty(user.getName()) || StringUtils.isEmpty(user.getPassword())){
                throw new UserException(ExceptionContants.USER_DATA_ERROR);
            }
            //注册前验证用户名是否已存在
            User byUsername = getByUsername(user.getName());
            if(byUsername != null){
                throw new UserException(ExceptionContants.USER_NAME_EXSIST);
            }
            Date now = new Date();
            user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()).toUpperCase());
            user.setCreateTime(now);
            user.setLoginTime(now);
            user.setUpdateTime(now);
            int save = userMapper.insertSelective(user);
            if (save == 1){
                User registUser = getByUsername(user.getName());
                return registUser;
            }else {
                return null;
            }
        }catch (UserException e) {
            throw e;
        }catch (Exception e) {
            logger.error(e.getMessage()+"["+e.getStackTrace()+"]");
            throw new BaseException(ExceptionContants.INNER_ERROR);
        }
    }

    @Override
    public User getByUsername(String name) {
        if(StringUtils.isEmpty(name)){
            throw new UserException(ExceptionContants.USER_DATA_ERROR);
        }
        User byName = userMapper.selectByName(name);
        return byName;
    }

    @Override
    public int updateByIdSelective(User user) {
        try {
            if(null == user || null == user.getId()){
                throw new UserException(ExceptionContants.USER_DATA_ERROR);
            }
            return userMapper.updateByPrimaryKeySelective(user);
        } catch (UserException e) {
            throw e;
        } catch (Exception e) {
            logger.error(e.getMessage()+"["+e.getStackTrace()+"]");
            throw new BaseException(ExceptionContants.INNER_ERROR);
        }
    }

    @Override
    public UserBasicInfo getBasicInfoByUserId(Long id) {
        try {
            if(null == id){
                throw new UserException(ExceptionContants.USER_DATA_ERROR);
            }
            UserBasicInfo byId = userBasicInfoMapper.selectByPrimaryKey(id);
            return byId;
        } catch (UserException e) {
            throw e;
        } catch (Exception e) {
            logger.error(e.getMessage()+"["+e.getStackTrace()+"]");
            throw new BaseException(ExceptionContants.INNER_ERROR);
        }
    }

    @Override
    public int updateBasicInfo(UserBasicInfo userBasicInfo) {
        try {
            if(userBasicInfo == null || userBasicInfo.getId() == null){
                throw new UserException(ExceptionContants.USER_DATA_ERROR);
            }
            return userBasicInfoMapper.updateByPrimaryKeySelective(userBasicInfo);
        } catch (UserException e) {
            throw e;
        } catch (Exception e) {
            logger.error(e.getMessage()+"["+e.getStackTrace()+"]");
            throw new BaseException(ExceptionContants.INNER_ERROR);
        }
    }

    @Override
    public int saveBasicInfo(UserBasicInfo userBasicInfo) {

        try {
            if(null == userBasicInfo || StringUtils.isEmpty(userBasicInfo.getMail()) ||
                    StringUtils.isEmpty(userBasicInfo.getNickName()) ||
                    StringUtils.isEmpty(userBasicInfo.getPhone()) ||
                    StringUtils.isEmpty(userBasicInfo.getRealName()) ){
                throw new UserException(ExceptionContants.USER_DATA_ERROR);
            }

            return userBasicInfoMapper.insertSelective(userBasicInfo);
        } catch (UserException e) {
            throw e;
        } catch (Exception e) {
            logger.error(e.getMessage()+"["+e.getStackTrace()+"]");
            throw new BaseException(ExceptionContants.INNER_ERROR);
        }
    }
}
