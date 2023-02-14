package org.pms.dao.impl;

import org.pms.dao.ProductDao;
import org.pms.entity.Product;
import org.pms.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 商品DAO接口的实现
 *
 * @Date 2023-02-14
 * @Author zqx
 */
public class ProductDaoImpl implements ProductDao {
    @Override
    public List<Product> selectAll() {
        List<Product> list = new ArrayList<>();

        //第一：定义操作数据库的SQL语句 ctrl + shift + u
        String sql = "select product_id,product_name,product_price,product_count,product_image,product_desc from product";

        //第二：获取连接对象
        Connection conn = DBUtil.getConnection();

        //第三：使用连接对象，获取语句对象(PreparedStatement)，并预编译SQL语句
        PreparedStatement pstmt = null;
        ResultSet rst = null;
        try {
            pstmt = conn.prepareStatement(sql);

            //第四：设置数据
            //语法：语句对象.setXxx(问号占位符索引,数据) ;


            //第五：执行SQL语句，并接收返回结果
            //语句对象.executeUpdate() -> 增,删,改	   ->  返回的是受影响的记录数
            //语句对象.executeQuery()  -> 查			-> 	返回的是结果集(ResultSet)
            rst = pstmt.executeQuery();

            //第六：对结果进行处理
            //遍历结构集各行各列的数据，封装到相关的实体对象或集合
            //判断有没有数据:结果集对象.next()
            //获取结果集的数据:
            //结果集对象.getXxx(查询数据的索引) ;
            //结果集对象.getXxx(查询数据的字段名称) ;

            while (rst.next()) {
                // 1.读取数据
                String productId = rst.getString(1);
                String productName = rst.getString(2);
                double productPrice = rst.getDouble(3);
                int productCount = rst.getInt(4);
                String productImage = rst.getString(5);
                String productDesc = rst.getString(6);

                // 2.创建实体对象
                Product product = new Product();

                //3.封装数据
                product.setId(productId);
                product.setName(productName);
                product.setPrice(productPrice);
                product.setCount(productCount);
                product.setImage(productImage);
                product.setDesc(productDesc);

                //4.把实体对象添加到List集合中
                list.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 第七：关闭对象
            DBUtil.close(conn, pstmt, rst);
        }
        // 返回集合对象
        return list;
    }

    @Override
    public int insert(Product product) {
        int r = 0;
        // 第一：定义要操作数据库的SQL语句
        String sql = "insert into product (product_id,product_name,product_price,product_count,product_image,product_desc) values (?,?,?,?,?,?)";

        // 第二：获取连接对象
        Connection conn = DBUtil.getConnection();
        PreparedStatement pstmt = null;

        try {
            // 第三：预编译SQL语句
            pstmt = conn.prepareStatement(sql);

            // 第四：填充占位符中的数据
            pstmt.setString(1, product.getId());
            pstmt.setString(2, product.getName());
            pstmt.setDouble(3, product.getPrice());
            pstmt.setInt(4, product.getCount());
            pstmt.setString(5, product.getImage());
            pstmt.setString(6, product.getDesc());

            // 第五：执行SQL语句
            r = pstmt.executeUpdate();

            // 第六：对执行的结果进行处理
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            // 第七：关闭相关的对象
            DBUtil.close(conn, pstmt, null);
        }
        return r;
    }

    @Override
    public int delete(String id) {
        int r = 0;
        // 第一：定义要操作数据库的SQL语句
        String sql = "delete from product where product_id=?";

        // 第二：获取连接对象
        Connection conn = DBUtil.getConnection();
        PreparedStatement pstmt = null;

        try {
            // 第三：预编译SQL语句
            pstmt = conn.prepareStatement(sql);

            // 第四：填充占位符中的数据
            pstmt.setString(1, id);

            // 第五：执行SQL语句
            r = pstmt.executeUpdate();

            // 第六：对执行的结果进行处理
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            // 第七：关闭相关的对象
            DBUtil.close(conn, pstmt, null);
        }
        return r;
    }

    @Override
    public int update(Product product) {
        int r = 0;
        // 第一：定义要操作数据库的SQL语句
        String sql = "update product set product_name=?,product_price=?,product_count=?,product_image=?,product_desc=? where product_id=?";

        // 第二：获取连接对象
        Connection conn = DBUtil.getConnection();
        PreparedStatement pstmt = null;

        try {
            // 第三：预编译SQL语句
            pstmt = conn.prepareStatement(sql);

            // 第四：填充占位符中的数据
            pstmt.setString(1, product.getName());
            pstmt.setDouble(2, product.getPrice());
            pstmt.setInt(3, product.getCount());
            pstmt.setString(4, product.getImage());
            pstmt.setString(5, product.getDesc());
            pstmt.setString(6, product.getId());

            // 第五：执行SQL语句
            r = pstmt.executeUpdate();

            // 第六：对执行的结果进行处理
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            // 第七：关闭相关的对象
            DBUtil.close(conn, pstmt, null);
        }
        return r;
    }

    @Override
    public int updateCount(String id, int count) {
        int r = 0;
        // 第一：定义要操作数据库的SQL语句
        String sql = "update product set product_count=? where product_id=?";

        // 第二：获取连接对象
        Connection conn = DBUtil.getConnection();
        PreparedStatement pstmt = null;

        try {
            // 第三：预编译SQL语句
            pstmt = conn.prepareStatement(sql);

            // 第四：填充占位符中的数据
            pstmt.setInt(1, count);
            pstmt.setString(2, id);

            // 第五：执行SQL语句
            r = pstmt.executeUpdate();

            // 第六：对执行的结果进行处理
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            // 第七：关闭相关的对象
            DBUtil.close(conn, pstmt, null);
        }
        return r;
    }
}
