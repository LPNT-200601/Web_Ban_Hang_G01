package estore.admin.controller;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import estore.repository.Category;
import estore.repository.Account;
import estore.repository.service.CategoryService;
import estore.repository.service.AccountService;
import estore.service.upload.UploadService;

@Controller
public class AccountAController {
	@Autowired
	AccountService accountService; 
	
	@Autowired
	UploadService uploadService;
	
	@RequestMapping("/admin/account/reset")
	public String reset(Model model) {
		return "forward:/admin/account/index";
	}
	
	@RequestMapping("/admin/account/index")
	public String index(Model model) {
		Account item = new Account();
		model.addAttribute("item", item);
		
		return this.forward(model);
	}
	
	@RequestMapping("/admin/account/edit/{username}")
	public String edit(Model model, @PathVariable("username") String username) {
		Account item = accountService.getByUsername(username);
		model.addAttribute("item", item);
		
		return this.forward(model);
	}
	
	@RequestMapping("/admin/account/create")
	public String create(Model model, 
			@RequestPart("photo_file") MultipartFile photoFile,
			@ModelAttribute("item") Account item) {
		if(!photoFile.isEmpty()) {
			File photo = uploadService.save(photoFile, "/images/photos/");
			item.setPhoto(photo.getName());
		}
		
		accountService.create(item);
		model.addAttribute("message", "Create successfully");
			
		return this.forward(model);
	}
			
	
	@RequestMapping("/admin/account/update")
	public String update(Model model, 
			@RequestPart("photo_fi") MultipartFile photoFile,
			@ModelAttribute("item") Account item) {
		if(!photoFile.isEmpty()) {
			File photo = uploadService.save(photoFile, "/images/photos/");
			item.setPhoto(photo.getName());
		}
		accountService.update(item);
		model.addAttribute("message", "Update successfully");
				
		return this.forward(model);
	}
		
		
	
	@RequestMapping("/admin/account/delete/{username}")
	public String delete(Model model, @PathVariable("username") String username) {
		accountService.deleteByUsername(username);
		model.addAttribute("message", "Delete successfully");
		
		 Account item = new Account();
		 model.addAttribute("item", item);
		 return this.forward(model);
	}
	
	String forward(Model model) {
		List<Account> items = accountService.findAll();
		 model.addAttribute("items", items);
		 return"admin/account/index";
	}	
}
