package kz.edu.controller;

import kz.edu.dao.CardDAO;
import kz.edu.dao.UserDAO;
import kz.edu.model.Card;
import kz.edu.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cards")
public class ControllerCard {

    private final CardDAO cardDAO;
    @Autowired
    public ControllerCard(CardDAO cardDAO){ this.cardDAO = cardDAO;}

    @GetMapping()
    public String helloPage(Model model)
    {
        model.addAttribute("cardsList", cardDAO.getCardsList());
        return "page-1";
    }

    @GetMapping("/{id}")
    public String book(@PathVariable("id") int id, Model model)
    {
        model.addAttribute("book", cardDAO.getCard(id));
        return "page-2";
    }

    @GetMapping("/anotherCard")
    public String tranAnotherCardGet(Model model)
    {
        model.addAttribute("card", new Card());
        return "anotherCard";
    }
    @PostMapping()
    public String tranAnotherCardPost(@ModelAttribute("card") Card card)
    {

        cardDAO.addCard(card);
        return "redirect:/books/"+card.getCard_id();
    }
}
