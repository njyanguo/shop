package com.shop.yi.login.controller;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shop.yi.common.util.ImageCodeUtils;
import com.shop.yi.login.service.LoginService;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private LoginService loginService;

	@RequestMapping("/index.do")
	public String login(HttpServletRequest request) {
		return "login";
	}
	@RequestMapping("/login.do")
	public void loginCheck(HttpServletRequest request){
		String password = request.getParameter("password");
		String userId = request.getParameter("userId");
		loginService.loginAuth(userId, password);
	}
	
	@RequestMapping("/getImageCode.do")
	public void getImageCode(HttpServletRequest request,
			HttpServletResponse response, HttpSession session)
			throws IOException {
		// 禁止缓存
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);

		// 生成验证码，写入用户session
		String imageCode = ImageCodeUtils.generateTextCode(
				ImageCodeUtils.TYPE_NUM_UPPER, 4, "0oOilJI1");
		request.getSession().setAttribute(ImageCodeUtils.VERIFY_TYPE_COMMENT,
				imageCode);
		LOGGER.info("verifyCode=" + imageCode);

		// 输出验证码给客户端
		response.setContentType("image/jpeg");
		/*
		 * textCode 文本验证码 width 图片宽度 height 图片高度 interLine 图片中干扰线的条数
		 * randomLocation 每个字符的高低位置是否随机 backColor 图片颜色，若为null，则采用随机颜色 foreColor
		 * 字体颜色，若为null，则采用随机颜色 lineColor 干扰线颜色，若为null，则采用随机颜色
		 */
		BufferedImage bim = ImageCodeUtils.generateImageCode(imageCode, 70,
				22, 15, true, Color.WHITE, Color.BLACK, null);
		ServletOutputStream out = response.getOutputStream();
		ImageIO.write(bim, "JPEG", out);
		try {
			out.flush();
		} finally {
			out.close();
		}
	}

}


/**
 * public void getAuthCode(HttpServletRequest request, HttpServletResponse response,HttpSession session)
            throws IOException {
        int width = 63;
        int height = 37;
        Random random = new Random();
        //设置response头信息
        //禁止缓存
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);

        //生成缓冲区image类
        BufferedImage image = new BufferedImage(width, height, 1);
        //产生image类的Graphics用于绘制操作
        Graphics g = image.getGraphics();
        //Graphics类的样式
        g.setColor(this.getRandColor(200, 250));
        g.setFont(new Font("Times New Roman",0,28));
        g.fillRect(0, 0, width, height);
        //绘制干扰线
        for(int i=0;i<40;i++){
            g.setColor(this.getRandColor(130, 200));
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int x1 = random.nextInt(12);
            int y1 = random.nextInt(12);
            g.drawLine(x, y, x + x1, y + y1);
        }

        //绘制字符
        String strCode = "";
        for(int i=0;i<4;i++){
            String rand = String.valueOf(random.nextInt(10));
            strCode = strCode + rand;
            g.setColor(new Color(20+random.nextInt(110),20+random.nextInt(110),20+random.nextInt(110)));
            g.drawString(rand, 13*i+6, 28);
        }
        //将字符保存到session中用于前端的验证
        session.setAttribute("strCode", strCode);
        g.dispose();

        ImageIO.write(image, "JPEG", response.getOutputStream());
        response.getOutputStream().flush();

    }
 * 
 * 
 */
