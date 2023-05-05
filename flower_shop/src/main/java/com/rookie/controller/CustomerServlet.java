package com.rookie.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rookie.myconstant.MyConstant;
import com.rookie.pojo.Address;
import com.rookie.pojo.Customer;
import com.rookie.pojo.OldPassword;
import com.rookie.service.AddressService;
import com.rookie.service.CustomerService;
import com.rookie.service.impl.AddressServiceImpl;
import com.rookie.service.impl.CustomerServiceImpl;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@WebServlet(value = "/CustomerServlet")
@MultipartConfig
public class CustomerServlet extends BaseServlet {
    private final CustomerService customerService = new CustomerServiceImpl();
    public AddressService addressService = new AddressServiceImpl();


    protected void checkRegistPhone(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        String phonenumber = req.getParameter("phonenumber");

        List<Customer> customers = customerService.allCustomerPhonenumber();
        if (phonenumber.equals("")){
            out.write("empty");
        }
        if (!phonenumber.equals("")){
            customers.forEach(customer->{
                if (customer.getPhonenumber().equalsIgnoreCase(phonenumber)) {
                    out.write("false");
                }

            });
        }

    }

    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        Customer customer = new Customer();
        //注册日期类型转换器
        ConvertUtils.register(new DateLocaleConverter(), Date.class);
        try {
            BeanUtils.populate(customer,req.getParameterMap());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(req.getParameter("phonenumber").equals("") || req.getParameter("username").equals("") ||req.getParameter("password").equals("")){
            out.write("手机号、密码、用户名不能为空,两秒后返回页面");
            resp.setHeader("Refresh","2;URL=customer.jsp");
            return;
        }
        List<Customer> customers = customerService.allCustomerPhonenumber();
        customers.forEach(customer1->{
            if (customer1.getPhonenumber().equalsIgnoreCase(req.getParameter("phonenumber"))) {
                out.write("手机号已被占用,1秒后返回页面");
                resp.setHeader("Refresh","1;URL=customer.jsp");
            }
        });

        if(!req.getParameter("surepassword").equals(customer.getPassword())){
            out.write("两次密码不一致,两秒后返回页面");
            resp.setHeader("Refresh","2;URL=customer.jsp");
            return;
        }
        customerService.regist(customer);
        out.write("注册成功,2秒后前往登录");
        resp.setHeader("Refresh","2;URL=customer.jsp");
    }


    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        HttpSession session = req.getSession();
        //验证码登录
        String fCaptcha = req.getParameter("captcha");
        String sCaptcha = (String) session.getAttribute(MyConstant.CAPTCHA);
        if (!fCaptcha.equals(sCaptcha)){
            out.write("验证码输入有误，2秒后返回登录页面");
            resp.setHeader("Refresh","2;URL=index.html");
            return;
        }

        String phonenumber = req.getParameter("phonenumber");
        String password = req.getParameter("password");
        Customer customer = customerService.login(phonenumber,password);
        if(customer==null){
            out.write("错误的手机号或密码,2秒后返回");
            resp.setHeader("Refresh","2;URL=index.html");
            return;
        }
        session.setAttribute(MyConstant.loginFlag,customer);
        session.setAttribute("uid",customer.getUid());
        resp.sendRedirect(req.getContextPath()+"/");
    }

    //安全登出
    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        //session.invalidate();//立刻销毁会话对象
        session.removeAttribute(MyConstant.loginFlag);
        resp.sendRedirect(req.getContextPath()+"/");
    }

    //个人中心
    //管理个人信息
    protected void toCustomerInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/customerInfo.jsp").forward(req,resp);
    }

    protected void customerInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        HttpSession session = req.getSession();
        //获取session中的客户id
        Integer uid = (Integer) session.getAttribute("uid");
        Customer info = customerService.getInfo(uid);
        ObjectMapper mapper = new ObjectMapper();
        String js = mapper.writeValueAsString(info);
        out.write(js);

    }
    protected  void updateCustomerInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        HttpSession session = req.getSession();
        //获取session中的客户id
        Integer uid = (Integer) session.getAttribute("uid");
        Customer customer = new Customer();
        try {
            BeanUtils.populate(customer,req.getParameterMap());
            Part imagePart = req.getPart("avatarimg");
            System.out.println(imagePart);
            if (imagePart.getSubmittedFileName()!=null && !imagePart.getSubmittedFileName().equals("")){
                String fileName = imagePart.getSubmittedFileName();

                String realPath = getServletContext().getRealPath("/images");
                //删除原来的文件
                File orginalFile = new File(realPath,customer.getAvatar());
                if(orginalFile.exists())
                    orginalFile.delete();

                File file = new File(realPath);
                if (!file.exists())
                    file.mkdirs();

                String extendFileNmae = fileName.substring(fileName.lastIndexOf("."));
                String imageName = UUID.randomUUID() + extendFileNmae;
                imagePart.write(realPath+File.separator+imageName);
                customer.setAvatar(imageName);
            }
            if(!(req.getParameter("usergender").equals("男") || req.getParameter("usergender").equals("女") ||req.getParameter("usergender").equals("保密"))){
                out.write("性别输入有误,1秒后返回页面");
                resp.setHeader("Refresh","1;URL=customerInfo.jsp");
                return;
            }
            String birthday = req.getParameter("birthday");
            customer.setBirthday(birthday);
        } catch (Exception e) {
            e.printStackTrace();
        }
        customerService.updateInfo(customer,uid);
        resp.setHeader("Refresh","0;URL=customerInfo.jsp");

    }

    //地址管理
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
        req.getRequestDispatcher("/manageAddress.jsp").forward(req,resp);
    }

    protected void toDeleteAddress(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
        String addressid = req.getParameter("addressid");
        Integer aid = Integer.parseInt(addressid);
        addressService.deleteAddress(aid);
        req.getRequestDispatcher("/manageAddress.jsp").forward(req,resp);
    }

    protected void toUpdateAddress(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
        String addressid = req.getParameter("addressid");
        Integer aid = Integer.parseInt(addressid);
        Address address = addressService.addressQueryByAid(aid);
        req.setAttribute("address",address);
        req.getRequestDispatcher("/updateManageAddress.jsp").forward(req,resp);

    }
    protected void addAddress(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
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

        resp.sendRedirect(req.getContextPath()+"/manageAddress.jsp");
    }


    //修改密码
    protected void updatePwd(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        HttpSession session = req.getSession();
        Integer uid =  (Integer) session.getAttribute("uid");
        String oldpwd = req.getParameter("oldpwd");
        String newpwd = req.getParameter("newpwd");
        String renewpwd = req.getParameter("renewpwd");
        OldPassword oldPassword = new OldPassword();
        oldPassword.setOldpassword(oldpwd);
        customerService.compareOldpwd(oldPassword);
        OldPassword oldPwd = customerService.obtainOldpwd(oldpwd);
        String passwordOld = oldPwd.getOldpassword();
        Customer info = customerService.getInfo(uid);
        String nowPwd = info.getPassword();

        if (nowPwd.equals(passwordOld)){
            if (newpwd.equals(renewpwd)){
                customerService.updatePwd(uid,newpwd);
                out.write("修改成功，1秒后重新登录！");
                session.removeAttribute(MyConstant.loginFlag);
                resp.setHeader("Refresh","1;URL=customer.jsp");
            }else {
                out.write("两次密码不一致，1秒后返回！");
                resp.setHeader("Refresh","1;URL=updatePwd.jsp");
            }
        }else {
            out.write("旧密码有误，两秒后返回！");
            resp.setHeader("Refresh","1;URL=updatePwd.jsp");
        }




    }


}
