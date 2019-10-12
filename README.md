# Movies Application

Simple Movies application , I will apply every architecture component on a separate branch with steps
(Any beginner can follow me to learn all architecture component)

-----------------------

##  API Hints

To fetch popular movies, you will use the API from themoviedb.org.
If you don’t already have an account, you will need to [create account](https://www.themoviedb.org/account/signup) , in order to request an [API Key](https://developers.themoviedb.org/3/getting-started/introduction) . 
   
In your request for a key, state that your usage will be for educational/non-commercial use. You will also need to provide some personal information to complete the request. Once you submit your request, you should receive your key via email shortly after.

After generate your API key put it inside string.xml file 
<string name="api_key" translatable="false">[YOUR_API_KEY]</string>
    
In order to request popular movies you will want to request data from the /movie/popular [documentation](https://developers.themoviedb.org/3/discover/movie-discover). An API Key is required.
Once you obtain your key, you append it to your HTTP request as a URL parameter like so:
http://api.themoviedb.org/3/movie/popular?api_key=[YOUR_API_KEY]

--------------------------------

##   master Branch 

Contain the final code

--------------------------------

##   1-BaseProject Branch

Contain the source code of the application 

--------------------------------

##   2-DataBinding Branch [tutorial](https://developer.android.com/topic/libraries/data-binding)

The Data Binding Library is a support library that allows you to bind UI components in your layouts to data sources in your app using a declarative format rather than programmatically.

Note: That DataBinding Branch contail all ToDo steps
and DataBindingRseult Branch contain the final result without ToDo 
##### you can accumulate on BaseProject branch and follow ToDo steps

--------------------------------

## 3- ViewModel Branch [tutorial](https://developer.android.com/topic/libraries/architecture/viewmodel)

- The ViewModel class is designed to separate out view data ownership from UI controller logic.
- The ViewModel class allows data to survive configuration changes such as screen rotations using LiveDate.

On This branch we will develop the first one and develop the second feature on LiveData branch

##### Note: you can accumulate on DataBindingRseult branch and follow ToDo steps

--------------------------------

## 4- LiveData Branch [tutorial](https://developer.android.com/topic/libraries/architecture/livedata)

- LiveData is an observable data holder class. Unlike a regular observable, LiveData is lifecycle-aware, meaning it respects the lifecycle of other app components, such as activities, fragments, or services. This awareness ensures LiveData only updates app component observers that are in an active lifecycle state.

##### Note: you can accumulate on ViewModelRseult branch and follow ToDo steps

--------------------------------

## 5- Repository Branch [tutorial](https://codelabs.developers.google.com/codelabs/android-training-livedata-viewmodel/#7)

- Although the Repository is not part of the Architecture Components libraries, but is a suggested best practice for code separation and architecture. A Repository class handles data operations and it's important to introduce it before start in Pagination and Rooming. 

##### Note: you can accumulate on LiveDataRseult branch and follow ToDo steps

--------------------------------

## 6- PagingWithNetwork Branch [tutorial](https://developer.android.com/topic/libraries/architecture/paging) 

- The Paging Library helps you load and display small chunks of data at a time. Loading partial data on demand reduces usage of network bandwidth and system resources.

- This guide introduce paging from network only, Later we will introduce paging from local + network

##### Note: you can accumulate on RepositoryRseult branch and follow ToDo steps

--------------------------------

## 7- RoomDB Branch [tutorial](https://developer.android.com/training/data-storage/room/index.html) 

- Room save data in a local database . Room provides an abstraction layer over SQLite to allow fluent database access while harnessing the full power of SQLite.

- This guide introduce room without paging, Later we will introduce paging from local + network

##### Note: you can accumulate on RepositoryRseult branch and follow ToDo steps

--------------------------------

## 8- Paging(Local+Network) Branch [tutorial](https://developer.android.com/topic/libraries/architecture/paging) 

- The Paging Library helps you load and display small chunks of data at a time. Loading partial data on demand reduces usage of network bandwidth and system resources. [example](https://codelabs.developers.google.com/codelabs/android-paging/#0)

- This guide introduce paging from local + network

##### Note: you can accumulate on RoomDBRseult branch and follow ToDo steps

--------------------------------

## 9- Testing

- Test Using Mock ViewModel
- Test Using Mock WebServer 

##### Note: you can accumulate on RepositoryRseult branch and follow ToDo steps
   
