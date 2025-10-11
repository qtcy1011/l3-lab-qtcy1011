package com.poly.lab5.service.impl;

import com.poly.lab5.bean.Item;
import com.poly.lab5.data.DB;
import com.poly.lab5.service.ShoppingCartService;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@SessionScope
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private Map<Integer, Item> map = new HashMap<>();

    @Override
    public Item add(Integer id) {
        Item item = map.get(id);

        if (item == null) {
 
            Item itemFromDB = DB.items.get(id);

            if (itemFromDB != null) {
        
                item = new Item(itemFromDB.getId(), itemFromDB.getName(), itemFromDB.getPrice(), 1);
                map.put(id, item);
            }
        } else {
     
            item.setQty(item.getQty() + 1);
        }
        return item;
    }

    @Override
    public void remove(Integer id) {
        map.remove(id);
    }

    @Override
    public Item update(Integer id, int qty) {
        Item item = map.get(id);
        
        if (item != null) {
            if (qty > 0) {
                item.setQty(qty); 
            } else {
                remove(id); 
                return null;
            }
        }
        return item;
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public Collection<Item> getItems() {
        return map.values(); 
    }

    @Override
    public int getCount() {
     
        return map.values().stream().mapToInt(Item::getQty).sum();
    }

    @Override
    public double getAmount() {
  
        return map.values().stream().mapToDouble(item -> item.getPrice() * item.getQty()).sum();
    }
}