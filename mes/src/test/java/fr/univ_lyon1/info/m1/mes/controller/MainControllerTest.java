package fr.univ_lyon1.info.m1.mes.controller;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import org.junit.jupiter.api.Test;
import fr.univ_lyon1.info.m1.mes.model.MES;

public class MainControllerTest {

    @Test
    public void instantiateMainControllerTest() throws Exception {
        MES model = MES.getInstance();
        Integer c = model.getPatients().size();
        MainController mainController = new MainController(model);
        assertThat(model.getPatients().size(), not(c));
    }
}
