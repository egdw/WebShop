package com.web.shop.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
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
import com.web.shop.model.Good;
import com.web.shop.model.User;
import com.web.shop.util.FormatUtils;
import com.web.shop.util.RandomUtils;

@Controller
@Scope(value = "prototype")
public class PublishGoodAction extends ActionSupport {

	private String inlineRadioOptions;
	private int types;
	private File[] uploadFile;
	private String[] uploadFileFileName;
	private String[] uploadFileContentType;
	private String textContext;
	private String goodName;
	@Autowired
	private SessionFactory sessionFactory;
	private double source_price;
	private double goods_price;
	private String contact;

	@Override
	public String execute() throws Exception {
		ArrayList<File> upload = upload();
		Good good = new Good();
		Session openSession = sessionFactory.openSession();
		if (upload.size() > 0) {
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < uploadFile.length; i++) {
				String absolutePath = upload.get(i).getAbsolutePath();
				int indexOf = absolutePath.indexOf(File.separatorChar
						+ "WebShop" + File.separatorChar + "GoodsImage"
						+ File.separatorChar);
				String substring = absolutePath.substring(indexOf);
				sb.append(substring).append(";");
				if (i == 0) {
					good.setFirstImage(substring);
				}
			}
			good.setImages(sb.toString());
		}
		if(source_price<goods_price){
			addActionError("卖价不能高于原价");
			return INPUT;
		}
		if(source_price<=0){
			addActionError("原价不能为零");
			return INPUT;
		}
		
		if(source_price<=0){
			addActionError("卖价不能为零");
			return INPUT;
		}
		good.setGoodFrom(types);
		good.setGoodName(goodName);
		good.setGoodDescription(textContext);
		good.setCreateDate(new Date(System.currentTimeMillis()));
		good.setFormerPrice(source_price);
		good.setSalePrice(goods_price);
		good.setGoodNum(1);
		if (inlineRadioOptions.equals("new")) {
			good.setGoodType(0);
		} else {
			good.setGoodType(1);
		}
		good.setSale(false);
		good.setPhoneNumber(contact);
		good.setGoodUser((User) ActionContext.getContext().getSession()
				.get("currentUser"));
		openSession.save(good);
		openSession.flush();
		openSession.close();
		return SUCCESS;
	}

	public ArrayList<File> upload() throws IOException {
		// 设置上传文件目录
		String realpath = ServletActionContext.getServletContext().getRealPath(
				File.separatorChar + "GoodsImage");
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

	public String getInlineRadioOptions() {
		return inlineRadioOptions;
	}

	public void setInlineRadioOptions(String inlineRadioOptions) {
		this.inlineRadioOptions = inlineRadioOptions;
	}

	public int getTypes() {
		return types;
	}

	public void setTypes(int types) {
		this.types = types;
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

	public String getTextContext() {
		return textContext;
	}

	public void setTextContext(String textContext) {
		this.textContext = textContext;
	}

	public String getGoodName() {
		return goodName;
	}

	public void setGoodName(String goodName) {
		this.goodName = goodName;
	}

	public double getSource_price() {
		return source_price;
	}

	public void setSource_price(double source_price) {
		this.source_price = source_price;
	}

	public double getGoods_price() {
		return goods_price;
	}

	public void setGoods_price(double goods_price) {
		this.goods_price = goods_price;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

}
