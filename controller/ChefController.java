package com.food.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.food.model.Chef;
import com.food.respository.ChefRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/chef")
public class ChefController {

    @Autowired
    private ChefRepository repo;

    public static String uploadDirectory = System.getProperty("user.dir") + "/src/main/images";

    @GetMapping("/display")
    public String display() {
        return "hello";
    }
//======================================================================================
    @PostMapping("/save")
    public String saveInfo(@ModelAttribute Chef chef, @RequestParam("image") MultipartFile file) throws IOException {
        if (file.isEmpty())
        {
        	String msg="please check wheather image is added or not";
            return msg; 
        }
        else if (chef.getAge()<18)
        {
            String msg = "Age should be greater than or equal to 18";
            return msg;
        }

        else if (chef.getName() == null || chef.getName().isEmpty()) {
        String msg = "Name field is required";
        return msg;
        } 
        else if (chef.getGender() == null || chef.getGender().isEmpty()) {
        String msg = "Gender field is required";
        return msg;
        } 
        else if (chef.getGmail() == null || chef.getGmail().isEmpty()) {
        String msg = "Email field is required";
        return msg;
        } 
        else if (chef.getMobile() == null) {
        String msg = "Mobile field is required";
        return msg;
        }
        else
        {
            String originalFilename = file.getOriginalFilename();
            Path fileNameAndPath = Paths.get(uploadDirectory, originalFilename);
            Files.createDirectories(fileNameAndPath.getParent());
            Files.write(fileNameAndPath, file.getBytes());
            chef.setProfileimg(fileNameAndPath.toString());
            String msg="succesfully instered data";
            repo.save(chef);
            return msg;
        }
       
        }
    //=================================================================================
    @GetMapping("/delete/{chefid}")
    public String deleteChef(@PathVariable String chefid) {
        Chef chef = repo.findById(chefid).get();
        if (chef != null) {
            repo.deleteById(chefid);
            return "Data is successfully deleted for chefId=" + chefid;
        } else 
        {
            return "Chef with ID " + chefid + " not found";
        }
    }

      //======================================================================
      @GetMapping("/get/{location}")
      public List<Chef> getlocation(@PathVariable String location) {
          List<Chef> chefs = repo.findByLocation(location);
		return chefs;
      }

     
      //=====================================================================
      @GetMapping("/getall")
      public List<Chef> getAll() {
    	 
    	  List<Chef> chefs=(List<Chef>) repo.findAll();
    	  return chefs.stream()
    			  .sorted((chef1,chef2)->chef1.getChefid().compareTo(chef2.getChefid()))
    			  .collect(Collectors.toList());
      }
     
//=====================================================================================
          @PutMapping("/update/{chefid}")
          public String updateChef(@PathVariable String chefid, @RequestBody Chef chef) {
              Chef oldrecord = repo.findById(chefid).get();
              if (oldrecord == null) {
                  return "Chef with ID " + chefid + " not found.";
              }
              oldrecord.setName(chef.getName());
              oldrecord.setAge(chef.getAge());
              oldrecord.setGender(chef.getGender());
              oldrecord.setGmail(chef.getGmail());
              oldrecord.setProfileimg(chef.getProfileimg());
              oldrecord.setExperiences(chef.getExperiences());
              oldrecord.setMobile(chef.getMobile());
              oldrecord.setLocation(chef.getLocation());
              oldrecord.setProfession(chef.getProfession());
              
              repo.save(oldrecord);
              
              return "Chef with ID " + chefid + " updated successfully."; 
      }
          //================================================================
          @PostMapping("/login")
          public String login(@RequestBody Chef chef) {
              Chef storedChef = repo.findByGmail(chef.getGmail());
              
              if (storedChef != null && storedChef.getChefid().equals(chef.getChefid())) {
                  return "Login successful";
              } else {
                  return "Invalid email or password";
              }
          }


}
