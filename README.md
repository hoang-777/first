# Transactions API

Objective: API to store live transactions and calculate statistics.

## Running

Start the terminal, from the main repository run 

```bash
./mvnw spring-boot:run
```
### Adding Transactions
To add transactions with the format
```json
{"instrument":"DB",
"price":101.1, 
"timestamp":1586116436151}
```
run from the terminal

```bash
curl -d '{"instrument":"DB","price":101.1, "timestamp":1586116436151}' -H "Content-Type: application/json" -X POST -i localhost:8080/ticks
```
### Getting Statistics for all transactions
Run from the command line
```bash
curl -X GET localhost:8080/statistics
```
The output should be
```json
{"avg":540.2896551724137,
"max":1434.1,
"min":136.4,
"maxDrawdown":-0.027368421052631597,
"volatility":592.0324608672828,
"quantile":138.6,
"twap":425.1260057471264,
"twap2":439.00820256071785,
"count":29.0}
```


### Getting Statistics for transactions with a particular id

```bash
curl -X GET localhost:8080/statistics/{id}
```

in the example with id GOS, the output should be



```json
{"avg":140.6818181818182,
"max":143.2,
"min":136.4,
"maxDrawdown":-0.027368421052631597,
"volatility":1.9488076456308911,
"quantile":138.6,
"twap":122.15280303030303,
"twap2":123.898401377313,
"count":11.0}
```
## Approach

Scheduler was set to clean the data from the old transactions. Additionally to assure that 
```bash
curl -X GET localhost:8080/statistics
```
is run in constant time, statistics are also automatically calculated.

## Further Steps

If time would allow, test would be made. There are many exceptions that need to be added.
Furthermore, some calculation of statistics was not optimal and it could be further improved. I'm also aware that the code needs a lot of optimisation.

## Comments
Overall I very enjoyed the task. I learned a lot and brushed up my java skills, since it was the first time that I had to build an API and haven't used java for 4 years.
Regardless, it was for me a very valuable experience, thank you for your time! 
