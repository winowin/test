package com.rookie.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.rookie.myconstant.CartItem;
import com.rookie.myconstant.ShoppingCart;
import com.rookie.pojo.Address;
import com.rookie.pojo.Flowers;
import com.rookie.pojo.Order;
import com.rookie.pojo.OrderItem;
import com.rookie.service.AddressService;
import com.rookie.service.FlowerService;
import com.rookie.service.OrderItemService;
import com.rookie.service.OrderService;
import com.rookie.service.impl.AddressServiceImpl;
import com.rookie.service.impl.FlowerSrviceImpl;
import com.rookie.service.impl.OrderItemServiceImpl;
import com.rookie.service.impl.OrderServiceImpl;
import com.rookie.utils.IDUtils;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@WebServlet(value = "/OrderServlet")
public class OrderServlet extends BaseServlet{
    public AddressService addressService = new AddressServiceImpl();
    public OrderItemService orderItemService = new OrderItemServiceImpl();
    public OrderService orderService = new OrderServiceImpl();
    public FlowerService flowerService = new FlowerSrviceImpl();

    protected void finishBuy(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        HttpSession session = req.getSession();
        Object oid = session.getAttribute("orderid");
        Integer orderid = Integer.parseInt(oid.toString());
        String paymethodid = req.getParameter("paymethod");
        Integer payid = Integer.parseInt(paymethodid);
        Order order = new Order();
        order.setPaymethodid(payid);
        order.setOrderid(orderid);
        orderService.setOrderPaymethod(order);

        List<OrderItem> orderItems = orderItemService.allOrderItemInfo(orderid);

        //Token 解决表单重复提交
        String fromToken = req.getParameter("token");
        String sessionToken = (String) req.getSession().getAttribute("token");
        if (fromToken.equals(sessionToken)){
            req.getSession().removeAttribute("token");
            orderItems.forEach(item->{
                Integer quantity = item.getQuantity();
                Integer flowerid = item.getFlowerid();
                Flowers flowers = flowerService.queryFlowerById(flowerid);
                Integer amount = flowers.getAmount()-quantity;//购买完成减库存
                Integer sales = flowers.getSales()+quantity;//购买完成加销量
                Flowers flowers1 = new Flowers();
                flowers1.setSales(sales);
                flowers1.setAmount(amount);
                flowers1.setFlowerid(flowerid);

                if(amount.equals(0)){
                    flowers1.setStockstatus(false);
                }else {
                    flowers1.setStockstatus(true);
                }
                flowerService.setSalesAndAmount(flowers1);

            });

            out.write("支付成功");
            req.getRequestDispatcher("/orderinfo.jsp").forward(req,resp);
        }else {
            resp.setHeader("Refresh","0;URL=orderinfo.jsp");
        }
    }
    protected void queryOrderType(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{
        resp.setContentType("application/json;charset=UTF-8");
        HttpSession session = req.getSession();
        PrintWriter out = resp.getWriter();
        Object id = session.getAttribute("uid");
        Integer uid = Integer.parseInt(id.toString());
        Object s = session.getAttribute("status");
        Integer status = Integer.parseInt(s.toString());
        ObjectMapper mapper = new ObjectMapper();
        List<Order> orders = orderService.allOrderTypeInfo(uid,status);
        orders.forEach(order1 -> {
            List<OrderItem> orderItems = orderItemService.allOrderItemInfo(order1.getOrderid());
            order1.setItems(orderItems);
            Timestamp createdate = order1.getCreatedate();
            String strn = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(createdate);
            order1.setCreatetime(strn);

        });
        String js = mapper.writeValueAsString(orders);
        out.write(js);

    }
    protected void toOrderTypeInfo(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{
        HttpSession session = req.getSession();
        Map<String, String[]> map = req.getParameterMap();

        for (Map.Entry<String, String[]> stringEntry : map.entrySet()) {
            //key值
            Object strKey = ((Map.Entry) stringEntry).getKey();
            //value,数组形式
            String[] value = (String[]) ((Map.Entry) stringEntry).getValue();
            if (strKey.equals("status")){
                for (String s : value) {
                    session.setAttribute("status",s);
                }
            }

        }

        req.getRequestDispatcher("/ordertype.jsp").forward(req,resp);
    }

    protected void orderInfo(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{
        resp.setContentType("application/json;charset=UTF-8");
        HttpSession session = req.getSession();
        PrintWriter out = resp.getWriter();
        Object oid = session.getAttribute("orderid");
        Integer orderid = Integer.parseInt(oid.toString());
        ObjectMapper mapper = new ObjectMapper();
//        List<Integer> orderids = orders.stream().map(Order::getOrderid).collect(Collectors.toList());

        Order order = orderService.queryAOrder(orderid);
        List<OrderItem> orderItems = orderItemService.allOrderItemInfo(orderid);

        order.setItems(orderItems);
        Timestamp createdate = order.getCreatedate();
        String strn = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(createdate);
        order.setCreatetime(strn);

        String js = mapper.writeValueAsString(order);
        out.write(js);
    }

    protected void toOrderInfo(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{
        HttpSession session = req.getSession();
        Map<String, String[]> map = req.getParameterMap();

        for (Map.Entry<String, String[]> stringEntry : map.entrySet()) {
            //key值
            Object strKey = ((Map.Entry) stringEntry).getKey();
            //value,数组形式
            String[] value = (String[]) ((Map.Entry) stringEntry).getValue();
            if (strKey.equals("orderid")){
                for (String s : value) {
                    session.setAttribute("orderid",s);
                }
            }

        }

        req.getRequestDispatcher("/orderInfoDetails.jsp").forward(req,resp);
    }
    protected void toFinishBuy(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{
        HttpSession session = req.getSession();
        Map<String, String[]> map = req.getParameterMap();

        for (Map.Entry<String, String[]> stringEntry : map.entrySet()) {
            //key值
            Object strKey = ((Map.Entry) stringEntry).getKey();
            //value,数组形式
            String[] value = (String[]) ((Map.Entry) stringEntry).getValue();
            if (strKey.equals("orderid")){
                for (String s : value) {
                    session.setAttribute("orderid",s);
                }
            }

        }

        req.getRequestDispatcher("/finishBuy.jsp").forward(req,resp);

    }

    protected void allOrderInfo(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{
        resp.setContentType("application/json;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        HttpSession session = req.getSession();//获取session中的客户id
        Integer uid = (Integer) session.getAttribute("uid");
        ObjectMapper mapper = new ObjectMapper();
        List<Order> orders = orderService.allOrderInfo(uid);
//        List<Integer> orderids = orders.stream().map(Order::getOrderid).collect(Collectors.toList());

        orders.forEach(order1 -> {
            List<OrderItem> orderItems = orderItemService.allOrderItemInfo(order1.getOrderid());
            order1.setItems(orderItems);
            Timestamp createdate = order1.getCreatedate();
            String strn = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(createdate);
            order1.setCreatetime(strn);

        });
        String js = mapper.writeValueAsString(orders);
        out.write(js);
    }

    protected void orderSave(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        HttpSession session = req.getSession();
        PrintWriter out = resp.getWriter();
        Integer uid =  (Integer) session.getAttribute("uid");
        Integer orderid = (Integer) session.getAttribute("orderid");
        Address address = addressService.addressQueryByUid(uid);
        ShoppingCart cart = (ShoppingCart)session.getAttribute("cart");
        Map<Integer, CartItem> map = cart.getMap();
        try {
            BeanUtils.populate(address,req.getParameterMap());
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<OrderItem> orderItems = (List<OrderItem>) session.getAttribute("items");
        //取出orderItems Seesion中的 所有itemid
        List<Integer> itemids = orderItems.stream().map(OrderItem::getItemid).collect(Collectors.toList());
//        System.out.println(itemid);

        String datetime = req.getParameter("datetime");
        String deliveryTime = datetime.replace("T", " ");
        String addressDetails = address.getProvince()+"省"+ address.getCity()+"市"+address.getCounty()+address.getAddress();
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        //Token 解决表单重复提交
        String fromToken = req.getParameter("token");
        String sessionToken = (String) req.getSession().getAttribute("token");
        if (fromToken.equals(sessionToken)){
            req.getSession().removeAttribute("token");
            Order order = new Order();
            order.setOrderid(orderid);
            order.setOrdernum(IDUtils.getUUID());
            order.setAddress(addressDetails);
            order.setCreatedate(timestamp);
            order.setPhonenumber(address.getPhonenumber());
            order.setUid(uid);
            order.setUsername(address.getUsername());
            order.setTotalnum(cart.getTotalNum());
            order.setTotalprice(cart.getTotalPrice());
            order.setStatus(0);
            order.setDatetime(deliveryTime);
            orderService.saveOrder(order);
            map.clear();
            session.setAttribute("order",order);

            itemids.forEach(itemid->{
                orderItemService.addOrderId(itemid,order.getOrderid());
            });
            orderItemService.clearStatus();
            req.getRequestDispatcher("/submitpay.jsp").forward(req,resp);
        }else {
            resp.setHeader("Refresh","0;URL=submitpay.jsp");
        }


    }

    protected void orderDetails(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        HttpSession session = req.getSession();
        //获取session中的客户id
        Integer uid = (Integer) session.getAttribute("uid");

        //Token 解决表单重复提交
        String fromToken = req.getParameter("token");
        String sessionToken = (String) req.getSession().getAttribute("token");
        if (fromToken.equals(sessionToken)){
            req.getSession().removeAttribute("token");
            //取购物车
            ShoppingCart cart = (ShoppingCart)session.getAttribute("cart");
            //获取map<key,value>
            Map<Integer, CartItem> map = cart.getMap();
            //遍历map<key,value>并添加到orderitem中
            for (Map.Entry<Integer,CartItem> vo:map.entrySet()){
                Integer key = vo.getKey();
                CartItem value = vo.getValue();
                orderItemService.addOrderItem(uid,value);
            }

            Integer recordsCount = orderItemService.orderItemCount(uid);
            List<OrderItem> itemRecords = orderItemService.allItemRecords(uid,recordsCount);
            //创建一个空的order
            Order order = new Order();
            order.setOrdernum(IDUtils.getUUID());
            orderService.addNewEmptyOrder(order);
            Order order1 = orderService.queryThisOrder(order.getOrdernum());
            session.setAttribute("orderid",order1.getOrderid());
            session.setAttribute("items",itemRecords);
            req.getRequestDispatcher("/order.jsp").forward(req,resp);


        }else {
//            out.write("请不要重复提交订单");
            resp.setHeader("Refresh","0;URL=order.jsp");
        }
    }

    protected void setDefaultAddress(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
        HttpSession session = req.getSession();
        //获取session中的客户id
        Integer uid = (Integer) session.getAttribute("uid");
        String addressid = req.getParameter("addressid");
        Integer aid = Integer.parseInt(addressid);
        //现将该用户所有地址设为非默认
        addressService.setNonDefault(uid);
        //再根据需要设为默认地址的地址id来进行更改
        addressService.setIsDefault(aid);
        //刷新页面
        resp.setHeader("Refresh","0;URL=order.jsp");
    }

    protected void showAddress(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
        resp.setContentType("application/json;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        HttpSession session = req.getSession();
        //获取session中的客户id
        Integer uid = (Integer) session.getAttribute("uid");
        //通过uid获取用户地址记录集合
        Integer count =  addressService.addressCount(uid);
        List<Address> addresses = addressService.allAddress(uid,count);
        ObjectMapper mapper = new ObjectMapper();
        String js = mapper.writeValueAsString(addresses);
        out.write(js);
    }

    protected void updateAddress(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
        String addressid = req.getParameter("addressid");
        Integer aid = Integer.parseInt(addressid);
        Address address = new Address();
        try {
            BeanUtils.populate(address,req.getParameterMap());
        } catch (Exception e) {
            e.printStackTrace();
        }
        addressService.updateAddress(address,aid);
        req.getRequestDispatcher("/order.jsp").forward(req,resp);
    }

    protected void toDeleteAddress(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
        String addressid = req.getParameter("addressid");
        Integer aid = Integer.parseInt(addressid);
        addressService.deleteAddress(aid);
        req.getRequestDispatcher("/order.jsp").forward(req,resp);
    }

    protected void toUpdateAddress(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
        String addressid = req.getParameter("addressid");
        Integer aid = Integer.parseInt(addressid);
        Address address = addressService.addressQueryByAid(aid);
        req.setAttribute("address",address);
        req.getRequestDispatcher("/updateAddress.jsp").forward(req,resp);

    }
    protected void addAddressToOrder(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        HttpSession session = req.getSession();
        Integer uid =  (Integer) session.getAttribute("uid");
        Address address = new Address();
        try {
            BeanUtils.populate(address,req.getParameterMap());
        } catch (Exception e) {
            e.printStackTrace();
        }
        addressService.addAddress(address,uid);

        resp.sendRedirect(req.getContextPath()+"/order.jsp");
    }
}
