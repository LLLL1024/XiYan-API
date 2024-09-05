package com.xiyan.xiyanapicommon.service;


import com.xiyan.xiyanapicommon.model.entity.UserInterfaceInfo;

/**
* @author 罗文俊
* @description 针对表【user_interface_info(用户调用接口关系)】的数据库操作Service
* @createDate 2024-08-13 17:41:36
*/
public interface InnerUserInterfaceInfoService {


    /**
     * 调用接口统计
     * @param interfaceInfoId
     * @param userId
     * @return
     */
    boolean invokeCount(long interfaceInfoId, long userId);
}
