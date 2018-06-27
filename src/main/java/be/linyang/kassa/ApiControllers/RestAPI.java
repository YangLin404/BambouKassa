package be.linyang.kassa.ApiControllers;


import be.linyang.kassa.Model.Table;
import be.linyang.kassa.Model.View;
import be.linyang.kassa.Model.items.Extra;
import be.linyang.kassa.Model.items.Item;
import be.linyang.kassa.Model.items.ItemJsonWrapper;
import be.linyang.kassa.Model.ticket.Ticket;
import be.linyang.kassa.RestoManager;
import com.fasterxml.jackson.annotation.JsonView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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

    @CrossOrigin(origins = "*")
    @GetMapping(value = "/api")
    public boolean healthCheck() {
        LOGGER.info("api healthCheck called");
        return restoManager != null;
    }


	@JsonView(View.Summary.class)
	@CrossOrigin(origins = "*")
	@GetMapping(value = "/api/getItems")
    public List<Item> getAllItems() {
		LOGGER.info("api getAllItems called");
        return restoManager.getItems();
    }

    @JsonView(View.Summary.class)
    @CrossOrigin(origins = "*")
    @GetMapping(value = "/api/extras")
    public List<Extra> getAllExtras() {
        LOGGER.info("api getAllExtras called");
        List<Extra> extras = restoManager.getExtras();
        LOGGER.info("extras are: " + extras.toString());
        return extras;
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

    @JsonView(View.Summary.class)
    @CrossOrigin(origins = "*")
    @GetMapping(value = "/api/ticket")
    public Ticket getTicketByIdentifier(@RequestParam("ID") String ticketID) {
        LOGGER.info("api getTicketByIdentifier called");
        Ticket ticket = restoManager.getTicketByID(ticketID);
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
    @PostMapping(value = "/api/resto/table/{fromTable}/changeTable")
    public boolean changeTable(@PathVariable("fromTable") String fromTable ,@RequestBody String toTable) {
        LOGGER.info("api changeTable called, from Table: " + fromTable + " to table: " + toTable);
        return restoManager.changeTable(fromTable, toTable);
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping(value = "/api/ticket/{ticketNr}")
    public boolean removeTicket(@PathVariable("ticketNr") int ticketNr) {
        LOGGER.info("api removeTicket called, ticketNr is " + ticketNr);
        return restoManager.deleteTodayTicket(ticketNr);
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
    @PostMapping(value = "/api/ticket/{ticketNr}/name")
    public boolean updateTicketName(@PathVariable("ticketNr") int ticketNr, @RequestBody String name) {
        LOGGER.info("api updateTicketName called." + name);
        return this.restoManager.updateTicketName(ticketNr, name) != null;
    }

    @CrossOrigin(origins = "*")
    @PostMapping(value = "/api/ticket/{ticketNr}/persons")
    public boolean updateTicketPersons(@PathVariable("ticketNr") int ticketNr, @RequestBody int persons) {
        LOGGER.info("api updateTicketPersons called." + persons);
        return this.restoManager.updateTicketPersons(ticketNr, persons) != null;
    }

    @CrossOrigin(origins = "*")
    @PostMapping(value = "/api/ticket/{ticketNr}/time")
    public boolean updateTicketTime(@PathVariable("ticketNr") int ticketNr, @RequestBody String time) {
        LOGGER.info("api updateTicketTime called." + time);
        return this.restoManager.updateTicketTime(ticketNr, time) != null;
    }

    @CrossOrigin(origins = "*")
    @PostMapping(value = "/api/ticket/{ticketNr}/ticketItem/{quicklink}/remark")
    public boolean updateTicketRemark(@PathVariable("ticketNr") int ticketNr,
                                      @PathVariable("quicklink") String quicklink,
                                      @RequestBody String remark) {
        LOGGER.info("api updateTicketRemark called. remark: " + remark);
        return this.restoManager.updateTicketItemRemark(ticketNr,quicklink,remark);
    }

    @CrossOrigin(origins = "*")
    @PostMapping(value = "/api/ticket/{ticketNr}/ticketItem/{quicklink}/extra")
    public boolean addExtraToTicketItem(@PathVariable("ticketNr") int ticketNr,
                                      @PathVariable("quicklink") String quicklink,
                                      @RequestBody String extra) {
        LOGGER.info("api addExtraToTicketItem called. extra: " + extra);
        return this.restoManager.addExtraToItem(ticketNr,quicklink,extra) != null;
    }



    @CrossOrigin(origins = "*")
    @PostMapping(value = "/api/takeaway/ticket/{ticketNr}/taken")
    public boolean updateTicketTaken(@PathVariable("ticketNr") int ticketNr, @RequestBody boolean taken) {
        LOGGER.info("api updateTicketTaken called." + taken);
        return this.restoManager.updateTicketTaken(ticketNr, taken) != null;
    }

	@JsonView(View.Summary.class)
    @CrossOrigin(origins = "*")
    @GetMapping(value = "/api/overview/tickets")
    public List<Ticket> getTicketByDate(@RequestParam("date") String date) {
        LOGGER.info("api getTicketByDate called. " + date);
        return this.restoManager.getNotDeletedTicketByDate(date);
    }

	@CrossOrigin(origins = "*")
	@PostMapping(value = "/api/config/reload")
	public boolean reloadData() {
		LOGGER.info("api reloadData called. ");
		return this.restoManager.reloadData();
	}

	@CrossOrigin(origins = "*")
	@PostMapping(value = "/api/config/menu/item")
	public boolean addItemToMenu(@RequestBody ItemJsonWrapper itemJsonWrapper) {
		LOGGER.info("api addItemToMenu called. ");
		LOGGER.debug("Json is: " + itemJsonWrapper.toString());
		Item item = new Item(itemJsonWrapper.getQuicklink(), itemJsonWrapper.getName(), itemJsonWrapper.getCh_name(), itemJsonWrapper.getPrice(), itemJsonWrapper.getItemType());
		return this.restoManager.addItem(item);
	}


}
