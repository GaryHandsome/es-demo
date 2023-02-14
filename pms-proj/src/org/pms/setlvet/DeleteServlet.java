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
import java.util.List;

/**
 * 删除商品
 *
 * @Date 2023-02-14
 * @Author zqx
 */
@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 第一：获取浏览器传递过来的数据 - 合法性验证（前、后）
        String id = req.getParameter("pid");

        // 第二：业务处理 - 数据库（查询、持久化数据） - DAO
        ProductDao productDao = new ProductDaoImpl();
        int row = productDao.delete(id) ;

        // 把处理的数据封装到统一的接口中
        ResponseData responseData = new ResponseData();
        responseData.setMsg("删除成功");

        if(row!=1) {
            responseData.setMsg("删除失败");
            responseData.setCode(500);
        }

        String json = new Gson().toJson(responseData);

        // 第三：响应结果
        resp.setContentType("application/json;charset=utf-8");
        resp.setCharacterEncoding("utf-8");
        PrintWriter out = resp.getWriter();
        out.print(json);

        out.flush();
        out.close();

    }
}
