# Movies Application

Simple Movies application , I will apply every architecture component on a separate branch with steps
(Any beginner can follow me to learn all architecture component)

-----------------------

##  API Hints

To fetch popular movies, you will use the API from themoviedb.org.
If you don’t already have an account, you will need to [create one](https://www.themoviedb.org/account/signup) in order to request an API Key. 
   
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



   
