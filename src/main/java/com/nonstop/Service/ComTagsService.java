package com.nonstop.Service;

import com.nonstop.Dao.ComTagsDao;
import com.nonstop.Model.CommodityTags;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


//↓[public class=程式功能]這段程式碼定義了一個名為 ShoppingCart 的類別
@Service
public class ComTagsService {
    @Autowired
    //↓[private=工具]包含一個私有成員變數 comTagsDao，用於處理商品類別相關的資料庫訪問操作。
    ComTagsDao comTagsDao;
    //查詢
    public List<CommodityTags> queryAll(){
        return comTagsDao.queryAll();
    }
    //新增
    public String addCommodityTag(CommodityTags tags){
        return comTagsDao.addCommodityTag(tags);
    }
    //刪除
    public String deleteCommodityTag(List<String> tags){
        int success = 0;
        int fail = 0;
        for (String tag:tags){
            if (comTagsDao.deleteCommodityTag(tag).equals("0000"))
                success++;
            else
                fail++;
        }
        return "刪除完成，共成功"+success+"筆，失敗"+fail+"筆";
    }
    //更新(=)
    public String updateCommodityTag(CommodityTags tags){
        return comTagsDao.updateCommodityTag(tags);
    }

}
