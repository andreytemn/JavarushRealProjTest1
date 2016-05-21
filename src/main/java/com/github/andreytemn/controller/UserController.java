package com.github.andreytemn.controller;

import com.github.andreytemn.model.User;
import com.github.andreytemn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Random;

/**
 * Created by Андрей on 20.05.2016.
 */
@Controller
public class UserController {

    private static Integer pageNumber;
    private UserService userService;

    @Autowired
    @Qualifier("userService")
    public void setUserService(UserService userService) {
        this.userService = userService;
    }


    @RequestMapping(value = "/list")
    public ModelAndView list(@RequestParam(required = false) Integer page) {
        ModelAndView modelAndView = new ModelAndView("list");

        List<User> users = userService.list();
        PagedListHolder<User> pagedListHolder = new PagedListHolder<User>(users);
        pagedListHolder.setPageSize(5);
        modelAndView.addObject("pageCount", pagedListHolder.getPageCount());
        modelAndView.addObject("user", new User());

        if (page == null || page < 1 || page > pagedListHolder.getPageCount()) page = 1;

        modelAndView.addObject("page", page);
        if (page < 1 || page > pagedListHolder.getPageCount()) {
            pagedListHolder.setPage(0);
            modelAndView.addObject("users", pagedListHolder.getPageList());
        } else if (page <= pagedListHolder.getPageCount()) {
            pagedListHolder.setPage(page - 1);
            modelAndView.addObject("users", pagedListHolder.getPageList());
        }

        pageNumber = page;

        return modelAndView;
    }

    @RequestMapping(value = "/list/add", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("user") User user) {

        if (user.getId() == 0) {
            this.userService.add(user);
        } else {
            this.userService.update(user);
        }

        return "redirect:/list";
    }

    @RequestMapping(value = "/remove/{id}")
    public String removeUser(@PathVariable("id") int id) {
        this.userService.delete(id);

        return "redirect:/list";
    }


    @RequestMapping("edit/{id}")
    public String editUser(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", this.userService.get(id));
        List<User> users = userService.list();
        PagedListHolder<User> pagedListHolder = new PagedListHolder<User>(users);
        pagedListHolder.setPageSize(5);
        if (pageNumber == 0) {
            pageNumber = 1;
        }
        pagedListHolder.setPage(pageNumber - 1);
        model.addAttribute("users", pagedListHolder.getPageList());

        return "list";
    }

    @RequestMapping(value = "list/{id}")
    public String userData(int id, Model model) {
        model.addAttribute("user", this.userService.get(id));

        return "foundUser";
    }

    @RequestMapping(value = "/testData")
    public String fillBaseWithTestData() {
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setName(getRandomName());
            user.setAge(random.nextInt(100));
            user.setAdmin(random.nextBoolean());
            this.userService.add(user);
        }
        return "redirect:/list";
    }

    private String getRandomName() {
        Random random = new Random();
        int length = random.nextInt(10) + 1;
        char[] chArr = new char[length];
        for (int i = 0; i < length; i++) {
            chArr[i] = (char) (random.nextInt(26) + 'a');
        }
        return new String(chArr);
    }
}
