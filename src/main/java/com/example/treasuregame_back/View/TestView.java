package com.example.treasuregame_back.View;


import com.example.treasuregame_back.game.Game;
import com.example.treasuregame_back.game.GameService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.map.Map;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.Router;
import com.vaadin.flow.router.RouterLink;
import org.vaadin.crudui.crud.impl.GridCrud;
import javax.annotation.security.RolesAllowed;
import javax.swing.text.html.ListView;



public class TestView extends AppLayout {

    public TestView(){
        createHeader();
        createDrawer();
    }
    private void createHeader() {
        H4 logo = new H4("Treasure Game App");


        var header = new HorizontalLayout(new DrawerToggle(), logo );

        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.setWidthFull();
        addToNavbar(header);

    }

    private void createDrawer() {
        addToDrawer(new VerticalLayout(
                new RouterLink("Dashboard", DashboardView.class),
                new RouterLink("NewGame", NewGameView.class)
        ));
    }

}
