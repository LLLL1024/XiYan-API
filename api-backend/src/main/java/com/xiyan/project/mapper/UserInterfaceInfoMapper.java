package com.xiyan.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiyan.xiyanapicommon.model.entity.UserInterfaceInfo;

import java.util.List;

/**
* @author 罗文俊
* @description 针对表【user_interface_info(用户调用接口关系)】的数据库操作Mapper
* @createDate 2024-08-13 17:41:36
* @Entity com.xiyan.project.model.entity.UserInterfaceInfo
*/
public interface UserInterfaceInfoMapper extends BaseMapper<UserInterfaceInfo> {

    List<UserInterfaceInfo> listTopInvokeInterfaceInfo(int limit);

}




