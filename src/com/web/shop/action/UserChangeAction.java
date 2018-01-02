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

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.web.shop.model.User;
import com.web.shop.service.UserService;
import com.web.shop.util.UserUtils;

@Controller
@Scope(value = "prototype")
public class UserChangeAction extends ActionSupport {
	private String goods_name;
	private File uploadFile;
	private String uploadFileFileName;
	private String uploadFileContentType;
	private User user;
	private String email;
	private String oldpwd;
	private String newpwd;
	private String confirmpwd;
	
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private UserService service;
	
	
	@Override
	public String execute() throws Exception {
		user = (User) ActionContext.getContext().getSession()
				.get("currentUser");
		File upload = upload();
		if (upload != null) {
			Session openSession = sessionFactory.openSession();
			User object = (User) openSession.get(User.class, user.getUser_id());
			String absolutePath = upload.getAbsolutePath();
			int indexOf = absolutePath.indexOf(File.separatorChar + "WebShop"
					+ File.separatorChar + "HeadImage" + File.separatorChar);
			String substring = absolutePath.substring(indexOf);
			object.setHeadImage(substring);
			openSession.update(object);
			openSession.flush();
			openSession.close();
			ActionContext.getContext().getSession().put("currentUser", object);
		}
		if(email!=null && !email.isEmpty()){
			
		}
		
		if(oldpwd!=null && newpwd!=null ){
			boolean equals = UserUtils.getCurrentUser().getUser_password().equals(oldpwd);
			if(!equals){
				addActionError("原密码错误");
				return INPUT;
			}
			if(!newpwd.equals(confirmpwd)){
				addActionError("两次密码不相同");
				return INPUT;
			}
			if(newpwd.length()<=8){
				addActionError("新密码太短");
				return INPUT;
			}
			boolean updatePassword = service.updatePassword(oldpwd, newpwd);
			if(updatePassword){
				return "updatePasswordSuccess";
			}else{
				addActionError("修改密码发生错误");
				return INPUT;
			}
		}
		return SUCCESS;
	}

	public File upload() throws IOException {
		// 设置上传文件目录
		String realpath = ServletActionContext.getServletContext().getRealPath(
				File.separatorChar + "HeadImage");
		// 判断上传文件是否为空
		if (uploadFile != null && user != null) {
			// 设置目标文件（根据 parent 路径名字符串和 child 路径名字符串创建一个新 File 实例）
			int lastIndexOf = uploadFileFileName.lastIndexOf(".");
			String substring = uploadFileFileName.substring(lastIndexOf);
			File savefile = new File(realpath, user.getUser_id() + substring);

			// 判断上传目录是否存在
			if (!savefile.getParentFile().exists())
				savefile.getParentFile().mkdirs();
			// 把文件uploadfile 拷贝到 savefile
			// 里,FileUtils类需要commons-io-x.x.x.jar包支持
			FileUtils.copyFile(uploadFile, savefile);
			// 设置request对象值
			return savefile;
		}
		return null;
	}

	public File getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(File uploadFile) {
		this.uploadFile = uploadFile;
	}

	public String getUploadFileFileName() {
		return uploadFileFileName;
	}

	public void setUploadFileFileName(String uploadFileFileName) {
		this.uploadFileFileName = uploadFileFileName;
	}

	public String getUploadFileContentType() {
		return uploadFileContentType;
	}

	public void setUploadFileContentType(String uploadFileContentType) {
		this.uploadFileContentType = uploadFileContentType;
	}

	public String getGoods_name() {
		return goods_name;
	}

	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getOldpwd() {
		return oldpwd;
	}

	public void setOldpwd(String oldpwd) {
		this.oldpwd = oldpwd;
	}

	public String getNewpwd() {
		return newpwd;
	}

	public void setNewpwd(String newpwd) {
		this.newpwd = newpwd;
	}

	public String getConfirmpwd() {
		return confirmpwd;
	}

	public void setConfirmpwd(String confirmpwd) {
		this.confirmpwd = confirmpwd;
	}
	
	
}
