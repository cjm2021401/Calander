package com.stance.calaleder.Controller;

import com.stance.calaleder.Domain.MonthStance;
import com.stance.calaleder.Domain.Months;
import com.stance.calaleder.Service.MonthService;
import com.stance.calaleder.Service.MonthStanceService;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MonthController {

    private final Logger logger = LoggerFactory.getLogger(MonthController.class);
    private final MonthService monthService;
    private final MonthStanceService monthStanceService;
    @Autowired
    public MonthController(MonthService monthService, MonthStanceService monthStanceService){
        this.monthService=monthService;
        this.monthStanceService=monthStanceService;
    }

    @GetMapping("/month")
    public String month(Authentication authentication, Model model){
        String name= authentication.getName();
        String email=makeEmail(authentication);
        if(monthService.checkMonthNameEvent(name, email)){
            Months month = monthService.getMonthNameEvent(name, email);
            List<MonthStance> monthStanceList=monthStanceService.getALlMonthStanceName(name);
            model.addAttribute("eventList", monthStanceList);
            model.addAttribute("name", name);
            model.addAttribute("email", email);
            if(name.equals("최정민") || name.equals("조예은")){
                model.addAttribute("rank","admin");
            }else{
                model.addAttribute("rank", "etc");
            }
            return "month";
        }else{
            model.addAttribute("message", "월 대관 회원이 아닙니다. \n 월 대관 원할 시 @stance_dance_studio 로 문의 부탁드립니다.");
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
