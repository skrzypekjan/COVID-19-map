package pl.skrzypekjan.covid19map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class Covid19DataJoin {

    private Covid19Parser covid19Parser;
    private RecoveredParser recoveredParser;
    private List<Point> confirmed;
    private List<Point> deaths;
    private List<Point> recovered;

    @Autowired
    public Covid19DataJoin(Covid19Parser covid19Parser, RecoveredParser recoveredParser) {
        this.covid19Parser = covid19Parser;
        this.recoveredParser = recoveredParser;
    }


    public Covid19DataJoin() {
        this.confirmed = new ArrayList<>();
        this.deaths = new ArrayList<>();
        this.recovered = new ArrayList<>();
    }

    public List<Point> finalList() throws IOException {
        confirmed = covid19Parser.confirmed();
        deaths = covid19Parser.deaths();
        recovered = recoveredParser.getConfirmData();
        List<Point> resultList = new ArrayList<>();

        for (int i = 0; i < recovered.size(); i++) {
            resultList.add(new Point(recovered.get(i).getLat(), recovered.get(i).getLon(),
                    ("Confirmed: " + confirmed.get(i).getText() + "<br>" +
                            " Deaths: " + deaths.get(i).getText() + "<br>" +
                            " Recovery: " + recovered.get(i).getText())));
        }
        return resultList;
    }


    public List<Point> getCovidData() throws IOException  {
        return finalList();
    }
}
