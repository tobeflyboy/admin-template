package com.nutcracker.common.util;

import cn.idev.excel.EasyExcel;
import cn.idev.excel.event.AnalysisEventListener;

import java.io.InputStream;

/**
 * Excel 工具类
 *
 * @author 胡桃夹子
 * @since 2023/03/01
 */
public class ExcelUtils {

    public static <T> void importExcel(InputStream is, Class clazz, AnalysisEventListener<T> listener) {
        EasyExcel.read(is, clazz, listener).sheet().doRead();
    }
}
