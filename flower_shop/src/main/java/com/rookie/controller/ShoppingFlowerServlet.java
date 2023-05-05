package com.rookie.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rookie.myconstant.PageBean;
import com.rookie.myconstant.ResultInfo;
import com.rookie.myconstant.ShoppingCart;
import com.rookie.pojo.FlowerKind;
import com.rookie.pojo.Flowers;
import com.rookie.service.FlowerService;
import com.rookie.service.impl.FlowerSrviceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/ShoppingFlowerServlet")
public class ShoppingFlowerServlet extends BaseServlet{
    public FlowerService flowerService = new FlowerSrviceImpl();

    protected void queryPageFlowers(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String pageNum = req.getParameter("pageNum");
        PageBean pageBean = flowerService.queryFlowersPageBean(pageNum);
        req.setAttribute("pageBean",pageBean);
        req.getRequestDispatcher("/index.jsp").forward(req,resp);
    }
    protected void searchPageFlowers(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String pageNum = req.getParameter("pageNum");

        String flowerName = req.getParameter("search");
        req.setAttribute("name",flowerName);
        PageBean pageBean = flowerService.queryFlowers(pageNum,flowerName);
        req.setAttribute("pageBean",pageBean);
        req.getRequestDispatcher("/search.jsp").forward(req,resp);
    }

    protected void searchPageFlowerKind(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String pageNum = req.getParameter("pageNum");
        String kindid = req.getParameter("kindid");
        Integer kid = Integer.parseInt(kindid);
        req.setAttribute("kindid",kid);

        PageBean pageBean = flowerService.queryFlowersKind(pageNum,kid);
        req.setAttribute("pageBean",pageBean);
        req.getRequestDispatcher("/kindflower.jsp").forward(req,resp);
    }

    protected void queryFlowerkind(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        List<FlowerKind> flowerKind = flowerService.queryFlowerkind();
        ObjectMapper mapper = new ObjectMapper();
        String js = mapper.writeValueAsString(flowerKind);
        out.write(js);
    }



    protected void flowersMaxSales(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        Flowers flowers = flowerService.flowersMaxSales();
        ObjectMapper mapper = new ObjectMapper();
        String js = mapper.writeValueAsString(flowers);
        out.write(js);
    }

    protected void flowersDetails(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fid = req.getParameter("flowerid");
        Integer flowerid = Integer.parseInt(fid);
        Flowers flowers = flowerService.queryFlowerById(flowerid);
        req.setAttribute("f",flowers);
        req.getRequestDispatcher("/flowerdetails.jsp").forward(req,resp);
    }

    protected void addFlowersToCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        String fid = req.getParameter("flowerid");
        Integer flowerid = Integer.parseInt(fid);
        String num = req.getParameter("num");//商品的数量
        ObjectMapper om = new ObjectMapper();
        try {

            Flowers flowers = flowerService.queryFlowerById(flowerid);
            //取购物车
            HttpSession session = req.getSession();
            ShoppingCart cart = (ShoppingCart)session.getAttribute("cart");

            if(cart==null){
                cart = new ShoppingCart();
                session.setAttribute("cart",cart);
            }
            cart.addFlowersToCart(flowers,Integer.parseInt(num));


            String json = om.writeValueAsString(new ResultInfo(true,"添加商品至购物车成功!"));
            out.write(json);
        } catch (NumberFormatException e) {
            String json = om.writeValueAsString(new ResultInfo(false,"添加商品至购物车失败!"));
            out.write(json);
            e.printStackTrace();
        }
    }



    protected void toShoppingCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/shoppingcart.jsp").forward(req,resp);
        resp.setHeader("Refresh","0;URL=shoppingcart.jsp");
    }

    protected void removeFlowers(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        String fid = req.getParameter("flowerid");
        Integer flowerid = Integer.parseInt(fid);
        Flowers flowers = flowerService.queryFlowerById(flowerid);
        HttpSession session = req.getSession();
        ShoppingCart cart = (ShoppingCart)session.getAttribute("cart");

        cart.removeFlowers(flowers.getFlowerid());
        resp.setHeader("Refresh","1;URL=shoppingcart.jsp");
    }





}
