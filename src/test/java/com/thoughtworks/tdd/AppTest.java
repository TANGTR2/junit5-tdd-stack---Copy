package com.thoughtworks.tdd;

import com.thoughtworks.tdd.core.Car;
import com.thoughtworks.tdd.core.ParkBoy;
import com.thoughtworks.tdd.core.Receipt;
import com.thoughtworks.tdd.shell.Controler;
import com.thoughtworks.tdd.shell.Request;
import com.thoughtworks.tdd.shell.Response;
import com.thoughtworks.tdd.shell.Router;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

public class AppTest {

    @Test
    public void test_handleInformation(){
        Controler controler = mock(Controler.class);
        Router router = new Router(controler,"addParkinglot");
        String command = "北方停车场,22";
        String[] div = controler.handleInformation(command);
        assertThat(div[0],is("北方停车场"));
        assertThat(div[1],is("22"));
    }

}
