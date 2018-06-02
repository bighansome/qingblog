package com.example.qingblog.controller;

import com.example.qingblog.entity.Article;
import com.example.qingblog.entity.Category;
import com.example.qingblog.entity.User;
import com.example.qingblog.service.ArticleService;
import com.example.qingblog.service.CategoryService;
import com.example.qingblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class UserController {

    final private UserService userService;
    final private ArticleService articleService;
    final private CategoryService categoryService;

    @Autowired
    public UserController(UserService userService, ArticleService articleService, CategoryService categoryService) {
        this.userService = userService;
        this.articleService = articleService;
        this.categoryService = categoryService;

    }

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    /**
     * 显示后台主页
     * @param model
     * @return
     */
    @RequestMapping("")
    public String admin(Model model) {
        List<Article> articles = articleService.list();
        model.addAttribute("articles", articles);
        return "admin/index";
    }

    /**
     * 后台登录
     * @return
     */
    @RequestMapping("/login")
    public String login() {
        return "admin/login";
    }

    /**
     * 后台登录验证
     * @param response
     * @param user
     * @param model
     * @return
     */
    @RequestMapping(value = "/dologin", method = RequestMethod.POST)
    public String doLogin(HttpServletResponse response, User user, Model model){

        if (userService.login(user.getUsername(), user.getPassword())){
            Cookie cookie = new Cookie("user", user.toString());
            response.addCookie(cookie);
            model.addAttribute("user",user);
            System.out.println(cookie.getName());

            return "redirect:/admin";
        }else {
            model.addAttribute("error","用户名或密码错误");
            System.out.println(response.getClass()+"failure");
            return "admin/login";
        }
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable("id") String id) {
        articleService.delete(id);

        return "redirect:/admin";
    }

    /**
     * 返回写作页面
     * @param model
     * @return
     */
    @RequestMapping("/write")
    public String write(Model model) {

        List<Category> categories = categoryService.list();
        model.addAttribute("categories", categories);
        model.addAttribute("article", new Article());

        return "admin/write";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Article article) {

        String name = article.getCategory().getName();
        Category category = categoryService.getByName(name);
        article.setCategory(category);

        if (article.getContent().length() >= 40) {
            article.setSummary(article.getContent().substring(0, 40));
        }else {
            article.setSummary(article.getContent());
        }
        article.setData(sdf.format(new Date()));
        articleService.save(article);

        return "redirect:/admin";
    }

    @RequestMapping("/update/{id}")
    public String update(@PathVariable("id") String id, Model model) {
        Article article = articleService.getById(id);
        model.addAttribute("target", article);
        List<Category> categories = categoryService.list();
        model.addAttribute("categories", categories);
        model.addAttribute("article", new Article());

        return "admin/update";
    }
}
