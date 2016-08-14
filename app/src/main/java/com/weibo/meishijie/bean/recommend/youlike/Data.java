package com.weibo.meishijie.bean.recommend.youlike;

/**
 * Created by 巴巴 on 2016/8/14.
 */

public class Data {

    /**
     * id : 1691616
     * title : #RIO#炫酷鸡尾果
     * titlepic : http://site.meishij.net/r/185/123/1655935/a1655935_146897223403247.jpg
     * is_recipe : 1
     */

    private String id;
    private String title;
    private String titlepic;
    private String is_recipe;

    @Override
    public String toString() {
        return "Data{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", titlepic='" + titlepic + '\'' +
                ", is_recipe=" + is_recipe +
                '}';
    }

    public Data() {
    }

    public Data(String id, String title, String titlepic, String is_recipe) {
        this.id = id;
        this.title = title;
        this.titlepic = titlepic;
        this.is_recipe = is_recipe;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitlepic() {
        return titlepic;
    }

    public void setTitlepic(String titlepic) {
        this.titlepic = titlepic;
    }

    public String getIs_recipe() {
        return is_recipe;
    }

    public void setIs_recipe(String is_recipe) {
        this.is_recipe = is_recipe;
    }
}
