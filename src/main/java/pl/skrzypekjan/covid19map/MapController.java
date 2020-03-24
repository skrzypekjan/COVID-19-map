package pl.skrzypekjan.covid19map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@Controller
public class MapController {


    private Covid19Parser covid19Parser;
    private Covid19DataJoin covid19DataJoin;
    public MapController(Covid19DataJoin covid19DataJoin) {
        this.covid19DataJoin = covid19DataJoin;
    }

    @GetMapping
    public String getMap(Model model) throws IOException {
        model.addAttribute("points", covid19DataJoin.getCovidData());
        return "map2";
    }
}
