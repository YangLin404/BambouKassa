package be.linyang.kassa.Controllers;

import be.linyang.kassa.Model.Table;
import be.linyang.kassa.Model.ticket.Ticket;
import be.linyang.kassa.RestoManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class RestaurantController {

    @Autowired
    private RestoManager restoManager;

    @RequestMapping("/restaurant")
    public String index(Model model) {
        model.addAttribute("tables", restoManager.getTables());

        return "restaurant";
    }

    @RequestMapping(value = "/restaurant/{tableNr}", method = RequestMethod.GET)
    public String showTicket(Model model, @PathVariable("tableNr") String ticketNr){
        Ticket ticketToShow = restoManager.getActiveTicketOfTable(ticketNr);
        if (ticketToShow != null) {
            model.addAttribute("ticket", ticketToShow);
            return "/fragments/ticket :: ticket";
        }
        return "/fragments/ticket :: newTicketBtn";
    }

    @GetMapping(value = "/restaurant/getTables", produces = "application/json")
    public @ResponseBody
    List<Table> getTableCount()
    {
        return restoManager.getTables();
    }

    @PostMapping(value = "/restaurant/createTicket")
    public String createTicket(Model model, @RequestParam("tableNr") String tableNr) {
        Ticket ticket = new Ticket();
        ticket.setTableNr(tableNr);
        Ticket createdTicket = restoManager.addTicket(ticket);
        model.addAttribute("ticket",createdTicket);
        return "/fragments/ticket :: ticket";
    }

    @PostMapping(value = "/restaurant/addItemToTicket/{ticketNr}")
    public String addItemToTicket(Model model, @PathVariable("ticketNr") String ticketNr, @RequestParam("itemQL") String ItemQL) {




        return "/fragments/ticket :: ticket";
    }
}
