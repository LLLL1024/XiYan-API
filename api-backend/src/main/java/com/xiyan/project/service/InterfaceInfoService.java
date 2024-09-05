package com.xiyan.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiyan.xiyanapicommon.model.entity.InterfaceInfo;

/**
* @author 罗文俊
* @description 针对表【interface_info(接口信息)】的数据库操作Service
* @createDate 2024-08-07 12:22:17
*/
public interface InterfaceInfoService extends IService<InterfaceInfo> {

    /**
     * 校验
     *
     * @param interfaceInfo
     * @param add
     */
    void validInterfaceInfo(InterfaceInfo interfaceInfo, boolean add);
}
