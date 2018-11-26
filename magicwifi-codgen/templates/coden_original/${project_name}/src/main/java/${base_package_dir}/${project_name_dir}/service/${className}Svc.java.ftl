<#include "/java_copyright.include">
<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
package ${base_package}.${project_name}.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ${base_package}.${project_name}.dao.${className}Dao;
import ${base_package}.${project_name}.entity.${className};
import cn.com.magicwifi.framework.core.mybatis.BaseSvc;

<#include "/java_imports.include">
@Transactional
@Service
public class ${className}Svc extends BaseSvc<${className}, ${table.pkColumns[0].simpleJavaType}, ${className}Dao> {

    @Autowired
    @Override
    public void setDao(${className}Dao ${classNameLower}Dao) {
        super.setDao(${classNameLower}Dao);
    }

}