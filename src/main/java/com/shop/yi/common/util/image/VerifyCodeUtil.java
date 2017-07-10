package com.shop.yi.common.util.image;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Random;

import javax.imageio.ImageIO;

/**
 * 验证码生成器
 * @see 可生成数字、大写、小写字母及三者混合类型的验证码
 * @see 支持自定义验证码字符数量,支持自定义验证码图片的大小,支持自定义需排除的特殊字符,支持自定义干扰线的数量,支持自定义验证码图文颜色
 */
public class VerifyCodeUtil {
    //使用到Algerian字体，系统里没有的话需要安装字体，字体只显示大写，去掉了1,0,i,o几个容易混淆的字符
    public static final String VERIFY_CODES = "23456789ABCDEFGHJKMNPQRSTUVWXYZabcdefghjkmnpqrstuvwxyz";
    private static Random random = new Random();
    
 // 验证码类型
 	public static final String VERIFY_TYPE_COMMENT = "VERIFY_TYPE_COMMENT";

 	/**
 	 * 验证码类型为仅数字 0~9
 	 */
 	public static final int TYPE_NUM_ONLY = 0;

 	/**
 	 * 验证码类型为仅字母，即大写、小写字母混合
 	 */
 	public static final int TYPE_LETTER_ONLY = 1;

 	/**
 	 * 验证码类型为数字、大写字母、小写字母混合
 	 */
 	public static final int TYPE_ALL_MIXED = 2;

 	/**
 	 * 验证码类型为数字、大写字母混合
 	 */
 	public static final int TYPE_NUM_UPPER = 3;

 	/**
 	 * 验证码类型为数字、小写字母混合
 	 */
 	public static final int TYPE_NUM_LOWER = 4;

 	/**
 	 * 验证码类型为仅大写字母
 	 */
 	public static final int TYPE_UPPER_ONLY = 5;

 	/**
 	 * 验证码类型为仅小写字母
 	 */
 	public static final int TYPE_LOWER_ONLY = 6;
 
 
    /**
     * 使用系统默认字符源生成验证码
     * @param verifySize    验证码长度
     * @return
     */
    public static String generateVerifyCode(int verifySize){
    	
        return generateVerifyCode(verifySize, VERIFY_CODES);
    }
    
    
    /**
     * 使用系统默认字符源生成验证码
     * @param verifySize    验证码长度 大于0的整数
     * @param type
	 *            验证码类型，参见本类的静态属性
	 * @param exChars
	 *            需排除的特殊字符（仅对数字、字母混合型验证码有效，无需排除则为null）
     * @return
     */
    public static String generateVerifyCode2(int verifySize, int codeType, String exchars){
    	
        return generateTextCode(codeType, verifySize, exchars);
    }
    /**
	 * 生成验证码字符串
	 * 
	 * @param type
	 *            验证码类型，参见本类的静态属性
	 * @param length
	 *            验证码长度，大于0的整数
	 * @param exChars
	 *            需排除的特殊字符（仅对数字、字母混合型验证码有效，无需排除则为null）
	 * @return 验证码字符串
	 */
	public static String generateTextCode(int type, int length, String exChars) {

		if (length <= 0)
			return "";

		StringBuffer code = new StringBuffer();
		int i = 0;
		Random r = new Random();

		switch (type) {

		// 仅数字
		case TYPE_NUM_ONLY:
			while (i < length) {
				int t = r.nextInt(10);
				if (exChars == null || exChars.indexOf(t + "") < 0) {// 排除特殊字符
					code.append(t);
					i++;
				}
			}
			break;

		// 仅字母（即大写字母、小写字母混合）
		case TYPE_LETTER_ONLY:
			while (i < length) {
				int t = r.nextInt(123);
				if ((t >= 97 || (t >= 65 && t <= 90))
						&& (exChars == null || exChars.indexOf((char) t) < 0)) {
					code.append((char) t);
					i++;
				}
			}
			break;

		// 数字、大写字母、小写字母混合
		case TYPE_ALL_MIXED:
			while (i < length) {
				int t = r.nextInt(123);
				if ((t >= 97 || (t >= 65 && t <= 90) || (t >= 48 && t <= 57))
						&& (exChars == null || exChars.indexOf((char) t) < 0)) {
					code.append((char) t);
					i++;
				}
			}
			break;

		// 数字、大写字母混合
		case TYPE_NUM_UPPER:
			while (i < length) {
				int t = r.nextInt(91);
				if ((t >= 65 || (t >= 48 && t <= 57))
						&& (exChars == null || exChars.indexOf((char) t) < 0)) {
					code.append((char) t);
					i++;
				}
			}
			break;

		// 数字、小写字母混合
		case TYPE_NUM_LOWER:
			while (i < length) {
				int t = r.nextInt(123);
				if ((t >= 97 || (t >= 48 && t <= 57))
						&& (exChars == null || exChars.indexOf((char) t) < 0)) {
					code.append((char) t);
					i++;
				}
			}
			break;

		// 仅大写字母
		case TYPE_UPPER_ONLY:
			while (i < length) {
				int t = r.nextInt(91);
				if ((t >= 65)
						&& (exChars == null || exChars.indexOf((char) t) < 0)) {
					code.append((char) t);
					i++;
				}
			}
			break;

		// 仅小写字母
		case TYPE_LOWER_ONLY:
			while (i < length) {
				int t = r.nextInt(123);
				if ((t >= 97)
						&& (exChars == null || exChars.indexOf((char) t) < 0)) {
					code.append((char) t);
					i++;
				}
			}
			break;

		}

		return code.toString();
	}
    /**
     * 使用指定源生成验证码
     * @param verifySize    验证码长度
     * @param sources   验证码字符源
     * @return
     */
    public static String generateVerifyCode(int verifySize, String sources){
        if(sources == null || sources.length() == 0){
            sources = VERIFY_CODES;
        }
        int codesLen = sources.length();
        Random rand = new Random(System.currentTimeMillis());
        StringBuilder verifyCode = new StringBuilder(verifySize);
        for(int i = 0; i < verifySize; i++){
            verifyCode.append(sources.charAt(rand.nextInt(codesLen-1)));
        }
        return verifyCode.toString();
    }
 
    /**
     * 生成随机验证码文件,并返回验证码值
     * @param w
     * @param h
     * @param outputFile
     * @param verifySize
     * @return
     * @throws IOException
     */
    public static String outputVerifyImage(int w, int h, File outputFile, int verifySize) throws IOException{
        String verifyCode = generateVerifyCode(verifySize);
        outputImage(w, h, outputFile, verifyCode);
        return verifyCode;
    }
 
    /**
     * 输出随机验证码图片流,并返回验证码值
     * @param width
     * @param height
     * @param os
     * @param verifySize
     * @return
     * @throws IOException
     */
    public static String outputVerifyImage(int width, int height, OutputStream os, int verifySize) throws IOException{
        String verifyCode = generateVerifyCode(verifySize);
        outputImage(width, height, os, verifyCode);
        return verifyCode;
    }
 
    /**
     * 生成指定验证码图像文件
     * @param width
     * @param height
     * @param outputFile
     * @param code
     * @throws IOException
     */
    public static void outputImage(int width, int height, File outputFile, String code) throws IOException{
        if(outputFile == null){
            return;
        }
        File dir = outputFile.getParentFile();
        if(!dir.exists()){
            dir.mkdirs();
        }
        try{
            outputFile.createNewFile();
            FileOutputStream fos = new FileOutputStream(outputFile);
            outputImage(width, height, fos, code);
            fos.close();
        } catch(IOException e){
            throw e;
        }
    }
 
    public static BufferedImage getImage(int width,int height,String code){
        int verifySize = code.length();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Random rand = new Random();
        Graphics2D g2 = image.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        Color[] colors = new Color[5];
        Color[] colorSpaces = new Color[] { Color.WHITE, Color.CYAN,
                Color.GRAY, Color.LIGHT_GRAY, Color.MAGENTA, Color.ORANGE,
                Color.PINK, Color.YELLOW };
        float[] fractions = new float[colors.length];
        for(int i = 0; i < colors.length; i++){
            colors[i] = colorSpaces[rand.nextInt(colorSpaces.length)];
            fractions[i] = rand.nextFloat();
        }
        Arrays.sort(fractions);
 
        g2.setColor(Color.GRAY);// 设置边框色
        g2.fillRect(0, 0, width, height);
 
        Color c = getRandColor(200, 250);
        g2.setColor(c);// 设置背景色
        g2.fillRect(0, 2, width, height-4);
 
        //绘制干扰线
        Random random = new Random();
        g2.setColor(getRandColor(160, 200));// 设置线条的颜色
        for (int i = 0; i < 20; i++) {
            int x = random.nextInt(width - 1);
            int y = random.nextInt(height - 1);
            int xl = random.nextInt(6) + 1;
            int yl = random.nextInt(12) + 1;
            g2.drawLine(x, y, x + xl + 40, y + yl + 20);
        }
 
        // 添加噪点
        float yawpRate = 0.05f;// 噪声率
        int area = (int) (yawpRate * width * height);
        for (int i = 0; i < area; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int rgb = getRandomIntColor();
            image.setRGB(x, y, rgb);
        }
 
        shear(g2, width, height, c);// 使图片扭曲
 
        g2.setColor(getRandColor(100, 160));
        int fontSize = height-4;
        Font font = new Font("Algerian", Font.ITALIC, fontSize);
        g2.setFont(font);
        char[] chars = code.toCharArray();
        for(int i = 0; i < verifySize; i++){
            AffineTransform affine = new AffineTransform();
            affine.setToRotation(Math.PI / 4 * rand.nextDouble() * (rand.nextBoolean() ? 1 : -1), (width / verifySize) * i + fontSize/2, height/2);
            g2.setTransform(affine);
            //g2.drawChars(chars, i, 1, ((width-10) / verifySize) * i + 5, height/2 + fontSize/2 - 10);
            g2.drawString(String.valueOf(chars[i]), ((width-10) / verifySize) * i + 5, height/2 + fontSize/2 - 10);
        }
 
        g2.dispose();
        return image;
    }
 
    /**
     * 输出指定验证码图片流
     * @param width
     * @param height
     * @param os
     * @param code
     * @throws IOException
     */
    public static void outputImage(int width, int height, OutputStream os, String code) throws IOException{
        BufferedImage image = getImage(width,height,code);
        ImageIO.write(image, "jpg", os);
    }
 
    private static Color getRandColor(int fc, int bc) {
        if (fc > 255)
            fc = 255;
        if (bc > 255)
            bc = 255;
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }
 
    private static int getRandomIntColor() {
        int[] rgb = getRandomRgb();
        int color = 0;
        for (int c : rgb) {
            color = color << 8;
            color = color | c;
        }
        return color;
    }
 
    private static int[] getRandomRgb() {
        int[] rgb = new int[3];
        for (int i = 0; i < 3; i++) {
            rgb[i] = random.nextInt(255);
        }
        return rgb;
    }
 
    private static void shear(Graphics g, int width, int height, Color color) {
        shearX(g, width, height, color);
        shearY(g, width, height, color);
    }
 
    private static void shearX(Graphics g, int width1, int height1, Color color) {
 
        int period = random.nextInt(2);
 
        boolean borderGap = true;
        int frames = 1;
        int phase = random.nextInt(2);
 
        for (int i = 0; i < height1; i++) {
            double d = (double) (period >> 1)
                    * Math.sin((double) i / (double) period
                    + (6.2831853071795862D * (double) phase)
                    / (double) frames);
            g.copyArea(0, i, width1, 1, (int) d, 0);
            if (borderGap) {
                g.setColor(color);
                g.drawLine((int) d, i, 0, i);
                g.drawLine((int) d + width1, i, width1, i);
            }
        }
 
    }
 
    private static void shearY(Graphics g, int width, int height, Color color) {
 
        int period = random.nextInt(40) + 10; // 50;
 
        boolean borderGap = true;
        int frames = 20;
        int phase = 7;
        for (int i = 0; i < width; i++) {
            double d = (double) (period >> 1)
                    * Math.sin((double) i / (double) period
                    + (6.2831853071795862D * (double) phase)
                    / (double) frames);
            g.copyArea(i, 0, 1, height, 0, (int) d);
            if (borderGap) {
                g.setColor(color);
                g.drawLine(i, (int) d, i, 0);
                g.drawLine(i, (int) d + height, i, height);
            }
 
        }
 
    }
    public static void main(String[] args) throws IOException{
        File dir = new File("d:/verifies");
        int w = 45, h = 80;
        for(int i = 0; i < 50; i++){
//            String verifyCode = generateVerifyCode(5);
//            File file = new File(dir, verifyCode + ".jpg");
//            outputImage(w*verifyCode.length(), h, file, verifyCode);
            String verifyCode1 = generateVerifyCode2(5,TYPE_ALL_MIXED, null);
            File file1 = new File(dir, verifyCode1 + ".jpg");
            outputImage(w*verifyCode1.length(), h, file1, verifyCode1);
        }
    }
}