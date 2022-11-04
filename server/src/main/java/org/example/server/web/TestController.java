package org.example.server.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

@Controller
public class TestController {

    @GetMapping("/export/{tag}")
    public void exportRaidSeason(@PathVariable String tag, HttpServletResponse response) {

    }
}
