package com.mall.cloud.passport.web.controller.storage;

import com.google.common.collect.Maps;
import com.mall.cloud.common.annotation.ApplicationAuthorize;
import com.mall.cloud.common.annotation.dubbo.DubboConsumerClient;
import com.mall.cloud.common.constant.Constants;
import com.mall.cloud.common.constant.ResponseType;
import com.mall.cloud.common.constant.ScopeType;
import com.mall.cloud.common.exception.ConsoleServerException;
import com.mall.cloud.common.exception.PassportServerException;
import com.mall.cloud.common.persistence.controller.BaseController;
import com.mall.cloud.common.persistence.param.StorageFileParam;
import com.mall.cloud.common.restful.ResponseResult;
import com.mall.cloud.common.utils.ApplicationServerUtil;
import com.mall.cloud.common.utils.CheckEmptyUtil;
import com.mall.cloud.common.utils.FileServerUtil;
import com.mall.cloud.common.utils.LoggerServerUtil;
import com.mall.cloud.model.entity.file.FileUpload;
import com.mall.cloud.model.entity.user.AdminUser;
import com.mall.cloud.passport.api.service.StorageServerService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

/**
 * <p>封装Qicloud项目PassportStorageController类.<br></p>
 * <p>//TODO...<br></p>
 *
 * @author Powered by marklin 2020-10-27 14:57
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p>
 */
@RestController
@RequestMapping(value = "/api/storage/center/", produces = "application/json;charset=UTF-8")
public class StorageCenterController extends BaseController {

    @DubboConsumerClient
    private StorageServerService storageServerService;

    @Value("${project.environment.type}")
    private String projectType;
    @Value("${project.environment.target}")
    private Boolean environment;

    /**
     * 获取上传进度
     *
     * @return 返回结果
     */
    @ResponseBody
    @PostMapping(value = "upload", produces = "application/json;charset=utf-8")
    public String uploadProcess() {
        ResponseResult result = new ResponseResult();
        HttpServletRequest request = ApplicationServerUtil.getRequest();
        HttpSession session = request.getSession();
        int status = session.getAttribute("uploadStatus") == null ? 0 : (int) session.getAttribute("uploadState");
        result.putResult("status", status);
        return result.parseToJson(result);
    }


    /**
     * 重置进度
     *
     * @param session session
     */
    private void reset(HttpSession session) {
        logger.info("初始化上传进度");
        session.setAttribute("uploadState", 0);
    }

    /**
     * 文件处理
     *
     * @param result    结果
     * @param fileParam 文件数据
     * @param file      文件
     * @param userId    登陆用户
     * @return 结果
     * @throws IOException 异常
     */
    private ResponseResult fileProcess(ResponseResult result, StorageFileParam fileParam, MultipartFile file, String userId) throws IOException {
        String originalFilename = file.getOriginalFilename();
        if (StringUtils.isEmpty(originalFilename)) {
            logger.error("上传文名称为空");
            result.setError("上传文名称为空");
            return result;
        }
        //获取文件类型
        String contentType = file.getContentType();
        //判断文件是不是图片
        //获取上传文件的原始文件名
        String oFileName = file.getOriginalFilename();
        //把文件转化成byte[]
        Long size = file.getSize();
        String extName = FileServerUtil.achieveFilePostfix(originalFilename);
        boolean checkExistPicture = FileServerUtil.checkIsExist(Constants.PICTURE_FORMATS, extName.toLowerCase());
        if (checkExistPicture) {
            BufferedImage image = ImageIO.read(file.getInputStream());
            //如果image=null 表示上传的不是图片格式
            if (image != null) {
                //获取图片宽度，单位px
                System.out.println(image.getWidth());
                fileParam.setHeight(image.getHeight());
                fileParam.setWidth(image.getWidth());
                //获取图片高度，单位px
                System.out.println(image.getHeight());
            }
        }
        fileParam.setExtName(extName);
        fileParam.setFile(file);
        fileParam.setName(oFileName);
        fileParam.setContentType(contentType);
        fileParam.setSize(size);
        fileParam.setUserId(userId);
        fileParam.setType(projectType);
        return result;
    }

    /**
     * 根据问价扩展名获取OSS存储空间
     *
     * @param extName 文件扩展名
     * @return OSS存储空间
     */
    private String getBucketName(String extName) {
        //图片文件
        boolean checkPicture = FileServerUtil.checkIsExist(Constants.PICTURE_FORMATS, extName.toLowerCase());
        if (checkPicture) {
            return Constants.PICTURE;
        }
        //音频文件
        boolean checkAudio = FileServerUtil.checkIsExist(Constants.AUDIO_FORMATS, extName.toLowerCase());
        if (checkAudio) {
            return Constants.AUDIO;
        }
        //视频文件
        boolean checkVideo = FileServerUtil.checkIsExist(Constants.VIDEO_FORMATS, extName.toLowerCase());
        if (checkVideo) {
            return Constants.VIDEO;
        }
        //视频文件
        boolean checkDocument = FileServerUtil.checkIsExist(Constants.DOCUMENT_FORMATS, extName.toLowerCase());
        if (checkDocument) {
            return Constants.DOCUMENT;
        }
        return Constants.TEMPLATE;
    }

    /**
     *
     * @param file
     * @return
     * @throws PassportServerException
     */
    @ResponseBody
    @ApplicationAuthorize(authorizeResources = false, authorizeLogin = false, authorizeScope = ScopeType.WEB)
    @PostMapping(value = "minio", produces = "application/json;charset=UTF-8")
    private String minio(MultipartFile file) throws PassportServerException {
        ResponseResult result = new ResponseResult();
        // [1].用户登录鉴权
        AdminUser user = (AdminUser) request.getAttribute(Constants.ADMIN_USER);
        if (CheckEmptyUtil.isEmpty(user)) {
            throw new ConsoleServerException("系统繁忙，请稍后再试!");
        }
        // [2].获取系统当前会话
        HttpServletRequest request = ApplicationServerUtil.getRequest();
        HttpSession session = request.getSession();
        reset(session);
        // [3].获取上传的文件的名称
        if (CheckEmptyUtil.isEmpty(file)) {
            result.setError("上传文件为空!");
            return result.parseToJson(result);
        }
        try {
            StorageFileParam fileParam = new StorageFileParam();
            if (Objects.equals(result.getCode(), ResponseType.FAILURE.code())) {
                return result.parseToJson(result);
            }
            result = this.fileProcess(result, fileParam, file, user.getId());
            String extName = FileServerUtil.achieveFilePostfix(fileParam.getExtName());
            String bucketName = this.getBucketName(extName);
            FileUpload fileUpload;
            if (Objects.equals(bucketName, Constants.VIDEO) || Objects.equals(bucketName, Constants.AUDIO)) {
                fileUpload = storageServerService.minioStorageFiles(fileParam, bucketName, environment);
            } else {
                fileUpload = storageServerService.minioStorageFile(fileParam, bucketName, environment);
            }
            //处理上传结果
            Map<String, Object> resultMap = Maps.newConcurrentMap();
            resultMap.put("id", fileUpload.getId() + "");
            resultMap.put("type", projectType);
            resultMap.put("url", fileUpload.getUrl());
            result.setResult(resultMap);
            LoggerServerUtil.info(logger, "Result:{}", result::parseToJson);
        } catch (PassportServerException | IOException exception) {
            exception.printStackTrace();
            result.setError("保存失败");
            LoggerServerUtil.info(logger, "Result:{}", result::parseToJson);
        }
        return result.parseToJson(result);
    }


    @ResponseBody
    @ApplicationAuthorize(authorizeResources = false, authorizeLogin = false, authorizeScope = ScopeType.WEB)
    @PostMapping(value = "oss", produces = "application/json;charset=UTF-8")
    private String oss(MultipartFile file) throws PassportServerException {
        ResponseResult result = new ResponseResult();
        // [1].用户登录鉴权
        AdminUser user = (AdminUser) request.getAttribute(Constants.ADMIN_USER);
        if (CheckEmptyUtil.isEmpty(user)) {
            throw new ConsoleServerException("系统繁忙，请稍后再试!");
        }
        // [2].获取系统当前会话
        HttpServletRequest request = ApplicationServerUtil.getRequest();
        HttpSession session = request.getSession();
        reset(session);
        // [3].获取上传的文件的名称
        if (CheckEmptyUtil.isEmpty(file)) {
            result.setError("上传文件为空!");
            return result.parseToJson(result);
        }
        try {
            StorageFileParam fileParam = new StorageFileParam();
            if (Objects.equals(result.getCode(), ResponseType.FAILURE.code())) {
                return result.parseToJson(result);
            }
            result = this.fileProcess(result, fileParam, file, user.getId());
            String extName = FileServerUtil.achieveFilePostfix(fileParam.getExtName());
            String bucketName = this.getBucketName(extName);
            FileUpload fileUpload;
            if (Objects.equals(bucketName, Constants.VIDEO) || Objects.equals(bucketName, Constants.AUDIO)) {
                fileUpload = storageServerService.ossStorageFiles(fileParam, bucketName, environment);
            } else {
                fileUpload = storageServerService.ossStorageFiles(fileParam, bucketName, environment);
            }
            //处理上传结果
            Map<String, Object> resultMap = Maps.newConcurrentMap();
            resultMap.put("id", fileUpload.getId() + "");
            resultMap.put("type", projectType);
            resultMap.put("url", fileUpload.getUrl());
            result.setResult(resultMap);
            LoggerServerUtil.info(logger, "Result:{}", result::parseToJson);
        } catch (PassportServerException | IOException exception) {
            exception.printStackTrace();
            result.setError("保存失败");
            LoggerServerUtil.info(logger, "Result:{}", result::parseToJson);
        }
        return result.parseToJson(result);
    }

    @ResponseBody
    @ApplicationAuthorize(authorizeResources = false, authorizeLogin = false, authorizeScope = ScopeType.WEB)
    @PostMapping(value = "base64", produces = "application/json;charset=UTF-8")
    private String base64(MultipartFile file) throws PassportServerException {
        ResponseResult result = new ResponseResult();
        // [1].用户登录鉴权
        AdminUser user = (AdminUser) request.getAttribute(Constants.ADMIN_USER);
        if (CheckEmptyUtil.isEmpty(user)) {
            throw new ConsoleServerException("系统繁忙，请稍后再试!");
        }
        // [2].获取系统当前会话
        HttpServletRequest request = ApplicationServerUtil.getRequest();
        HttpSession session = request.getSession();
        reset(session);
        // [3].获取上传的文件的名称
        if (CheckEmptyUtil.isEmpty(file)) {
            result.setError("上传文件为空!");
            return result.parseToJson(result);
        }
        try {
            StorageFileParam fileParam = new StorageFileParam();
            if (Objects.equals(result.getCode(), ResponseType.FAILURE.code())) {
                return result.parseToJson(result);
            }
            result = this.fileProcess(result, fileParam, file, user.getId());
            String extName = FileServerUtil.achieveFilePostfix(fileParam.getExtName());
            String bucketName = this.getBucketName(extName);
            FileUpload fileUpload;
            if (Objects.equals(bucketName, Constants.VIDEO) || Objects.equals(bucketName, Constants.AUDIO)) {
                fileUpload = storageServerService.ossStorageFile(fileParam, bucketName, environment);
            } else {
                fileUpload = storageServerService.ossStorageFiles(fileParam, bucketName, environment);
            }
            //处理上传结果
            Map<String, Object> resultMap = Maps.newConcurrentMap();
            resultMap.put("id", fileUpload.getId() + "");
            resultMap.put("type", projectType);
            resultMap.put("url", fileUpload.getUrl());
            result.setResult(resultMap);
            LoggerServerUtil.info(logger, "Result:{}", result::parseToJson);
        } catch (PassportServerException | IOException exception) {
            exception.printStackTrace();
            result.setError("保存失败");
            LoggerServerUtil.info(logger, "Result:{}", result::parseToJson);
        }
        return result.parseToJson(result);
    }

    /**
     * 富文本上传
     * @param file
     * @return
     * @throws PassportServerException
     */
    @ResponseBody
    @ApplicationAuthorize(authorizeResources = false, authorizeLogin = false, authorizeScope = ScopeType.WEB)
    @PostMapping(value = "ueditor", produces = "application/json;charset=UTF-8")
    private String ueditor(MultipartFile file) throws PassportServerException {
        ResponseResult result = new ResponseResult();
        // [1].用户登录鉴权
        AdminUser user = (AdminUser) request.getAttribute(Constants.ADMIN_USER);
        if (CheckEmptyUtil.isEmpty(user)) {
            throw new ConsoleServerException("系统繁忙，请稍后再试!");
        }
        // [2].获取系统当前会话
        HttpServletRequest request = ApplicationServerUtil.getRequest();
        HttpSession session = request.getSession();
        reset(session);
        // [3].获取上传的文件的名称
        if (CheckEmptyUtil.isEmpty(file)) {
            result.setError("上传文件为空!");
            return result.parseToJson(result);
        }
        try {
            StorageFileParam fileParam = new StorageFileParam();
            if (Objects.equals(result.getCode(), ResponseType.FAILURE.code())) {
                return result.parseToJson(result);
            }
            result = this.fileProcess(result, fileParam, file, user.getId());
            String extName = FileServerUtil.achieveFilePostfix(fileParam.getExtName());
            String bucketName = this.getBucketName(extName);
            FileUpload fileUpload;
            if (Objects.equals(bucketName, Constants.VIDEO) || Objects.equals(bucketName, Constants.AUDIO)) {
                fileUpload = storageServerService.ossStorageFile(fileParam, bucketName, environment);
            } else {
                fileUpload = storageServerService.ossStorageFiles(fileParam, bucketName, environment);
            }
            //处理上传结果
            Map<String, Object> resultMap = Maps.newConcurrentMap();
            resultMap.put("id", fileUpload.getId() + "");
            resultMap.put("type", projectType);
            resultMap.put("url", fileUpload.getUrl());
            result.setResult(resultMap);
            LoggerServerUtil.info(logger, "Result:{}", result::parseToJson);
        } catch (PassportServerException | IOException exception) {
            exception.printStackTrace();
            result.setError("保存失败");
            LoggerServerUtil.info(logger, "Result:{}", result::parseToJson);
        }
        return result.parseToJson(result);
    }
}
