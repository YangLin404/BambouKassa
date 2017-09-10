package be.linyang.kassa.Controllers;

import be.linyang.kassa.RestoManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OverviewController {

    @Autowired
    private RestoManager restoManager;

    @GetMapping("/overview")
    public String index() {

        return "overview";
    }

    @GetMapping("/overview/dayOverview")
    public String dayOverview() {

        return "fragments/dayOverview :: dayOverview";
    }
}
