package com.oneconnect.OneConnect.Landing;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.web.servlet.ModelAndView;

import com.oneconnect.OneConnect.Utility;
import com.oneconnect.OneConnect.Login.LoginService;

public class LandingService {

	public ModelAndView getModelAndViewByRoleAndUserId(String role, String userId) {
		ModelAndView modelAndView = new ModelAndView();
        LoginService loginService = new LoginService();
        
        try {
	        if (role.equals("default")) {
	        	role = loginService.retrieveRole(userId).get(1);
		    }
		        
	        System.out.println("Role: " + role + " userId: " + userId);
	        if(userId.equals("f")) {
	            modelAndView.setViewName("forbidden");
	        } else {
	            switch(role) {
	                case "admin":
	                    modelAndView.setViewName("TEMP4");
	                    break;
	                case "parent" :
	                    modelAndView.setViewName("TEMP3");
	                    break;
	                case "teacher" :
	                    modelAndView.setViewName("TEMP2");
	                    break;
	                case "student" :
	                    modelAndView.setViewName("STUDENT_LANDING");
	                    
	                    List<String> courseIds = retrieveCourses(userId);
	                    List<String> courseNames = new ArrayList<String>();
	                    for(String courseId : courseIds) {
	                    	courseNames.add(retrieveCourseName(courseId));
	                    }
	                    String userName = retrieveUserName(userId);
	                    
	                    modelAndView.addObject("courseNames", courseNames);
	                    modelAndView.addObject("userId", userId);
	                    modelAndView.addObject("userName", userName);
	                    
	                    break;
	                default:
	                    modelAndView.setViewName("forbidden");
	                    break;
	            }
	        }
	        return modelAndView;
        } catch(Exception e) {
        	System.out.println("Unable to process userId: "+ userId +", role: " + role);
            modelAndView.setViewName("forbidden");
        }
        return modelAndView;

	}
	
	
	 public List<String> retrieveCourses(String id) {
	        Utility utility = new Utility();
	        List<String> courses = null;
	        File file = null;
	        JSONArray jsonArray = utility.jsonArrayGenerator("Users.json");
	        if(utility.numberChecker(id) && jsonArray != null) {
	        	courses = new ArrayList<>();
	            for (int i = 0; i < jsonArray.size(); i++) {
	                JSONObject jsonObject = (JSONObject)jsonArray.get(i);
	                if(jsonObject.get("id").equals(id)) {
	                    JSONArray courseArray = (JSONArray)jsonObject.get("classes");
	                    for(int j = 0; j < courseArray.size(); j++) {
	                    	courses.add((String)courseArray.get(j));
	                    }
	                    break;
	                }
	            }
	        }
	        return courses;
	    }

	public String retrieveCourseName(String courseId) {
		 Utility utility = new Utility();
	        String courseName = "Course Not Found";
	        JSONArray jsonArray = utility.jsonArrayGenerator("Class.json");
	        if(utility.numberChecker(courseId) && jsonArray != null) {
	            for (int i = 0; i < jsonArray.size(); i++) {
	                JSONObject jsonObject = (JSONObject)jsonArray.get(i);
	                if(jsonObject.get("id").equals(courseId)) {
	                    courseName = (String) jsonObject.get("name");
	                    
	          	      	return courseName;
	                }
	            }
	        }
	      return courseName;
	}
	
	public String retrieveUserName(String userId) {
		 Utility utility = new Utility();
	        String userName = "No name found";
	        JSONArray jsonArray = utility.jsonArrayGenerator("Users.json");
	        if(utility.numberChecker(userId) && jsonArray != null) {
	            for (int i = 0; i < jsonArray.size(); i++) {
	                JSONObject jsonObject = (JSONObject)jsonArray.get(i);
	                if(jsonObject.get("id").equals(userId)) {
	                    userName = (String) jsonObject.get("name");
	                    
	          	      	return userName;
	                }
	            }
	        }
	      return userName;
	}
	
	
	
}
