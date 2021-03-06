package com.rinats.rin.controller.storemanager

import com.rinats.rin.model.form.LaborForm
import com.rinats.rin.service.LaborService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping

@Controller
class LaborController(
    @Autowired
    val laborService: LaborService
) {

    @GetMapping("labor_check")
    fun laborCheck(model: Model): String {
        model.addAttribute("laborList", laborService.getLabor())
        model.addAttribute("name", laborService.getName(laborService.getLabor()))
        return "LaborCheck"
    }

    @PostMapping("labor_edit")
    fun laborEdit(model: Model, employeeLevelForm: LaborForm): String {
        val name = laborService.getByName(employeeLevelForm.employeeId ?: "")
        model.apply {
            addAttribute("employeeId", employeeLevelForm.employeeId)
            addAttribute("roleId", employeeLevelForm.roleId)
            addAttribute("level", employeeLevelForm.level)
            addAttribute("name", name)
        }
        return "LaborEdit"
    }

    @PostMapping("labor_edit_conf")
    fun laborEditCheck(model: Model, employeeLevelForm: LaborForm): String {
        val name = laborService.getByName(employeeLevelForm.employeeId ?: "")
        model.apply {
            addAttribute("employeeId", employeeLevelForm.employeeId)
            addAttribute("roleId", employeeLevelForm.roleId)
            addAttribute("level", employeeLevelForm.level)
            addAttribute("name", name)
        }
        return "LaborEditConf"
    }

    @PostMapping("labor_edit_complete")
    fun laborEditComplete(model: Model, employeeLevelForm: LaborForm): String {
        laborService.levelUpdate(employeeLevelForm.employeeId ?: "", employeeLevelForm.roleId ?: "2", employeeLevelForm.level ?: 1)
        return "redirect:labor_check"
    }
}