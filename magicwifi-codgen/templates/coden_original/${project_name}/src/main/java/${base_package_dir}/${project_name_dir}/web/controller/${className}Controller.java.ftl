<#include "/java_copyright.include" />
<#assign className = table.className />
<#assign classNameLower = className?uncap_first />
package ${base_package}.${project_name}.web.controller;

import cn.com.magicwifi.framework.core.web.controller.BaseController;
import ${base_package}.${project_name}.entity.${className};
import ${base_package}.${project_name}.service.${className}Svc;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

<#include "/java_imports.include" />
@RequestMapping("/${project_name}")
@Controller
public class ${className}Controller extends BaseController {

    @Autowired
    private ${className}Svc ${classNameLower}Svc;

    @RequiresPermissions("${project_name}:${table.constantName?lower_case}:v_list")
    @RequestMapping(value = "/${table.constantName?lower_case}/v_list")
    public String list(Integer pageNo, HttpServletRequest request, Model model) {
        model.addAttribute("pageNo", pageNo);
        return "${project_name}/${table.constantName?lower_case}/list";
    }

    @RequiresPermissions("${project_name}:${table.constantName?lower_case}:v_list")
    @RequestMapping(value = "/${table.constantName?lower_case}/v_edit", method = RequestMethod.GET)
    public String edit(${table.pkColumns[0].simpleJavaType} id, Model model) {
        if (null != id) {
            ${className} bean = ${classNameLower}Svc.get(id);
            model.addAttribute("bean", bean);
        }
        return "${project_name}/${table.constantName?lower_case}/edit";
    }

    @RequiresPermissions("${project_name}:${table.constantName?lower_case}:o_update")
    @RequestMapping(value = "/${table.constantName?lower_case}/o_save", method = RequestMethod.POST)
    public String save(${className} bean, Model model, RedirectAttributes ra) {
        if (!beanValidator(model, bean)) {
            return edit(null, model);
        }
        ${classNameLower}Svc.create(bean);
        success(ra);
        return "redirect:v_list";
    }

    @RequiresPermissions("${project_name}:${table.constantName?lower_case}:o_update")
    @RequestMapping(value = "/${table.constantName?lower_case}/o_update", method = RequestMethod.POST)
    public String update(${className} bean, Model model, RedirectAttributes ra) {
        if (!beanValidator(model, bean)) {
            return edit(bean.getId(), model);
        }
        ${classNameLower}Svc.modifySelective(bean);
        success(ra);
        return "redirect:v_list";
    }

    @RequiresPermissions("${project_name}:${table.constantName?lower_case}:o_update")
    @RequestMapping(value = "/${table.constantName?lower_case}/o_delete")
    public String delete(${table.pkColumns[0].simpleJavaType}[] ids, RedirectAttributes ra) {
        ${classNameLower}Svc.batchRemove(Arrays.asList(ids));
        success(ra);
        return "redirect:v_list";
    }

}