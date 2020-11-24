# spring-boot-exception

### 说明
统一异常处理

这里分为两个两个异常处理类型，分别是json统一异常和页面统一异常。

我们声明一个异常处理器
```
@ControllerAdvice
@Slf4j
public class ExceptionHandler {
	private static final String DEFAULT_ERROR_VIEW = "error";

	/**
	 * 统一 json 异常处理
	 *
	 * @param exception JsonException
	 * @return 统一返回 json 格式
	 */
	@org.springframework.web.bind.annotation.ExceptionHandler(value = JsonException.class)
	@ResponseBody
	public ApiResponse jsonErrorHandler(HttpServletRequest req, JsonException exception) {
		log.error("【JsonException】:{}", exception.getMessage());
		return ApiResponse.ofException(exception);
	}

	/**
	 * 统一 页面 异常处理
	 *
	 * @param exception PageException
	 * @return 统一跳转到异常页面
	 */
	@org.springframework.web.bind.annotation.ExceptionHandler(value = PageException.class)
	public ModelAndView pageErrorHandler(HttpServletRequest req,PageException exception) {
		log.error("【PageException】:{}", exception.getMessage());
		ModelAndView view = new ModelAndView();
		view.addObject("url", req.getRequestURL());
		view.addObject("message", exception.getMessage());
		view.setViewName(DEFAULT_ERROR_VIEW);
		return view;
	}
}
```

处理器里面分为两个异常处理的方法：
- jsonErrorHandler 用来处理需要返回的接送数据。
- pageErrorHandler 用来处理需要返回的错误页面。

然后我们写一个**TestController**测试一下
```
@Controller
public class TestController {

    /**
     * 统一异常返回接送
     *
     * @return json数据
     */
    @GetMapping("/json")
    @ResponseBody
    public ApiResponse jsonException() {
        throw new JsonException(Status.UNKNOWN_ERROR);
    }

    /**
     * 统一异常返回页面
     *
     * @return 页面
     */
    @GetMapping("/page")
    public ModelAndView pageException() {
        throw new PageException(Status.UNKNOWN_ERROR);
    }
}
```

json效果：
```
{
	"code": 500,
	"message": "服务器出错啦",
	"data": null
}
```

页面效果：

![](https://hwy-figure-bed.oss-cn-hangzhou.aliyuncs.com/image/1.png)

### 问题建议

- 联系我的邮箱：ilovey_hwy@163.com
- 我的博客：https://hwy.ac.cn
- GitHub：https://github.com/HWYWL