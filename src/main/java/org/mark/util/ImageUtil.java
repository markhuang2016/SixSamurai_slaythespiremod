package org.mark.util;

import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.lang3.StringUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @description: 图片处理
 * @author: huangzhiqiang
 * @create: 2022/01/07 11:18
 */
public class ImageUtil {

    public static void main(String[] args) throws IOException {
        final String resourceDir =
            "/Users/mark/IdeaProjects/SixSamurai/src/main/resources/SixSamurai/img/relics/";
        final String resizeDir =
            "/Users/mark/IdeaProjects/SixSamurai/src/main/resources/SixSamurai/img/relics/resize/";
        final String destinationDir =
            "/Users/mark/IdeaProjects/SixSamurai/src/main/resources/SixSamurai/img/relics/outline/";
        final String templateImagePath =
            "/Users/mark/IdeaProjects/SixSamurai/src/main/resources/SixSamurai/img/relics/template.png";
        File templateImage = new File(templateImagePath);
        if (!templateImage.exists()) {
            templateImage.createNewFile();
        }

        ImageIO.write(new BufferedImage(128, 128, BufferedImage.TYPE_INT_BGR), "png", templateImage);

        File resDir = new File(resourceDir);
        File destDir = new File(destinationDir);
        if (!destDir.exists()) {
            destDir.mkdir();
        }
        File resiDir = new File(resizeDir);
        if (!resiDir.exists()) {
            resiDir.mkdir();
        }

        File[] files = resDir.listFiles();

        for (File file : files) {
            if (file.isDirectory()) {
                continue;
            }
            String name = file.getName();
            if (!name.endsWith("png")) {
                continue;
            }
            File resizeFile = new File(resizeDir + name);
            if (!resizeFile.exists()) {
                resizeFile.createNewFile();
            }
            File destFile = new File(destinationDir + name);
            if (!destFile.exists()) {
                destFile.createNewFile();
            }
            ImageUtil.resizeImage(file.getPath(), resizeDir + name, 120, 120, true);
            ImageUtil.mergeImages(templateImagePath, resizeDir + name, 4, 4, destFile.getPath(), "png", false);
        }

        //        String resource =
        //            "/Users/mark/IdeaProjects/SixSamurai/src/main/resources/SixSamurai/img/cards/CunningOfTheSixSamurai.png";
        //        String dest =
        //            "/Users/mark/IdeaProjects/SixSamurai/src/main/resources/SixSamurai/img/cards/resize/CunningOfTheSixSamurai.png";
        //        File file = new File(dest);
        //        if (!file.exists()) {
        //            file.createNewFile();
        //        }
        //
        //        resizeImage(resource, dest, 380, 380, false);

    }

    public static void refreshCardsImage() throws IOException {

        final String resourceDir =
            "/Users/mark/IdeaProjects/SixSamurai/src/main/resources/SixSamurai/img/cards/";
        final String resizeDir =
            "/Users/mark/IdeaProjects/SixSamurai/src/main/resources/SixSamurai/img/cards/resize/";
        final String destinationDir =
            "/Users/mark/IdeaProjects/SixSamurai/src/main/resources/SixSamurai/img/cards/dest/";
        final String templateImagePath =
            "/Users/mark/IdeaProjects/SixSamurai/src/main/resources/SixSamurai/img/cards/template.png";
        File templateImage = new File(templateImagePath);
        if (!templateImage.exists()) {
            templateImage.createNewFile();
        }

        ImageIO.write(new BufferedImage(250, 190, BufferedImage.TYPE_INT_BGR), "png", templateImage);

        File resDir = new File(resourceDir);
        File destDir = new File(destinationDir);
        if (!destDir.exists()) {
            destDir.mkdir();
        }
        File resiDir = new File(resizeDir);
        if (!resiDir.exists()) {
            resiDir.mkdir();
        }

        File[] files = resDir.listFiles();

        for (File file : files) {
            if (file.isDirectory()) {
                continue;
            }
            String name = file.getName();
            if (!name.endsWith("png")) {
                continue;
            }
            File resizeFile = new File(resizeDir + name);
            if (!resizeFile.exists()) {
                resizeFile.createNewFile();
            }
            File destFile = new File(destinationDir + name);
            if (!destFile.exists()) {
                destFile.createNewFile();
            }
            ImageUtil.resizeImage(file.getPath(), resizeDir + name, 190, 190, true);
            ImageUtil.mergeImages(templateImagePath, resizeDir + name, 30, 0, destFile.getPath(), "png", false);
        }
    }

    /**
     * 绘制图片
     *
     * @param bufferedImage 缓冲图片
     * @param type          图片类型
     * @param file          文件
     * @throws IOException
     */
    public static boolean drawSimpleImage(BufferedImage bufferedImage, String type, File file) throws IOException {
        Graphics g = bufferedImage.getGraphics();//获取图片画笔
        try {
            int backgroundX = 10;//背景x坐标
            int backgroundY = 40;//背景y坐标
            int backgroundWith = 180;//背景宽
            int backgroundHeight = 120;//背景高
            g.fillRect(backgroundX, backgroundY, backgroundWith, backgroundHeight);//填充背景，默认白色

            g.setColor(new Color(120, 120, 120));//设置画笔颜色

            int fontSize = 28;//字体大小
            g.setFont(new Font("宋体", Font.BOLD, fontSize));//设置字体

            int stringX = 10;//文字x坐标
            int stringY = 100;//文字y坐标
            g.drawString("绘制简单图片", stringX, stringY);
            return ImageIO.write(bufferedImage, type, file);
        } finally {
            g.dispose();//释放画笔
        }
    }

    /**
     * 重新生成图片宽、高
     *
     * @param srcPath   图片路径
     * @param destPath  新生成的图片路径
     * @param newWith   新的宽度
     * @param newHeight 新的高度
     * @param forceSize 是否强制使用指定宽、高,false:会保持原图片宽高比例约束
     * @return
     * @throws IOException
     */
    public static boolean resizeImage(String srcPath, String destPath, int newWith, int newHeight, boolean forceSize)
        throws IOException {
        if (forceSize) {
            Thumbnails.of(srcPath).forceSize(newWith, newHeight).toFile(destPath);
        } else {
            Thumbnails.of(srcPath).width(newWith).height(newHeight).toFile(destPath);
        }
        return true;
    }

    /**
     * 合并两个图片
     *
     * @param img1Path   图片1地址
     * @param img2Path   图片2地址
     * @param x          图片2左上点在X轴起始坐标
     * @param y          图片2左上点在y轴起始坐标
     * @param destPath   合并两个图片生成新的图片地址
     * @param formatName 图片格式  bmp|gif|jpg|jpeg|png
     * @param maxBorder  ture:取两图最大边框;false:以图片1尺寸和坐标为准
     * @return
     * @throws IOException
     */
    public static boolean mergeImages(String img1Path, String img2Path,
        int x, int y, String destPath, String formatName, boolean maxBorder) throws IOException {
        boolean isSuccess = false;
        if (StringUtils.isNotEmpty(img1Path) && StringUtils.isNotEmpty(img2Path)) {
            BufferedImage img1 = ImageIO.read(new File(img1Path));//读取图片1
            BufferedImage img2 = ImageIO.read(new File(img2Path));//读取图片2
            if (!maxBorder) {//以图片1尺寸和坐标为准
                isSuccess = drawNewImageInImage1(img1, img2, x, y, destPath, formatName);
            } else {//取两图最大边框
                if (!needReptain(img1, img2, x, y)) {
                    isSuccess = drawNewImageInImage1(img1, img2, x, y, destPath, formatName);
                } else {
                    int w1 = img1.getWidth();
                    int w2 = img2.getWidth();
                    int wx1 = w1 - x;

                    int h1 = img1.getHeight();
                    int h2 = img2.getHeight();
                    int hy1 = h1 - y;
                    //创建背景图片
                    BufferedImage blankImage =
                        new BufferedImage(wx1 >= w2 ? (x >= 0 ? w1 : w1 - x) : (x >= 0 ? w2 + x : w2),
                            hy1 > h2 ? (y >= 0 ? h1 : h1 - y) : (y >= 0 ? h2 + y : h2), BufferedImage.TYPE_INT_RGB);
                    //画图片1、图片2
                    Graphics blankImgGraphics = null;
                    try {
                        blankImgGraphics = blankImage.getGraphics();
                        blankImgGraphics
                            .drawImage(img1, x < 0 ? -1 * x : 0, y < 0 ? -1 * y : 0, img1.getWidth(), img1.getHeight(),
                                null);
                        blankImgGraphics
                            .drawImage(img2, x < 0 ? 0 : x, y < 0 ? 0 : y, img2.getWidth(), img2.getHeight(), null);
                        isSuccess = ImageIO.write(blankImage, formatName, new File(destPath));
                    } finally {
                        if (blankImgGraphics != null) {
                            blankImgGraphics.dispose();
                        }
                    }
                }
            }
        }
        return isSuccess;
    }

    /**
     * 在图片1中画图片2
     *
     * @param x          X坐标位置
     * @param y          Y坐标位置
     * @param destPath   新图片存储位置
     * @param formatName 图片格式
     * @param img1       图片1
     * @param img2       图片2
     * @return
     * @throws IOException
     */
    private static boolean drawNewImageInImage1(BufferedImage img1, BufferedImage img2,
        int x, int y, String destPath, String formatName) throws IOException {
        boolean isSuccess;
        Graphics img1Graphics = null;
        try {
            img1Graphics = img1.getGraphics();
            img1Graphics.drawImage(img2, x, y, img2.getWidth(), img2.getHeight(), null);
            isSuccess = ImageIO.write(img1, formatName, new File(destPath));
        } finally {
            if (img1Graphics != null) {
                img1Graphics.dispose();
            }
        }
        return isSuccess;
    }

    /**
     * 判断是否需要重新画最大边框的图片背景
     *
     * @param img1 图片1
     * @param img2 图片2
     * @param x    图片2起始X坐标
     * @param y    图片2起始Y坐标
     * @return
     */
    private static boolean needReptain(BufferedImage img1, BufferedImage img2,
        int x, int y) {
        if (img1 == null || img2 == null || img1.getWidth() <= 0 || img1.getHeight() <= 0
            || img2.getWidth() <= 0 || img2.getHeight() <= 0) {
            throw new IllegalArgumentException("图片信息不正确");
        }
        int w1 = img1.getWidth();
        int h1 = img1.getHeight();
        int w2 = img2.getWidth();
        int h2 = img2.getHeight();
        return x < 0 || y < 0 || w1 - x < w2 || h1 - y < h2;
    }

}
