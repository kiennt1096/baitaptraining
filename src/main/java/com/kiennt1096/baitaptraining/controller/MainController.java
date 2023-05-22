package com.kiennt1096.baitaptraining.controller;

import com.kiennt1096.baitaptraining.model.DetailUser;
import com.kiennt1096.baitaptraining.model.Group;
import com.kiennt1096.baitaptraining.model.JapaneseLevel;
import com.kiennt1096.baitaptraining.model.User;
import com.kiennt1096.baitaptraining.service.DetailUserService;
import com.kiennt1096.baitaptraining.service.GroupService;
import com.kiennt1096.baitaptraining.service.JapaneseLevelService;
import com.kiennt1096.baitaptraining.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
public class MainController {
    @Autowired
    private UserService userService;
    @Autowired
    private GroupService groupService;
    @Autowired
    private JapaneseLevelService japaneseLevelService;
    @Autowired
    private DetailUserService detailUserService;


    public MainController() {
    }

    @GetMapping({"/", "/login"})
    public String login() {
        return "MNG001";
    }

    @GetMapping("/top")
    public String topPage(Model model) {
        return findPaginated(1, "fullName", "asc", model);
    }

    @GetMapping("/page/{pageNo}")
    private String findPaginated(@PathVariable(value = "pageNo") int pageNo, @RequestParam("sortField") String sortField, @RequestParam("sortDir") String sortDir, Model model) {
        int pageSize = 5;

        Page<User> page = userService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<User> listUser = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("userList", listUser);
        return "MNG002";
    }

    @PostMapping("/top")
    public String searchUser(Model model, @RequestParam("searchName") String searchName, @RequestParam("groupid") Integer groupid) {
        List<User> userList;
        if (searchName != "" && groupid != 0) {
            userList = userService.findUserByNameAndGroup(searchName, groupid);
        } else if (searchName != "" && groupid == 0) {
            userList = userService.findUserByName(searchName);
        } else if (searchName == "" && groupid != 0) {
            userList = userService.findUserByGroup(groupid);
        } else {
            userList = userService.getAllUser();
        }
        model.addAttribute("userList", userList);
        return "MNG002";
    }


    @GetMapping("/addnewuser")
    public String addUser(Model model) {
        User user = new User();

        model.addAttribute("idtest", 0);
//        model.addAttribute("birthdayY", 2002);
        model.addAttribute("user", user);
        return "MNG003";
    }

    @PostMapping("/saveuser")
    public String saveUser(HttpSession httpSession,
                           @ModelAttribute("user") User user,
                           @RequestParam("birthdayY") int birthdayY,
                           @RequestParam("birthdayM") int birthdayM,
                           @RequestParam("birthdayD") int birthdayD,
                           @RequestParam("startDateY") int startDateY,
                           @RequestParam("startDateM") int startDateM,
                           @RequestParam("startDateD") int startDateD,
                           @RequestParam("endDateY") int endDateY,
                           @RequestParam("endDateM") int endDateM,
                           @RequestParam("endDateD") int endDateD
    ) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(birthdayY, birthdayM - 1, birthdayD);
        Date birthday = calendar.getTime();
        calendar.set(startDateY, startDateM - 1, startDateD);
        Date startday = calendar.getTime();
        calendar.set(endDateY, endDateM - 1, endDateD);
        Date endday = calendar.getTime();
        Group group = groupService.getGroupById(user.getGroup().getGroupId());
        JapaneseLevel japaneseLevel = japaneseLevelService.getJapaneseLevelById(user.getDetailUser().getJapaneseLevel().getCodeLevel());

        user.setGroup(group);
        user.setBirthday(birthday);
        DetailUser detailUser = new DetailUser(0, japaneseLevel, startday, endday, user.getDetailUser().getTotal());
//        DetailUser saved = detailUserService.saveDetailUser(detailUser);
        user.setDetailUser(detailUser);
//        //userService.saveUser(new User(0, group, "kien1", "123456", "trung kien", "trung kien kana", "kiennt@gmail", "0123", Calendar.getInstance().getTime(), new DetailUser(0, new JapaneseLevel("N1", "N1"), Calendar.getInstance().getTime(), Calendar.getInstance().getTime(), 190)));
//        userService.saveUser(user);
        httpSession.setAttribute("user", user);
        httpSession.setAttribute("detailuser", detailUser);
        //Dùng httpSession.setAttribute("user", user); để truyền object user sang màn hình confrimUser;
        return "redirect:/confirmUser";
    }

    @GetMapping("/userdetail")
    public String userDetail(Model model) {
        return "MNG005";
    }

    @GetMapping("/confirmUser")
    public String confirmUser(HttpSession httpSession, Model model) {
        model.addAttribute("user", (User) httpSession.getAttribute("user"));
//        model.addAttribute("user_id", (Integer) httpSession.getAttribute("id"));
        return "MNG004";
    }

    @PostMapping("/saveuserToDB")
    public String addnewuserDB(HttpSession httpSession, Model model) {
        User user = (User) httpSession.getAttribute("user");
        Integer userid = (Integer) httpSession.getAttribute("id");
        DetailUser detailUser = (DetailUser) httpSession.getAttribute("detailuser");

        if (userid != null) {
            userService.updateUser(userid, user);
            httpSession.setAttribute("type", "update");
            httpSession.removeAttribute("user");
            httpSession.removeAttribute("id");
            return "redirect:/complete/update";
//            System.out.println(userid);
//            System.out.println("TRUE");
        } else {
            if (userService.findUserByLoginName(user.getLoginName())) {
                DetailUser saved = detailUserService.saveDetailUser(detailUser);
                user.setDetailUser(saved);
                userService.saveUser(user);
                httpSession.setAttribute("type", "addnew");
                httpSession.removeAttribute("user");
                httpSession.removeAttribute("id");
                return "redirect:/complete/new";
            } else {
                httpSession.setAttribute("type", "errorloginname");
                return "redirect:/complete/errorloginname";
            }

//            System.out.println("FALSE");
        }
        //System.out.println( (Integer) httpSession.getAttribute("id"));

    }

    @GetMapping("/saveuserToDBDone")
    public String saveuserToDBDone(Model model) {
        return "MNG002";
    }

    @GetMapping("/showformUpdate/{id}")
    public String showFormUpdate(Model model, HttpSession httpSession, @PathVariable(value = "id") Integer id) {
        User user = userService.getUserById(id);
        httpSession.setAttribute("id", id);
        model.addAttribute("user", user);
        return "MNG005";
    }

    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable(value = "id") Integer id) {

        userService.deleteUserById(id);
        detailUserService.deleteDetailUserById(id);
        return "redirect:/complete/delete";
    }

    @GetMapping("/formUpdate/{id}")
    public String formUpdate(Model model, @PathVariable(value = "id") Integer id) {
        User user = userService.getUserById(id);
        model.addAttribute("idtest", id);
        model.addAttribute("user", user);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(user.getBirthday());
        model.addAttribute("birthdayY", calendar.get(Calendar.YEAR));
        model.addAttribute("birthdayM", calendar.get(Calendar.MONTH) + 1);
        model.addAttribute("birthdayD", calendar.get(Calendar.DATE));
        calendar.setTime(user.getDetailUser().getStartDate());
        model.addAttribute("startDateY", calendar.get(Calendar.YEAR));
        model.addAttribute("startDateM", calendar.get(Calendar.MONTH) + 1);
        model.addAttribute("startDateD", calendar.get(Calendar.DATE));
        calendar.setTime(user.getDetailUser().getEndDate());
        model.addAttribute("endDateY", calendar.get(Calendar.YEAR));
        model.addAttribute("endDateM", calendar.get(Calendar.MONTH) + 1);
        model.addAttribute("endDateD", calendar.get(Calendar.DATE));
        return "MNG003";
    }

    @GetMapping("/complete/{type}")
    public String complete(Model model, @PathVariable(value = "type") String type) {
        model.addAttribute("type", type);
        return "MNG006";
    }
}
