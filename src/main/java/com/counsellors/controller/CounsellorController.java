package com.counsellors.controller;

import com.counsellors.binding.CounsellorDTO;
import com.counsellors.binding.DashbordDTO;
import com.counsellors.service.CounsellorService;
import com.counsellors.service.EnquiryService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CounsellorController {

    @Autowired
    private CounsellorService counsellorService;

    @Autowired
    private EnquiryService enquiryService;

    // Display login page
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("counsellor", new CounsellorDTO());
        return "index";
    }

    // Handle login form submission
    @PostMapping("/login")
    public String login(HttpServletRequest request,
                        @ModelAttribute("counsellor") CounsellorDTO counsellorDTO,
                        Model model) {

        CounsellorDTO counsellor = counsellorService.login(counsellorDTO);

        if (counsellor == null) {
            model.addAttribute("counsellor", new CounsellorDTO());
            model.addAttribute("msg", "Invalid Credentials! Please enter a valid Email or Password.");
            return "index";
        } else {
            HttpSession session = request.getSession(true);
            session.setAttribute("counsellor", counsellor);
/*
            DashbordDTO dashbordDto = enquiryService.getDashbordInfo(counsellor.getCounsellorId());

            if (dashbordDto == null) {
                dashbordDto = new DashbordDTO();
                dashbordDto.setTotalEnqCount(2);
                dashbordDto.setOpenEnqCount(3);
                dashbordDto.setEnrolledEnqCount(5);
                dashbordDto.setLostEnqCount(6);
            }

            model.addAttribute("dashbordDto", dashbordDto);

            
            DashbordDTO dashbordDto = enquiryService.getDashbordInfo(counsellor.getCounsellorId());

            model.addAttribute("dashbordDto", dashbordDto);

            // Logging for debug (optional)
            System.out.println("Dashboard Values:");
            System.out.println("Total: " + dashbordDto.getTotalEnqCount());
            System.out.println("Open: " + dashbordDto.getOpenEnqCount());
            System.out.println("Enrolled: " + dashbordDto.getEnrolledEnqCount());
            System.out.println("Lost: " + dashbordDto.getLostEnqCount());

            return "dashbord";*/
            
            DashbordDTO dashbordDto = enquiryService.getDashbordInfo(counsellor.getCounsellorId());

            if (dashbordDto == null) {
                dashbordDto = new DashbordDTO(); // avoids NPE
            }

            model.addAttribute("dashbordDto", dashbordDto);

            // Logging (optional)
            System.out.println("Dashboard Values:");
            System.out.println("Total: " + dashbordDto.getTotalEnqCount());
            System.out.println("Open: " + dashbordDto.getOpenEnqCount());
            System.out.println("Enrolled: " + dashbordDto.getEnrolledEnqCount());
            System.out.println("Lost: " + dashbordDto.getLostEnqCount());

            return "dashbord";


        }
    }

    // Display registration page
    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("counsellor", new CounsellorDTO());
        return "register";
    }

    // Handle register form submission
    @PostMapping("/register")
    public String handleRegister(@ModelAttribute("counsellor") CounsellorDTO counsellorDTO, Model model) {

        boolean unique = counsellorService.uniqueEmailChacke(counsellorDTO.getEmail());

        if (unique) {
            boolean register = counsellorService.register(counsellorDTO);
            if (register) {
                model.addAttribute("smsg", "Registration Successful!");
            } else {
                model.addAttribute("fmsg", "Registration Failed! Please try again.");
            }
        } else {
            model.addAttribute("emsg", "Email already registered!");
            return "register";
        }

        model.addAttribute("counsellor", new CounsellorDTO()); // clear form
        return "register";
    }
}



/*
 * package com.counsellors.controller;
 * 
 * import com.counsellors.binding.CounsellorDTO; import
 * com.counsellors.binding.DashbordDTO; import
 * com.counsellors.service.CounsellorService; import
 * com.counsellors.service.EnquiryService; import
 * jakarta.servlet.http.HttpServletRequest; import
 * jakarta.servlet.http.HttpSession; import
 * org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.stereotype.Controller; import
 * org.springframework.ui.Model; import
 * org.springframework.web.bind.annotation.GetMapping; import
 * org.springframework.web.bind.annotation.ModelAttribute; import
 * org.springframework.web.bind.annotation.PostMapping;
 * 
 * @Controller public class CounsellorController {
 * 
 * @Autowired private CounsellorService counsellorService;
 * 
 * @Autowired private EnquiryService enquiryService;
 * 
 * // Display login page
 * 
 * @GetMapping("/") public String index(Model model) {
 * 
 * model.addAttribute("counsellor", new CounsellorDTO()); return "index"; }
 * 
 * // Handle login form submission
 * 
 * @PostMapping("/login") public String login(HttpServletRequest request,
 * 
 * @ModelAttribute("counsellor") CounsellorDTO counsellorDTO, Model model) {
 * 
 * CounsellorDTO counsellor = counsellorService.login(counsellorDTO);
 * 
 * if (counsellor == null) {
 * 
 * model.addAttribute("counsellor", new CounsellorDTO());
 * 
 * model.addAttribute("msg",
 * "Invalid Credentials ! Use Valid Email or Password"); return "index"; } else
 * { HttpSession session = request.getSession(true);
 * session.setAttribute("counsellor", counsellor);
 * 
 * DashbordDTO dashbordDto =
 * enquiryService.getDashbordInfo(counsellor.getCounsellorId());
 * model.addAttribute("dashbordDto", dashbordDto);
 * 
 * System.out.println("Dashboard Values:"); System.out.println("Total: " +
 * dashbordDto.getTotalEnqCount()); System.out.println("Open: " +
 * dashbordDto.getOpenEnqCount()); System.out.println("Enrolled: " +
 * dashbordDto.getEnrolledEnqCount()); System.out.println("Lost: " +
 * dashbordDto.getLostEnqCount());
 * 
 * 
 * return "dashbord"; } }
 * 
 * // Display registration page
 * 
 * @GetMapping("/register") public String register(Model model) {
 * 
 * model.addAttribute("counsellor", new CounsellorDTO());
 * 
 * 
 * return "register"; }
 * 
 * 
 * // Handle register form submission
 * 
 * @PostMapping("/register") public String
 * handleRegister(@ModelAttribute("counsellor") CounsellorDTO
 * counsellorDTO,Model model) {
 * 
 * boolean unique =
 * counsellorService.uniqueEmailChacke(counsellorDTO.getEmail());
 * 
 * if(unique) {
 * 
 * boolean register = counsellorService.register(counsellorDTO);
 * 
 * if(register) { model.addAttribute("smsg", "Registration Success"); }else {
 * 
 * model.addAttribute("fmsg", "Registration Failed"); }
 * 
 * }else{
 * 
 * model.addAttribute("emsg","Email Already Registered !"); return "register";
 * 
 * }
 * 
 * model.addAttribute("counsellor", new CounsellorDTO()); // clear form return
 * "register"; } }
 */