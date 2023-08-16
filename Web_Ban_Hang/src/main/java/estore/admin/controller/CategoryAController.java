package estore.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import estore.repository.Category;
import estore.repository.service.CategoryService;

@Controller
public class CategoryAController {
	@Autowired
	CategoryService categoryService;
	
	@RequestMapping("/admin/category/reset")
	public String reset(Model model) {
		return "forward:/admin/category/index";
	}
	
	@RequestMapping("/admin/category/index")
	public String index(Model model) {
		Category item = new Category();
		model.addAttribute("item", item);
		return this.forward(model);
	}
	
	@RequestMapping("/admin/category/edit/{id}")
	public String edit(Model model, @PathVariable("id") Integer id) {
		Category item = categoryService.getById(id);
		model.addAttribute("item", item);
		return this.forward(model);
	}
	
	@RequestMapping("/admin/category/create")
	public String create(Model model, 
			@Validated @ModelAttribute("item") Category item, BindingResult errors) {
		if (errors.hasErrors()) {
			model.addAttribute("message", "Please fix the errors bellow!");
		}else if(item.getId() != null){
			model.addAttribute("message", "Item is existed!");
		}else {
			try {
				categoryService.create(item);
				model.addAttribute("message", "Create successfully");
				return "forward:/admin/category/index";
			} catch (Exception e) {
				model.addAttribute("message", "Create failure!");	
			}
			
		}
		return this.forward(model);
		}
			
	
	@RequestMapping("/admin/category/update")
	public String update(@Validated Model model, @ModelAttribute("item") Category item, BindingResult errors) {
			if (errors.hasErrors()) {
				model.addAttribute("message", "Please fix the errors bellow!");
			} else if(item.getId() == null) {
				model.addAttribute("message", "Item not found!");
			}else {
				try {
					categoryService.update(item);
					model.addAttribute("message", "Update successfully");
				} catch (Exception e) {
					model.addAttribute("message", "Update failure!");
				}
			}
		
		return this.forward(model);
		}
		
		
	
	@RequestMapping("/admin/category/delete/{id}")
	public String delete(Model model, @PathVariable("id") Integer id) {
		 try {
			 categoryService.deleteById(id);
			 model.addAttribute("message", "Delete successfully");
		} catch (Exception e) {
			 model.addAttribute("message", "Delete failuere!");
		}
		
		 Category item = new Category();
		 model.addAttribute("item", item);
		 return this.forward(model);
	}
	
	String forward(Model model) {
		List<Category> items = categoryService.findAll();
		 model.addAttribute("items", items);
		 return"admin/category/index";
	}
}
