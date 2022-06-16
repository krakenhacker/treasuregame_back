package com.example.treasuregame_back.View;

import com.example.treasuregame_back.game.Game;
import com.example.treasuregame_back.game.GameService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.BigDecimalField;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.timepicker.TimePicker;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.converter.StringToDoubleConverter;
import com.vaadin.flow.router.Route;

import javax.annotation.security.RolesAllowed;
import java.awt.*;

@Route("new")
@RolesAllowed("ADMIN")
public class NewGameView extends VerticalLayout {
    private TextField name = new TextField("Name");
    private DatePicker date = new DatePicker("Starting Date");
    private TimePicker time = new TimePicker("Starting Time");
    private NumberField duration = new NumberField("Duration (hours)");

    private NumberField x = new NumberField("X");
    private NumberField y = new NumberField("Y");
    private NumberField w = new NumberField("W");
    private NumberField z = new NumberField("Z");


    public NewGameView(GameService service){
        var binder = new Binder<>(Game.class);
        binder.bindInstanceFields(this);

        add(
                new H1("New Game"),
                new FormLayout(name,date,time,NumberFieldStep(duration),x,y,w,z),
                new Button("Save", event ->{
                    var game = new Game();
                    binder.writeBeanIfValid(game);
                    service.add(game);
                    Notification.show("Game Saved.");
                    binder.readBean(new Game());
                })
        );
    }



    public Component NumberFieldStep(NumberField numberField) {
        this.duration=numberField;
        numberField.setStep(0.5);
        numberField.setValue(0.5);
        numberField.setHasControls(true);
        return numberField;
    }
}
