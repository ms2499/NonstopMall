package com.nonstop.Dao;

//SQLMX資料庫編碼是ISO-8859-1，所有String資料需先使用cstool.utf82iso方法進行轉碼，操作資料庫才不會出錯

import com.nonstop.Model.Commodities;
import com.nonstop.Tools.CharsetTool;
import com.nonstop.properties.CustomProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommodityDao {
    @Autowired
    JdbcTemplate jt;
    CharsetTool cstool = new CharsetTool();

    public List<Commodities> queryAll(){
        String sql = "SELECT * FROM commodities;";

        try{
            return jt.query(sql, (rs, n) -> new Commodities(rs.getLong("CommodityID"),
                    rs.getString("CommodityName"),
                    rs.getInt("CommodityQty"),
                    rs.getLong("CommodityPrice"),
                    rs.getString("CommodityTag"),
                    rs.getString("CommodityImgPath"),
                    rs.getString("CommodityDetail"),
                    rs.getByte("CommoditySaleFlag"),
                    rs.getByte("CommodityDiscount"),
                    rs.getByte("CommodityDisRate")));
        }
        catch (EmptyResultDataAccessException e){
            return null;
        }
        catch (Exception e){
            cstool.pLogln(e.toString(), "CommodityDao.queryAll");
            return null;
        }
    }

    public Commodities queryById(Long id){
        String sql = "SELECT * FROM commodities WHERE CommodityID = ?";

        try{
            return jt.queryForObject(sql, (rs, n) -> new Commodities(rs.getLong("CommodityID"),
                    rs.getString("CommodityName"),
                    rs.getInt("CommodityQty"),
                    rs.getLong("CommodityPrice"),
                    rs.getString("CommodityTag"),
                    rs.getString("CommodityImgPath"),
                    rs.getString("CommodityDetail"),
                    rs.getByte("CommoditySaleFlag"),
                    rs.getByte("CommodityDiscount"),
                    rs.getByte("CommodityDisRate")),
                    id);
        }
        catch (EmptyResultDataAccessException e){
            return null;
        }
        catch (Exception e){
            cstool.pLogln(e.toString(), "CommodityDao.queryById");
            return null;
        }
    }

    public List<Commodities> queryByTag(String tag){
        String sql = "SELECT * FROM commodities WHERE CommodityTag = ?";

        try{
            return jt.query(sql, (rs, n) -> new Commodities(rs.getLong("CommodityID"),
                            rs.getString("CommodityName"),
                            rs.getInt("CommodityQty"),
                            rs.getLong("CommodityPrice"),
                            rs.getString("CommodityTag"),
                            rs.getString("CommodityImgPath"),
                            rs.getString("CommodityDetail"),
                            rs.getByte("CommoditySaleFlag"),
                            rs.getByte("CommodityDiscount"),
                            rs.getByte("CommodityDisRate")),
                            cstool.utf82iso(tag));
        }
        catch (EmptyResultDataAccessException e){
            return null;
        }
        catch (Exception e){
            cstool.pLogln(e.toString(), "CommodityDao.queryByTag");
            return null;
        }
    }

    public List<Commodities> search(String key){
        String str = "%" + key + "%";
        String sql = "SELECT * FROM commodities WHERE CommodityName LIKE ?";

        try{
            return jt.query(sql, (rs, n) -> new Commodities(rs.getLong("CommodityID"),
                            rs.getString("CommodityName"),
                            rs.getInt("CommodityQty"),
                            rs.getLong("CommodityPrice"),
                            rs.getString("CommodityTag"),
                            rs.getString("CommodityImgPath"),
                            rs.getString("CommodityDetail"),
                            rs.getByte("CommoditySaleFlag"),
                            rs.getByte("CommodityDiscount"),
                            rs.getByte("CommodityDisRate")),
                    cstool.utf82iso(str));
        }
        catch (EmptyResultDataAccessException e){
            return null;
        }
        catch (Exception e){
            cstool.pLogln(e.toString(), "CommodityDao.queryByTag");
            return null;
        }
    }

    public List<Commodities> queryDiscountCom(){
        String sql = "SELECT * FROM commodities WHERE CommodityDiscount = 1";

        try{
            return jt.query(sql, (rs, n) -> new Commodities(rs.getLong("CommodityID"),
                            rs.getString("CommodityName"),
                            rs.getInt("CommodityQty"),
                            rs.getLong("CommodityPrice"),
                            rs.getString("CommodityTag"),
                            rs.getString("CommodityImgPath"),
                            rs.getString("CommodityDetail"),
                            rs.getByte("CommoditySaleFlag"),
                            rs.getByte("CommodityDiscount"),
                            rs.getByte("CommodityDisRate")));
        }
        catch (EmptyResultDataAccessException e){
            return null;
        }
        catch (Exception e){
            cstool.pLogln(e.toString(), "CommodityDao.queryByTag");
            return null;
        }
    }

    public List<Commodities> queryByRow(Long key, int rowNum){
        String sql;
        Long startPos = key;
        if (CustomProperty.osType.equals("OSS"))
            sql = "SELECT * FROM commodities WHERE CommodityID > ? and rownum <= ?";
        else{
            sql = "SELECT * FROM commodities ORDER BY CommodityID OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
            startPos = (key - 1) * rowNum;
        }

        try{
            return jt.query(sql, (rs, n) -> new Commodities(rs.getLong("CommodityID"),
                    rs.getString("CommodityName"),
                    rs.getInt("CommodityQty"),
                    rs.getLong("CommodityPrice"),
                    rs.getString("CommodityTag"),
                    rs.getString("CommodityImgPath"),
                    rs.getString("CommodityDetail"),
                    rs.getByte("CommoditySaleFlag"),
                    rs.getByte("CommodityDiscount"),
                    rs.getByte("CommodityDisRate")),
                    startPos, rowNum);
        }
        catch (EmptyResultDataAccessException e){
            return null;
        }
        catch (Exception e){
            cstool.pLogln(e.toString(), "CommodityDao.queryByTag");
            return null;
        }
    }

    public Long queryRowCount(){
        String sql;

        if (CustomProperty.osType.equals("OSS"))
            sql = "SELECT ROW COUNT FROM commodities";
        else
            sql = "SELECT COUNT(*) FROM commodities";

        try{
            return jt.queryForObject(sql, (rs, n) -> rs.getLong(1));
        }
        catch (EmptyResultDataAccessException e){
            return null;
        }
        catch (Exception e){
            cstool.pLogln(e.toString(), "CommodityDao.queryRowCount");
            return null;
        }
    }

    public String insertItem(Commodities commodities){
        try {
            String sql = "INSERT INTO commodities (CommodityName, CommodityQty, CommodityPrice, CommodityTag, CommodityImgPath, " +
                                                  "CommodityDetail, CommoditySaleFlag, CommodityDiscount, CommodityDisRate) " +
                         "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

            int rowsAffected = jt.update(sql,
                    cstool.utf82iso(commodities.getCommodityName()),
                    commodities.getCommodityQty(),
                    commodities.getCommodityPrice(),
                    cstool.utf82iso(commodities.getCommodityTag()),
                    cstool.utf82iso(commodities.getCommodityImgPath()),
                    cstool.utf82iso(commodities.getCommodityDetail()),
                    commodities.getCommoditySaleFlag(),
                    commodities.getCommodityDiscount(),
                    commodities.getCommodityDisRate());
            return "新增" + rowsAffected + "筆資料!";
        }
        catch (Exception e){
            cstool.pLogln(e.toString(), "CommodityDao.insertItem");
            return "資料庫新增失敗!";
        }
    }

    public String updateItem(Commodities commodities){
        try {
            String sql = "UPDATE commodities SET CommodityName=?, CommodityQty=?, CommodityPrice=?, CommodityTag=?, "+
                         "CommodityImgPath=?, CommodityDetail=?, CommoditySaleFlag=?, CommodityDiscount=?, CommodityDisRate=? "+
                         "WHERE CommodityID = ?;";

            int rowsAffected = jt.update(sql,
                    cstool.utf82iso(commodities.getCommodityName()),
                    commodities.getCommodityQty(),
                    commodities.getCommodityPrice(),
                    cstool.utf82iso(commodities.getCommodityTag()),
                    cstool.utf82iso(commodities.getCommodityImgPath()),
                    cstool.utf82iso(commodities.getCommodityDetail()),
                    commodities.getCommoditySaleFlag(),
                    commodities.getCommodityDiscount(),
                    commodities.getCommodityDisRate(),
                    commodities.getCommodityID());
            return "更新" + rowsAffected + "筆資料!";
        }
        catch (Exception e){
            cstool.pLogln(e.toString(), "CommodityDao.updateItem");
            return "資料庫更新失敗!";
        }
    }

    public String deleteItem(List<Long> idList){
        try {
            String sql = "DELETE FROM commodities WHERE CommodityID in(";
            StringBuilder str = new StringBuilder(sql);
            for (int i = 0; i < idList.size(); i++){
                str.append(idList.get(i));
                if (i == (idList.size() - 1))
                    str.append(");");
                else
                    str.append(",");
            }

            int rowsAffected = jt.update(str.toString());

            return "刪除" + rowsAffected + "筆資料!";
        }
        catch (Exception e){
            cstool.pLogln(e.toString(), "CommodityDao.deleteItem");
            return "資料庫刪除失敗";
        }
    }
}
