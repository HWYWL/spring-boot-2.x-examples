# spring-boot-version
web服务版本号控制接口

### 说明
系统上线以后，需求仍会发生变动，功能也会迭代更新。可能是接口参数发生变更，也有可能是业务逻辑需要调整，如果直接在原来的接口上进行修改，必然会影响原有服务的正常运行。

常见的解决方案，是在接口路径中加入版本号用于区分，此外还可以在参数甚至 header 里带上版本号。这里以在请求路径中带上版本号为例，如：http://IP:PORT/api/v1/test ，v1 即代表的是版本号。当然了，可以像这样，直接写死在 @RequestMapping("api/v1/test") 属性中，不过下面提供了更为优雅的解决方案。

### 实现
**自定义版本号标记注解**
```java
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ApiVersion {

    /**
     * 标识版本号，默认为0
     */
    int value() default 1;
}
```
**重写请求版本号条件匹配**
```java
@Data
@Slf4j
public class ApiVersionCondition implements RequestCondition<ApiVersionCondition> {

    /**
     * 接口路径中的版本号前缀，如: api/v[1-n]/test
     */
    private final static Pattern VERSION_PREFIX_PATTERN = Pattern.compile("/v(\\d+)/");

    private int apiVersion;

    ApiVersionCondition(int apiVersion) {
        this.apiVersion = apiVersion;
    }

    @Override
    public ApiVersionCondition combine(ApiVersionCondition other) {
        // 最近优先原则，方法定义的 @ApiVersion > 类定义的 @ApiVersion
        return new ApiVersionCondition(other.getApiVersion());
    }

    @Override
    public ApiVersionCondition getMatchingCondition(HttpServletRequest request) {
        Matcher m = VERSION_PREFIX_PATTERN.matcher(request.getRequestURI());
        if (m.find()) {
            // 获得符合匹配条件的ApiVersionCondition
            int version = Integer.parseInt(m.group(1));
            if (version >= getApiVersion()) {
                return this;
            }
        }
        return null;
    }

    @Override
    public int compareTo(ApiVersionCondition other, HttpServletRequest request) {
        // 当出现多个符合匹配条件的ApiVersionCondition，优先匹配版本号较大的
        return other.getApiVersion() - getApiVersion();
    }
}
```

**重写请求映射**
```java
@Slf4j
public class CustomRequestMappingHandlerMapping extends RequestMappingHandlerMapping {

    @Override
    protected RequestCondition<?> getCustomTypeCondition(Class<?> handlerType) {
        // 扫描类上的 @ApiVersion
        ApiVersion apiVersion = AnnotationUtils.findAnnotation(handlerType, ApiVersion.class);
        return createRequestCondition(apiVersion);
    }

    @Override
    protected RequestCondition<?> getCustomMethodCondition(Method method) {
        // 扫描方法上的 @ApiVersion
        ApiVersion apiVersion = AnnotationUtils.findAnnotation(method, ApiVersion.class);
        return createRequestCondition(apiVersion);
    }

    private RequestCondition<ApiVersionCondition> createRequestCondition(ApiVersion apiVersion) {
        if (Objects.isNull(apiVersion)) {
            return null;
        }
        int value = apiVersion.value();
        Assert.isTrue(value >= 1, "Api Version Must be greater than or equal to 1");
        return new ApiVersionCondition(value);
    }

}
```
**把我们写的映射器写入注册配置**
```java
@Slf4j
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {

    @Override
    public RequestMappingHandlerMapping createRequestMappingHandlerMapping() {
        return new CustomRequestMappingHandlerMapping();
    }
}
```

**最后写我们的controller测试**
```java
@ApiVersion
@RestController
@RequestMapping("api/{version}/test")
public class TestController {

    /**
     * 只能匹配v1的版本
     *
     * @param version 版本号
     */
    @GetMapping
    public String test01(@PathVariable String version) {
        return "test01 : " + version;
    }

    /**
     * 匹配大于等于版本为2的版本，例如v2、v3
     *
     * @param version 版本号
     */
    @GetMapping
    @ApiVersion(2)
    public String test02(@PathVariable String version) {
        return "test02 : " + version;
    }
}
```


**结果**

![](https://i.imgur.com/OMbVBxb.png)

我们可以看到设计达到了预期，v1版本只能走test01，v2版本走test02，版本数大于2的走最大版本数接口，既test02。

文章思路来自于：肥朝公众号

### 问题建议

- 联系我的邮箱：ilovey_hwy@163.com
- 我的博客：http://www.hwy.ac.cn
- GitHub：https://github.com/HWYWL
