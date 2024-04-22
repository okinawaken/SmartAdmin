package net.lab1024.sa.admin.util.excel;

import cn.hutool.core.date.DateUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.alibaba.excel.EasyExcel;
import lombok.extern.slf4j.Slf4j;
import net.lab1024.sa.admin.module.system.login.domain.LoginResultVO;
import net.lab1024.sa.admin.module.system.login.service.LoginService;
import net.lab1024.sa.admin.util.AdminRequestUtil;
import net.lab1024.sa.base.common.util.SmartResponseUtil;
import org.springframework.core.env.Environment;

import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author liuzhexian
 * @since 2024-04-16
 * @email 1037512352@qq.com
 */
@Slf4j
public class ExcelUtils<T> implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 导出文件后缀
     */
    public static final String XLSX = ".xlsx";

    /**
     * 实体对象
     */
    public Class<T> clazz;

    public ExcelUtils(Class<T> clazz) {
        this.clazz = clazz;
    }

    /**
     * 通用导出方法
     *
     * @param response response
     * @param list     导出的列表
     * @param title    导出的名称
     */
    public void exportData(HttpServletResponse response, List<T> list, String title) {
        // 设置下载消息头
        SmartResponseUtil.setDownloadFileHeader(response, title + XLSX, null);
        // 根据配置文件中的参数判断导出是否带水印
        String show = SpringUtil.getBean(Environment.class).getProperty("export.show-watermark");
        if(Boolean.parseBoolean(show)) {
            exportSheetWithWatermark(response, list, title);
        } else {
            exportSheet(response, list, title);
        }
    }

    /**
     * 通用导出方法(不带水印)
     *
     * @param response response
     * @param list     导出的列表
     * @param title    导出的名称
     */
    public void exportSheet(HttpServletResponse response, List<T> list, String title) {
        try {
            EasyExcel.write(response.getOutputStream(), this.clazz).sheet(title).doWrite(list);
        } catch (Exception e) {
            log.error("{}导出异常", title, e);
        }
    }

    /**
     * 通用导出方法(带水印)
     *
     * @param response   response
     * @param exportList 导出的列表
     * @param title      导出的名称
     */
    public void exportSheetWithWatermark(HttpServletResponse response, List<T> exportList, String title) {
        try {
            LoginResultVO loginResult = SpringUtil.getBean(LoginService.class).getLoginResult(AdminRequestUtil.getRequestUser());
            // 注册水印处理器  水印  员工昵称 + 日期
            Watermark watermark = new Watermark(String.format("%s %s",loginResult.getActualName(), DateUtil.formatDate(new Date())));
            // 一定要inMemory
            EasyExcel.write(response.getOutputStream(), this.clazz)
                    .inMemory(true).sheet(title).registerWriteHandler(new CustomWaterMarkHandler(watermark)).doWrite(exportList);
        } catch (Exception e) {
            log.error("{}导出异常", title, e);
        }
    }
}
