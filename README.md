## Lexis Nexis Demo - Paul Brandon

Welcome, I hope you find this demo in good order.

This implements the first part, i.e. retrieving data from the TruNarrative API, and *should* work against the actual API. 

I didn't get around to inserting into a DB, which I intended to do via JPA and H2. As a compromise I have added basic caching.
This is a standard spring boot application utilising Web Flux throughout, built via gradle. Note there is use of Lombok too.

I have prioritised getting a working end to end so there are initial integration tests for that, though they definitely 
need to be more comprehensive, and sadly lacking in Unit tests.

There would also ideally be more in the way of logging and maybe some more specific error handling via controller advice.

Please also note I add the API key via the environment variable *CLIENT.RISK.API-KEY* which seemed to make more sense to me,
I didn't have a PO to talk to about altering requirements ;)