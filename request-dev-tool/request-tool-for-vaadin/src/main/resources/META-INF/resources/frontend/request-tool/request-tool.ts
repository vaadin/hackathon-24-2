// @ts-ignore
import {html, css, LitElement, nothing} from 'lit';
// @ts-ignore
import {customElement, property, state} from 'lit/decorators.js';

import type {
    DevToolsInterface,
    DevToolsPlugin,
    MessageHandler,
    ServerMessage,
    VaadinDevTools
}
// @ts-ignore
from 'Frontend/generated/jar-resources/vaadin-dev-tools/vaadin-dev-tools';

let devTools: DevToolsInterface;


@customElement('request-tool')
// @ts-ignore
export class RequestTool extends LitElement implements MessageHandler {

    static styles = css`
    `;

    @property()
    items: PerformanceEntry[] = [];

    count: number = 0;

    observer: PerformanceObserver;
    constructor(props) {
        super(props);
        this.observer = new PerformanceObserver((list) => {
            list.getEntries().forEach(entry => {
                this.items.push(entry);
                this.requestUpdate();
                this.count++;
            });
        });
        this.observer.observe({ type: "resource", buffered: true });

    }


    render() {
        return html`<h2>${this.count} Requests sent:</h2>
        ${this.items.map(
            (entry) => html`<p>${entry.name}</p>`
        )}
        `;
    }
    // ${entry.name}: ${entry.responseStart}ms
    /**
     * Handle the messages from the server
     *
     * @param message
     */
    handleMessage(message: ServerMessage): boolean {
        return false; // The message was not handled
    }
}



const plugin: DevToolsPlugin = {
    init: function (devToolsInterface: DevToolsInterface): void {
        devTools = devToolsInterface;
        devTools.addTab('Request Tool', 'request-tool');
    }
};

(window as any).Vaadin.devToolsPlugins.push(plugin);