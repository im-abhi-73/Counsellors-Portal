package com.counsellors.controller;

import com.counsellors.binding.DashbordDTO;
import com.counsellors.entity.EnquiryEntity;
import com.counsellors.repo.EnquiryRepo;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class EnquiryController {

    @Autowired
    private EnquiryRepo enquiryRepo;

    // Show Add Enquiry form
    @GetMapping("/addenquiry")
    public String showAddEnquiryForm(Model model) {
        model.addAttribute("enquiry", new EnquiryEntity());
        return "add-enquiry";  // Thymeleaf template name (add-enquiry.html)
    }

    // Save Enquiry and go to Dashboard with success message
    @PostMapping("/saveenquiry")
    public String saveEnquiry(HttpServletRequest request ,@ModelAttribute("enquiry") EnquiryEntity enquiry,
                              RedirectAttributes redirectAttributes) {
    	
    	
    	HttpSession session = request.getSession(false);
    	Integer counsellorId = (Integer)session.getAttribute("counsellorId");
    	
    	
        enquiryRepo.save(enquiry);
        redirectAttributes.addFlashAttribute("successMsg", "Enquiry saved successfully!");
        return "redirect:/dashbord"; // Redirect to dashboard page
    }

    // Show Dashboard Page
    @GetMapping("/dashbord")
    public String showDashboard(Model model) {
        
        // Example counts - you can replace with real logic
        long totalEnqCount = enquiryRepo.count();

        // Create a simple DTO object
        DashbordDTO dashbordDto = new DashbordDTO();
        dashbordDto.setTotalEnqCount((int) totalEnqCount);
       

        model.addAttribute("dashbordDto", dashbordDto);
        model.addAttribute("enquiries", enquiryRepo.findAll());

        return "dashbord";  // make sure the file is dashbord.html
    }

}



/*
package com.counsellors.controller;

import com.counsellors.entity.EnquiryEntity;
import com.counsellors.repo.EnquiryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EnquiryController {

    @Autowired
    private EnquiryRepo enquiryRepo;

    @GetMapping("/addenquiry")
    public String showAddEnquiryForm(Model model) {
        model.addAttribute("enquiry", new EnquiryEntity());
        return "add-enquiry";  // name of your HTML file
    }


    @PostMapping("saveenquiry")
    public String saveEnquiry(@ModelAttribute("enquiry") EnquiryEntity enquiry) {


        enquiryRepo.save(enquiry);
        return "redirect:/login";

    }

}
*/
