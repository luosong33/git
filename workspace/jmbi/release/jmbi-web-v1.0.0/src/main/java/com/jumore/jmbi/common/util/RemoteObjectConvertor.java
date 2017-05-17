package com.jumore.jmbi.common.util;

import java.util.List;

import com.jumore.jmbi.common.exceptions.RemoteGenericException;
import com.jumore.ucenter.dubbo.service.api.base.dto.PageResponseDTO;
import com.jumore.ucenter.dubbo.service.api.base.dto.ResponseDTO;

/**
 * 
 *
 * @author: Lao Yang
 * @since: 2016年6月28日  下午4:21:13
 * @history:
 */
public class RemoteObjectConvertor {

    /*
     * 有返回值,没有成功返回异常
     */
    public static <T> T convert(ResponseDTO<T> remoteResponseDTO) throws RemoteGenericException {
        if (remoteResponseDTO.isSuccess()) {
            return remoteResponseDTO.getResult();
        } else if (remoteResponseDTO.isNoData()) {
            return null;
        } else {
            throw new RemoteGenericException(remoteResponseDTO.getCode().name(), remoteResponseDTO.getDesc());
        }
    }

    /*
     * 无返回值，判断是否执行成功，没成功则抛出异常
     */
    public static void handleIfSuccess(ResponseDTO<?> remoteResponseDTO) throws RemoteGenericException {
        if (!remoteResponseDTO.isSuccess()) {
            throw new RemoteGenericException(remoteResponseDTO.getCode().name(), remoteResponseDTO.getDesc());
        }
    }

    /*
     * 集合版本，有返回值,没有成功返回异常
     */
    public static <T> List<T> convertList(PageResponseDTO<List<T>> remoteResponseDTOs) throws RemoteGenericException {
        if (remoteResponseDTOs.isSuccess()) {
            return remoteResponseDTOs.getResult();
        } else {
            throw new RemoteGenericException(remoteResponseDTOs.getCode().name(), remoteResponseDTOs.getDesc());
        }
    }
}
