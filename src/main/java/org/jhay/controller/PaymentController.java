package org.jhay.controller;



import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.jhay.service.impl.CartServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class PaymentController {
    private final CartServiceImpl cartServiceImpl;
    @PostMapping("/checkout")
    public String checkOut(Model model, @RequestParam("session_id") Long user_id,
                           @RequestParam("session_name") String session_name){
        model.addAttribute("orderLink", cartServiceImpl.checkOut(user_id));
        model.addAttribute("cartStatus", "Redirecting");
        model.addAttribute("cartList", cartServiceImpl.viewAllCart(user_id));
        model.addAttribute("cartTotal", cartServiceImpl.getCartTotal(user_id));
        model.addAttribute("session_id",user_id);
        model.addAttribute("session_name",session_name);
        return "cart";
    }
    @GetMapping("/redirect-transaction")
    public String transactionStatus(@RequestParam("status") String status,
                                    @RequestParam("tx_ref") String tx_ref,
                                    @RequestParam("transaction_id") String transaction_id,
                                    HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        String userName = (String) session.getAttribute("userName");
        Long user_id = (Long) session.getAttribute("user_id");
        if(status.equalsIgnoreCase("completed")){
            cartServiceImpl.orderMade(user_id);
            model.addAttribute("cartStatus", "Thanks for Ordering");
        }else{
            model.addAttribute("cartStatus", "Failed to place order");
        }
        model.addAttribute("cartList", cartServiceImpl.viewAllCart(user_id));
        model.addAttribute("cartTotal", cartServiceImpl.getCartTotal(user_id));
        model.addAttribute("session_id",user_id);
        model.addAttribute("session_name",userName);
        return "cart";
    }
}
