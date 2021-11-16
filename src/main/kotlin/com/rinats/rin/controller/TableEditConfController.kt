package com.rinats.rin.controller

import com.rinats.rin.annotation.NonAuth
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.PostMapping
import javax.servlet.http.HttpServletRequest

@Controller
class TableEditConfController() {
    @NonAuth
    @PostMapping("/table_edit_conf")
    fun tableEditConf(request: HttpServletRequest, model: Model): String {
        val name = request.getParameter("name")
        val numOfPeople = request.getParameter("numOfPeople")
        model.addAttribute("name", name)
        model.addAttribute("numOfPeople", numOfPeople)
        return "TableEditConfPage"
    }
}