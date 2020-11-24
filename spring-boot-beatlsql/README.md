# spring-boot-beatlsql
BeetSql是一个全功能DAO工具， 同时具有Hibernate 优点 & Mybatis优点功能，适用于承认以SQL为中心，同时又需求工具能自动能生成大量常用的SQL的应用。

### 说明
特性如下：

- 开发效率
  - 无需注解，自动使用大量内置SQL，轻易完成增删改查功能，节省50%的开发工作量
  - 数据模型支持Pojo，也支持Map/List这种快速模型，也支持混合模型
  - SQL 模板基于Beetl实现，更容易写和调试，以及扩展
  - 可以针对单个表(或者视图）代码生成pojo类和sql模版，甚至是整个数据库。能减少代码编写工作量
- 维护性
  - SQL 以更简洁的方式，Markdown方式集中管理，同时方便程序开发和数据库SQL调试。
  - 可以自动将sql文件映射为dao接口类
  - 灵活直观的支持支持一对一，一对多，多对多关系映射而不引入复杂的OR Mapping概念和技术。
  - 具备Interceptor功能，可以调试，性能诊断SQL，以及扩展其他功能
- 其他
  - 内置支持主从数据库支持的开源工具
  - 性能数倍于JPA，MyBatis
  - 支持跨数据库平台，开发者所需工作减少到最小，目前跨数据库支持mysql,postgres,oracle,sqlserver,h2,sqllite,DB2.

### 代码
只需要简单继承BaseMapper，就能实现一个功能完善的dao
```java
/**
 * 操作数据库
 * @author YI
 * @date 2019-3-5 22:05:40
 */
public interface BaikeDao extends BaseMapper<Baike> {

}
```

在BaseMapper类中实现了各种常用的sql语法操作
```java
package org.beetl.sql.core.mapper;

import java.util.List;

import org.beetl.sql.core.SQLManager;
import org.beetl.sql.core.db.KeyHolder;
import org.beetl.sql.core.engine.PageQuery;
import org.beetl.sql.core.query.LambdaQuery;
import org.beetl.sql.core.query.Query;

/**
 * BaseMapper.
 *
 * @param <T> the generic type
 */
public interface BaseMapper<T> {

    /**
     * 通用插入，插入一个实体对象到数据库，所以字段将参与操作，除非你使用ColumnIgnore注解
     *
     * @param entity
     */
    void insert(T entity);

    /**
     * （数据库表有自增主键调用此方法）如果实体对应的有自增主键，插入一个实体到数据库，设置assignKey为true的时候，将会获取此主键
     *
     * @param entity
     * @param autDbAssignKey 是否获取自增主键
     */
    void insert(T entity, boolean autDbAssignKey);

    /**
     * 插入实体到数据库，对于null值不做处理
     *
     * @param entity
     */
    void insertTemplate(T entity);

    /**
     * 如果实体对应的有自增主键，插入实体到数据库，对于null值不做处理,设置assignKey为true的时候，将会获取此主键
     *
     * @param entity
     * @param autoDbAssignKey
     */
    void insertTemplate(T entity, boolean autoDbAssignKey);

    /**
     * 批量插入实体。此方法不会获取自增主键的值，如果需要，建议不适用批量插入，适用
     * <pre>
     * insert(T entity,true);
     * </pre>
     *
     * @param list
     */
    void insertBatch(List<T> list);

    /**
     * 批量插入，使用数据库返回自增主键
     * @param list
     * @param autoDbAssignKey
     */
    void insertBatch(List<T> list,boolean  autoDbAssignKey);

    /**
     * （数据库表有自增主键调用此方法）如果实体对应的有自增主键，插入实体到数据库，自增主键值放到keyHolder里处理
     *
     * @param entity
     * @return
     */
    KeyHolder insertReturnKey(T entity);

    /**
     * 根据主键更新对象，所以属性都参与更新。也可以使用主键ColumnIgnore来控制更新的时候忽略此字段
     * @param entity
     * @return
     */
    int updateById(T entity);

    /**
     * 根据主键更新对象，只有不为null的属性参与更新
     *
     * @param entity
     * @return
     */
    int updateTemplateById(T entity);

    /**
     * 按照主键更新更新或插入,自增或者序列id自动赋值给entity
     * @param entity 待更新/插入的实体对象
     * @return 受影响条数
     */
    int upsert(T entity);

    /**按照主键更新或插入，更新失败，会调用插入，属性为空的字段将不更新或者插入。自增或者序列id自动赋值给entity
     * @param entity 待更新/插入的实体对象
     * @return
     */
    int upsertByTemplate(T entity);

    /**
     * 根据主键删除对象，如果对象是复合主键，传入对象本生即可
     *
     * @param key
     * @return
     */
    int deleteById(Object key);


    /**
     * 根据主键获取对象，如果对象不存在，则会抛出一个Runtime异常
     *
     * @param key
     * @return
     */
    T unique(Object key);

    /**
     * 根据主键获取对象，如果对象不存在，返回null
     *
     * @param key
     * @return
     */
    T single(Object key);


    /**
     * 根据主键获取对象，如果在事物中执行会添加数据库行级锁(select * from table where id = ? for update)，如果对象不存在，返回null
     *
     * @param key
     * @return
     */
    T lock(Object key);

    /**
     * 返回实体对应的所有数据库记录
     *
     * @return
     */
    List<T> all();

    /**
     * 返回实体对应的一个范围的记录
     *
     * @param start
     * @param size
     * @return
     */
    List<T> all(int start, int size);

    /**
     * 返回实体在数据库里的总数
     *
     * @return
     */
    long allCount();

    /**
     * 模板查询，返回符合模板得所有结果。beetlsql将取出非null值（日期类型排除在外），从数据库找出完全匹配的结果集
     *
     * @param entity
     * @return
     */
    List<T> template(T entity);


    /**
     * 模板查询，返回一条结果,如果没有，返回null
     *
     * @param entity
     * @return
     */
    <T> T templateOne(T entity);

    List<T> template(T entity, int start, int size);

    void templatePage(PageQuery<T> query);

    /**
     * 符合模板得个数
     *
     * @param entity
     * @return
     */
    long templateCount(T entity);


    /**
     * 执行一个jdbc sql模板查询
     *
     * @param sql
     * @param args
     * @return
     */
    List<T> execute(String sql, Object... args);

    /**
     * 执行一个更新的jdbc sql
     *
     * @param sql
     * @param args
     * @return
     */
    int executeUpdate(String sql, Object... args);

    SQLManager getSQLManager();

    /**
     * 返回一个Query对象
     *
     * @return
     */
    Query<T> createQuery();


    /**
     * 返回一个LambdaQuery对象
     *
     * @return
     */
    LambdaQuery<T> createLambdaQuery();
}

```

### 执行结果
![](https://i.imgur.com/fBlRLHO.png)

知道SQL监控
![](https://i.imgur.com/TcLqmhf.png)

更多复杂的操作，请参考官方文档：http://ibeetl.com/guide/#beetlsql


### 问题建议

- 联系我的邮箱：ilovey_hwy@163.com
- 我的博客：https://www.hwy.ac.cn
- GitHub：https://github.com/HWYWL