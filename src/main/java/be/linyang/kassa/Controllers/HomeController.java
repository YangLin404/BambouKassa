package be.linyang.kassa.Controllers;


import be.linyang.kassa.Repository.MongoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @Autowired
    private MongoRepo mongoRepo;

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("tickets",mongoRepo.findAllTickets());

        return "index";
    }


}
