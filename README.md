# PA3
In4 124 PA3

Team Members: Juan Ramirez, Weihan Wang

about page, products page , store page , and cart page have all been updated to jsp.
New servlets created to handle requests for getting city and state when inputting zip.
These servelts work in junction with a js file that performs string splitting and inserting into correct position.
On cart page, inputting value into zip text box will invoke on blur which calls on javascript function for the servlet, iterates through the zip or tax file to find correspodning info.
city and state are updated as well as overall price of cart depending on tax rate for state.
