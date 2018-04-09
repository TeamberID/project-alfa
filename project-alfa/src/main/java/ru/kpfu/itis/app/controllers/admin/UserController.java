package ru.kpfu.itis.app.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.kpfu.itis.app.forms.UserEditForm;
import ru.kpfu.itis.app.forms.UserRegistrationForm;
import ru.kpfu.itis.app.model.UserData;
import ru.kpfu.itis.app.repositories.UsersRepository;
import ru.kpfu.itis.app.services.UserDatasService;
import ru.kpfu.itis.app.validators.UserEditFormValidator;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Created by Robert Gareev
 * 11-601 ITIS KPFU
 * 21.03.2018
 */
@Controller
@RequestMapping("/admin/users")
public class UserController {

    @Autowired
    private UserDatasService userDatasService;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private UserEditFormValidator userEditFormValidator;

    @InitBinder("userEditForm")
    public void initUserFormValidator(WebDataBinder binder) {
        binder.addValidators(userEditFormValidator);
    }

    @GetMapping("")
    public String getUsers(@ModelAttribute("model")ModelMap model){
        model.addAttribute("users",userDatasService.getAll());
        return "admin/entities/users";
    }
    @GetMapping("/{id}")
    public String getUser(@ModelAttribute("model")ModelMap model, @PathVariable("id")Long id,
                          HttpServletRequest req
                          ){
        String flag = req.getParameter("isSuccessfullyAdded");
        if (flag != null){
            model.addAttribute("isSuccessfullyAdded",Boolean.parseBoolean(flag));
        }
        model.addAttribute("user",userDatasService.getById(id));
        return "admin/entities/user";
    }
    @PostMapping("/{id}/edit")
    public String editUser(RedirectAttributes redirectAttributes, @PathVariable("id")Long id,
                           @Valid @ModelAttribute("userEditForm") UserEditForm userEditForm,
                           BindingResult errors){
        boolean flag =false;
        try {
            userDatasService.add(userEditForm);
            flag = true;
        }
        catch (Exception e){
            flag = false;
        }finally{
            redirectAttributes.addAttribute("isSuccessfullyAdded",flag);
        }
        return "redirect:/admin/users/" + id ;
    }
    @PostMapping("/{id}/delete")
    public String deleteUser(RedirectAttributes redirectAttributes, @PathVariable("id")Long id){
        userDatasService.delete(id);
        return "redirect:/admin/users";
    }
}
