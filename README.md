# restcountriesJava
Java Wrapper for https://restcountries.eu/ done as an exercise

This is for the V3.1 endpoint.

Classes have been originally done with https://www.jsonschema2pojo.org/ and modified where needed.

The functionality has been influenced by https://github.com/Eyefyre/restcountries-java


## Examples of usable Methods
 Below is a list of usable calls, that can be used in the browser
  ```
http://localhost:8080/countries/
http://localhost:8080/countries/Finland
```
## Format of response

All countries:
 ```
          {
                   "countries": [
                             {
                                       "name": "Finland",
                                       "country_code": "FI"

                             },
                             ...

                   ]
          }```
 ```
one country:
 ```
 
          {
                   "name": "Finland",
                   "country_code": "FI",
                   "capital": "Helsinki",
                   "population": 5491817,
                   "flag_file_url": "<url to the flag file>"
          }