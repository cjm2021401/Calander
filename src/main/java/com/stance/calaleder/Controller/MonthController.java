package com.stance.calaleder.Controller;

import com.stance.calaleder.Domain.Event;
import com.stance.calaleder.Domain.Monthstance;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MonthController {

    private final Logger logger = LoggerFactory.getLogger(MonthController.class);
    private final MonthService monthService;
    private final MonthStanceService monthStanceService;
    private final StanceService stanceService;
    @Autowired
    public MonthController(MonthService monthService, MonthStanceService monthStanceService, StanceService stanceService){
        this.monthService=monthService;
        this.monthStanceService=monthStanceService;
        this.stanceService=stanceService;
    }

    @GetMapping("/month")
    public String month(Authentication authentication, Model model){
        String name= authentication.getName();
        String email=makeEmail(authentication);
        model.addAttribute("name", name);
        model.addAttribute("email", email);
        if(name.equals("최정민") || name.equals("최슬기")){
            model.addAttribute("rank","admin");
        }else{
            model.addAttribute("rank", "etc");
        }
        if(monthService.checkMonthNameEvent(name, email)  || name.equals("최슬기")){
            //Monthuser monthuser = monthService.getMonthNameEvent(name, email);
            List<Monthstance> monthstanceList =monthStanceService.getALlMonthStanceName(name);
            List<Event> eventList=new ArrayList<>();
            for(Monthstance monthstance1 : monthstanceList){
                Event event = new Event();
                event.setTitle(name);
                event.setStart(monthstance1.getSTART_TIME());
                event.setEnd(monthstance1.getEND_TIME());
                eventList.add(event);
            }

            model.addAttribute("eventList", eventList);
            model.addAttribute("name", name);
            model.addAttribute("email", email);
            if(name.equals("최정민") || name.equals("최슬기")){
                model.addAttribute("rank","admin");
            }else{
                model.addAttribute("rank", "etc");
            }
            return "month";
        }else{
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
            model.addAttribute("message", "월 대관 회원이 아닙니다. \n 월 대관 이용을 원할 시 @stance_dance_studio 로 문의 부탁드립니다.");
            return "home";
        }
    }

    @GetMapping("/monthview")
    public String monthview(Authentication authentication, Model model){
        String name= authentication.getName();
        String email=makeEmail(authentication);
        model.addAttribute("name", name);
        model.addAttribute("email", email);
        if(name.equals("최정민") || name.equals("최슬기")){
            model.addAttribute("rank","admin");
        }else{
            model.addAttribute("rank", "etc");
        }
        if(monthService.checkMonthNameEvent(name, email)  || name.equals("최슬기")){
            //Monthuser monthuser = monthService.getMonthNameEvent(name, email);
            List<Monthstance> monthstanceList =monthStanceService.getAllMonthStance();
            List<Event> eventList=new ArrayList<>();
            for(Monthstance monthstance1 : monthstanceList){
                Event event = new Event();
                event.setTitle(monthstance1.getNAME());
                event.setStart(monthstance1.getSTART_TIME());
                event.setEnd(monthstance1.getEND_TIME());
                eventList.add(event);
            }

            model.addAttribute("eventList", eventList);
            model.addAttribute("name", name);
            model.addAttribute("email", email);
            if(name.equals("최정민") || name.equals("최슬기")){
                model.addAttribute("rank","admin");
            }else{
                model.addAttribute("rank", "etc");
            }
            return "monthview";
        }else{
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
            model.addAttribute("message", "월 대관 회원이 아닙니다. \n 월 대관 이용을 원할 시 @stance_dance_studio 로 문의 부탁드립니다.");
            return "home";
        }
    }

    @PostMapping("/addmonthevent")
    public String addMonthEvent(Authentication authentication, MonthEvent monthEvent, Model model){
        String name= authentication.getName();
        String email=makeEmail(authentication);
        model.addAttribute("name", name);
        model.addAttribute("email", email);
        if(name.equals("최정민") || name.equals("최슬기")){
            model.addAttribute("rank","admin");
        }else{
            model.addAttribute("rank", "etc");
        }
        if(monthService.checkMonthNameEvent(name, email)|| name.equals("최슬기")) {
            logger.info(monthEvent.toString());
            Monthstance monthStance = new Monthstance();
            monthStance.setNAME(name);
            monthStance.setSTART_TIME(monthEvent.getStart());
            monthStance.setEND_TIME(monthEvent.getEnd());
            logger.info(monthStance.toString());
            monthStanceService.join(monthStance);

            List<Monthstance> monthstanceList =monthStanceService.getALlMonthStanceName(name);

            List<Event> eventList=new ArrayList<>();
            for(Monthstance monthstance1 : monthstanceList){
                Event event = new Event();
                event.setTitle(name);
                event.setStart(monthstance1.getSTART_TIME());
                event.setEnd(monthstance1.getEND_TIME());
                eventList.add(event);
            }

            model.addAttribute("eventList", eventList);
            return "month";
        }
        return "home";
    }

    @PostMapping("/deletemonthevent")
    public String deleteMonthEvent(Authentication authentication, MonthEvent1 monthEvent, Model model) {
        logger.info(monthEvent.getStart1());
        logger.info("하하하");
        String name= authentication.getName();
        String email=makeEmail(authentication);
        model.addAttribute("name", name);
        model.addAttribute("email", email);
        if(name.equals("최정민") || name.equals("최슬기")){
            model.addAttribute("rank","admin");
        }else{
            model.addAttribute("rank", "etc");
        }
        monthStanceService.deleteMonthStanceName(name, monthEvent.getStart1(), monthEvent.getEnd1());

        List<Monthstance> monthstanceList =monthStanceService.getALlMonthStanceName(name);

        List<Event> eventList=new ArrayList<>();
        for(Monthstance monthstance1 : monthstanceList){
            Event event = new Event();
            event.setTitle(name);
            event.setStart(monthstance1.getSTART_TIME());
            event.setEnd(monthstance1.getEND_TIME());
            eventList.add(event);
        }

        model.addAttribute("eventList", eventList);
        return "month";
    }

    public String makeEmail(Authentication authentication){
        String principal =authentication.getPrincipal().toString();
        int principalEmailIndex = principal.indexOf("email=");
        String email = principal.substring(principalEmailIndex+6, principal.length()-2);
        return email;
    }

    static class MonthEvent{
        private String start;
        private String end;

        public String getStart() {
            return start;
        }

        public void setStart(String start) {
            this.start = start;
        }

        public String getEnd() {
            return end;
        }

        public void setEnd(String end) {
            this.end = end;
        }
    }
    static class MonthEvent1{
        private String start1;
        private String end1;

        public MonthEvent1(String start1, String end1) {
            this.start1 = start1;
            this.end1 = end1;
        }

        public String getStart1() {
            return start1;
        }

        public void setStart1(String start1) {
            this.start1 = start1;
        }

        public String getEnd1() {
            return end1;
        }

        public void setEnd1(String end1) {
            this.end1 = end1;
        }
    }
}
