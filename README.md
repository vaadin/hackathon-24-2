# My Push App

Combining WebPush and Collaboration Kit to always show notifications in the UI as soon as possible and additionally also sends a WebPush notification in case the user doesn't currently have the application open.

Encountered bugs:
* The documentation assumes you know what `subject` means in this context. [Docs issue #2885](https://github.com/vaadin/docs/issues/2885)
* `Subscription` is complicated to work with since it doesn't implement `equals` and `hashCode`.  [Flow issue #17832](https://github.com/vaadin/flow/issues/17832)
* The API overall is complicated to work with because it's so low level. But  that's just as designed so no bug filed.