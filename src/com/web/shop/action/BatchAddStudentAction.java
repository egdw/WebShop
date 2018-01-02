package com.web.shop.action;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.web.shop.service.StudentService;
import com.web.shop.util.UserUtils;
import com.web.shop.util.XlsFormatUtila;

@Controller
@Scope(value = "prototype")
public class BatchAddStudentAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private File upload;
	private String uploadFileName;
	private String uploadContentType;
	@Autowired
	private StudentService service;
	@Override
	public String execute() throws Exception {
		File file = upload();
		if (file != null && file.exists()) {
			Object[] format = XlsFormatUtila.getFormat(file);
			ArrayList<String> sIds = (ArrayList<String>) format[0];
			ArrayList<String> names = (ArrayList<String>) format[1];
			boolean saveStudents = service.saveStudents(sIds, names);
			if(saveStudents){
				return SUCCESS;
			}else{
				return ERROR;
			}
		}
		return INPUT;
	}

	public File upload() throws IOException {
		// 设置上传文件目录
		String realpath = ServletActionContext.getServletContext().getRealPath(
				File.separatorChar + "DownLoads");
		// 判断上传文件是否为空
		if (upload != null) {
			// 设置目标文件（根据 parent 路径名字符串和 child 路径名字符串创建一个新 File 实例）
			int lastIndexOf = uploadFileName.lastIndexOf(".");
			String substring = uploadFileName.substring(lastIndexOf);
			File savefile = new File(realpath, UserUtils.getCurrentUser()
					.getUser_id() + substring);

			// 判断上传目录是否存在
			if (!savefile.getParentFile().exists())
				savefile.getParentFile().mkdirs();
			// 把文件uploadfile 拷贝到 savefile
			// 里,FileUtils类需要commons-io-x.x.x.jar包支持
			FileUtils.copyFile(upload, savefile);
			// 设置request对象值
			return savefile;
		}
		return null;
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

}
