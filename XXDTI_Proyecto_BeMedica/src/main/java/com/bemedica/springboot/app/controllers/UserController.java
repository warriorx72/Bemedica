package com.bemedica.springboot.app.controllers;

import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bemedica.springboot.app.dto.ChangePasswordForm;
import com.bemedica.springboot.app.models.entity.User;
import com.bemedica.springboot.app.repository.RoleRepository;
import com.bemedica.springboot.app.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	RoleRepository roleRepository;
	
	@GetMapping({"/","/login"})
	public String login(Model model) {
		model.addAttribute("titulo","Iniciar Sesión");
		return "login";
	}
	
	@GetMapping("/index")
	public String index(Model model) {
		model.addAttribute("titulo","Inicio");
		return "index";
	}

	@GetMapping("/administracion_usuarios")
	public String userForm(Model model) {
		model.addAttribute("userForm", new User());
		model.addAttribute("userList", userService.getAllUsers());
		model.addAttribute("roles", roleRepository.findAll());
		model.addAttribute("titulo","Usuarios");
		model.addAttribute("listTab", "active");
		model.addAttribute("passwordForm",new ChangePasswordForm());
		return "administracion_usuarios";
	}
	
	@PostMapping("/administracion_usuarios")
	public String createUser(@Valid @ModelAttribute("userForm")User user,BindingResult result, ModelMap model) {
		if(result.hasErrors()) {
			model.addAttribute("userForm", user);
			model.addAttribute("titulo","Usuarios");
			model.addAttribute("formTab", "active");
			model.addAttribute("passwordForm",new ChangePasswordForm(user.getId()));
		}else {
			try {
				userService.createUser(user);
				model.addAttribute("userForm", new User());
				model.addAttribute("titulo","Usuarios");
				model.addAttribute("listTab", "active");
				model.addAttribute("passwordForm",new ChangePasswordForm(user.getId()));
			} catch (Exception e) {
				model.addAttribute("formErrorMessage",e.getMessage());
				model.addAttribute("userForm", user);
				model.addAttribute("titulo","Usuarios");
				model.addAttribute("formTab", "active");
				model.addAttribute("userList", userService.getAllUsers());
				model.addAttribute("roles", roleRepository.findAll());
				model.addAttribute("passwordForm",new ChangePasswordForm(user.getId()));
			}
		}
		model.addAttribute("userList", userService.getAllUsers());
		model.addAttribute("roles", roleRepository.findAll());
		
	return "administracion_usuarios";
	}
	
	@GetMapping("/editUser/{id}")
	public String getEditUserForm(Model model, @PathVariable(name="id")Long id) throws Exception{
		User userToEdit = userService.getUserById(id);
		model.addAttribute("userForm", userToEdit);
		model.addAttribute("userList", userService.getAllUsers());
		model.addAttribute("roles", roleRepository.findAll());
		model.addAttribute("titulo","Usuarios");
		model.addAttribute("formTab", "active");
		model.addAttribute("editMode", "true");
		model.addAttribute("passwordForm",new ChangePasswordForm(id));
		return "administracion_usuarios";
	}
	
	@PostMapping("/editUser")
	public String postEditUserForm(@Valid @ModelAttribute("userForm")User user , BindingResult result, ModelMap model) {
		if(result.hasErrors()) {
			model.addAttribute("userForm", user);
			model.addAttribute("titulo","Usuarios");
			model.addAttribute("formTab", "active");
			model.addAttribute("editMode", "true");
			model.addAttribute("passwordForm",new ChangePasswordForm(user.getId()));
		}else {
			try {
				userService.updateUser(user);
				model.addAttribute("userForm", new User());
				model.addAttribute("titulo","Usuarios");
				model.addAttribute("listTab", "active");
				model.addAttribute("passwordForm",new ChangePasswordForm(user.getId()));
			} catch (Exception e) {
				model.addAttribute("formErrorMessage",e.getMessage());
				model.addAttribute("userForm", user);
				model.addAttribute("titulo","Usuarios");
				model.addAttribute("formTab", "active");
				model.addAttribute("userList", userService.getAllUsers());
				model.addAttribute("roles", roleRepository.findAll());
				model.addAttribute("editMode", "true");
				model.addAttribute("passwordForm",new ChangePasswordForm(user.getId()));
			}
		}
		model.addAttribute("userList", userService.getAllUsers());
		model.addAttribute("roles", roleRepository.findAll());
		
	return "administracion_usuarios";
	}
	
	@GetMapping("/administracion_usuarios/cancel")
	public String cancelEditUser(ModelMap model) {
		return "redirect:/administracion_usuarios";
	}
	
	@GetMapping("/deleteUser/{id}")
	public String deleteUser(Model model, @PathVariable(name="id")Long id) {
		try {
			userService.deleteUser(id);
		}catch(Exception e) {
			 model.addAttribute("listErrorMessage",e.getMessage());
		}
		return "redirect:/administracion_usuarios";
	}
	
	@PostMapping("/user_status")
	public String status(@RequestParam("id") Long id,@RequestParam("aux") int aux, Model model, RedirectAttributes redirectAttrs) throws Exception {
		User user;
		user=userService.getUserById(id.longValue());
		
		if(aux==1) {
			user.setUserStatus("0");
			userService.updateUser(user);
			model.addAttribute("userForm", new User());
			model.addAttribute("titulo","Usuarios");
			model.addAttribute("listTab", "active");
			redirectAttrs
            .addFlashAttribute("mensaje", "Usuario desactivado ")
            .addFlashAttribute("clase", "success");
		}
		else {
			user.setUserStatus("1");
			userService.updateUser(user);
			model.addAttribute("userForm", new User());
			model.addAttribute("titulo","Usuarios");
			model.addAttribute("listTab", "active");
			redirectAttrs
            .addFlashAttribute("mensaje", "Usuario activado")
            .addFlashAttribute("clase", "success");
		}
		return "redirect:/administracion_usuarios";
	}
	
	@PostMapping("/editUser/changePassword")
	public ResponseEntity postEditUseChangePassword(@Valid @RequestBody ChangePasswordForm form, Errors errors) {
		try {
			if(errors.hasErrors()) {
				String result = errors.getAllErrors()
                        .stream().map(x -> x.getDefaultMessage())
                        .collect(Collectors.joining(""));

            throw new Exception(result);
			}
			userService.changePassword(form);
		}
		catch(Exception e){
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok("Success");
	}
	
}