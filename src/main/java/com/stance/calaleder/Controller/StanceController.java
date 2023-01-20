package com.stance.calaleder.Controller;

import com.stance.calaleder.Domain.Event;
import com.stance.calaleder.Domain.Stance;
import com.stance.calaleder.Service.StanceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class StanceController {

    private final Logger logger = LoggerFactory.getLogger(StanceController.class);
    private final StanceService stanceService;

    @Autowired
    public StanceController(StanceService stanceService){this.stanceService=stanceService;}


    @GetMapping("/")
    public String home(Authentication authentication, Model model){
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
        String name=authentication.getName();
        String email=makeEmail(authentication);
        model.addAttribute("name", name);
        model.addAttribute("email", email);
        logger.info(email);
        if(name.equals("최정민")){
            logger.info("ㅇㅇㅇㅇ");
            model.addAttribute("rank","admin");
        }else{
            model.addAttribute("rank", "etc");
        }
        logger.info(model.toString());
        return "home";

    }


    @PostMapping("/addevent")
    public String Addevent(Event event, Model model){
        String start=event.getStart().substring(0,19);
        String end= event.getEnd().substring(0,19);
        String [] start_arr=start.split("T");
        String [] end_arr=end.split("T");


        model.addAttribute("start",start_arr[0]+' '+start_arr[1]);
        model.addAttribute("end",end_arr[0]+' '+end_arr[1]);

        return "select";
    }

    @PostMapping("/request")
    public String Requeststance(Stance stance, Model model, Authentication authentication){
        try{
            logger.info(stance.getNAME());
            stanceService.join(stance);
            model.addAttribute("message", "대관 신청이 완료되었습니다.");
        }catch (IllegalStateException e){
            model.addAttribute("message", e.getMessage());
        }catch (Exception e){
            model.addAttribute("message", "대관 신청이 실패하였습니다.");
        } finally{
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
            String name=authentication.getName();
            if(name.equals("최정민")){
                logger.info("ㅇㅇㅇㅇ");
                model.addAttribute("rank","admin");
            }else{
                model.addAttribute("rank", "etc");
            }
            logger.info(model.toString());
            model.addAttribute("eventList", eventList);
            return "home";
        }
    }

    @PostMapping("/success")
    public String Getsucess(Authentication authentication, Model model, Stance stance){
        logger.info(stance.getSTART_TIME());
        stanceService.updateStance(stance);
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
        String name=authentication.getName();
        String email=makeEmail(authentication);
        model.addAttribute("name", name);
        model.addAttribute("email", email);
        logger.info(email);
        if(name.equals("최정민")){
            logger.info("ㅇㅇㅇㅇ");
            model.addAttribute("rank","admin");
        }else{
            model.addAttribute("rank", "etc");
        }
        logger.info(model.toString());
        return "home";

    }

    @PostMapping("/admin")
    public String getAdmin(Authentication authentication, Model model){

        List<Stance> stanceList=stanceService.getAllStanceList();
        List<Stance> stanceNoaccessList=new ArrayList<>();
        for (Stance stance : stanceList){
            logger.info(stance.getADMIN().toString());
            if (!stance.getADMIN()){
                stanceNoaccessList.add(stance);
            }
        }
        logger.info(stanceNoaccessList.toString());
        model.addAttribute("LISTV1", stanceNoaccessList);
        return "admin";
    }
    public String makeEmail(Authentication authentication){
        String principal =authentication.getPrincipal().toString();
        int principalEmailIndex = principal.indexOf("email=");
        String email = principal.substring(principalEmailIndex+6, principal.length()-2);
        return email;
    }



}
