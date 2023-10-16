package org.vaadin.addons.request;

/*-
 * #%L
 * accessibility-checker-for-vaadin
 * %%
 * Copyright (C) 2023 Add-on Author Name
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import com.vaadin.base.devserver.DevToolsInterface;
import com.vaadin.base.devserver.DevToolsMessageHandler;
import com.vaadin.base.devserver.IdeIntegration;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.dom.Element;
import com.vaadin.flow.server.VaadinService;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.server.startup.ApplicationConfiguration;
import elemental.json.Json;
import elemental.json.JsonObject;

import java.util.Optional;

/**
 * @author jcgueriaud
 */

@JsModule(value = "./request-tool/request-tool.ts", developmentOnly = true)
public class RequestToolPlugin implements DevToolsMessageHandler {

    public static final String REQUEST_TOOL = "request-tool";


    private IdeIntegration ideIntegration;
    public RequestToolPlugin() {

    }

    @Override
    public void handleConnect(DevToolsInterface devToolsInterface) {
        devToolsInterface.send(REQUEST_TOOL + "-init", Json.createObject());
    }

    @Override
    public boolean handleMessage(String command, JsonObject data, DevToolsInterface devToolsInterface) {
        System.out.println(data.toString());
        return false;
    }


}
