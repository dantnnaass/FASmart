package poly.store.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import poly.store.common.duongDan;

@Controller
public class AdminInformationShopController {
	/**
	 * Hien thi trang chu cua giao dien nguoi dung
	 * 
	 * @return trang quan ly nhan vien
	 */
	@GetMapping("/admin/shop/form")
	public String form(Model model) {
		model.addAttribute("enableBtnUpdate", false);
		return duongDan.USER_DISPLAY_ADMIN_SHOP_FORM;
	}
	
	/**
	 * Hien thi trang chu cua giao dien nguoi dung
	 * 
	 * @return trang quan ly nhan vien
	 */
	@GetMapping("/admin/shop/list")
	public String list(Model model) {
		return duongDan.USER_DISPLAY_ADMIN_SHOP_LIST;
	}
	
	@GetMapping("/admin/shop/update/{id}")
	public String update(Model model, @PathVariable("id") Integer id) {
		model.addAttribute("shopId", id);
		model.addAttribute("enableBtnUpdate", true);
		return duongDan.USER_DISPLAY_ADMIN_SHOP_FORM;
	}
}
