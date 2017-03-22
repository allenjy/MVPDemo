package com.allen.mvpdemo.model;

import java.util.List;

/**
 *
 * Created by Allen on 2017/3/16.
 */

public class NewsBean {

    public int size;
    public List<News> list;


    public static class News {

        public String imgurl;
        public boolean has_content;
        public String docurl;
        public int id;
        public String time;
        public String title;
        public String channelname;
    }
}
