package com.rookie.dao.impl;

import com.rookie.dao.FlowerDao;
import com.rookie.pojo.FlowerKind;
import com.rookie.pojo.Flowers;
import com.rookie.utils.HikariCPUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class FlowerDaoImpl implements FlowerDao {
    public QueryRunner qr = new QueryRunner(HikariCPUtil.getDataSource());

    @Override
    public int queryFlowersTotalRecords() {
        String sql = "select count(flowerid) from flower";
        try {
            Long pagenum = (Long) qr.query(sql, new ScalarHandler<>());
            return pagenum.intValue();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Flowers> findPageFlowers(Integer startIndex, Integer pageSize) {
        String sql = "select * from flower limit ?,?";

        try {
            return qr.query(sql,new BeanListHandler<>(Flowers.class),startIndex,pageSize);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Flowers queryFlowerById(Integer flowerid) {
        String sql = "select * from flower where flowerid=?";

        try {
            return qr.query(sql,new BeanHandler<>(Flowers.class),flowerid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Flowers> findPageFlowerName(String flowerName) {
        String sql = "select * from flower where flowername like concat('%',?,'%')";
        try {
            return qr.query(sql,new BeanListHandler<>(Flowers.class),flowerName);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public List<Flowers> findPageFlowerName(Integer startIndex, Integer pageSize, String flowerName) {
        String sql = "select * from flower where flowername like concat('%',?,'%') limit ?,? ";
        try {
            return qr.query(sql,new BeanListHandler<>(Flowers.class),flowerName,startIndex,pageSize);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Flowers flowersMaxSales() {
        String sql = "SELECT *FROM `flower` WHERE `sales`= (SELECT MAX(sales) FROM `flower`)";
        try {
            return qr.query(sql,new BeanHandler<>(Flowers.class));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void setSalesAndAmount(Flowers flowers1) {
        String sql = "UPDATE `flower` SET `sales`=?,`amount`= ?,`stockstatus`=? where flowerid=?";
        try {
            qr.update(sql,flowers1.getSales(),flowers1.getAmount(),flowers1.getStockstatus(),flowers1.getFlowerid());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<FlowerKind> queryFlowerkind() {
        String sql = "SELECT *FROM `flowerkind`";
        try {
            return qr.query(sql,new BeanListHandler<>(FlowerKind.class));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Flowers> findPageFlowerKind(Integer kid) {
        String sql = "select * from flower where kindid=?  ";
        try {
            return qr.query(sql,new BeanListHandler<>(Flowers.class),kid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Flowers> findPageFlowerKind(Integer startIndex, Integer pageSize, Integer kid) {
        String sql = "select * from flower where kindid=? limit ?,? ";
        try {
            return qr.query(sql,new BeanListHandler<>(Flowers.class),kid,startIndex,pageSize);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
