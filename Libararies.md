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

## Dagger - dependency injection
- Example article in `https://medium.com/@yostane/dependency-injection-with-dagger-2-inject-and-provides-ce21f7449ec5`
- Dagger 2 provides dependency injection through annotations:
  + `@Singleton`: As it name implies.
  + `@Component`: used to annotate the interface that returns the root object of the graph.
  + `@Inject`: Inject a class into the D.I graph of Dagger. Very convenient, however, it is not always usable since it's not applicable to interface, furthermore you would need to modify the source code to use this annotation so it's not applicable to modules. Also, not applicable to objects that require configuration outside of the constructor (like objects generated with a factory).
  + `@Provides` & `@Module`: we instantiate Dagger allows to add these object to its graph using modules. A module is a class that has the `@Module` annotation and defines methods annotated with the `@Provides` annotation. Annotated methods return instances of classes that cannot support the `@Inject` annotation.

## Batching requests
- Example: `https://developers.google.com/classroom/guides/batch`
### Format
- A single HTTP request containing multiple requests.
- Each part begins with a HTTP header `Content-Type: application/http`, only to mark the beginning of the parts.
- The body of each parts is itself a complete HTTP request, with its own verb, URL, headers, and body. Must contains the PATH only, not the full URL.
- The HTTP header applied to the OUTER batch req will get applied to every part inside it. Might be better if apply the header to each specific call.
- When the server receives the batched request, it applies the outer request's query parameters and headers (as appropriate) to each part, and then treats each part as if it were a separate HTTP request.
### Response
- Response is a single HTTP response, each part is the response to one of the requests in the batched request, in the same order as the requests.
- Each response part is preceded by a Content-Type header that marks the beginning of the part.
## TabLayout + ViewPager
- `https://developer.android.com/guide/navigation/navigation-swipe-view`
- A way to implement frequent-swipe tab view.
