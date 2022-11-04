package com.example.foodorderingsystem.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.example.foodorderingsystem.exception.UserNotFoundException;
import com.example.foodorderingsystem.model.User;
import com.example.foodorderingsystem.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class UserController {

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/userlogin")
    public String goToLoginPage() {
        return "userlogin";
    }

    @GetMapping("/userregister")
    public String goToRegisterPage() {
        return "userregister";
    }

    @GetMapping("/logout")
    public ModelAndView logout(HttpSession session) {
        ModelAndView mv = new ModelAndView();

        session.removeAttribute("active-user");
        session.removeAttribute("user-login");
        mv.addObject("status","Logged out Successfully");
        mv.setViewName("index");

        return mv;
    }

    @PostMapping("/userregister")
    public ModelAndView registerAdmin(@ModelAttribute User user) throws UserNotFoundException {
        ModelAndView mv = new ModelAndView();
        if(this.userRepo.save(user)!= null) {
            mv.addObject("status", user.getFirstname()+" Successfully Registered!");
            mv.setViewName("userlogin");
        }

        else {
            throw new UserNotFoundException("User Not Found with provided details !!");
//            mv.addObject("status", user.getFirstname()+" Failed to Registered User!");
//            mv.setViewName("userregister");
        }

        return mv;
    }

    @PostMapping("/forgetpassword")
    public ModelAndView forgetpassword(@RequestParam("email") String email, @RequestParam("pass") String password,
                                       @RequestParam("phone") String phone) throws UserNotFoundException {
        ModelAndView mv = new ModelAndView();

        User user = userRepo.findByEmailidAndMobileno(email, phone);

        if(user != null) {
            user.setPassword(password);
            userRepo.save(user);
        }

        else {
            throw new UserNotFoundException("User Not Found with provided details !!");
//            mv.addObject("status", "No User found!");
//            mv.setViewName("userregister");
        }

        return mv;
    }

    @PostMapping("/userlogin")
    public ModelAndView loginAdmin(HttpServletRequest request, @RequestParam("emailid") String emailId, @RequestParam("password") String password ) throws UserNotFoundException {
        ModelAndView mv = new ModelAndView();

        User user = userRepo.findByEmailidAndPassword(emailId, password);

        if(user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("active-user", user);
            session.setAttribute("user-login","user");
            mv.addObject("status", user.getFirstname()+" Successfully Logged In!");
            mv.setViewName("index");
        }

        else {
            throw new UserNotFoundException("User Not Found with provided details !!");
//            mv.addObject("status","Failed to login!");
//            mv.setViewName("index");
        }

        return mv;
    }


}
