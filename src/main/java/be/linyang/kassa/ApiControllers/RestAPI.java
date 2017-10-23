package be.linyang.kassa.ApiControllers;


import be.linyang.kassa.Model.Table;
import be.linyang.kassa.Model.View;
import be.linyang.kassa.Model.items.Item;
import be.linyang.kassa.Model.ticket.Ticket;
import be.linyang.kassa.RestoManager;
import com.fasterxml.jackson.annotation.JsonView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RestAPI {

	private static final Logger LOGGER = LogManager.getLogger(RestAPI.class);

    @Autowired
    private RestoManager restoManager;


	@JsonView(View.Summary.class)
	@CrossOrigin(origins = "*")
	@GetMapping(value = "/api/getItems")
    public List<Item> getAllItems() {
		LOGGER.info("api getAllItems called");
        return restoManager.getItems();
    }

	@JsonView(View.Summary.class)
	@CrossOrigin(origins = "*")
	@GetMapping(value = "/api/getTables")
	public List<Table> getAllTables() {
		LOGGER.info("api getAllTables called");
		return restoManager.getTables();
	}

	@JsonView(View.Summary.class)
	@CrossOrigin(origins = "*")
	@GetMapping(value = "/api/getTodayTicketByNr")
	public Ticket getTodayTicketByNr(@RequestParam("ticketNr") int ticketNr) {
		LOGGER.info("api getTodayTicketByNr called");
		Ticket ticket = restoManager.findTodayTicketByNr(ticketNr);
		LOGGER.info("ticket: " + ticket);
		return ticket;
	}

	@CrossOrigin(origins = "*")
	@PostMapping(value = "/api/resto/createTicket")
	public int createRestoTicket(@RequestBody String tableNr) {
		LOGGER.info("api createRestoTicket called, tableNr is " + tableNr);
		return restoManager.createTicketOnTable(tableNr).getTicketNr();
	}

	@CrossOrigin(origins = "*")
	@PostMapping(value = "/api/ticket/{ticketNr}/addItemToTicket")
	public boolean addItemToTicket(@RequestBody String quicklink, @PathVariable("ticketNr") int ticketNr) {
		LOGGER.info("api addItemToTicket called, quicklink is " + quicklink + ", ticketNr is " + ticketNr);
		return restoManager.addItemToTicket(ticketNr,quicklink) != null;
	}

	@CrossOrigin(origins = "*")
	@PostMapping(value = "/api/ticket/{ticketNr}/removeItemFromTicket")
	public boolean removeItemFromTicket(@RequestBody String quicklink, @PathVariable("ticketNr") int ticketNr) {
		LOGGER.info("api removeItemFromTicket called, quicklink is " + quicklink + ", ticketNr is " + ticketNr);
		return restoManager.removeItemFromTicket(ticketNr,quicklink) != null;
	}

	@CrossOrigin(origins = "*")
	@PostMapping(value = "/api/ticket/{ticketNr}/pay")
	public boolean payTicket(@RequestBody String payMethod, @PathVariable("ticketNr") int ticketNr) {
		LOGGER.info("api payTicket called, paymethod is " + payMethod + ", ticketNr is " + ticketNr);
		Ticket paidTicket = this.restoManager.payTicket(ticketNr,payMethod);
		if (paidTicket != null) {
            LOGGER.info("paid ticket is " + paidTicket.toString());
        }
		return paidTicket != null ;
	}

	@JsonView(View.Summary.class)
	@CrossOrigin(origins = "*")
	@GetMapping(value = "/api/takeaway/getTodaysTakeawayTicket")
	public List<Ticket> getTodaysTakeawayTicket() {
		LOGGER.info("api getTodaysTakeawayTicket called");
		return this.restoManager.getTodaysTakewayTicket();
	}

	@CrossOrigin(origins = "*")
	@PostMapping(value = "/api/takeaway/createTicket")
	public int createTakeawayTicket() {
		LOGGER.info("api createTakeawayTicket called");
		return this.restoManager.createTakewayTicket().getTicketNr();
	}

    @CrossOrigin(origins = "*")
    @PostMapping(value = "/api/takeaway/updateTicket/{ticketNr}")
    public boolean updateTicketName(@PathVariable("ticketNr") int ticketNr, @RequestBody String name) {
        LOGGER.info("api updateTicketName called." + name);
        return this.restoManager.updateTicketName(ticketNr, name) != null;
    }

    @CrossOrigin(origins = "*")
    @PostMapping(value = "/api/takeaway/ticket/{ticketNr}/time")
    public boolean updateTicketTime(@PathVariable("ticketNr") int ticketNr, @RequestBody String time) {
        LOGGER.info("api updateTicketTime called." + time);
        return this.restoManager.updateTicketTime(ticketNr, time) != null;
    }

    @CrossOrigin(origins = "*")
    @PostMapping(value = "/api/takeaway/ticket/{ticketNr}/taken")
    public boolean updateTicketTaken(@PathVariable("ticketNr") int ticketNr, @RequestBody boolean taken) {
        LOGGER.info("api updateTicketTaken called." + taken);
        return this.restoManager.updateTicketTaken(ticketNr, taken) != null;
    }


}
