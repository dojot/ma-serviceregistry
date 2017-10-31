**Registry**
----
[/registry/check](#check)

[/registry/findservice](#findservice)

[/registry/register](#register)

[/registry/unregister](#unregister)

<a name="check"></a>
* **URL**

  /registry/check

* **Method:**

  `POST`

* **Success Response:**

  * **Code:** 200

* **Sample Call:**

    ``` shell
    POST /registry/check
    Accept: application/json
    ```
---

<a name="findservice"></a>
* **URL**

  /registry/findservice

* **Method:**

  `POST`

* **Data Params**

    **Media type:** application/json

    | Parameter | Type | Required |
    | --- | --- | --- |
    | target | String | Yes |
    | restful | String | Yes |
    | method | String | Yes |
    | path | String | Yes |
    | version | String | Yes |
    | microservice | String | Yes |

* **Sample Call:**

    ``` shell
    POST /registry/findservice
    Content-Type: application/json
    Accept: application/json


    {
      "target" : "...",
      "restful" : "...",
      "method" : "...",
      "path" : "...",
      "version" : "...",
      "microservice" : "..."
    }
    ```
---

<a name="register"></a>
* **URL**

  /registry/register

* **Method:**

  `POST`

* **Data Params**

    **Media type:** application/json

    | Parameter | Type | Required |
    | --- | --- | --- |
    | port | String | Yes |
    | context | String | Yes |
    | hostname | String | Yes |
    | node | String | Yes |
    | version | String | Yes |
    | microservice | String | Yes |

* **Sample Call:**

    ``` shell
    POST /registry/register
    Content-Type: application/json
    Accept: application/json


    {
      "port" : "...",
      "context" : "...",
      "hostname" : "...",
      "node" : "...",
      "microservice" : "...",
      "version" : "..."
    }
    ```
---

<a name="unregister"></a>
* **URL**

  /registry/unregister

* **Method:**

  `POST`

* **Data Params**

    **Media type:** application/json

    | Parameter | Type | Required |
    | --- | --- | --- |
    | port | String | Yes |
    | context | String | Yes |
    | hostname | String | Yes |
    | node | String | Yes |
    | version | String | Yes |
    | microservice | String | Yes |

* **Sample Call:**

    ``` shell
    POST /registry/unregister
    Content-Type: application/json
    Accept: application/json


    {
      "port" : "...",
      "context" : "...",
      "hostname" : "...",
      "node" : "...",
      "microservice" : "...",
      "version" : "..."
    }
    ```
