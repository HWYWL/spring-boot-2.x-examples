package com.yi.sm.controller;

import com.yi.sm.model.Sm;
import com.yi.sm.utils.MessageResult;
import com.yi.sm.utils.SmUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;

/**
 * 上传图片到sm图床
 *
 * @author YI
 * @date 2019-3-22 17:39:16
 */
@Controller
@RequestMapping("/sm")
public class SmController {

	/**
	 * 主页
	 * @return
	 */
	@RequestMapping("/")
	public String index(){
		return "index";
	}


	/**
	 * 图片文件上传
	 * @param file 文件
	 * @return
	 */
	@RequestMapping("/banner")
	@ResponseBody
	public MessageResult banner(@RequestParam("file") MultipartFile file) {
		HashMap<Object, Object> map = new HashMap<>(16);
		MessageResult messageResult = MessageResult.ok();

		if (file.isEmpty()) {
			return MessageResult.errorMsg("请选择要上传的文件！");
		}

		try {
			Sm sm = SmUtil.saveFile(file, false, "json");
			if (sm.getCode().equals("success")){
				map.put("url", sm.getData().getUrl());
			}else {
				messageResult = MessageResult.errorMsg("上传失败！" + sm.getMsg());
			}
		} catch (Exception e) {
			messageResult = MessageResult.errorMsg("上传失败！" + e.getMessage());
		}

		messageResult.setData(map);

		return messageResult;
	}
}
