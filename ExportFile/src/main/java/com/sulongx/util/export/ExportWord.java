package com.sulongx.util.export;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 导出Word
 * @author Sulongx
 * @version 1.0
 */
public class ExportWord<T> implements ExportAPI {

    private T model;
    private Map<String,T> dataMap;
    private final static Configuration config;

    //初始化config实例
    static {
        config = new Configuration(Configuration.VERSION_2_3_22);
        config.setDefaultEncoding("UTF-8");
    }

    /**
     * 构造函数-加载模板所在位置
     * @param templatePath 模板所在目录
     */
    public ExportWord(String templatePath) throws IOException {
        config.setDirectoryForTemplateLoading(new File(templatePath));
        config.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
    }

    public <M> Boolean createNewByTemplate(String templateName, M model, String outFilePath) throws IOException {

        return null;
    }

}
