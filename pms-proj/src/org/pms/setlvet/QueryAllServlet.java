package org.pms.setlvet;

import com.google.gson.Gson;
import org.pms.dao.ProductDao;
import org.pms.dao.impl.ProductDaoImpl;
import org.pms.entity.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * 查询所有商品 - 商品列表
 *
 * @Date 2023-02-14
 * @Author zqx
 */
@WebServlet("/QueryAllServlet")
public class QueryAllServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 第一：获取浏览器传递过来的数据 - 合法性验证（前、后）

        // 第二：业务处理 - 数据库（查询、持久化数据） - DAO
        ProductDao productDao = new ProductDaoImpl();
        List<Product> list = productDao.selectAll();

        String json = new Gson().toJson(list);

        // 第三：响应结果
        resp.setContentType("application/json;charset=utf-8");
        resp.setCharacterEncoding("utf-8");
        PrintWriter out = resp.getWriter();
        out.print(json);

        out.flush();
        out.close();

    }
}
