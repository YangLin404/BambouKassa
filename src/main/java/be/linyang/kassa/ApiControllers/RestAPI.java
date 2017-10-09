package be.linyang.kassa.ApiControllers;


import be.linyang.kassa.Model.items.Item;
import be.linyang.kassa.RestoManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RestAPI {

	private static final Logger LOGGER = LogManager.getLogger(RestAPI.class);

    @Autowired
    private RestoManager restoManager;


	@CrossOrigin(origins = "*")
	@GetMapping(value = "/api/getItems")
    public List<Item> getAllItems() {
		LOGGER.info("api getAllItems called");
        return restoManager.getItems();
    }
}
