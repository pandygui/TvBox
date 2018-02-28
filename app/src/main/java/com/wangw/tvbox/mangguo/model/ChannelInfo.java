package com.wangw.tvbox.mangguo.model;

import java.util.List;

/**
 * Created by wangw on 2018/2/28.
 */

public class ChannelInfo {


    /**
     * channelId : 1
     * channelName : 综艺
     * items : [{"fstlvlId":"1","fstlvlName":"综艺","imgType":"0","items":[{"eName":"kind","items":[{"link":"","tagId":"a4","tagName":"全部"},{"link":"","tagId":"2836094","tagName":"芒果出品"},{"link":"","tagId":"1657120","tagName":"真人秀"},{"link":"","tagId":"1282983","tagName":"选秀"},{"link":"","tagId":"1273158","tagName":"情感"},{"link":"","tagId":"1288586","tagName":"脱口秀"},{"link":"","tagId":"1273380","tagName":"访谈"},{"link":"","tagId":"2835040","tagName":"八卦"},{"link":"","tagId":"1786669","tagName":"搞笑"},{"link":"","tagId":"1273155","tagName":"晚会"},{"link":"","tagId":"1284979","tagName":"时尚"},{"link":"","tagId":"2834359","tagName":"益智"},{"link":"","tagId":"2825153","tagName":"音乐"},{"link":"","tagId":"1284864","tagName":"生活"},{"link":"","tagId":"2825156","tagName":"纪实"},{"link":"","tagId":"1283091","tagName":"竞技"},{"link":"","tagId":"2859501","tagName":"亲子"},{"link":"","tagId":"2835521","tagName":"旅游"},{"link":"","tagId":"2825152","tagName":"美食"},{"link":"","tagId":"2859785","tagName":"教育"},{"link":"","tagId":"2833635","tagName":"其他"}],"typeId":"16","typeName":"类型"},{"eName":"area","items":[{"link":"","tagId":"a3","tagName":"全部"},{"link":"","tagId":"49","tagName":"内地"},{"link":"","tagId":"1275612","tagName":"中国香港"},{"link":"","tagId":"1275386","tagName":"中国台湾"},{"link":"","tagId":"1277384","tagName":"日本"},{"link":"","tagId":"12245","tagName":"韩国"},{"link":"","tagId":"537193","tagName":"美国"},{"link":"","tagId":"2820956","tagName":"新加坡"}],"typeId":"3","typeName":"地区"},{"eName":"sort","items":[{"link":"","tagId":"c1","tagName":"最新"},{"link":"","tagId":"c2","tagName":"最热"}],"typeId":"-2","typeName":"排序"}]}]
     */

    public String channelId;
    public String channelName;
    public List<ItemsBeanXX> items;

    public static class ItemsBeanXX {
        /**
         * fstlvlId : 1
         * fstlvlName : 综艺
         * imgType : 0
         * items : [{"eName":"kind","items":[{"link":"","tagId":"a4","tagName":"全部"},{"link":"","tagId":"2836094","tagName":"芒果出品"},{"link":"","tagId":"1657120","tagName":"真人秀"},{"link":"","tagId":"1282983","tagName":"选秀"},{"link":"","tagId":"1273158","tagName":"情感"},{"link":"","tagId":"1288586","tagName":"脱口秀"},{"link":"","tagId":"1273380","tagName":"访谈"},{"link":"","tagId":"2835040","tagName":"八卦"},{"link":"","tagId":"1786669","tagName":"搞笑"},{"link":"","tagId":"1273155","tagName":"晚会"},{"link":"","tagId":"1284979","tagName":"时尚"},{"link":"","tagId":"2834359","tagName":"益智"},{"link":"","tagId":"2825153","tagName":"音乐"},{"link":"","tagId":"1284864","tagName":"生活"},{"link":"","tagId":"2825156","tagName":"纪实"},{"link":"","tagId":"1283091","tagName":"竞技"},{"link":"","tagId":"2859501","tagName":"亲子"},{"link":"","tagId":"2835521","tagName":"旅游"},{"link":"","tagId":"2825152","tagName":"美食"},{"link":"","tagId":"2859785","tagName":"教育"},{"link":"","tagId":"2833635","tagName":"其他"}],"typeId":"16","typeName":"类型"},{"eName":"area","items":[{"link":"","tagId":"a3","tagName":"全部"},{"link":"","tagId":"49","tagName":"内地"},{"link":"","tagId":"1275612","tagName":"中国香港"},{"link":"","tagId":"1275386","tagName":"中国台湾"},{"link":"","tagId":"1277384","tagName":"日本"},{"link":"","tagId":"12245","tagName":"韩国"},{"link":"","tagId":"537193","tagName":"美国"},{"link":"","tagId":"2820956","tagName":"新加坡"}],"typeId":"3","typeName":"地区"},{"eName":"sort","items":[{"link":"","tagId":"c1","tagName":"最新"},{"link":"","tagId":"c2","tagName":"最热"}],"typeId":"-2","typeName":"排序"}]
         */

        public String fstlvlId;
        public String fstlvlName;
        public String imgType;
        public List<ItemsBeanX> items;

        public static class ItemsBeanX {
            /**
             * eName : kind
             * items : [{"link":"","tagId":"a4","tagName":"全部"},{"link":"","tagId":"2836094","tagName":"芒果出品"},{"link":"","tagId":"1657120","tagName":"真人秀"},{"link":"","tagId":"1282983","tagName":"选秀"},{"link":"","tagId":"1273158","tagName":"情感"},{"link":"","tagId":"1288586","tagName":"脱口秀"},{"link":"","tagId":"1273380","tagName":"访谈"},{"link":"","tagId":"2835040","tagName":"八卦"},{"link":"","tagId":"1786669","tagName":"搞笑"},{"link":"","tagId":"1273155","tagName":"晚会"},{"link":"","tagId":"1284979","tagName":"时尚"},{"link":"","tagId":"2834359","tagName":"益智"},{"link":"","tagId":"2825153","tagName":"音乐"},{"link":"","tagId":"1284864","tagName":"生活"},{"link":"","tagId":"2825156","tagName":"纪实"},{"link":"","tagId":"1283091","tagName":"竞技"},{"link":"","tagId":"2859501","tagName":"亲子"},{"link":"","tagId":"2835521","tagName":"旅游"},{"link":"","tagId":"2825152","tagName":"美食"},{"link":"","tagId":"2859785","tagName":"教育"},{"link":"","tagId":"2833635","tagName":"其他"}]
             * typeId : 16
             * typeName : 类型
             */

            public String eName;
            public String typeId;
            public String typeName;
            public List<ItemsBean> items;

            public static class ItemsBean {
                /**
                 * link :
                 * tagId : a4
                 * tagName : 全部
                 */

                public String link;
                public String tagId;
                public String tagName;
            }
        }
    }

    public ItemsBeanXX getChannel(){
        if (items != null && !items.isEmpty()){
            return items.get(0);
        }
        return null;
    }

    public String getFstlvlId(){
        if (getChannel() != null){
            return getChannel().fstlvlId;
        }
        return channelId;
    }

}
