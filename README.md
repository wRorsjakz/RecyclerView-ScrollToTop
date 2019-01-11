# RVScroller

<img src="https://img.shields.io/badge/API-18%2B-brightgreen.svg" style="max-width:100%;"> [![](https://jitpack.io/v/wRorsjakz/RecyclerView-ScrollToTop.svg)](https://jitpack.io/#wRorsjakz/RecyclerView-ScrollToTop) [![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

A simple to use and customizable scroll-to-top library for Android RecyclerView.

![ezgif com-video-to-gif 1](https://user-images.githubusercontent.com/39665412/51018063-3c811d00-15b1-11e9-94d8-bfbb37999834.gif)

## Prerequisites
Add this in your root build.gradle file:
```java
allprojects {
    repositories {
     	...
        maven { url 'https://jitpack.io' }
    }
}
```

## Dependency
Add this to your module's build.gradle file:

```java
dependencies {
	...
    implementation 'com.github.wRorsjakz:RecyclerView-ScrollToTop:1.0.0'
}
```

## Usage
In your own xml layout file, create a `FloatingActionButton` and initialise it.

Create a new instance of RVSroller:

```java
RVScroller rvScroller = new RVScroller
	.Custom(@NonNull FloatingActionButton,@NonNull boolean endVisibility)  // Required
	.build(); // Required
```

`@NonNull boolean endVisibility` determines the visibility of `FloatingActionButton` when the user has scrolled to the bottom of the recyclerview:

* `true` - continue to remain visible
* `false` - disappears

Attach `rvScroller` to your recyclerview:
```java
mRecyclerView.addOnScrollListener(rvScroller);
```
## Configuration

```java
RVScroller rvScroller = new RVScroller
	.Custom(@NonNull FloatingActionButton,@NonNull boolean endVisibility)  // Required
    .withDelay(@NonNull int delay) // Optional
	.build(); // Required
```

* `withDelay(@NonNull int delay)` - Set the delay (in milliseconds) for the FAB to disappear when not at top or bottom of recyclerview, pass `RVScroller.NO_DELAY` to disable.

## Changelog

* 1.0.0 
	* Initial Release

## License

```txt
Copyright 2019 Nicholas Gan

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
