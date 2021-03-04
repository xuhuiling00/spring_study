package com.xhl.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.xhl.mapper.SearchRecordsMapper;
import com.xhl.service.BgmService;
import com.xhl.service.VideoService;
import com.xhl.utils.FetchVideo;
import com.xhl.utils.MergeVideo;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.xhl.pojo.Bgm;
import com.xhl.pojo.PageResult;
import com.xhl.pojo.VideoStatusEnum;
import com.xhl.pojo.Videos;
import com.xhl.pojo.vo.VideosVo;
import com.xhl.utils.VideoJsonResult;
import com.xhl.pojo.vo.CommentsVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@Api(value = "视频相关的业务数据接口", tags = { "视频相关业务controller" })
@RequestMapping("/video")
public class VideoController extends BasicController {

	@Autowired
	private BgmService bgmService;
	@Autowired
	private VideoService videoService;

	@ApiOperation(value = "用户上传视频", notes = "用户上传视频接口")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "userId", value = "用户id", required = true, dataType = "String", paramType = "form"),
			@ApiImplicitParam(name = "bgmId", value = "背景音乐id", required = false, dataType = "String", paramType = "form"), //// 可以不指定
			@ApiImplicitParam(name = "duration", value = "视频时间", required = false, dataType = "String", paramType = "form"), //// 可以不指定
			@ApiImplicitParam(name = "tmpWidth", value = "视频宽度", required = true, dataType = "int", paramType = "form"),
			@ApiImplicitParam(name = "tmpHeight", value = "视频高度", required = true, dataType = "int", paramType = "form"),
			@ApiImplicitParam(name = "desc", value = "视频描述", required = false, dataType = "String", paramType = "query"), // 可以不指定
			@ApiImplicitParam(name = "videoCategory", value = "视频分类", required = false, dataType = "String", paramType = "query"),// 可以不指定
			@ApiImplicitParam(name = "videoFilter", value = "视频滤镜", required = false, dataType = "String", paramType = "query")// 可以不指定

	})
	@PostMapping(value = "/upload", headers = "content-type=multipart/form-data")
	public VideoJsonResult uploadFace(String userId, String bgmId, double duration, int tmpWidth, int tmpHeight,
									String desc, String videoCategory, String videoFilter,
									@ApiParam(value = "短视频", required = true) MultipartFile file) {

		if (StringUtils.isBlank(userId)) {
			return VideoJsonResult.errorException("用户id不能为空...");
		}
		// 文件保存命名空间
		//String fileSpace = "D:/IdeaProjects/spring_study/video/green_videos_dev";
		// 保存到数据库路径 相对路径
		String uploadPathDB = "/" + userId + "/video";
		String coverPath = "/" + userId + "/video";

		String finalVideoPath = "";
		String fileName = null;
		if (file != null) {

			FileOutputStream fileOutputStream = null;
			InputStream inputStream = null;
			fileName = file.getOriginalFilename();
			try {
				if (StringUtils.isNotBlank(fileName)) {
					// 文件上传的最终保存路径
					finalVideoPath = File_Space + uploadPathDB + "/" + fileName;
					// 数据库保存路径
					uploadPathDB += ("/" + fileName);
					File outFile = new File(finalVideoPath);
					if (outFile.getParentFile() != null || !outFile.getParentFile().isDirectory()) {
						outFile.getParentFile().mkdirs();
					}

					fileOutputStream = new FileOutputStream(outFile);
					inputStream = file.getInputStream();

					IOUtils.copy(inputStream, fileOutputStream);

				} else {// 增加校验防止入侵攻击
					return VideoJsonResult.errorException("上传失败了");

				}
			} catch (IOException e) {
				return VideoJsonResult.errorException("上传失败了");
			} finally {
				// 如果不为空
				// 则flush 关闭
				try {
					fileOutputStream.flush();
					fileOutputStream.close();
//					IOUtils.closeQuietly(fileOutputStream);
//					IOUtils.closeQuietly(inputStream);
				} catch (IOException e) {
					e.printStackTrace();
				}
//				 判断bgmid是否为空，如果不为空 则需要查询信息，并且合并视频生成新的视频
//				 如果bgmid为空
				MergeVideo tool = new MergeVideo(FFMPEGEXE);
				if (StringUtils.isNotBlank(bgmId)) {
					Bgm bgm = bgmService.queryBgmById(bgmId);
					String mp3InputPath = File_Space +  bgm.getPath();// 得到路径

					String videoInputPath = finalVideoPath;
					String outPathName = UUID.randomUUID().toString() + ".mp4";
					uploadPathDB = "/" + userId + "/video" + "/" + outPathName;
					String outputStream = File_Space + "/" + uploadPathDB;

					tool.convertor(mp3InputPath, videoInputPath, duration, outputStream, videoFilter);
				} else {
					if (!videoFilter.equals(Filter_DEFINE))// 如果当前程序的滤镜不为define
															// 则给视频增加滤镜
					{
						String videoInputPath = finalVideoPath;
						String outPathName = UUID.randomUUID().toString() + ".mp4";
						uploadPathDB = "/" + userId + "/video" + "/" + outPathName;
						String outputStream = File_Space + "/" + uploadPathDB;
						tool.convertor(null, videoInputPath, duration, outputStream, videoFilter);
					}
				}
			}
		}
		FetchVideo videoInfo = new FetchVideo(FFMPEGEXE);
		String fileNamePrefix = fileName.split("\\.")[0];
		coverPath = coverPath + "/" + fileNamePrefix + UUID.randomUUID().toString() + ".jpg";// 相对路径放到数据库里
		videoInfo.getcover(finalVideoPath, File_Space + coverPath);// 输出转换后的图片
		// 保存视频到数据库
		Videos video = new Videos();
		video.setVideoCategory(videoCategory);
		video.setAudioId(bgmId);
		video.setUserId(userId);
		video.setVideoSeconds((float) duration);
		video.setVideoHeight(tmpHeight);
		video.setVideoWidth(tmpWidth);
		video.setVideoDesc(desc);
		video.setCreateTime(new Date());
		video.setVideoPath(uploadPathDB);
		video.setCoverPath(coverPath);
		video.setLikeCounts(0L);
		video.setVideoFileter(videoFilter);
		video.setStatus(VideoStatusEnum.Success.value);
		String videoId = videoService.saveVideo(video);
		return VideoJsonResult.ok(videoId);// 返回200
	}
	/**
	 * 用于上传封面，目前已经整合到·上传视频的接口中
	 */
//	@ApiOperation(value = "上传封面", notes = "上传封面的接口")
//	@ApiImplicitParams({
//			@ApiImplicitParam(name = "userId", value = "用户id", required = true, dataType = "String", paramType = "form"),
//			@ApiImplicitParam(name = "videoId", value = "视频主键id", required = true, dataType = "String", paramType = "form") })
//	@PostMapping(value = "/uploadCover", headers = "content-type=multipart/form-data")
//	public VideoJsonResult uploadCover(String userId, String videoId,
//			@ApiParam(value = "视频封面", required = true) MultipartFile file) throws Exception {
//
//		if (StringUtils.isBlank(videoId) || StringUtils.isBlank(userId)) {
//			return VideoJsonResult.errorMsg("视频主键id和用户id不能为空...");
//		}
//
//		// 文件保存的命名空间
//		// 保存到数据库中的相对路径
//		String uploadPathDB = "/" + userId + "/video";
//
//		FileOutputStream fileOutputStream = null;
//		InputStream inputStream = null;
//		// 文件上传的最终保存路径
//		String finalCoverPath = "";
//		try {
//			if (file != null) {
//
//				String fileName = file.getOriginalFilename();
//				if (StringUtils.isNotBlank(fileName)) {
//
//					finalCoverPath = FILe_SPACE + uploadPathDB + "/" + fileName;
//					// 设置数据库保存的路径
//					uploadPathDB += ("/" + fileName);
//
//					File outFile = new File(finalCoverPath);
//					if (outFile.getParentFile() != null || !outFile.getParentFile().isDirectory()) {
//						// 创建父文件夹
//						outFile.getParentFile().mkdirs();
//					}
//
//					fileOutputStream = new FileOutputStream(outFile);
//					inputStream = file.getInputStream();
//					IOUtils.copy(inputStream, fileOutputStream);
//				}
//
//			} else {
//				return VideoJsonResult.errorMsg("上传出错...");
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			return VideoJsonResult.errorMsg("上传出错...");
//		} finally {
//			if (fileOutputStream != null) {
//				fileOutputStream.flush();
//				fileOutputStream.close();
//			}
//		}
//
//		videoService.updateVideo(videoId, uploadPathDB);
//
//		return VideoJsonResult.ok();
//	}

	
	// 热搜
	@PostMapping(value = "/hot")
	public VideoJsonResult hot() {
		return VideoJsonResult.ok(videoService.getHotWords());
	}
//
	@ApiOperation(value = "查询(某条件下)所有视频", notes = "视频查询接口")
	@PostMapping(value = "/showAll") // isSaveRecord 是否保存记录 (1:需要保存）
	public VideoJsonResult showAll(@RequestBody Videos video, Integer isSaveRecord, Integer page, String category) {
		if (page == null) {
			page = 1;
		}
		PageResult pageResult = videoService.getAllVideos(video, isSaveRecord, page, PAGE_SIZE, category);
		System.out.println(pageResult.toString());
		return VideoJsonResult.ok(pageResult);
	}


	@ApiOperation(value = "点赞视频", notes = "点赞视频接口")
	@PostMapping(value = "/userLike") // isSaveRecord 是否保存记录
	public VideoJsonResult userLike(String userId, String videoId, String videoCreaterId) {
		videoService.userLikeVideo(userId, videoId, videoCreaterId);
		return VideoJsonResult.ok();
	}

	@ApiOperation(value = "取消点赞视频", notes = "取消点赞视频接口")
	@PostMapping(value = "/userUnLike") // isSaveRecord 是否保存记录
	public VideoJsonResult userUnLike(String userId, String videoId, String videoCreaterId) {
		videoService.userUnLikeVideo(userId, videoId, videoCreaterId);
		return VideoJsonResult.ok();
	}

	// 保存用户的评论到数据库
	@ApiOperation(value = "保存视频评论", notes = "保存视频评论接口")
	@PostMapping(value = "/saveComments") // isSaveRecord 是否保存记录
	public VideoJsonResult saveComments(String userId, String videoId, String comment) {
		videoService.saveComment(userId, videoId, comment);
		return VideoJsonResult.ok();
	}

	@ApiOperation(value = "查询视频所有评论", notes = "查询视频评论接口")
	@PostMapping(value = "/queryCommentsByVideoId")
	public VideoJsonResult queryCommentsByVideoId(String videoId) {
		List<CommentsVo> commentsAll = videoService.queryCommentsByVideoId(videoId);
		return VideoJsonResult.ok(commentsAll);// 返回当前视频的所有评论
	}

	@ApiOperation(value = "查询用户所有视频", notes = "查询用户所有视频接口")
	@PostMapping(value = "/queryVideosByUser")
	public VideoJsonResult queryVideosByUser(String userId) {
		List<VideosVo> listVideos = videoService.queryVideosByUser(userId);
		return VideoJsonResult.ok(listVideos);
	}

	/**
	 * @param userId      举报人的id
	 * @param dealUserId  被举报用户的id
	 * @param dealVideoId 被举报视频的id
	 * @return
	 */
	@ApiOperation(value = "举报视频", notes = "举报视频接口")
	@PostMapping("/report")
	public VideoJsonResult reportVideosByUser(String userId, String dealUserId, String dealVideoId, String title,
			String content) {
		videoService.reportVideoByUser(dealUserId, dealVideoId, userId, title, content);
		return VideoJsonResult.ok();
	}

}
