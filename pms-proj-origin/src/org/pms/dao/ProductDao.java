package org.pms.dao;

import org.pms.entity.Product;

import java.util.List;

/**
 * 商品DAO接口
 *
 * @Date 2023-02-14
 * @Author zqx
 */
public interface ProductDao {
    /**
     * 查询所有的数据
     * @return
     */
    List<Product> selectAll() ;


    /**
     * 添加
     * @param product
     * @return
     */
    int insert(Product product) ;

    /**
     * 删除
     * @param id
     * @return
     */
    int delete(String id) ;

    /**
     * 修改
     * @param product
     * @return
     */
    int update(Product product) ;


    /**
     * 修改数量
     * @param id
     * @param count
     * @return
     */
    int updateCount(String id,int count) ;
}
