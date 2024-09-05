package com.xiyan.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiyan.project.common.ErrorCode;
import com.xiyan.project.exception.BusinessException;
import com.xiyan.project.mapper.UserInterfaceInfoMapper;
import com.xiyan.project.service.UserInterfaceInfoService;
import com.xiyan.xiyanapicommon.model.entity.UserInterfaceInfo;
import org.springframework.stereotype.Service;

/**
* @author 罗文俊
* @description 针对表【user_interface_info(用户调用接口关系)】的数据库操作Service实现
* @createDate 2024-08-13 17:41:36
*/
@Service
public class UserInterfaceInfoServiceImpl extends ServiceImpl<UserInterfaceInfoMapper, UserInterfaceInfo>
    implements UserInterfaceInfoService {

    @Override
    public void validUserInterfaceInfo(UserInterfaceInfo userInterfaceInfo, boolean add) {
        if (userInterfaceInfo == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        // 创建时，所有参数必须非空
        if (add) {
            if (userInterfaceInfo.getInterfaceInfoId() <= 0 || userInterfaceInfo.getUserId() <= 0){
                throw new BusinessException(ErrorCode.PARAMS_ERROR, "接口或用户不存在");
            }
        }
        if (userInterfaceInfo.getLeftNum() < 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "剩余次数不能小于 0");
        }
    }

    @Override
    public boolean invokeCount(long interfaceInfoId, long userId) {
        // 判断
        if (interfaceInfoId <= 0 || userId <= 0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "接口或用户不存在");
        }
        // 找出两个id对应的数据
        // TODO 校验用户是否还有剩余调用次数
        QueryWrapper<UserInterfaceInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userId", userId);
        queryWrapper.eq("interfaceInfoId", interfaceInfoId);
        UserInterfaceInfo one = this.getOne(queryWrapper);
        if (one == null) {// 第一次调用某个接口要新增用户 - 接口关系，而不是 update
            UserInterfaceInfo userInterfaceInfo = new UserInterfaceInfo();
            userInterfaceInfo.setUserId(userId);
            userInterfaceInfo.setInterfaceInfoId(interfaceInfoId);
            userInterfaceInfo.setTotalNum(1);
            return this.save(userInterfaceInfo);
        } else {
            Integer handledTotalNum = one.getTotalNum() + 1;
//            Integer handledLeftNum = one.getLeftNum() - 1;
            one.setTotalNum(handledTotalNum);
//            one.setLeftNum(handledLeftNum);
            return this.updateById(one);
        }
            // 接口被调用时，剩余次数 - 1，总调用次数 + 1
//        UpdateWrapper<UserInterfaceInfo> updateWrapper = new UpdateWrapper<>();
//        updateWrapper.eq("interfaceInfoId", interfaceInfoId);
//        updateWrapper.eq("userId", userId);
//        // todo 在这里加一个分布式锁，提高安全性，还有多判断一些条件
//        updateWrapper.setSql("leftNum = leftNum - 1, totalNum = totalNum + 1");
//        return this.update(updateWrapper);
    }
}




