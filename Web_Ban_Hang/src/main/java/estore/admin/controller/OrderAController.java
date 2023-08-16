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

import estore.repository.Order;
import estore.repository.Status;
import estore.repository.service.OrderService;
import estore.repository.service.StatusService;

@Controller
public class OrderAController {
	@Autowired
	OrderService orderService;
	
	@Autowired
	StatusService statusService;
	
	@RequestMapping("/admin/order/reset")
	public String reset(Model model) {
		return "forward:/admin/order/index";
	}
	
	@RequestMapping("/admin/order/index")
	public String index(Model model) {
		Order item = new Order();
		model.addAttribute("item", item);
		return this.forward(model);
	}
	
	@RequestMapping("/admin/status/edit/{id}")
	public String edit(Model model, @PathVariable("id") Long id) {
		Order item = orderService.getById(id);
		model.addAttribute("item", item);
		return this.forward(model);
	}
	
	@RequestMapping("/admin/order/create")
	public String create(Model model, @ModelAttribute("item") Order item) {
				orderService.create(item);
				model.addAttribute("message", "Create successfully");
				return this.forward(model);
			} 
				
	@RequestMapping("/admin/order/update")
	public String update(Model model, @ModelAttribute("item") Order item) {
				orderService.update(item);
				model.addAttribute("message", "Update successfully");
				return this.forward(model);
		}
		
	@RequestMapping("/admin/status/delete/{id}")
	public String delete(Model model, @PathVariable("id") Long id) {
		 try {
			 orderService.deleteById(id);
			 model.addAttribute("message", "Delete successfully");
		} catch (Exception e) {
			 model.addAttribute("message", "Delete failuere!");
		}
		
		 Order item = new Order();
		 model.addAttribute("item", item);
		 return this.forward(model);
	}
	
	String forward(Model model) {
		List<Order> items = orderService.findAll();
		 model.addAttribute("items", items);
		 return"admin/order/index";
	}
	
	@ModelAttribute("statuses")
	public List<Status> getStatusList() {
		return statusService.findAll();
	}
}
