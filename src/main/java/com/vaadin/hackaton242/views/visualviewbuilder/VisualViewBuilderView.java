package com.vaadin.hackaton242.views.visualviewbuilder;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;
import com.vaadin.hackaton242.views.MainLayout;

@PageTitle("Visual View Builder")
@Route(value = "visual-view-builder", layout = MainLayout.class)
@Uses(Icon.class)
public class VisualViewBuilderView extends Composite<VerticalLayout> {

    public VisualViewBuilderView() {
        HorizontalLayout layoutRow = new HorizontalLayout();
        VerticalLayout layoutColumn2 = new VerticalLayout();
        LoginForm loginForm = new LoginForm();
        getContent().setHeightFull();
        getContent().setWidthFull();
        getContent().setFlexGrow(1.0, layoutRow);
        layoutRow.setWidthFull();
        layoutRow.addClassName(Gap.MEDIUM);
        layoutRow.setFlexGrow(1.0, layoutColumn2);
        layoutColumn2.setHeightFull();
        layoutColumn2.setWidth(null);
        layoutColumn2.setAlignSelf(FlexComponent.Alignment.CENTER, loginForm);
        getContent().add(layoutRow);
        layoutRow.add(layoutColumn2);
        layoutColumn2.add(loginForm);
    }
}
