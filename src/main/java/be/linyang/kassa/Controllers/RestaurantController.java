package be.linyang.kassa.Controllers;

import be.linyang.kassa.Model.Table;
import be.linyang.kassa.Model.items.Item;
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
    public String showTicket(Model model, @PathVariable("tableNr") String tableNr){
        Ticket ticketToShow = restoManager.getActiveTicketOfTable(tableNr);
        if (ticketToShow != null) {
            model.addAttribute("ticket", ticketToShow);
            return "fragments/ticket :: ticket";
        }
        return "fragments/ticket :: newTicketBtn";
    }

    @GetMapping(value = "/restaurant/getTables", produces = "application/json")
    public @ResponseBody
    List<Table> getTableCount()
    {
        return restoManager.getTables();
    }

    @GetMapping(value = "/restaurant/getItems", produces = "application/json")
    public @ResponseBody
    List<Item> getAllItems() {
        return restoManager.getItems();
    }

    @PostMapping(value = "/restaurant/createTicket")
    public String createTicket(Model model, @RequestParam("tableNr") String tableNr) {
        Ticket ticket = new Ticket();
        ticket.setTableNr(tableNr);
        Ticket createdTicket = restoManager.createTicket(ticket);
        model.addAttribute("ticket",createdTicket);
        return "fragments/ticket :: ticket";
    }

    @PostMapping(value = "/restaurant/addItemToTicket/{ticketNr}")
    public String addItemToTicket(Model model,
                                  @PathVariable("ticketNr") String ticketNr,
                                  @RequestParam("quicklink") String quicklink) {
        Ticket ticket = restoManager.addItemToTicket(Integer.valueOf(ticketNr), quicklink);
        model.addAttribute("ticket", ticket);

        return "fragments/ticket :: ticket";
    }

    @PostMapping(value = "/restaurant/{ticketNr}/{quicklink}/AddExtraToItem")
    public String addExtraToItem(Model model, @PathVariable("ticketNr") String ticketNr,
                                 @PathVariable("quicklink") String quicklink,
                                 @RequestParam("extra") String extra) {

        Ticket ticket = restoManager.addExtraToItem(Integer.valueOf(ticketNr),quicklink,extra);
        model.addAttribute("ticket", ticket);

        return "fragments/ticket :: ticket";
    }

    @PostMapping(value = "/restaurant/{ticketNr}/pay")
    @ResponseBody
	public boolean payTicket(Model model, @PathVariable("ticketNr") String ticketNr,
                             @RequestParam("payMethod") String payMethod) {
    	Ticket ticket = restoManager.payTicket(Integer.valueOf(ticketNr), payMethod);
    	model.addAttribute("ticket", ticket);
    	return true;
    }

    @PostMapping(value = "/takeway/{ticketNr}/delete")
    @ResponseBody
    public boolean deleteTicket(Model model, @PathVariable("ticketNr") String ticketNr) {
        return restoManager.deleteTodayTicket(Integer.valueOf(ticketNr));

    }

    @RequestMapping("/takeway")
    public String takeway(Model model) {
        model.addAttribute("tickets", restoManager.getTodaysTakewayTicket());

        return "takeway";
    }

    @PostMapping(value = "/takeway/createTicket")
    @ResponseBody
    public Ticket createTicket(Model model) {
        Ticket ticket = restoManager.createTakewayTicket();
        if (ticket != null)
            return ticket;
        return null;
    }

    @PostMapping(value = "/takeway/updateTicket/{ticketNr}")
    @ResponseBody
    public Ticket updateTicket(Model model, @PathVariable("ticketNr") int ticketNr,
                               @RequestParam("name") String name,
                               @RequestParam("time") String time) {
        return restoManager.updateTicket(ticketNr,name,time);

    }

    @GetMapping(value = "/takeway/{ticketNr}")
    public String showTicket(Model model, @PathVariable("ticketNr") int ticketNr) {
        Ticket ticketToShow = restoManager.findTodayTicketByNr(ticketNr);
        model.addAttribute("ticket", ticketToShow);
        return "fragments/ticket :: ticket";
    }

    @GetMapping("/test")
    public String test(Model model) {
        model.addAttribute("tables", restoManager.getTables());
        return "test";
    }

    @PostMapping("/restaurant/{ticketNr}/addExistItem")
    public String addExistItem(Model model, @PathVariable("ticketNr") int ticketNr,
                               @RequestParam("name") String name,
                               @RequestParam("quicklink") String quicklink) {

        if (quicklink.equalsIgnoreCase("undefined"))
            quicklink = "";
        String keyword = quicklink + "," + name;
        Ticket ticket = restoManager.addItemToTicket(ticketNr, keyword);
        model.addAttribute("ticket", ticket);
        return "fragments/ticket :: ticket";
    }

    @PostMapping("/restaurant/{ticketNr}/removeExistItem")
    public String removeExistItem(Model model, @PathVariable("ticketNr") int ticketNr,
                                  @RequestParam("name") String name,
                                  @RequestParam("quicklink") String quicklink) {

        if (quicklink.equalsIgnoreCase("undefined"))
            quicklink = "";
        String keyword = quicklink + "," + name;
        Ticket ticket = restoManager.removeItemFromTicket(ticketNr, keyword);
        model.addAttribute("ticket", ticket);
        return "fragments/ticket :: ticket";
    }
}
