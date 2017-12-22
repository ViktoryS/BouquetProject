package ua.bouquet.controller.commands;

import ua.bouquet.controller.FrontCommand;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * Created by Vikki on 13.12.2017.
 */
public class Command extends FrontCommand {
    @Override
    public void process() throws ServletException, IOException {
        forward("home");
    }
}
