package com.sulongx.util.export;

import java.io.IOException;

/**
 * 导出工具类接口
 * @author Sulongx
 * @date 2019/07/31
 * @version 1.0
 */
public interface ExportAPI {

    /**
     * 根据模板生成新的文件
     * @param templateName 模板文件名
     * @param model 填充的数据
     * @param outFilePath 指定
     * @return
     */
    <M> Boolean createNewByTemplate(String templateName,M model,String outFilePath) throws IOException;

}
