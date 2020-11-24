# spring-boot-anima

### 说明
Anima 是一款操作数据库的框架，它支持MySQL、SQLite、H2、Oracle、SqlServer等数据库，非常方便的对数据库进行操作。

[快速入门](https://github.com/biezhi/anima/wiki/%E5%BF%AB%E9%80%9F%E5%85%A5%E9%97%A8)

[创建 Model](https://github.com/biezhi/anima/wiki/%E5%88%9B%E5%BB%BA-Model)

[查询数据](https://github.com/biezhi/anima/wiki/%E6%9F%A5%E8%AF%A2%E6%95%B0%E6%8D%AE)

[保存数据](https://github.com/biezhi/anima/wiki/%E4%BF%9D%E5%AD%98%E6%95%B0%E6%8D%AE)

[更新和删除](https://github.com/biezhi/anima/wiki/%E6%9B%B4%E6%96%B0%E5%92%8C%E5%88%A0%E9%99%A4)

[事务操作](https://github.com/biezhi/anima/wiki/%E4%BA%8B%E5%8A%A1%E6%93%8D%E4%BD%9C)

[在 Spring 框架中使用](https://github.com/biezhi/anima/wiki/%E5%9C%A8-Spring-%E6%A1%86%E6%9E%B6%E4%B8%AD%E4%BD%BF%E7%94%A8)

[高级用法](https://github.com/biezhi/anima/wiki/%E9%AB%98%E7%BA%A7%E7%94%A8%E6%B3%95)

本例程是在spring boot的基础上集成Anima操作MySQL数据库，代码进行了简单的CRUD操作：

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

具体请看测试用例。

### 问题建议

- 联系我的邮箱：ilovey_hwy@163.com
- 我的博客：https://www.hwy.ac.cn
- GitHub：https://github.com/HWYWL