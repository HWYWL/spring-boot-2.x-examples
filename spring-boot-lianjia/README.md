# spring-boot-lianjia

### 说明
别问为什么爬的是二手房，问就是买不起，嗯，是的，我连现在爬的二手房也一样买不起。

![](https://i.imgur.com/Z2BWQfG.jpg)

我们言归正传，我在连接上找一了一下二手房，挺多的，六万多套，就是不知道我的在哪。

![](https://i.imgur.com/P7nnT61.png)

听说今年房地产不景气，价格有所下降(一点点)，所以我风平浪静的心也开始蠢蠢欲动，啊，春天来了吗！！！

爬虫需要知道几个关键信息，一个是分页，另一个是详情页，我们从链家的地址可以看出**https://gz.lianjia.com/ershoufang/pg2/**，pg2应该就是单词page2的缩写，赶紧翻到第三页,果不起然是pg3，分页有了我们来看看详情页，万能的**F12**,通过标题直接显示出详情页，连拼接都省了，链家真贴心。

![](https://i.imgur.com/MRXYnZI.png)

再看看里面的详情页所有数据被安排的整整齐齐，真香。

![](https://i.imgur.com/88OWL7m.png)

竟然万事俱备，那我们就上吧，代码撸起来！！！

![](https://i.imgur.com/TVsyREI.jpg)

### 撸代码
爬虫框架用的是 **webmagic** 通过一个循环生成 **pg** 页，再把怕下来的数据落到MySQL数据库。
别问为啥用 **webmagic** ，问就是喜欢(简单)。

![](https://i.imgur.com/QhgYNCZ.jpg)

巴拉巴拉写好代码，代码不复杂，只要会xpath，撸遍天下都不怕，代码地址：
```
https://github.com/HWYWL/spring-boot-2.x-examples/tree/master/spring-boot-lianjia
```

我们来看看成果，我们来排序一下，从高到低，刺激。
![](https://i.imgur.com/pB20zv7.png)

红色框左边的是单价（万元/平米），右边的是总价（万元），看完这价格感觉血压有点高，手里的窝窝头突然不香了。

![](https://i.imgur.com/MWookXS.jpg)

我们还是看看低价吧，惹不起.jpg

![](https://i.imgur.com/s8IoeHF.png)

低价的就是很香了,除了偏僻一点、小一点。。。


### 总结一下
爬取的时候只能爬到一百页，如果再往下就是重复数据，此时你需要增加筛选条件，数据就会变得不一样，
可能就是为了防止我这种人去爬数据做限制。不过想想其实也正常，正常看房的谁真的能翻到一百页，
我羊城的房子看看就好，不要管几手的，反正我都买不起。

![](https://i.imgur.com/dHOTmhZ.jpg)


### 问题建议

- 联系我的邮箱：ilovey_hwy@163.com
- 我的博客：http://www.hwy.ac.cn
- GitHub：https://github.com/HWYWL