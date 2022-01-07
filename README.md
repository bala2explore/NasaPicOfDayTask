"Nasa Pic Of the Day"

Acceptance Criteria
1. Given: The NASA APOD API is up (working) AND the phone is connected to the internet When:
The user arrives at the APOD page for the first time today Then: The page should display the
image of Astronomy Picture of the Day along with the title and explanation, for that day
2. Given: The user has already seen the APOD page once AND the phone is not connected to
the internet When: The user arrives at the APOD page on the same day Then: The page
should display the image of Astronomy Picture of the Day along with the title and explanation,
for that day
3. Given: The user has not seen the APOD page today AND the phone is not connected to the
internet When: The user arrives at the APOD page Then: The page should display an error
"We are not connected to the internet, showing you the last image we have." AND The page
should display the image of Astronomy Picture of the Day along with the title and explanation,
that was last seen by the user
4. Given: The NASA APOD API is up (working) AND the phone is connected to the internet When:
The APOD image loads fully on the screen Then: The user should be able to see the complete
image without distortion or clipping

Instructions:
Kindly sync the project in android studio and run with emulator or device
(Project build with : Android Studio Bumblebee | 2021.1.1 Canary 12)


Improvements:
1) Introducing domain layer:
In current implementation due to limited time I implemented the viewmodel to access NasaEntity which is in data layer. 
The final intention to convert this project to clean architecture by introducing domain layer and decouple the business components.
* Introduce NasaObject and mapper to limit the visibility of entire NasaEntity. 
Since url,title,explanation,date are the fields used in presentation, improvement needed on converting NasaEntity to NasaObject using a mapper with limited required data and to pass NasaObject to the presentation layer.
  
2) Cleaning up repository and to introduce usecases:
Current implementation is clubed with NetworkApiCall, database operation with SSOT.
Need to clean up the code in a proper and optimized way.

3) Introduce usecases and viewmodel will interact with usecase then usecase will call the repository method
viewmodel (Presentation layer) -> usecase (Domain layer) -> repository (Data layer)

4) Ui improvements:
adding progress in the imageview
improving the text view

