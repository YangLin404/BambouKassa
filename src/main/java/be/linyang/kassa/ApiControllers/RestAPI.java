package be.linyang.kassa.ApiControllers;


import be.linyang.kassa.Model.items.Item;
import be.linyang.kassa.RestoManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController(value = "/api")
public class RestAPI {

    @Autowired
    private RestoManager restoManager;

    @GetMapping(value = "/getItems", produces = "application/json")
    public @ResponseBody
    List<Item> getAllItems() {
        return restoManager.getItems();
    }
}
