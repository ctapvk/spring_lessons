package ru.otus.spring05.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring05.service.HelloService;

@ShellComponent
public class HelloCommands {
    private final HelloService service;

    @Autowired
    public HelloCommands(HelloService service) {
        this.service = service;
    }

    @ShellMethod("Translate text from one language to another.")
    public String hello(
            @ShellOption String name
    ) {
        // invoke service
        return service.hello(name);
    }

    @ShellMethod("Translate text from one language to another.")
    public String buy(
            @ShellOption String name
    ) {
        // invoke service
        return service.buy(name);
    }

}
