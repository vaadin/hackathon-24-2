# hackathon-24-2
Olli:
* Created a new Development Tool add-on for tracking requests sent from the browser. Idea was to add request lengths using the PerformanceObserver API and seems it's feasible, but ran out of time for the request lengths.
* Discoveries: development workflow is not optimal
    * Any changes in the TS side mean a new add-on jar needs to be compiled and a new dev bundle needs to be created
    * At the same time, TS type checking doesn't work correctly in the add-on submodule
    * A good template project would help a lot!
* There are helpful things available for dev tools from modern browser APIs that don't even require the client/server communication part of dev tools add-ons (like tracking requests)

## links
- Hackathon Results [Repository](https://github.com/vaadin/hackathon-24-2)
- Slack [Channel](https://join.slack.com/share/enQtNTk4NTM5Njg5MDI0NS00MWE2YjMzZDI4Yzc4NmE0YjhiMzU1ZTc3ODdlMzJmNWZmNzI1OGJmYWMzMjM1MmQ5OWY0ZWU1N2YyNzczNzIw)
## Rules
- Use Platform [24.2.0.rc2](https://github.com/vaadin/platform/releases/tag/24.2.0.rc2), Hilla [2.3.0.beta1](https://github.com/vaadin/hilla/releases/tag/2.3.0.beta1)
- Work on an app, a fix, a migration, experiment new features, improve docs, update addon, open issues, etc.
- Push a branch to the REPO (Before Monday 16th at noon)
- If there is no code add a README or screenshots
- Everyone will get a present.
- Gift for 3 winners 
