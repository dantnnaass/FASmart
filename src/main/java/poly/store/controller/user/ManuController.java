package poly.store.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import poly.store.common.duongDan;

@Controller
public class ManuController {
	@GetMapping("/manu")
	public String index() {
		return duongDan.USER_DISPLAY_MANU;
	}
}
