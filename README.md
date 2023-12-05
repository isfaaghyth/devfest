### DevFest Intro

A local tech conference hosted by Google Developer Groups around the world. Whether it be through hands-on learning experiences, technical talks delivered in local languages by experts, or by simply meeting fellow local developers.

DevFest brings together thousands of developers globally for the largest virtual weekend of community-led technical learning and a shared passion for Google technologies on Aug-Dec. The magic of DevFest has always come from the people involved - developers from all different backgrounds and skill levels.

## Devfest Mobile app!
<img width="1676" alt="Screenshot 2023-12-02 at 11 59 42" src="https://github.com/isfaaghyth/devfest/assets/6775159/24a8defe-1165-4249-8319-cdffc9c8f9d9">

This project does not represent the official DevFest mobile app. Instead, it is only an experimental app designed to showcase how to build a multiplatform app using KMP and Compose Multiplatform. This project helps understand how to build an Android and iOS app in a single codebase using several tech stacks.

This project is designed differently compared to the official KMP project. I got inspired from the [NYTimes-KMP](https://github.com/xxfast/NYTimes-KMP) project on how to build a straightforward project structure using a single app directory while maintaining scalable app development.

### Stacks

- Compose Multiplatform
- Ktor
- Decompose
- Koin
- Turbine
- MockKMP
- MobiusKt
- Coroutines Flow

#### Project Structure
```
app/
├─ android/
├─ ios/
├─ src/
│  ├─ androidMain/
│  ├─ commonMain/
│  ├─ iosMain/
│  ├─ build.gradle.kts
│  ├─ devfest.podspec
```

#### Router
```kt
@Parcelize
sealed class NavRouter : Parcelable {

    data object Home : NavRouter()
    data class Detail(val chapter: Chapter) : NavRouter()
}
```

MIT @ 2023
