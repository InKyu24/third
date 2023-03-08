package com.example.third.controller;

import com.example.third.entity.Item;
import com.example.third.entity.ItemDto;
import com.example.third.service.ItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class ItemController {

  private final ItemService itemService;

  public ItemController(ItemService itemService) {
    this.itemService = itemService;
  }

  @GetMapping("/items")
  public String itemList(Model model) {
    List<Item> items = itemService.allItems();
    model.addAttribute("items", items);
    return "basic/items";
  }

  @GetMapping("/item/{id}")
  public String itemDetail(@PathVariable Long id, Model model) {
    Optional<Item> itemById = itemService.findItemById(id);
    model.addAttribute("item", itemById.get());
    return "basic/item";
  }

  @GetMapping("/add")
  public String AddItem() {
    return "basic/addForm";
  }

  @PostMapping("/add")
  public String addItem(ItemDto itemDto) {
    long id = itemService.addItem(itemDto);
    return "redirect:/items";
  }

  @GetMapping("/edit/{id}")
  public String EditItem(@PathVariable Long id, Model model) {
    Optional<Item> itemById = itemService.findItemById(id);
    model.addAttribute("item", itemById.get());
    return "basic/editForm";
  }

  @PostMapping("/edit")
  public String EditItem(ItemDto itemDto) {
    itemService.updateItem(itemDto.getId(), itemDto);
    return "redirect:/items";
  }
}
