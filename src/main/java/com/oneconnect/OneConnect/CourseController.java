package com.oneconnect.OneConnect;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.oneconnect.OneConnect.Course.CourseService;

@Controller
public class CourseController {

	@RequestMapping(value = "/course", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView getCourse(@RequestParam(defaultValue = "f") String id, @RequestParam String role, @RequestParam String courseId) {
		 
		System.out.println("asdfdasf");
		CourseService courseService = new CourseService();
		 
	    return courseService.getModelAndViewByRoleAndUserIdAndClassId(role, id, courseId); 

		 
		 

		 
	 }
}