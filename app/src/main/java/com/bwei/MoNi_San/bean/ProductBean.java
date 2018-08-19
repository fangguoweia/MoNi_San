package com.bwei.MoNi_San.bean;

import java.util.List;

/**
 * ....XRecyclerView
 * Created by 房国伟 on 2018/8/18.
 */
public class ProductBean {

    public String msg;
    public String code;
    public List<Product> data;

    public class Product{
        public String title;
        public String images;
        public String pid;
    }
}
