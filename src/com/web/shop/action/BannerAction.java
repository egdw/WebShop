package com.web.shop.action;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.web.shop.model.Banner;
import com.web.shop.util.FormatUtils;
import com.web.shop.util.RandomUtils;

@Controller
@Scope(value = "prototype")
public class BannerAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private File[] uploadFile;
	private String[] uploadFileFileName;
	private String[] uploadFileContentType;
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public String execute() throws Exception {
		ArrayList<File> upload = upload();

		Session openSession = sessionFactory.openSession();
		if (upload.size() > 0) {
			for (int i = 0; i < uploadFile.length; i++) {
				Banner banner = new Banner();
				String absolutePath = upload.get(i).getAbsolutePath();
				int indexOf = absolutePath.indexOf(File.separatorChar
						+ "WebShop" + File.separatorChar + "BannerImage"
						+ File.separatorChar);
				String substring = absolutePath.substring(indexOf);
				banner.setBannerImage(substring);
				openSession.save(banner);
			}
			openSession.flush();
			openSession.close();
			return SUCCESS;
		} else {
			return ERROR;
		}
	}

	public ArrayList<File> upload() throws IOException {
		// 设置上传文件目录
		String realpath = ServletActionContext.getServletContext().getRealPath(
				File.separatorChar + "BannerImage");
		ArrayList<File> lists = new ArrayList<File>();
		// 判断上传文件是否为空
		if (uploadFile != null) {
			for (int i = 0; i < uploadFile.length; i++) {
				// 设置目标文件（根据 parent 路径名字符串和 child 路径名字符串创建一个新 File 实例）
				String format = FormatUtils.getFormat(uploadFileFileName[i]);
				File savefile = null;
				String fileName = null;
				if (format != null) {
					fileName = RandomUtils.getCode() + format;
					savefile = new File(realpath, fileName);
				} else {
					savefile = new File(realpath, uploadFileFileName[i]);
				}

				// 判断上传目录是否存在
				if (!savefile.getParentFile().exists())
					savefile.getParentFile().mkdirs();

				// 把文件uploadfile 拷贝到 savefile
				// 里,FileUtils类需要commons-io-x.x.x.jar包支持
				FileUtils.copyFile(uploadFile[i], savefile);
				// 设置request对象值
				if (fileName == null) {
					lists.add(new File(realpath, uploadFileFileName[i]));
				} else {
					lists.add(new File(realpath, fileName));
				}
			}
		}
		return lists;
	}

	public File[] getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(File[] uploadFile) {
		this.uploadFile = uploadFile;
	}

	public String[] getUploadFileFileName() {
		return uploadFileFileName;
	}

	public void setUploadFileFileName(String[] uploadFileFileName) {
		this.uploadFileFileName = uploadFileFileName;
	}

	public String[] getUploadFileContentType() {
		return uploadFileContentType;
	}

	public void setUploadFileContentType(String[] uploadFileContentType) {
		this.uploadFileContentType = uploadFileContentType;
	}

}
