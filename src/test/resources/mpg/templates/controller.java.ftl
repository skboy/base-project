package ${package.Controller};

import org.springframework.web.bind.annotation.RequestMapping;
<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
<#else>
import org.springframework.stereotype.Controller;
</#if>
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>

/**
<#if table.comment != "">
 * ${table.comment!}前端控制器
 *
</#if>
* @author ${author}
* @since ${date}
*/
@Api("")
<#if restControllerStyle>
@RestController
<#else>
@Controller
</#if>
@RequestMapping("${package.ModuleName}")
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
<#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
<#else>
public class ${table.controllerName} {
</#if>


}
</#if>
