## mealListing
* Android App that displays list of meals.

![](/images/header.png)

## Basic Building blocks of Clean Architecture

![](/images/clean_architecture.png)

## Presentation (ui) Layer

* The Presentation layer contains UI related feature packages that have:
  * Activities/Fragments/Dialogs
  * Adapter
  * UI-States
  * ViewModel

* The UI-States holds the states according to which the data needs to be displayed on the UI.
  * Eg: Loading, Error, Success (data state)

* The ViewModel  performs 2 operations:
  * Communicates with the UseCases present in Domain layer to fetch the data.
  * Update the UI with the fetched data.

## Domain Layer
* The Domain layer contains the business logic. It has following packages:
  * Model
  * Repository
  * UseCase

* The Model defined in Domain layer will be used to hold the data that needs to be displayed on the UI.

* Repository contains just the interfaces.

* Each UseCase (or interactor) specifies a single task that needs to be performed (usually based on the user's action). It  performs 2 operations:
  * Fetches data via Repository’s implementation present in Data layer.
  * Then, the fetched data is passed to Presentation layer’s ViewModel via coroutine or flow.

## Data Layer

* The Data layer contains functionality related to fetching data from data source. It contains following packages:
  * Model
  * Remote
  * Repository

* Model classes in these layers are basically the DTO that hold the data that is received from data sources. These DTOs are later mapped to Domain layer's Model classes.

* Remote acts as a data source that is fetched via REST API
  * Eg: Retrofit's API interface where we define API calls.

* The Repository here implements the Domain layer's repository interface and performs 2 operations:
  * Fetch the data from data source (API)
  * Pass the result to UseCase in Domain layer.

#### Besides the above layers, we can also have other packages like:

* commons / utils : contains classes for common functionalities:
  * DateTimeUtil
  * Constants, etc...

* hilt : contains implementation of Hilt Modules

## Package Structure

![](/images/package_structure.png)