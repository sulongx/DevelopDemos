package com.sulongx.util.export;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

import javax.xml.stream.FactoryConfigurationError;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * 导出Word
 * @author Sulongx
 * @version 1.0
 */
public class ExportWord {


    private final static String templatePath;
    private Map<String,Object> dataMap;
    private final static Configuration config;
    private static Template template;


    //初始化config实例
    static {
        //模板所在资源相对路径
        templatePath = "/template/";
        config = new Configuration(Configuration.VERSION_2_3_22);
        config.setDefaultEncoding("UTF-8");
    }

    private ExportWord(){}

    /**
     * 加载模板
     * @param templateName 模板名
     */
    public  ExportWord loadTemplate(String templateName) throws IOException{
        config.setDirectoryForTemplateLoading(
                new File(ExportWord.class.getResource("/").getPath()
                        + templatePath
                        + templateName));
        config.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        template = config.getTemplate(templateName);
        return this;
    }

    /**
     * 加载数据
     * @param data
     * @return
     */
    public ExportWord loadData(Object data){
        dataMap = new HashMap<String, Object>();
        dataMap.put("data",data);
        return this;
    }

    /**
     * 导出文件
     * @param outFilePath 自定义输出路径
     * @return
     */
    public Boolean export(String outFilePath){
        if(dataMap == null){
            throw new NullPointerException("dataMap数据未加载!");
        }
        try(
            FileOutputStream out = new FileOutputStream(outFilePath)
        ) {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out,"UTF-8"));
            template.process(dataMap, writer);
            writer.flush();
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }


    public InputStream getExportInputStream(String id){
        return null;
    }

}
