package com.yi.webflux.mongo.controller;

import com.yi.webflux.mongo.model.Baike;
import com.yi.webflux.mongo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * mongoDB执行操作
 * @author YI
 * @date 2018-8-22 15:14:14
 */
@RestController
@RequestMapping("/mongodb")
public class BaiKeController {
    @Autowired
    private PersonRepository personRepository;

    /**
     * 添加百科文档
     * @return
     */
    @RequestMapping("/addbaike")
    public Flux<ResponseEntity<Baike>> addDict() {
        List<String> list1 = new ArrayList<>();
        list1.add("玄幻");
        list1.add("小说");
        Baike baike1 = new Baike(3, "斗罗大陆", list1, 1050, 8, "唐家三少", "男", 500, 0, new Date(), new Date());

        List<String> list2 = new ArrayList<>();
        list2.add("玄幻");
        list2.add("小说");
        Baike baike2 = new Baike(4, "完美世界", list2, 700, 12, "辰东", "男", 300, 0, new Date(), new Date());

        List<Baike> list = new ArrayList<>();
        list.add(baike1);
        list.add(baike2);
        personRepository.insert(baike1);
//        return personRepository.insert(baike1)
//                .map(ResponseEntity::ok)
//                .defaultIfEmpty(ResponseEntity.notFound().build());

        return null;
    }

    /**
     * 根据名称查询文档
     * @param id 文档主键
     * @return
     */
    @RequestMapping("/baike")
    public Mono<ResponseEntity<Baike>> findUser(Integer id) {
        return personRepository.findById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    /**
     * 查询所有数据
     * @return
     */
    @RequestMapping("/queryAll")
    public Flux<Baike> queryAll() {

        return personRepository.findAll();
    }

    /**
     * 查询comment的bad属性等于一定数量的Baike对象
     * @param bad
     * @return
     */
    @RequestMapping("/querybad")
    public Flux<Baike> queryBad(int bad) {
        return personRepository.findByBad(bad);
    }

    /**
     * 检索置顶标签，分页显示
     * http://127.0.0.1:8080/mongodb/baike/tagPageNum?tag=魔幻&page=1
     * @param tag       标签名称
     * @param page
     * @return
     */
    @RequestMapping("/baike/tagPageNum")
    public Flux<Baike> findBaike(String tag, int page) {
        PageRequest of = PageRequest.of(page, 10);

        return personRepository.findByTagOrderByCrateDate(tag, of);
    }

    /**
     *  查询点赞数大于good，负面评价小于bad的所有Baike
     *  http://127.0.0.1:8080/mongodb/baike/tagRange?tag=魔幻&good=999999&bad=6
     * @param tag   标签名称
     * @param good  ＜(＾－＾)＞ 你最棒 点赞数
     * @param bad   (｡•ˇ‸ˇ•｡)滚犊子    负面数
     * @return
     */
    @RequestMapping("/baike/tagRange")
    public Flux<Baike> findBaike(String tag, int good, int bad) {
        return personRepository.findByTagAndGoodGreaterThanAndBadLessThan(tag, good, bad);
    }

    /**
     * 更新文档,更新某个字段(此处更改打赏金币数)
     * @param baike
     * @return
     */
    @RequestMapping("/updatebaike")
    public Mono<ResponseEntity<Baike>> updateDict(Integer id, Baike baike) {
        return personRepository.findById(id)
                .flatMap(existingBaike -> {
                    existingBaike.setGoldCoin(baike.getGoldCoin());
                    return personRepository.save(existingBaike);
                })
                .map(updateTweet -> new ResponseEntity<>(updateTweet, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * 更新文档,bean中包含id
     * @param baike
     * @return
     */
    @RequestMapping("/updateDictAll")
    public Mono<ResponseEntity<Baike>> updateDictAll(Baike baike) {
        return personRepository.save(baike)
                .map(updateBaike -> new ResponseEntity<>(updateBaike, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * 通过id删除文档
     * @param id
     * @return
     */
    @RequestMapping("/deletebaike")
    public Mono<ResponseEntity<Void>> deleteDict(Integer id) {
        return personRepository.findById(id)
                .flatMap(existingTweet ->
                        personRepository.delete(existingTweet)
                                .then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK)))
                )
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
