
package com.felix.project.module.role.action;

import com.felix.project.dto.RoleDTO;
import com.felix.project.module.jurisdiction.service.JurisdictionServiceImpl;
import com.felix.project.module.role.service.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/role/content")
public class RoleContentController {
	
	@Autowired
	private RoleServiceImpl roleService;
	
	@Autowired
	private JurisdictionServiceImpl jurisdictionService;
	
	/**
	 * 添加角色
	 *
	 * @param model
	 * @return
	 */
	@RequestMapping("/add")
	public String main(Model model, @RequestParam("roleId") Long roleId) {
		model.addAttribute("role", roleService.getFullRole(roleId));
		model.addAttribute("jurisdictions", jurisdictionService.getSimpleJurisdictions());
		
		return "/role/content/add";
	}
	
	/**
	 * 新增或更新角色
	 *
	 * @param role
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public String save(@ModelAttribute RoleDTO role) {
		roleService.saveOrUpdateRole(role);
		
		return "success";
	}
	
	/**
	 * 删除角色
	 *
	 * @param roleId
	 * @return
	 */
	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	@ResponseBody
	public String remove(@RequestParam("roleId") Long roleId) {
		roleService.removeRole(roleId);
		
		return "success";
	}
	
	/**
	 * 删除多个角色
	 *
	 * @param roleIds
	 * @return
	 */
	@RequestMapping(value = "/removes", method = RequestMethod.POST)
	@ResponseBody
	public String removes(@RequestParam("roleIds") String roleIds) {
		roleService.removeRoles(roleIds);
		
		return "success";
	}
	
}
