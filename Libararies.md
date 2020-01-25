# Libraries

## Retrofit
- A libarary for reading Restful APIs
- Retrofit needs at least 2 things available to it to build a web service API:
  + A base URL
  + A converter factory: a converter tells Retrofit what to do with the data it gets back from the web service. E.g: `ScalarsConverterFactory`, `MoshiConverterFactory`, ...
#### Using coroutines with Retrofit
- Add the `.addCallAdapterFactory(CoroutineCallAdapterFactory())` (dependencies: `CoroutineCallAdapterFactory`).
- Using this, a API call will return a `Deferred` object. 
- The `Deferred` interface defines a coroutine job that returns a result value (`Deferred` inherits from `Job`). The `Deferred` interface includes a method called `await()`, which causes your code to wait without blocking until the value is ready, and then that value is returned.

## Moshi
- A JSON parsing library. Parsing JSON by using data class adapted to the JSON.
- Example in `https://gist.github.com/swankjesse/61354fd0a20bf56072f6a1d0c82fb9fc`.
