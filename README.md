# LazyGrid
[![](https://jitpack.io/v/yusufarisoy/lazy-grid.svg)](https://jitpack.io/#yusufarisoy/lazy-grid)
<img src="https://camo.githubusercontent.com/a05a54d005013841cf29ac57361136623697028285f4ecb2c153df14bd9fbf5d/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f6d696e53646b56657273696f6e2d32312d79656c6c6f77677265656e" alt="Min SDK" data-canonical-src="https://img.shields.io/badge/minSdkVersion-21-yellowgreen" style="max-width: 100%;">
<br>
LazyGrid library for [Jetpack Compose](https://developer.android.com/jetpack/compose).

## About
LazyGrid is a grid view implementation for Android's new and modern UI toolkit Compose. This library is suitable for to display large amount of data, it'll only compose the rows which are visible in the component’s viewport. It can be used in a LazyColumn to create a grid view.

LazyCollapsibleGrid version has the ability to collapse desired amount of rows with animations. It has some extra parameters but is also lazy and suitable for to display big data.

## Metrics
Metrics is the way of measuring the performance of composables. Ideally a composable method should be `restartable` and `skipabble` for the compiler to work with best performance. Besides being lazy, LazyGrid and LazyCollapsibleGrid are also `restartable` and `skipabble` for better performance for your Android application.

## Demo
### FillSize
All items of row shares the full width equally without any spaces.
| LazyGrid | LazyCollapsibleGrid |
| ------ | ------ |
| <img src="./demo/fill_size.gif" alt="gif" height="600" width="300"> | <img src="./demo/fill_size_collapsible.gif" alt="gif" height="600" width="300"> |

### FixedSize
Items placed with a fixed width and spaced equally according to remaining space.
| LazyGrid | LazyCollapsibleGrid |
| ------ | ------ |
| <img src="./demo/fixed_size.gif" alt="gif" height="600" width="300"> | <img src="./demo/fixed_size_collapsible.gif" alt="gif" height="600" width="300"> |

### SpacedBy
Items spaced by a fixed space and share the remaining width equally.
| LazyGrid | LazyCollapsibleGrid |
| ------ | ------ |
| <img src="./demo/spaced_by.gif" alt="gif" height="600" width="300"> | <img src="./demo/spaced_by_collapsible.gif" alt="gif" height="600" width="300"> |

## Setup
### Gradle
```Gradle
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}

dependencies {
    implementation 'com.github.yusufarisoy:lazy-grid:1.0.0'
}
```

### Composable
```Kotlin
LazyColumn(modifier = Modifier.fillMaxSize()) {
    LazyGrid(
        rows = listOf(),
        elementPerRow = 4,
        itemPlacementType = ItemPlacementType.FixedSize(itemWidth = 80.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) { item, modifier ->
        CustomGridItem(item, modifier)
    }
    
    // Collapsible
    var isExpanded by remember { mutableStateOf(false) }
    
    LazyCollapsibleGrid(
        rows = listOf(),
        collapsibleRows = listOf(),
        elementPerRow = 3,
        itemPlacementType = ItemPlacementType.SpacedBy(space = 16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        isExpanded = isExpanded,
        enterTransition = expandVertically(),
        exitTransition = shrinkVertically(),
        collapseButton = { CustomCollapseButton(onClick = { isExpanded = !isExpanded }) }
    ) { item, modifier ->
        CustomGridItem(item, modifier)
    }
}
```