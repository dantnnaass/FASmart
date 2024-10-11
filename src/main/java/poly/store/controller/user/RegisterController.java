package poly.store.controller.user;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import poly.store.common.duongDan;
import poly.store.entity.Role;
import poly.store.entity.User;
import poly.store.entity.UserRole;
import poly.store.model.UserRegister;
import poly.store.service.RoleService;
import poly.store.service.SessionService;
import poly.store.service.UserRoleService;
import poly.store.service.UserService;
import poly.store.validator.user.RegisterFormValidator;

/**
 * Class tạo mới một tài khoản
 */
@Controller
public class RegisterController {
    // Thông tin bắt lỗi form
    @Autowired
    RegisterFormValidator registerFormValidator;

    // Thông tin user service
    @Autowired
    UserService userService;

    // Class cung cấp các service làm việc với session
    @Autowired
    SessionService sessionService;

    // Thông tin role service
    @Autowired
    RoleService roleService;

    // Thông tin user role service
    @Autowired
    UserRoleService userRoleService;

    /**
     * Ràng buộc form với trình bắt lỗi
     * 
     * @param binder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        Object target = binder.getTarget();
        if (target == null) {
            return;
        }
        if (target.getClass() == UserRegister.class) {
            binder.setValidator(registerFormValidator);
        }
    }

    /**
     * Hiển thị trang register
     * 
     * @param model
     * @return màn hình register
     */
    @GetMapping("/register")
    public String displayFormRegister(Model model) {
        // Ràng buộc form tên userRegister với model UserRegister.class
        UserRegister userRegister = new UserRegister();
        model.addAttribute("userRegister", userRegister);

        // Hiển thị trang register
        return duongDan.USER_DISPLAY_REGISTER;
    }

    /**
     * Xử lý form register
     * 
     * @param model
     * @param userRegister
     * @param result
     * @return Trang register nếu có lỗi. Ngược lại qua trang thông báo đăng ký thành công.
     */
    @PostMapping("/register")
    public String handlerRegisterForm(Model model, 
            @ModelAttribute("userRegister") @Validated UserRegister userRegister,
            BindingResult result) {
        if (result.hasErrors()) {
            return duongDan.USER_DISPLAY_REGISTER;
        } else {
            if (!userRegister.isConfirmTerm()) {
                model.addAttribute("checkTerm", true);
                return duongDan.USER_DISPLAY_REGISTER;
            } else {
                // Thực hiện việc tạo tài khoản ngay lập tức

                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                // Thêm mới một user
                User user = new User();
                user.setEmail(userRegister.getEmail());
                user.setFullname(userRegister.getFullName());
                user.setPassword(userRegister.getPassword());
                user.setCreateday(timestamp.toString());
                user.setSubscribe(userRegister.getSubscribe());
                userService.save(user);

                // Tìm thông tin role theo roleId
                Role role = roleService.findRoleById(1);
                // Thêm mới một user có vai trò là ROLE_USER
                UserRole userRole = new UserRole();
                userRole.setUser(user);
                userRole.setRole(role);
                userRoleService.save(userRole);

                // Hiển thị màn hình đăng ký thành công
                model.addAttribute("alert", "Đăng ký thành công!");
                model.addAttribute("message", "Chúc mừng bạn đã tạo mới một tài khoản thành công!");
                return duongDan.USER_DISPLAY_ALERT_STATUS;
            }
        }
    }
}
