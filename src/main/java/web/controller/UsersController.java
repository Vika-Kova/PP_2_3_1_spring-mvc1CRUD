package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.dao.UserDao;
import web.model.User;

@Controller
@RequestMapping("/users")
public class UsersController {
    private final UserDao userDao;

    @Autowired
    public UsersController(UserDao userDao) {
        this.userDao = userDao;
    }

    @GetMapping()//пусто т.к. адрес просто /
    //все люди из dao и передадим в представление
    public String index(Model model) {//model
        model.addAttribute("users", userDao.index());
        return "users/index";
    }

    ////1 чел по id dao и передадим в
    @GetMapping("/{id}")//id=число  /users/id
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userDao.show(id));
        return "users/show";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user) {
        // model.addAttribute("user",newUser());
        return "users/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") User user) {//создать
        userDao.save(user);
        return "redirect:/users";//переход на др страницу .user
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {//для редактирования человека
        model.addAttribute("user", userDao.show(id));
        return "users/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") int id) {
        userDao.update(id, user);
        return "redirect:/users";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        userDao.delete(id);
        return "redirect:/users";
    }

}
