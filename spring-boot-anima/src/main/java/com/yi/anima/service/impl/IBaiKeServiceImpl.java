package com.yi.anima.service.impl;

import com.yi.anima.model.Baike;
import com.yi.anima.service.IBaiKeService;
import io.github.biezhi.anima.Anima;
import org.springframework.stereotype.Service;

import java.util.List;

import static io.github.biezhi.anima.Anima.select;

/**
 * 自定义实现类
 * @author YI
 * @date 2018-11-27 14:40:31
 */
@Service
public class IBaiKeServiceImpl implements IBaiKeService {
    @Override
    public Baike selectById(int id) {
        return select().from(Baike.class).byId(id);
    }

    @Override
    public int save(Baike baike) {
        return baike.save().asInt();
    }

    @Override
    public void saveBatch(List<Baike> baikes) {
        Anima.saveBatch(baikes);
    }

    @Override
    public void update(Baike baike) {
        baike.update();
    }

    @Override
    public void deleteById(int id) {
        Anima.deleteById(Baike.class, id);
    }

    @Override
    public List<Baike> selectListBySQL() {
        return select().bySQL(Baike.class, "select * from baike limit ?", 3).all();
    }

    @Override
    public List<Baike> selectListAll() {
        return select().from(Baike.class).all();
    }

    @Override
    public List<Baike> selectListByGood(int good) {
        return select().from(Baike.class).gte("age", good).all();
    }
}
