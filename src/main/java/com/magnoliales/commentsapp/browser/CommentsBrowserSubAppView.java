package com.magnoliales.commentsapp.browser;

import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import info.magnolia.ui.api.view.View;

public class CommentsBrowserSubAppView implements View {

    private VerticalLayout layout;

    public CommentsBrowserSubAppView() {
        layout = new VerticalLayout();
        layout.setMargin(true);
        layout.setSpacing(true);
        layout.addComponent(new Label("There we go"));
    }

    @Override
    public Component asVaadinComponent() {
        return layout;
    }
}
