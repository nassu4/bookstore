package com.nassu.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CheckCodeController {
	@RequestMapping("checkCode.do")
	public void checkCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//设置不缓存图片
		response.setHeader("Pragma", "No-cache");  
        response.setHeader("Cache-Control", "No-cache");  
        response.setDateHeader("Expires", 0);
		
		//生成图片
		BufferedImage img = new BufferedImage(100, 50, BufferedImage.TYPE_3BYTE_BGR);
		Graphics2D g = img.createGraphics();
		g.setColor(Color.gray);
		g.fillRect(0, 0, 100, 50);
		
		//画出干扰图案
		g.setColor(Color.green);
		Random random = new Random();
		for (int i = 0; i < 150; i++) {
			int x = random.nextInt(99);
			int y = random.nextInt(49);
			int x1 = random.nextInt(12);
			int y1 = random.nextInt(6);
			g.drawLine(x, y, x + x1, y + y1);
		}
		
		//生成随机数并画到图片上
		String rBase = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		g.setColor(Color.black);
		g.setFont(new Font("宋体", Font.BOLD, 18));
		int x = 10;
		int y = 30;
		String checkcode = "";
		for (int i = 0; i < 4; i++) {
			int pos = random.nextInt(rBase.length() - 1);
			char c = rBase.charAt(pos);
			double theta = (30 - random.nextInt(60)) * Math.PI / 180;
			g.rotate(theta, x, y);
			g.drawString(String.valueOf(c), x, y);
			g.rotate(-theta, x, y);
			checkcode += c;
			x += 20;
		}
		
		//向页面输出
		HttpSession session = request.getSession();
		session.setAttribute("checkCode", checkcode);
		ImageIO.write(img, "JPEG", response.getOutputStream());
		System.out.println(checkcode);
	}
}
