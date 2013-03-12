## About
This is China province, city and county Linkage demo, it will show the linkage result after opening the demo.html.

## Issue
If you are using Google Chrome browser, after double clicking(show as file:// URL) the demo.html, 
suppose you will NOT get the expected result and see below error message in your control console.

"Origin null is not allowed by Access-Control-Allow-Origin."

That solved the problem by no longer trying to perform a CORS(Cross-Origin Resource Sharing) request from a file:// URL, you can put this demo in server and try Http, suppose you can get expected result.

and i think you would get expected result if using FireFox , IE or other browser.

Enjoy it, thanks.