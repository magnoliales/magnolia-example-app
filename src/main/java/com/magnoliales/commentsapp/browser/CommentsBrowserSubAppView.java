package com.magnoliales.commentsapp.browser;

import com.vaadin.server.Sizeable;
import com.vaadin.ui.*;
import info.magnolia.ui.api.view.View;

import java.util.Date;

public class CommentsBrowserSubAppView implements View {

    public enum Status { NEW, APPROVED, REJECTED };

    private VerticalLayout layout;
    private int counter;

    /**
     * @todo Add column generator for the status
     * @todo Move search box to the right
     * @todo Connect to the workspace
     * @todo See how to make use of dialogs in Magnolia from Vaadin application
     * @todo Implement search to parametrise the search box
     */
    public CommentsBrowserSubAppView() {

        final Button button = new Button();
        final Table table = new Table();

        button.setCaption("Click me");
        button.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                table.addItem(new Object[] {
                    "Date", new Date().toString(), Status.APPROVED
                }, counter++);
            }
        });

        table.addContainerProperty("Title", String.class, null);
        table.addContainerProperty("Author", String.class, null);
        table.addContainerProperty("Status", Status.class, Status.NEW);
        table.setSizeFull();

        layout = new VerticalLayout();
        layout.setMargin(true);
        layout.setSpacing(true);
        layout.setSizeFull();

        layout.addComponent(button);
        layout.addComponent(table);

        layout.setExpandRatio(table, 1);
    }

    @Override
    public Component asVaadinComponent() {
        return layout;
    }
}
