package poly.store.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import poly.store.common.duongDan;

@Controller
public class AdminOrderController {
	@GetMapping("/admin/order/list/pending")
	public String pending(Model model) {
		return duongDan.USER_DISPLAY_ADMIN_ORDER_PENDING;
	}
	
	@GetMapping("/admin/order/list/shipping")
	public String shipping(Model model) {
		return duongDan.USER_DISPLAY_ADMIN_ORDER_SHIPPING;
	}
	
	@GetMapping("/admin/order/list/success")
	public String success(Model model) {
		return duongDan.USER_DISPLAY_ADMIN_ORDER_SUCCESS;
	}
	
	@GetMapping("/admin/order/list/cancel")
	public String cancel(Model model) {
		return duongDan.USER_DISPLAY_ADMIN_ORDER_CANCEL;
	}
}
