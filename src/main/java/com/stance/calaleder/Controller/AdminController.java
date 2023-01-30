package com.stance.calaleder.Controller;

import com.stance.calaleder.Domain.Event;
import com.stance.calaleder.Domain.Monthuser;
import com.stance.calaleder.Domain.Stance;
import com.stance.calaleder.Service.MonthService;
import com.stance.calaleder.Service.MonthStanceService;
import com.stance.calaleder.Service.StanceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminController {

    private final Logger logger = LoggerFactory.getLogger(StanceController.class);
    private final MonthService monthService;
    private final StanceService stanceService;

    @Autowired
    public AdminController(StanceService stanceService, MonthService monthService){
        this.stanceService=stanceService;
        this.monthService=monthService;
    }

    @PostMapping("/adminPage")
    public String getAdminPage(Authentication authentication, Model model){
        String name=authentication.getName();
        String email=makeEmail(authentication);
        model.addAttribute("name", name);
        model.addAttribute("email", email);
        if(name.equals("최정민") || name.equals("조예은")) {
            model.addAttribute("rank","admin");
            return "adminPage";
        }
        else{
            model.addAttribute("rank", "etc");
            List<Stance> stanceList=stanceService.getAllStanceList();
            List<Event> eventList=new ArrayList<>();
            for (Stance stance1 : stanceList){
                if(stance1.getADMIN()) {
                    Event event = new Event();
                    event.setTitle(stance1.getNAME());
                    String[] startArr = stance1.getSTART_TIME().split(" ");
                    String[] endArr = stance1.getEND_TIME().split(" ");
                    event.setStart(startArr[0] + "T" + startArr[1]);
                    event.setEnd(endArr[0] + "T" + endArr[1]);
                    eventList.add(event);
                }
            }
            model.addAttribute("eventList", eventList);
            model.addAttribute("message", "관리자만 접근 가능합니다.");
            return "home";
        }
    }

    @PostMapping("/admin")
    public String getAdmin(Authentication authentication, Model model){
        String name=authentication.getName();
        String email=makeEmail(authentication);
        model.addAttribute("name", name);
        model.addAttribute("email", email);
        if(name.equals("최정민") || name.equals("조예은")){
            model.addAttribute("rank","admin");
            List<Stance> stanceList = stanceService.getAllStanceList();
            List<Stance> stanceNoaccessList = new ArrayList<>();
            for (Stance stance : stanceList) {
                logger.info(stance.getADMIN().toString());
                if (!stance.getADMIN()) {
                    stanceNoaccessList.add(stance);
                }
            }
            logger.info(stanceNoaccessList.toString());
            model.addAttribute("LISTV1", stanceNoaccessList);
            return "admin";
        }else{
            model.addAttribute("rank", "etc");
            List<Stance> stanceList=stanceService.getAllStanceList();
            List<Event> eventList=new ArrayList<>();
            for (Stance stance1 : stanceList){
                if(stance1.getADMIN()) {
                    Event event = new Event();
                    event.setTitle(stance1.getNAME());
                    String[] startArr = stance1.getSTART_TIME().split(" ");
                    String[] endArr = stance1.getEND_TIME().split(" ");
                    event.setStart(startArr[0] + "T" + startArr[1]);
                    event.setEnd(endArr[0] + "T" + endArr[1]);
                    eventList.add(event);
                }
            }
            model.addAttribute("eventList", eventList);
            model.addAttribute("message", "관리자만 접근 가능합니다.");
            return "home";
        }
    }
    @PostMapping("/monthuser")
    public String getMonthuser(Authentication authentication, Model model){
        String name=authentication.getName();
        String email=makeEmail(authentication);
        model.addAttribute("name", name);
        model.addAttribute("email", email);
        if(name.equals("최정민") || name.equals("조예은")){
            model.addAttribute("rank","admin");
            List<Monthuser> monthusers= monthService.getAllMonthList();
            model.addAttribute("LISTV1", monthusers);
            return "monthuser";
        }else{
            model.addAttribute("rank", "etc");
            List<Stance> stanceList=stanceService.getAllStanceList();
            List<Event> eventList=new ArrayList<>();
            for (Stance stance1 : stanceList){
                if(stance1.getADMIN()) {
                    Event event = new Event();
                    event.setTitle(stance1.getNAME());
                    String[] startArr = stance1.getSTART_TIME().split(" ");
                    String[] endArr = stance1.getEND_TIME().split(" ");
                    event.setStart(startArr[0] + "T" + startArr[1]);
                    event.setEnd(endArr[0] + "T" + endArr[1]);
                    eventList.add(event);
                }
            }
            model.addAttribute("eventList", eventList);
            model.addAttribute("message", "관리자만 접근 가능합니다.");
            return "home";
        }
    }


    @PostMapping("/deleteuser")
    public String deleteUser(Authentication authentication, Model model, Monthuser monthuser){
        String name=authentication.getName();
        String email=makeEmail(authentication);
        model.addAttribute("name", name);
        model.addAttribute("email", email);

        if(name.equals("최정민") || name.equals("조예은")){
            model.addAttribute("rank","admin");
            monthService.deleteMonth(monthuser.getID());
            List<Monthuser> monthusers= monthService.getAllMonthList();
            model.addAttribute("LISTV1", monthusers);
            model.addAttribute("message", "삭제가 성공했습니다.");
            return "monthuser";

        }else{
            model.addAttribute("rank", "etc");
            List<Stance> stanceList=stanceService.getAllStanceList();
            List<Event> eventList=new ArrayList<>();
            for (Stance stance1 : stanceList){
                if(stance1.getADMIN()) {
                    Event event = new Event();
                    event.setTitle(stance1.getNAME());
                    String[] startArr = stance1.getSTART_TIME().split(" ");
                    String[] endArr = stance1.getEND_TIME().split(" ");
                    event.setStart(startArr[0] + "T" + startArr[1]);
                    event.setEnd(endArr[0] + "T" + endArr[1]);
                    eventList.add(event);
                }
            }
            model.addAttribute("eventList", eventList);
            model.addAttribute("message", "관리자만 접근 가능합니다.");
            return "home";
        }
    }

    @PostMapping("/adduser")
    public String addMonthuser(Authentication authentication, Model model){
        String name=authentication.getName();
        String email=makeEmail(authentication);
        model.addAttribute("name", name);
        model.addAttribute("email", email);
        if(name.equals("최정민") || name.equals("조예은")){
            model.addAttribute("rank","admin");
            return "adduser";
        }else{
            model.addAttribute("rank", "etc");
            List<Stance> stanceList=stanceService.getAllStanceList();
            List<Event> eventList=new ArrayList<>();
            for (Stance stance1 : stanceList){
                if(stance1.getADMIN()) {
                    Event event = new Event();
                    event.setTitle(stance1.getNAME());
                    String[] startArr = stance1.getSTART_TIME().split(" ");
                    String[] endArr = stance1.getEND_TIME().split(" ");
                    event.setStart(startArr[0] + "T" + startArr[1]);
                    event.setEnd(endArr[0] + "T" + endArr[1]);
                    eventList.add(event);
                }
            }
            model.addAttribute("eventList", eventList);
            model.addAttribute("message", "관리자만 접근 가능합니다.");
            return "home";
        }
    }

    @PostMapping("/addmonthuser")
    public String addMonthuserinList(Authentication authentication, Model model, Monthuser monthuser){
        String name=authentication.getName();
        String email=makeEmail(authentication);
        model.addAttribute("name", name);
        model.addAttribute("email", email);
        if(name.equals("최정민") || name.equals("조예은")){
            monthService.join(monthuser);
            List<Monthuser> monthusers= monthService.getAllMonthList();
            model.addAttribute("LISTV1", monthusers);
            model.addAttribute("message", "멤버 추가가 성공했습니다.");
            return "monthuser";
        }else{
            model.addAttribute("rank", "etc");
            List<Stance> stanceList=stanceService.getAllStanceList();
            List<Event> eventList=new ArrayList<>();
            for (Stance stance1 : stanceList){
                if(stance1.getADMIN()) {
                    Event event = new Event();
                    event.setTitle(stance1.getNAME());
                    String[] startArr = stance1.getSTART_TIME().split(" ");
                    String[] endArr = stance1.getEND_TIME().split(" ");
                    event.setStart(startArr[0] + "T" + startArr[1]);
                    event.setEnd(endArr[0] + "T" + endArr[1]);
                    eventList.add(event);
                }
            }
            model.addAttribute("eventList", eventList);
            model.addAttribute("message", "관리자만 접근 가능합니다.");
            return "home";
        }
    }

    public String makeEmail(Authentication authentication){
        String principal =authentication.getPrincipal().toString();
        int principalEmailIndex = principal.indexOf("email=");
        String email = principal.substring(principalEmailIndex+6, principal.length()-2);
        return email;
    }

}
