# Dashboard App


# Video
https://github.com/tanmaysuryawanshi/DashboardApp/assets/93265845/d1089d67-9e5d-46c1-a9b2-9e799b518fdc


# Uses:
- MVVM Clean Architecture
- Dagger Hilt for dependency injection
- Retrofit for api calls
- Coroutines
- Livedata

### Structure  
```
com.tanmaysuryawanshi.dashboard  #root package    
├───core    
│   └───util    
└───dashboardfeature  
    ├───data  # For data handling.   
    │   ├───remote  #remote data source  
    │   │   ├───api  
    │   │   └───dto  
    │   └───repository_impl  #implementation of repository  
    ├───di  #dependency injection  
    ├───domain  #domain layer  
    │   ├───model   
    │   └───repository  # Repository Definitions  
    ├───presentation  #Presentation Layer  
    │   ├───adapter  
    │   ├───fragments  
    │   └───viewmodels  
    └───utils  
```
