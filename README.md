# Service Registry

Service Registry manages the registration of services used on the mutual authentication process.

**Service Registry REST API**

| name | path | methods  | description |
| ------------- |------------- | -----:|--|
| [Registry](./api/registry.md) | /registry/check | POST | Checks if every microservice is up. Removes that microservice otherwise. |
| | /registry/findservice  | POST | Checks if a microservice exists and returns the server where it is available |
| | /registry/register  | POST | Registers a new microservice |
| | /registry/unregister  | POST | Removes a microservice  |
