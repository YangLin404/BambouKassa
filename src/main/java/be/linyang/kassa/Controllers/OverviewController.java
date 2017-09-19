package be.linyang.kassa.Controllers;

import be.linyang.kassa.Model.ticket.Ticket;
import be.linyang.kassa.RestoManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

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

    @GetMapping("/overview/dayOverview/findTicketsByDate")
    public String findTicketByDate(Model model,
                                   @RequestParam("date") String date,
                                   @RequestParam("filter") String filter) {
        List<Ticket> tickets = restoManager.getTicketByDate(date,filter.equals("all")?"":filter);
        if (tickets.isEmpty())
            return "fragments/result :: noResult";
        else {
            Double resultTotal = tickets.stream()
                                    .mapToDouble(t -> Double.valueOf(t.getTotalPrice()))
                                    .sum();
            Double resultRestoTotal = tickets.stream()
                                    .filter(t -> t.getTicketType() == Ticket.TicketType.Resto)
                                    .mapToDouble(t -> Double.valueOf(t.getTotalPrice()))
                                    .sum();
            Double resultTakewayTotal = tickets.stream()
                    .filter(t -> t.getTicketType() == Ticket.TicketType.Takeway)
                    .mapToDouble(t -> Double.valueOf(t.getTotalPrice()))
                    .sum();
            model.addAttribute("tickets", tickets);
            model.addAttribute("resultTotal", resultTotal);
            model.addAttribute("resultRestoTotal", resultRestoTotal);
            model.addAttribute("resultTakewayTotal", resultTakewayTotal);

        }


        return "fragments/result :: result";
    }
}
