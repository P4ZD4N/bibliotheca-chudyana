package com.p4zd4n.bibliothecachudyana.controller;

import com.p4zd4n.bibliothecachudyana.entity.Authority;
import com.p4zd4n.bibliothecachudyana.entity.Order;
import com.p4zd4n.bibliothecachudyana.entity.OrderItem;
import com.p4zd4n.bibliothecachudyana.entity.User;
import com.p4zd4n.bibliothecachudyana.service.OrderService;
import com.p4zd4n.bibliothecachudyana.util.FindOrdersForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class OrderManagementController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/orders-management")
    public String displayOrdersManagementPanel(
            @RequestParam(required = false) Integer id,
            @RequestParam(required = false) String username,
            @RequestParam(required = false) LocalDate minDate,
            @RequestParam(required = false) LocalDate maxDate,
            @RequestParam(required = false) Double minTotalAmount,
            @RequestParam(required = false) Double maxTotalAmount,
            @RequestParam(required = false) String status,
            Model model
    ) {
        if (id != null) {
            model.addAttribute("orders", orderService.findById(id));
            return "/ordersmanagement/orders-management";
        }

        List<List<Order>> searchedOrders = new ArrayList<>();

        if (username != null) {
            searchedOrders.add(orderService.findByUsername(username));
        }

        if (minDate != null && maxDate == null) {
            searchedOrders.add(orderService.findByMinDate(minDate));
        }

        if (minDate == null && maxDate != null) {
            searchedOrders.add(orderService.findByMaxDate(maxDate));
        }

        if (minDate != null && maxDate != null) {
            searchedOrders.add(orderService.findByMinAndMaxDate(minDate, maxDate));
        }

        if (minTotalAmount != null && maxTotalAmount == null) {
            searchedOrders.add(orderService.findByMinTotalAmount(minTotalAmount));
        }

        if (minTotalAmount == null && maxTotalAmount != null) {
            searchedOrders.add(orderService.findByMaxTotalAmount(maxTotalAmount));
        }

        if (minTotalAmount != null && maxTotalAmount != null) {
            searchedOrders.add(orderService.findByMinAndMaxTotalAmount(minTotalAmount, maxTotalAmount));
        }

        if (status != null) {
            searchedOrders.add(orderService.findByStatus(status));
        }

        if (searchedOrders.isEmpty()) {
            List<Order> orders = orderService.findAll();
            model.addAttribute("orders", orders);
        } else {
            List<Order> commonOrders = new ArrayList<>(searchedOrders.getFirst());

            for (List<Order> orders : searchedOrders)
                commonOrders.retainAll(orders);

            model.addAttribute("orders", commonOrders);
        }

        return "/ordersmanagement/orders-management";
    }

    @GetMapping("/orders-management/find-orders")
    public String displayFindOrdersForm(Model model) {
        model.addAttribute("findOrdersForm", new FindOrdersForm());
        return "/ordersmanagement/find-orders";
    }

    @PostMapping("/orders-management/find-orders")
    public String findOrders(@ModelAttribute("findOrdersForm") FindOrdersForm findOrdersForm) {
        Integer id = findOrdersForm.getId();
        String username = findOrdersForm.getUsername();
        LocalDate minDate = findOrdersForm.getMinDate();
        LocalDate maxDate = findOrdersForm.getMaxDate();
        Double minTotalAmount = findOrdersForm.getMinTotalAmount();
        Double maxTotalAmount = findOrdersForm.getMaxTotalAmount();
        String status = findOrdersForm.getStatus();

        StringBuilder redirectUrlBuilder = new StringBuilder("redirect:/orders-management?");

        if (id != null) {
            redirectUrlBuilder.append("id=").append(id);
            return redirectUrlBuilder.toString();
        }

        if (username != null && !username.isEmpty()) {
            redirectUrlBuilder.append("username=").append(username).append("&");
        }

        if (minDate != null) {
            redirectUrlBuilder.append("minDate=").append(minDate).append("&");
        }

        if (maxDate != null) {
            redirectUrlBuilder.append("maxDate=").append(maxDate).append("&");
        }

        if (minTotalAmount != null) {
            redirectUrlBuilder.append("minTotalAmount=").append(minTotalAmount).append("&");
        }

        if (maxTotalAmount != null) {
            redirectUrlBuilder.append("maxTotalAmount=").append(maxTotalAmount).append("&");
        }

        if (status != null && !status.isEmpty()) {
            redirectUrlBuilder.append("status=").append(status).append("&");
        }

        if (redirectUrlBuilder.charAt(redirectUrlBuilder.length() - 1) == '&') {
            redirectUrlBuilder.deleteCharAt(redirectUrlBuilder.length() - 1);
        }

        return redirectUrlBuilder.toString();
    }

    @GetMapping("/orders-management/update-order")
    public String displayUpdateOrderForm(@RequestParam("orderId") Integer id, Model model) {
        Order order = orderService.findById(id);

        model.addAttribute("order", order);

        return "/ordersmanagement/save-order";
    }

    @PostMapping("/orders-management/save-order")
    public String saveOrder(@ModelAttribute("order") Order order) {

        order.setUser(orderService.findById(order.getId()).getUser());
        order.setOrderDate(orderService.findById(order.getId()).getOrderDate());
        order.setTotalAmount(orderService.findById(order.getId()).getTotalAmount());
        order.setItems(orderService.findById(order.getId()).getItems());

        orderService.update(order);

        return "redirect:/orders-management";
    }

    @GetMapping("/orders-management/delete-order")
    public String deleteOrder(@RequestParam("orderId") Integer id) {
        Order order = orderService.findById(id);
        orderService.delete(order);
        return "redirect:/orders-management";
    }
}
