package net.lab1024.sa.admin.util.excel;


import com.alibaba.excel.write.handler.SheetWriteHandler;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteWorkbookHolder;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.openxml4j.opc.PackagePartName;
import org.apache.poi.openxml4j.opc.PackageRelationship;
import org.apache.poi.openxml4j.opc.TargetMode;
import org.apache.poi.xssf.usermodel.XSSFPictureData;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

/**
 *
 * @author liuzhexian
 * @since 2024-04-16
 * @email 1037512352@qq.com
 */
@Slf4j
public class CustomWaterMarkHandler implements SheetWriteHandler {

    private final Watermark watermark;

    public CustomWaterMarkHandler(Watermark watermark) {
        this.watermark = watermark;
    }

    @Override
    public void afterSheetCreate(WriteWorkbookHolder writeWorkbookHolder, WriteSheetHolder writeSheetHolder) {
        BufferedImage bufferedImage = createWatermarkImage();
        XSSFWorkbook workbook = (XSSFWorkbook) writeSheetHolder.getParentWriteWorkbookHolder().getWorkbook();
        try {
            // 添加水印的具体操作
            addWatermarkToSheet(workbook, bufferedImage);
        } catch (Exception e) {
            log.error("添加水印出错:", e);
        }

    }

    /**
     * 创建水印图片
     *
     * @return
     */
    private BufferedImage createWatermarkImage() {
        // 获取水印相关参数
        Font font = watermark.getFont();
        int width = watermark.getWidth();
        int height = watermark.getHeight();
        Color color = watermark.getColor();
        String text = watermark.getContent();

        // 创建带有透明背景的 BufferedImage
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = image.createGraphics();

        // 设置画笔字体、平滑、颜色
        g.setFont(font);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(color);

        // 计算水印位置和角度
        int y = watermark.getYAxis();
        int x = watermark.getXAxis();
        AffineTransform transform = AffineTransform.getRotateInstance(Math.toRadians(-watermark.getAngle()), 0, y);
        g.setTransform(transform);
        // 绘制水印文字
        g.drawString(text, x, y);

        // 释放资源
        g.dispose();

        return image;
    }

    private void addWatermarkToSheet(XSSFWorkbook workbook, BufferedImage watermarkImage) {
        try (ByteArrayOutputStream os = new ByteArrayOutputStream()) {
            ImageIO.write(watermarkImage, "png", os);
            int pictureIdx = workbook.addPicture(os.toByteArray(), XSSFWorkbook.PICTURE_TYPE_PNG);
            XSSFPictureData pictureData = workbook.getAllPictures().get(pictureIdx);
            for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
                // 获取每个Sheet表
                XSSFSheet sheet = workbook.getSheetAt(i);
                PackagePartName ppn = pictureData.getPackagePart().getPartName();
                String relType = XSSFRelation.IMAGES.getRelation();
                PackageRelationship pr = sheet.getPackagePart().addRelationship(ppn, TargetMode.INTERNAL, relType, null);
                sheet.getCTWorksheet().addNewPicture().setId(pr.getId());
            }
        } catch (Exception e) {
            // 处理ImageIO.write可能抛出的异常
            log.error("添加水印图片时发生错误", e);
        }
    }
}

