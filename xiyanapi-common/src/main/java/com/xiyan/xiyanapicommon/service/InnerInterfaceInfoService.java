package com.xiyan.xiyanapicommon.service;

import com.xiyan.xiyanapicommon.model.entity.InterfaceInfo;

/**
* @author 罗文俊
* @description 针对表【interface_info(接口信息)】的数据库操作Service
* @createDate 2024-08-07 12:22:17
*/
public interface InnerInterfaceInfoService {


    /**
     * 从数据库中查询模拟接口是否存在（请求路径、请求方法、请求参数，返回接口信息，为空表示不存在）
     * @param path
     * @param method
     * @return
     */
    InterfaceInfo getInterfaceInfo(String path, String method);
}
