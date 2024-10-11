package poly.store.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import poly.store.common.duongDan;

/**
 * Class de hien thi trang chu quan tri
 */
@Controller
public class AdminIndexController {
	
	/**
	 * Hien thi trang chu cua giao dien nguoi dung
	 * 
	 * @return trang admin index.html
	 */
	@GetMapping("/admin/index")
	public String index(Model model) {
		return duongDan.USER_DISPLAY_ADMIN_INDEX;
	}
}
