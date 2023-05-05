package com.rookie.controller;

import com.rookie.myconstant.MyConstant;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@WebServlet(value = "/CaptchaServlet")
//登录验证码
public class CaptchaServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int width = 125;
        int height = 30;
        BufferedImage img = new BufferedImage(width,height, BufferedImage.TYPE_INT_RGB);

        Graphics graphics = img.getGraphics();

        graphics.setColor(Color.white);
        graphics.drawRect(0,0,width-1,height-1);

//        graphics.setColor(Color.LIGHT_GRAY);
        graphics.fillRect(1,1,width-2,height-2);

        Random random = new Random();
        graphics.setColor(Color.pink);
        for (int i=0; i<9; i++){
            graphics.drawLine(random.nextInt(width-2),random.nextInt(height-2),
                    random.nextInt(width-2),random.nextInt(height-2));
        }

        //验证码
        graphics.setColor(Color.cyan);
        graphics.setFont(new Font("宋体",Font.BOLD|Font.ITALIC,24));
        int x = 15;
        StringBuffer sb = new StringBuffer();
        for (int i=0;i<4;i++){
            String s  = random.nextInt(10)+"";
            sb.append(s);
            graphics.drawString(s,x,20);
            x+=20;
        }
        req.getSession().setAttribute(MyConstant.CAPTCHA,sb.toString());
        ImageIO.write(img,"jpeg",resp.getOutputStream());

    }
}

