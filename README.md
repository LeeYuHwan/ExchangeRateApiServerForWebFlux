## WebFlux Toy Project 환율 Api 서버


#### 시스템 구성도

![image](https://github.com/LeeYuHwan/ExchangeRateApiServerForWebFlux/assets/66478929/9d6cdcb7-fdee-4262-a019-1547bebeb75e)

--------------------

#### API 명세
---------------------
+ JWT Token 발급 : /api/v1/jwt/token - POST
+ -> DB에 미리 저장된 유저와 키 확인 후  Token 값 발급

Headers

1. Content-Type - application/json
------------

Request Parameter

1. userName - String (max length : 100)
2. userKey - String (max length : 100)

![image](https://github.com/LeeYuHwan/ExchangeRateApiServerForWebFlux/assets/66478929/763de445-10ae-4011-b1e7-530df094b53a)


Response Parameter

1. resultCode - Number
2. resultMsg - String
3. token - String

![image](https://github.com/LeeYuHwan/ExchangeRateApiServerForWebFlux/assets/66478929/9a9bd675-c69c-4bfa-96cd-8a5495c82c87)

---------------------

+ 환율 OpenApi 호출 후 DB에 저장 : /api/v1/exchange/web-client - GET

Headers
1. AUTHORIZATION - (JWT Token)   
-----------

Response Parameter

1. exchangeId - Number
2. usd - String (max length : 100)
3. krw - String (max length : 100)
4. timeLastUpdateUtc - String
5. createTime - DateTime

![image](https://github.com/LeeYuHwan/ExchangeRateApiServerForWebFlux/assets/66478929/3456c163-6bf9-4798-ae99-87e9dc3ff068)

---------------------

+ 환율 정보 출력 : /api/v1/exchange/data - GET

-----------

Response Parameter

1. resultCode - Number
2. resultMsg - String
3. usd - String
4. krw - String
5. timeLastUpdateUtc - String

![image](https://github.com/LeeYuHwan/ExchangeRateApiServerForWebFlux/assets/66478929/2aa0a974-9ef8-459e-a3c8-d40655e36b39)
























