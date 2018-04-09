package com.kygo.api.download.controller;

import com.cdvcredit.common.utils.DateUtil;
import com.cdvcredit.common.utils.ExcelUtil;
import com.cdvcredit.common.utils.JSONUtils;
import com.kygo.api.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.*;

@Api(tags = "奖品导入导出", produces = MediaType.APPLICATION_JSON_VALUE)
@Controller
@RequestMapping("/download/luckDraw")
public class ExcelJsonDownloadController extends BaseController{
	
	private Logger logger = LoggerFactory.getLogger(getClass().getSimpleName());
	
	@ApiOperation(value = "用户数据下载", notes = "用户数据下载", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/users", method = RequestMethod.POST)
    public ResponseEntity<byte[]> luckDrawSystemUser(@RequestParam("file") MultipartFile file) throws Exception {
		logger.debug("调用用户数据下载");
		String contentType = file.getContentType();
        String fileName = file.getOriginalFilename();
        logger.debug("contentType = {}, fileName = {}", contentType, fileName);
        InputStream inputStream = file.getInputStream();
        List<String[]> data = ExcelUtil.readExcel(inputStream, 5);
        Map<String, Object> resultMap = new HashMap<>();
        Map<String, String> currentMap = new HashMap<>();
        currentMap.put("current", DateUtil.format(new Date()));
        resultMap.put("system", currentMap);
        List<Map<String, String>> users = new ArrayList<>(); 
        for(String[] userArr : data){
        	Map<String, String> user = new HashMap<>();
        	user.put("id", userArr[0]);
        	user.put("name", userArr[1]);
        	user.put("phone", userArr[2]);
        	user.put("entry", userArr[3]);
        	users.add(user);
        }
        resultMap.put("users", users);
        String json = JSONUtils.obj2Json(resultMap);
        return stringToResponseEntity(json, "users.json");
    }
	
	@ApiOperation(value = "奖品数据下载", notes = "奖品数据下载", produces = "application/json")
    @PostMapping("/awards")
    public ResponseEntity<byte[]> luckDrawAward(@RequestParam("file") MultipartFile file) throws Exception {
    	logger.debug("调用奖品数据下载");
		String contentType = file.getContentType();
        String fileName = file.getOriginalFilename();
        logger.debug("contentType = {}, fileName = {}", contentType, fileName);
        InputStream inputStream = file.getInputStream();
        List<String[]> data = ExcelUtil.readExcel(inputStream, 5);
        Map<String, Object> resultMap = new HashMap<>();
        
        List<Map<String, String>> awardList = new ArrayList<>(); 
        for(String[] arr : data){
        	Map<String, String> user = new HashMap<>();
        	user.put("level", arr[0]);
        	user.put("name", arr[1]);
        	user.put("desc", arr[2]);
        	user.put("number", arr[3]);
        	awardList.add(user);
        }
        resultMap.put("draws", awardList);
        String json = JSONUtils.obj2Json(resultMap);
        return stringToResponseEntity(json, "awards.json");
    }
    
    private ResponseEntity<byte[]> stringToResponseEntity(String json, String attachmentName) throws UnsupportedEncodingException{
    	 HttpHeaders headers = new HttpHeaders();    
         headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);  
         headers.setContentDispositionFormData("attachment", new String(attachmentName.getBytes("UTF-8"), "ISO8859-1")); 
 		return new ResponseEntity<byte[]>(json.getBytes(), headers, HttpStatus.CREATED);  
    }

}
