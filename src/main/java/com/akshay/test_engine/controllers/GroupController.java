package com.akshay.test_engine.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.akshay.test_engine.models.group.Group;
import com.akshay.test_engine.models.group.GroupService;
import com.akshay.test_engine.models.group.MapTestGroup;
import com.akshay.test_engine.models.group.StudentsInGroup;
import com.akshay.test_engine.utils.MessageBundle;
import com.akshay.test_engine.utils.StringResponse;
import com.akshay.test_engine.utils.Lang;

@RestController
@CrossOrigin
public class GroupController implements Lang {
	@Autowired
	private GroupService service;
	@Autowired
	private MessageBundle messageBundle;

	@RequestMapping(path = "/createGroup", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody StringResponse createGroup(@RequestBody Group group) {
		StringResponse response = new StringResponse();
		messageBundle.setLang(ENGLISH);
		if(service.createGroup(group)>0)
			response.setMsg(messageBundle.getMessage("createGroup.success"));
		else
			response.setMsg(messageBundle.getMessage("createGroup.fail"));
		return response;
	}
	
//	@RequestMapping(path = "/addTestToGroup", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//	public @ResponseBody StringResponse addTest(@RequestBody MapTestGroup mapper) {
//		StringResponse response = new StringResponse();
//		messageBundle.setLang(ENGLISH);
//		if (service.addTest(mapper.getTestid(), mapper.getGid())>0)
//			response.setMsg(messageBundle.getMessage("mapTestGroup.success"));
//		else
//			response.setMsg(messageBundle.getMessage("mapTestGroup.fail"));
//		return response;
//	}
//	
//	@RequestMapping(path = "/assignTestToGroup", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//	public @ResponseBody StringResponse addStudents (@RequestBody StudentsInGroup listOfStudents) {
//		StringResponse response = new StringResponse();
//		messageBundle.setLang(ENGLISH);
//		int students = service.addStudents(listOfStudents.getGid(), listOfStudents.getMailids());
//		if (students>0)
//			response.setMsg(students + " " +messageBundle.getMessage("addStudentsInGroup.success"));
//		else
//			response.setMsg(messageBundle.getMessage("addStudentsInGroup.fail"));
//		return response;
//	}
	
}