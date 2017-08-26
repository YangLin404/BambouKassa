package be.linyang.kassa.Controllers;

import be.linyang.kassa.Model.Table;
import be.linyang.kassa.Model.ticket.Ticket;
import be.linyang.kassa.Repository.MongoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class RestaurantController {

    @Autowired
    private MongoRepo mongoRepo;

    @RequestMapping("/restaurant")
    public String index(Model model) {
        model.addAttribute("tables",mongoRepo.getTables());

        return "restaurant";
    }

    @RequestMapping(value = "/restaurant/{ticketNr}", method = RequestMethod.GET)
    public String showTicket(Model model, @PathVariable("ticketNr") String ticketNr){
        Ticket ticketToShow = mongoRepo.getTicketByNr(ticketNr);
        model.addAttribute("ticket", ticketToShow);
        return "/fragments/ticket :: ticket";
    }

    @GetMapping(value = "/restaurant/getTables", produces = "application/json")
    public @ResponseBody
    List<Table> getTableCount()
    {
        return mongoRepo.getTables();
    }
}
