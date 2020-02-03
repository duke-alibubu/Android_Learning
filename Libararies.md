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
