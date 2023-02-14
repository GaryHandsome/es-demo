package org.pms.entity;

/**
 * 商品
 *
 * @Date 2023-02-14
 * @Author zqx
 */
public class Product {
    /**
     *  商品编号
     */
    private String id ;
    private String name ;
    private double price ;
    private int count  ;
    private String image ;
    private String desc  ;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
