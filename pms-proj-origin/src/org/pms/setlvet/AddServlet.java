package org.pms.setlvet;

import com.google.gson.Gson;
import org.pms.dao.ProductDao;
import org.pms.dao.impl.ProductDaoImpl;
import org.pms.entity.Product;
import org.pms.vo.ResponseData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 添加商品
 *
 * @Date 2023-02-14
 * @Author zqx
 */
@WebServlet("/AddServlet")
public class AddServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 第一：获取浏览器传递过来的数据 - 合法性验证（前、后）
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        double price = Double.parseDouble(req.getParameter("price"));
        String desc = req.getParameter("desc");
        int count = Integer.parseInt(req.getParameter("count"));
        String image = req.getParameter("image");


        Product product = new Product();
        product.setId(id);
        product.setName(name);
        product.setCount(count);
        product.setPrice(price);
        product.setDesc(desc);
        product.setImage(image);


        // 第二：业务处理 - 数据库（查询、持久化数据） - DAO
        ProductDao productDao = new ProductDaoImpl();
        int row = productDao.insert(product);

        // 第三：响应结果

        // 把处理的数据封装到统一的接口中
        ResponseData responseData = new ResponseData();
        responseData.setMsg("添加成功");

        if(row!=1) {
            responseData.setMsg("添加失败");
            responseData.setCode(500);
        }

        String json = new Gson().toJson(responseData);
        resp.setContentType("application/json;charset=utf-8");
        resp.setCharacterEncoding("utf-8");
        PrintWriter out = resp.getWriter();
        out.print(json);

        out.flush();
        out.close();

    }
}
