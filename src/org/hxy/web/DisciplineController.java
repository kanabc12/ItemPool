package org.hxy.web;

import java.util.HashMap;
import java.util.Map;

import org.hxy.service.IDisciplineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/discipline")
public class DisciplineController {
	
	@Autowired
	IDisciplineService  disciplineService;
	
	@RequestMapping(value="/getAll")
	@ResponseBody
	public Map<String,Integer> getAllDiscipline(){
		Map<String,Integer> getAccount = new HashMap<String,Integer>();
		int account = disciplineService.countAll();
		getAccount.put("account", new Integer(account));
		return getAccount;
	}
}
