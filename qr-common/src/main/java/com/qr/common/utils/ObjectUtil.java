package com.qr.common.utils;

import lombok.SneakyThrows;
import org.apache.commons.beanutils.BeanUtils;

/**
 * 工具类的规范例子
 * 
 * 
 * @author 肖文杰
 * 
 */
public class ObjectUtil {

	@SneakyThrows
	public void copyAttribute(Object source, Object dest) {
		// org.springframework.beans.BeanUtils.copyProperties(source, dest);
		BeanUtils.copyProperties(dest, source);
	}
}
